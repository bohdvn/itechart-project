package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Contact;
import DAO.ContactDAO;
import entities.SearchData;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */


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
                default:
                    listContact(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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
        List<Contact> listContact = contactDAO.listAllContacts();
        request.setAttribute("listContact", listContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact = contactDAO.getContact(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactForm.jsp");
        request.setAttribute("contact", existingContact);
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

        Contact newContact = new Contact(name, surname, patronymic, dateOfBirth, gender, nationality,
                maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        contactDAO.insertContact(newContact);
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

        Contact contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        contactDAO.updateContact(contact);
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
