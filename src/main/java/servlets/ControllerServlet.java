package servlets;

import DAO.ContactDAO;
import entities.Attachment;
import entities.Contact;
import entities.Number;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utility.LogicUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@MultipartConfig(maxFileSize = 8088608*2)
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
            logger.error(ex);
            throw new ServletException(ex);
        }
    }

    private Logger logger = LogManager.getLogger(ControllerServlet.class);

    private void listActions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        if (request.getParameter("DeleteSelected") != null) {
            // Invoke FirstServlet's job here.
            String[] checkedIds = request.getParameterValues("checked");
            LogicUtility.deleteSelected(checkedIds);
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

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchForm.jsp");
        dispatcher.forward(request, response);

    }

    private void searchContact(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Contact> listContact = LogicUtility.initSearchData(request);
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
            throws IOException {
        LogicUtility.insertContact(request);
        response.sendRedirect("list");
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LogicUtility.updateContact(request);
        response.sendRedirect("list");

    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        LogicUtility.deleteContact(request);
        response.sendRedirect("list");

    }
}
