package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Contact;
import entities.SearchData;

public class ContactDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public ContactDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertContact(Contact contact) throws SQLException {
        String sql = "Insert into users.Contact(Name, Surname, Patronymic, Date_of_birth, Gender, Nationality, " +
                "Marital_status, Web_site, Email, Work_place, Country, City, Address, Zip_code) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        connect();

        PreparedStatement pstm = jdbcConnection.prepareStatement(sql);
        pstm.setString(1, contact.getName());
        pstm.setString(2, contact.getSurname());
        pstm.setString(3, contact.getPatronymic());
        pstm.setString(4, contact.getDateOfBirth());
        pstm.setString(5, contact.getGender());
        pstm.setString(6, contact.getNationality());
        pstm.setString(7, contact.getMaritalStatus());
        pstm.setString(8, contact.getWebSite());
        pstm.setString(9, contact.getEmail());
        pstm.setString(10, contact.getWorkPlace());
        pstm.setString(11, contact.getCountry());
        pstm.setString(12, contact.getCity());
        pstm.setString(13, contact.getAddress());
        pstm.setString(14, contact.getZipCode());

        boolean rowInserted = pstm.executeUpdate() > 0;
        pstm.close();
        disconnect();
        return rowInserted;
    }

    public List<Contact> listAllContacts() throws SQLException {
        List<Contact> listContact = new ArrayList<>();

        String sql = "SELECT * FROM contact";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {

            Integer id = rs.getInt("Id");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String patronymic = rs.getString("Patronymic");
            String dateOfBirth = rs.getString("Date_of_birth");
            String gender = rs.getString("Gender");
            String nationality = rs.getString("Nationality");
            String maritalStatus = rs.getString("Marital_status");
            String webSite = rs.getString("Web_site");
            String email = rs.getString("Email");
            String workPlace = rs.getString("Work_place");
            String country = rs.getString("Country");
            String city = rs.getString("City");
            String address = rs.getString("Address");
            String zipCode = rs.getString("Zip_code");

            Contact contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                    maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
            listContact.add(contact);
        }

        rs.close();
        statement.close();

        disconnect();

        return listContact;
    }

    public boolean deleteContact(Contact contact) throws SQLException {
        String sql = "DELETE FROM users.contact where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contact.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE users.Contact SET Name =?, Surname=?, Patronymic=?, Date_of_birth=?, Gender=?, Nationality=?, Marital_status=?, Web_site=?, Email=?, Work_place=?, Country=?, City=?, Address=?, Zip_code=?";
        sql += " WHERE Id = ?";
        connect();

        PreparedStatement pstm = jdbcConnection.prepareStatement(sql);

        pstm.setString(1, contact.getName());
        pstm.setString(2, contact.getSurname());
        pstm.setString(3, contact.getPatronymic());
        pstm.setString(4, contact.getDateOfBirth());
        pstm.setString(5, contact.getGender());
        pstm.setString(6, contact.getNationality());
        pstm.setString(7, contact.getMaritalStatus());
        pstm.setString(8, contact.getWebSite());
        pstm.setString(9, contact.getEmail());
        pstm.setString(10, contact.getWorkPlace());
        pstm.setString(11, contact.getCountry());
        pstm.setString(12, contact.getCity());
        pstm.setString(13, contact.getAddress());
        pstm.setString(14, contact.getZipCode());
        pstm.setInt(15, contact.getId());

        boolean rowUpdated = pstm.executeUpdate() > 0;
        pstm.close();
        disconnect();
        return rowUpdated;
    }

    public Contact getContact(int id) throws SQLException {
        Contact contact = null;
        String sql = "SELECT * FROM users.contact WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String patronymic = rs.getString("Patronymic");
            String dateOfBirth = rs.getString("Date_of_birth");
            String gender = rs.getString("Gender");
            String nationality = rs.getString("Nationality");
            String maritalStatus = rs.getString("Marital_status");
            String webSite = rs.getString("Web_site");
            String email = rs.getString("Email");
            String workPlace = rs.getString("Work_place");
            String country = rs.getString("Country");
            String city = rs.getString("City");
            String address = rs.getString("Address");
            String zipCode = rs.getString("Zip_code");

            contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                    maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
        }

        rs.close();
        statement.close();

        return contact;
    }

    public List<Contact> searchContact(SearchData data) throws SQLException {
        List<Contact> listContact = new ArrayList<>();

        String sql = "SELECT * FROM contact WHERE " +
                "name LIKE ? " +
                "and surname LIKE ? " +
                "and patronymic LIKE ? " +
                "and gender LIKE ? " +
                "and nationality LIKE ? " +
                "and marital_status LIKE ? " +
                "and country LIKE ? " +
                "and city LIKE ? " +
                "and address LIKE ? " +
                "and zip_code LIKE ?";
        if (data.getDateAfter()!=null)
            sql+="and date_of_birth >= ? ";

        if (data.getDateBefore()!=null)
            sql+="and date_of_birth <= ? ";

        connect();

        PreparedStatement pstm = jdbcConnection.prepareStatement(sql);
        pstm.setString(1, data.getName()+'%');
        pstm.setString(2, data.getSurname()+'%');
        pstm.setString(3, data.getPatronymic()+'%');
        pstm.setString(4, data.getGender()+'%');
        pstm.setString(5, data.getNationality()+'%');
        pstm.setString(6, data.getMaritalStatus()+'%');
        pstm.setString(7, data.getCountry()+'%');
        pstm.setString(8, data.getCity()+'%');
        pstm.setString(9, '%'+data.getAddress()+'%');
        pstm.setString(10, data.getZipCode()+'%');

        if (data.getDateAfter()!=null)
        pstm.setString(11, data.getDateAfter()+'%');
        if (data.getDateBefore()!=null)
        pstm.setString(12, data.getDateBefore()+'%');

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("Id");
            String name = rs.getString("Name");
            String surname = rs.getString("Surname");
            String patronymic = rs.getString("Patronymic");
            String dateOfBirth = rs.getString("Date_of_birth");
            String gender = rs.getString("Gender");
            String nationality = rs.getString("Nationality");
            String maritalStatus = rs.getString("Marital_status");
            String webSite = rs.getString("Web_site");
            String email = rs.getString("Email");
            String workPlace = rs.getString("Work_place");
            String country = rs.getString("Country");
            String city = rs.getString("City");
            String address = rs.getString("Address");
            String zipCode = rs.getString("Zip_code");

            Contact contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                    maritalStatus, webSite, email, workPlace, country, city, address, zipCode);
            listContact.add(contact);
        }

        rs.close();
        pstm.close();

        disconnect();

        return listContact;
    }
}
