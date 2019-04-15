package entities;

import java.util.Objects;

public class Number {
    public static final String TYPE_HOME = "HOME";
    public static final String TYPE_MOBILE = "MOBILE";

    private Integer id;
    private Integer contactId;
    private String countryCode;
    private String operatorCode;
    private String number;
    private String type;
    private String comment;

    public String getFullPhone() {
        return  countryCode + " "+ operatorCode + " " + number;
    }

    @Override
    public String toString() {
        return  countryCode + " "+ operatorCode + " " + number;
    }

    public Number() {
    }

    public Number(Integer id, Integer contactId) {
        this.id = id;
        this.contactId = contactId;
    }

    public Number(Integer id, Integer contactId, String countryCode, String operatorCode, String number, String type, String comment) {
        this.id = id;
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.operatorCode = operatorCode;
        this.number = number;
        this.type = type;
        this.comment = comment;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Number that = (Number) o;
        return id.equals(that.id) &&
                contactId.equals(that.contactId) &&
                countryCode.equals(that.countryCode) &&
                operatorCode.equals(that.operatorCode) &&
                number.equals(that.number) &&
                Objects.equals(type, that.type) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactId, countryCode, operatorCode, number, type, comment);
    }
}
