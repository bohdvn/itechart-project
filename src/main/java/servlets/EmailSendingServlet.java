package servlets;

import utility.EmailUtility;
import utility.StringTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8"); // кодировка ответа
        request.setCharacterEncoding("UTF-8");
        // reads form fields
        String recipient = request.getParameter("recipient");
        String name = request.getParameter("name");
        String subject = null;
        String template = request.getParameter("template");
        String content = null;
        if (template.equals("Hello")){
            content = StringTemplate.Hello(name);
            subject = "Hello";
        }
        else if (template.equals("Meeting")){
            content = StringTemplate.Meeting(name);
            subject = "Meeting";
        }
        else if (template.equals("None")){
            content = request.getParameter("content");
            subject = request.getParameter("subject");
        }

        String resultMessage = "";

        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/Result.jsp").forward(
                    request, response);
        }
    }
}