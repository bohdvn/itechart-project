package utility;

import entities.Attachment;
import entities.Number;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaveUtility {
    public static List<Number> getNumbers(HttpServletRequest request, Integer idContact) {
        List<Number> phones = new ArrayList<>();
        Enumeration<String> paramNames = request.getParameterNames();
        Pattern pattern = Pattern.compile("phone\\d+");

        int i=0;
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            Matcher matcher = pattern.matcher(paramName);
                Number phone = new Number();
                phone.setCountryCode(request.getParameter("countryCode" + i));
                phone.setOperatorCode(request.getParameter("operatorCode" + i));
                phone.setNumber(request.getParameter("number" + i));
                phone.setType(request.getParameter("type" + i));
                phone.setComment(request.getParameter("comment" + i));
                if(idContact != null)
                    phone.setContactId(idContact);
                if (phone.getCountryCode()!=null){
                phones.add(phone);
                }
                i++;
            }

        return phones;
    }

    public static List<Attachment> getAttachments(HttpServletRequest request, Integer idContact) {
        List<Attachment> attachments = new ArrayList<>();
        Enumeration<String> paramNames = request.getParameterNames();
        Pattern pattern = Pattern.compile("attachment\\d+");

        int i=0;
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            Matcher matcher = pattern.matcher(paramName);
            Attachment attachment = new Attachment();
            attachment.setName(request.getParameter("name" + i));
            attachment.setComment(request.getParameter("commentAtt" + i));
            attachment.setDate(request.getParameter("date" + i));
            if(idContact != null)
                attachment.setContactId(idContact);
            if (attachment.getName()!=null){
               attachments.add(attachment);
            }
            i++;
        }

        return attachments;
    }
}
