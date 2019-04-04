package entities;

public class SearchData {
    private String name;
    private String surname;
    private String patronymic;
    private String dateAfter;
    private String dateBefore;
    private String gender;
    private String nationality;
    private String maritalStatus;
    private String country;
    private String city;
    private String address;
    private String zipCode;

    public SearchData() {
    }

    @Override
    public String toString() {
        return "SearchData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateAfter='" + dateAfter + '\'' +
                ", dateBefore='" + dateBefore + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    public SearchData(String name, String surname, String patronymic, String dateAfter, String dateBefore, String gender, String nationality, String maritalStatus, String country, String city, String address, String zipCode) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateAfter = dateAfter;
        this.dateBefore = dateBefore;
        this.gender = gender;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateAfter() {
        return dateAfter;
    }

    public void setDateAfter(String dateAfter) {
        this.dateAfter = dateAfter;
    }

    public String getDateBefore() {
        return dateBefore;
    }

    public void setDateBefore(String dateBefore) {
        this.dateBefore = dateBefore;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
