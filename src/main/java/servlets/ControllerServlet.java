package servlets;

import DAO.ContactDAO;
import entities.Contact;
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
    }

    private Logger logger = LogManager.getLogger(ControllerServlet.class);

    private void listActions(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("DeleteSelected") != null) {
            // Invoke FirstServlet's job here.
            String[] checkedIds = request.getParameterValues("checked");
            LogicUtility.deleteSelected(checkedIds);
            try {
                response.sendRedirect("list");
            } catch (IOException e) {
                logger.error(e);
                e.printStackTrace();
            }
        } else if (request.getParameter("EmailSelected") != null) {
            // Invoke SecondServlet's job here.
            String[] checkedIds = request.getParameterValues("checked");
            if (checkedIds==null){
                try {
                    response.sendRedirect("list");
                } catch (IOException e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
            else {
                LogicUtility.emailSelected(request);
                emailForm(request, response);
            }
        }

    }

    private void emailForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmailForm.jsp");
        forward(request, response, dispatcher);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, RequestDispatcher dispatcher) {
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private void showEmailForm(HttpServletRequest request, HttpServletResponse response)
    {
        LogicUtility.showEmailForm(request);
        emailForm(request, response);
    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchForm.jsp");
        dispatcher.forward(request, response);

    }

    private void searchContact(HttpServletRequest request, HttpServletResponse response) {
        List<Contact> listContact = LogicUtility.initSearchData(request);
        request.setAttribute("listContact", listContact);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactList.jsp");
        forward(request, response, dispatcher);
    }

    private void listContact(HttpServletRequest request, HttpServletResponse response) {
        LogicUtility.listContact(request,response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactList.jsp");
        forward(request, response, dispatcher);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        showContactForm(request, response);
    }

    private void showContactForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ContactForm.jsp");
        forward(request, response, dispatcher);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        LogicUtility.showEditForm(request);
        showContactForm(request, response);

    }

    private void insertContact(HttpServletRequest request, HttpServletResponse response) {
        LogicUtility.insertContact(request);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response) {
        LogicUtility.updateContact(request);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }

    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response) {
        LogicUtility.deleteContact(request);
        try {
            response.sendRedirect("list");
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }

    }
}
