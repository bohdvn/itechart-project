package utility;

import DAO.ContactDAO;
import entities.Attachment;
import entities.Contact;
import entities.Number;
import entities.SearchData;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LogicUtility {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "password";
    private static ContactDAO contactDAO = new ContactDAO(jdbcURL, jdbcUsername, jdbcPassword);

    private static Logger logger = LogManager.getLogger(LogicUtility.class);

    public static void deleteSelected (String[] checkedIds) {
        if (checkedIds != null && checkedIds.length != 0) {
            for (int i = 0; i < checkedIds.length; i++) {
                int id= Integer.parseInt(checkedIds[i]);
                Contact contact = new Contact(id);
                try {
                    contactDAO.deleteContact(contact);
                } catch (SQLException e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        }
        logger.info("Selected contacts deleted");
    }

    public static List<Contact> initSearchData (HttpServletRequest request){
        SearchData data=new SearchData();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String dateAfter = request.getParameter("dateAfter");
        if (dateAfter.equals("")) dateAfter = null;
        String dateBefore = request.getParameter("dateBefore");
        if (dateBefore.equals("")) dateBefore = null;
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

        List<Contact> listContact = null;
        try {
            listContact = contactDAO.searchContact(data);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }

        return listContact;
    }

    public static void insertContact (HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String dateOfBirth = null;
        if(!request.getParameter("dateOfBirth").equals("")) dateOfBirth = request.getParameter("dateOfBirth");
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
        if (base64Image.equals("")) base64Image=null;

        Contact newContact = new Contact(name, surname, patronymic, dateOfBirth, gender, nationality,
                maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        newContact.setBase64Image(base64Image);
        int id = 0;
        try {
            id = contactDAO.insertContact(newContact);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }

        List<Number> phones = SaveUtility.getNumbers(request, id);
        initNumbers(phones);

        List<Attachment> attachments = SaveUtility.getAttachments(request, id);
        for(Attachment attachment : attachments) {
            try {
                contactDAO.insertAttachment(attachment);
            } catch (SQLException e) {
                logger.error(e);
                e.printStackTrace();
            }
        }

        logger.info("Inserted contact: "+newContact.getName()+" "+newContact.getSurname());
    }

    public static void updateContact (HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String dateOfBirth = request.getParameter("dateOfBirth");
        if (dateOfBirth.equals("")) dateOfBirth = null;
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
        if (base64Image.equals("")) base64Image=null;


        Contact contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        contact.setBase64Image(base64Image);
        try {
            contactDAO.updateContact(contact);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }

        List<Number> phones = SaveUtility.getNumbers(request, id);
        try {
            contactDAO.deleteNumbers(id);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        initNumbers(phones);

        List<Attachment> attachments = SaveUtility.getAttachments(request, id);
        try {
            contactDAO.deleteAttachments(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Attachment attachment : attachments) {
            try {
                contactDAO.insertAttachment(attachment);
            } catch (SQLException e) {
                logger.error(e);
                e.printStackTrace();
            }
        }
        logger.info("Updated contact: "+contact.getName()+" "+contact.getSurname());
    }

    private static void initNumbers(List<Number> phones) {
        for(Number phone : phones) {
            try {
                contactDAO.insertNumber(phone);
            } catch (SQLException e) {
                logger.error(e);
                e.printStackTrace();
            }
        }
    }

    public static void deleteContact (HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        Contact contact = new Contact(id);
        try {
            contactDAO.deleteContact(contact);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        logger.info("Deleted contact: "+contact.getName()+" "+contact.getSurname());
    }

    public static void listContact (HttpServletRequest request, HttpServletResponse response){
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<Contact> list = null;
        try {
            list = contactDAO.listAllContacts((page-1)*recordsPerPage,
                    recordsPerPage);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        int noOfRecords = contactDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("listContact", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
    }
    public static void emailSelected (HttpServletRequest request) {
        String[] checkedIds = request.getParameterValues("checked");
        String emails = null;
        try {
            emails = (contactDAO.getContact(Integer.parseInt(checkedIds[0])).getEmail());
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        String names = null;
        try {
            names = (contactDAO.getContact(Integer.parseInt(checkedIds[0])).getName());
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        if (checkedIds != null && checkedIds.length != 0) {
            for (int i = 1; i < checkedIds.length; i++) {
                int id = Integer.parseInt(checkedIds[i]);
                Contact existingContact = null;
                try {
                    existingContact = contactDAO.getContact(id);
                } catch (SQLException e) {
                    logger.error(e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.error(e);
                    e.printStackTrace();
                }
                emails += ", ";
                emails += existingContact.getEmail();
                names += ", ";
                names += existingContact.getName();
            }
            request.setAttribute("emails", emails);
            request.setAttribute("names", names);
        }
    }
    public static void showEmailForm (HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact = null;
        try {
            existingContact = contactDAO.getContact(id);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        request.setAttribute("emails", existingContact.getEmail());
        request.setAttribute("names", existingContact.getName());
    }
    public static void showEditForm (HttpServletRequest request){

        int id = Integer.parseInt(request.getParameter("id"));
        Contact existingContact = null;
        try {
            existingContact = contactDAO.getContact(id);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        List<Number> listNumber = null;
        try {
            listNumber = contactDAO.listAllNumbers(id);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        List<Attachment> listAttachment = null;
        try {
            listAttachment = contactDAO.listAllAttachments(id);
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
        }
        request.setAttribute("contact", existingContact);
        request.setAttribute("phones", listNumber);
        request.setAttribute("listAttachment", listAttachment);
    }
}
