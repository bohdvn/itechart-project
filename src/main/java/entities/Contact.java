package entities;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private String maritalStatus;
    private String webSite;
    private String email;
    private String workPlace;
    private String country;
    private String city;
    private String address;
    private String zipCode;
    private String base64Image = null;

    private String validateName(String name){
        if(name==null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }
        return name;
    }

    private String validateSurname(String surname){
        if(surname==null) {
            throw new IllegalArgumentException("Surname cannot be null!");
        }
        return surname;
    }

    private String validateGender(String gender){
        if(!gender.equals("M")&&!gender.equals("F")&&!gender.equals("")) {
            throw new IllegalArgumentException("Invalid gender format");
        }
        return gender;
    }

    public String validateEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if ((email == null || !pat.matcher(email).matches())&&!email.equals("")){
            throw new IllegalArgumentException("Invalid email format");
        }
        return email;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public Contact(Integer id, String name, String surname, String patronymic, String dateOfBirth, String gender, String nationality, String maritalStatus, String webSite, String email, String workPlace, String country, String city, String address, String zipCode) {
        this.id = id;
        this.name = validateName(name);
        this.surname = validateSurname(surname);
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.gender = validateGender(gender);
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.webSite = webSite;
        this.email = validateEmail(email);
        this.workPlace = workPlace;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Contact(String name, String surname, String patronymic, String dateOfBirth, String gender, String nationality, String maritalStatus, String webSite, String email, String workPlace, String country, String city, String address, String zipCode) {
        this.name = validateName(name);
        this.surname = validateSurname(surname);
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.gender = validateGender(gender);
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.webSite = webSite;
        this.email = validateEmail(email);
        this.workPlace = workPlace;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = validateName(name);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = validateSurname(surname);
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = validateGender(gender);
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validateEmail(email);
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id.equals(contact.id) &&
                name.equals(contact.name) &&
                Objects.equals(surname, contact.surname) &&
                Objects.equals(patronymic, contact.patronymic) &&
                Objects.equals(dateOfBirth, contact.dateOfBirth) &&
                Objects.equals(gender, contact.gender) &&
                Objects.equals(nationality, contact.nationality) &&
                Objects.equals(maritalStatus, contact.maritalStatus) &&
                Objects.equals(webSite, contact.webSite) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(workPlace, contact.workPlace) &&
                Objects.equals(country, contact.country) &&
                Objects.equals(city, contact.city) &&
                Objects.equals(address, contact.address) &&
                Objects.equals(zipCode, contact.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, dateOfBirth, gender, nationality, maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
    }
}
