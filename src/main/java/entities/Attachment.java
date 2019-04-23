package entities;

import java.io.Serializable;
import java.util.Objects;

public class Attachment implements Serializable{
    private Integer id;
    private Integer contactId;
    private String name;
    private String date;
    private String comment;
    private String base64File = null;



    public String getBase64File() {
        return base64File;
    }

    public void setBase64File(String base64File) {
        this.base64File = base64File;
    }

    public Attachment(Integer id, Integer contactId, String name, String date, String comment) {
        this.id = id;
        this.contactId = contactId;
        this.name = name;
        this.date = date;
        this.comment = comment;
    }

    public Attachment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return id.equals(that.id) &&
                contactId.equals(that.contactId) &&
                name.equals(that.name) &&
                date.equals(that.date) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactId, name, date, comment);
    }
}
