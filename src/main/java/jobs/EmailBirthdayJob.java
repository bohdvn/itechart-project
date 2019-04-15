package jobs;

import DAO.ContactDAO;
import entities.Contact;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import utility.EmailUtility;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.*;

public class EmailBirthdayJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) {
        EmailUtility emailUtility = new EmailUtility();
        String jdbcURL = "jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String jdbcUsername = "root";
        String jdbcPassword = "password";

        ContactDAO contactDAO = new ContactDAO(jdbcURL, jdbcUsername, jdbcPassword);

        List<Contact> admin = new ArrayList<>();

        List<Contact> contacts = null;
        try {
            contacts = contactDAO.getContactsWithBirthday();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contacts.size()!=0) {
            String message = null;
            try {
                message = createNotificationMessage(contacts);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                emailUtility.sendEmail("smtp.gmail.com", "587", "cntctapp@gmail.com", "itechart2019", "gunnerinho@gmail.com", "Birthday notification", message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }

    private String createNotificationMessage(List<Contact> contacts) throws ParseException {
        StringBuilder message = new StringBuilder();
        message.append("The following contacts have birthday today:\n");
        for (Contact contact : contacts) {
            message.append(contact.getName() + " ");
            message.append(contact.getSurname() + " ");
            message.append("("+getDiffYears(contact.getDateOfBirth()) + " years)\n");
        }

        return message.toString();
    }

    public static int getDiffYears(String first) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(first);
        Calendar a = getCalendar(date);
        Date last = new Date();
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
