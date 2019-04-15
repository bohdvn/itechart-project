package servlets;

import DAO.ContactDAO;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.oracle.tools.packager.IOUtils;
import entities.Attachment;
import entities.Contact;
import entities.Number;
import entities.SearchData;
import org.apache.commons.codec.binary.Base64;
import utility.SaveUtility;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@MultipartConfig(maxFileSize = 8088608)
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO;


    public void init() {
        String jdbcURL = "jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        contactDAO = new ContactDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/search":
                    showSearchForm(request, response);
                    break;
                case "/find":
                    searchContact(request, response);
                    break;
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertContact(request, response);
                    break;
                case "/delete":
                    deleteContact(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateContact(request, response);
                    break;
                case "/editPhoto":
                    showFormPhoto(request, response);
                    break;
                case "/updatePhoto":
                    updatePhoto(request, response);
                    break;
                case "/email":
                    showEmailForm(request,response);
                    break;
                case "/listActions":
                    listActions(request, response);
                    break;
                default:
                    listContact(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listActions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        if (request.getParameter("DeleteSelected") != null) {
            // Invoke FirstServlet's job here.
            String[] checkedIds = request.getParameterValues("checked");
            if (checkedIds != null && checkedIds.length != 0) {
                for (int i = 0; i < checkedIds.length; i++) {
                    int id= Integer.parseInt(checkedIds[i]);
                    Contact contact = new Contact(id);
                    contactDAO.deleteContact(contact);
                }
            }
            response.sendRedirect("list");
        } else if (request.getParameter("EmailSelected") != null) {
            // Invoke SecondServlet's job here.
            String[] checkedIds = request.getParameterValues("checked");
            String emails=(contactDAO.getContact(Integer.parseInt(checkedIds[0])).getEmail());
            String names=(contactDAO.getContact(Integer.parseInt(checkedIds[0])).getName());
            if (checkedIds != null && checkedIds.length != 0) {
                for (int i = 1; i < checkedIds.length; i++) {
                    int id= Integer.parseInt(checkedIds[i]);
                    Contact existingContact = contactDAO.getContact(id);
                    emails+=", ";
                    emails+=existingContact.getEmail();
                    names+=", ";
                    names+=existingContact.getName();
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("EmailForm.jsp");
                request.setAttribute("emails", emails);
                request.setAttribute("names", names);
                dispatcher.forward(request, response);
            }
        }

    }

    private void showEmailForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact = contactDAO.getContact(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmailForm.jsp");
        request.setAttribute("emails", existingContact.getEmail());
        request.setAttribute("names", existingContact.getName());
        dispatcher.forward(request, response);
    }


    private void updatePhoto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact=contactDAO.getContact(id);
        InputStream inputStream = null;
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        byte[] imageBytes = new byte[(int)filePart.getSize()];
        inputStream.read(imageBytes, 0, imageBytes.length);
        inputStream.close();
        String imageStr = Base64.encodeBase64String(imageBytes);
        existingContact.setBase64Image(imageStr);
        request.setAttribute("contact", existingContact);
       getServletContext().getRequestDispatcher("edit?id="+id).forward(request, response);
    }

    private void showFormPhoto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact = contactDAO.getContact(id);
        request.setAttribute("contact", existingContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PhotoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchForm.jsp");
        dispatcher.forward(request, response);

    }

    private void searchContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        SearchData data=new SearchData();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String dateAfter = request.getParameter("dateAfter");
        if (dateAfter=="") dateAfter = null;
        String dateBefore = request.getParameter("dateBefore");
        if (dateBefore=="") dateBefore = null;
        String gender = request.getParameter("gender");
        String nationality = request.getParameter("nationality");
        String maritalStatus = request.getParameter("maritalStatus");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");

        data.setName(name);
        data.setSurname(surname);
        data.setAddress(address);
        data.setCity(city);
        data.setCountry(country);
        data.setDateAfter(dateAfter);
        data.setDateBefore(dateBefore);
        data.setPatronymic(patronymic);
        data.setGender(gender);
        data.setNationality(nationality);
        data.setMaritalStatus(maritalStatus);
        data.setZipCode(zipCode);

        List<Contact> listContact = contactDAO.searchContact(data);
        request.setAttribute("listContact", listContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactList.jsp");
        dispatcher.forward(request, response);
    }

    private void listContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<Contact> list = contactDAO.listAllContacts((page-1)*recordsPerPage,
                recordsPerPage);
        int noOfRecords = contactDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("listContact", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Number> listNumber = new ArrayList<>();
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact = contactDAO.getContact(id);
        List<Number> listNumber = contactDAO.listAllNumbers(id);
        List<Attachment> listAttachment = contactDAO.listAllAttachments(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactForm.jsp");
        request.setAttribute("contact", existingContact);
        request.setAttribute("phones", listNumber);
        request.setAttribute("listAttachment", listAttachment);
        dispatcher.forward(request, response);

    }

    private void insertContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String dateOfBirth = request.getParameter("dateOfBirth");
        if (dateOfBirth=="") dateOfBirth = null;
        String gender = request.getParameter("gender");
        String nationality = request.getParameter("nationality");
        String maritalStatus = request.getParameter("maritalStatus");
        String webSite = request.getParameter("webSite");
        String email = request.getParameter("email");
        String workPlace = request.getParameter("workPlace");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String base64Image = request.getParameter("image64");

        Contact newContact = new Contact(name, surname, patronymic, dateOfBirth, gender, nationality,
                maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        newContact.setBase64Image(base64Image);
        int id = contactDAO.insertContact(newContact);

        List<Number> phones = SaveUtility.getNumbers(request, id);
        for(Number phone : phones) {
            contactDAO.insertNumber(phone);
        }

        List<Attachment> attachments = SaveUtility.getAttachments(request, id);
        for(Attachment attachment : attachments) {
            contactDAO.insertAttachment(attachment);
        }

        response.sendRedirect("list");
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String dateOfBirth = request.getParameter("dateOfBirth");
        if (dateOfBirth=="") dateOfBirth = null;
        String gender = request.getParameter("gender");
        String nationality = request.getParameter("nationality");
        String maritalStatus = request.getParameter("maritalStatus");
        String webSite = request.getParameter("webSite");
        String email = request.getParameter("email");
        String workPlace = request.getParameter("workPlace");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String zipCode = request.getParameter("zipCode");
        String base64Image=request.getParameter("image64");

        Contact contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        contact.setBase64Image(base64Image);
        contactDAO.updateContact(contact);

        List<Number> phones = SaveUtility.getNumbers(request, id);
        contactDAO.deleteNumbers(id);
        for(Number phone : phones) {
            contactDAO.insertNumber(phone);
        }

        List<Attachment> attachments = SaveUtility.getAttachments(request, id);
        contactDAO.deleteAttachments(id);
        for(Attachment attachment : attachments) {
            contactDAO.insertAttachment(attachment);
        }

        response.sendRedirect("list");

    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Contact contact = new Contact(id);
        contactDAO.deleteContact(contact);
        response.sendRedirect("list");

    }
}
