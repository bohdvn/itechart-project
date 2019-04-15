package DAO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import entities.Attachment;
import entities.Contact;
import entities.SearchData;
import entities.Number;

import javax.sql.rowset.serial.SerialBlob;

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

    public int insertContact(Contact contact) throws SQLException {
        String sql = "Insert into users.Contact(Name, Surname, Patronymic, Date_of_birth, Gender, Nationality, " +
                "Marital_status, Web_site, Email, Work_place, Country, City, Address, Zip_code, IMAGE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        connect();

        PreparedStatement pstm = jdbcConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
        pstm.setBytes(15,Base64.getDecoder().decode(contact.getBase64Image()));

        pstm.executeUpdate();

        ResultSet generatedKeys = pstm.getGeneratedKeys();

        generatedKeys.next();
        return generatedKeys.getInt(1);
    }

    public boolean insertNumber(Number phone) throws SQLException {
        String sql = "Insert into users.number(CONTACT_ID, COUNTRY_CODE, OPERATOR_CODE, NUMBER, TYPE, COMMENT) " +
                "values (?,?,?,?,?,?)";
        connect();

        PreparedStatement pstm = jdbcConnection.prepareStatement(sql);
        pstm.setInt(1, phone.getContactId());
        pstm.setString(2, phone.getCountryCode());
        pstm.setString(3, phone.getOperatorCode());
        pstm.setString(4, phone.getNumber());
        pstm.setString(5, phone.getType());
        pstm.setString(6, phone.getComment());

        boolean rowInserted = pstm.executeUpdate() > 0;
        pstm.close();
        disconnect();
        return rowInserted;
    }

    public boolean insertAttachment(Attachment attach) throws SQLException {
        String sql = "Insert into users.attachment(CONTACT_ID, NAME, COMMENT, DATE ) " +
                "values (?,?,?,?)";
        connect();

        PreparedStatement pstm = jdbcConnection.prepareStatement(sql);
        pstm.setInt(1, attach.getContactId());
        pstm.setString(2, attach.getName());
        pstm.setString(3, attach.getComment());
        pstm.setString(4, attach.getDate());

        boolean rowInserted = pstm.executeUpdate() > 0;
        pstm.close();
        disconnect();
        return rowInserted;
    }

    public List<Number> listAllNumbers(int contactId) throws SQLException {
        List<Number> listNumber = new ArrayList<>();

        String sql = "SELECT * FROM number where CONTACT_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contactId);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("Id");
            String countryCode = rs.getString("Country_Code");
            String operatorCode = rs.getString("Operator_Code");
            String number = rs.getString("Number");
            String type = rs.getString("Type");
            String comment = rs.getString("Comment");
            Number newNumber = new Number(id,contactId,countryCode,operatorCode,number,type,comment);
            listNumber.add(newNumber);
        }

        rs.close();
        statement.close();

        disconnect();

        return listNumber;
    }

    public List<Attachment> listAllAttachments(int contactId) throws SQLException {
        List<Attachment> listAttachment = new ArrayList<>();

        String sql = "SELECT * FROM attachment where CONTACT_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, contactId);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {

            Integer id = rs.getInt("Id");
            String name = rs.getString("Name");
            String date = rs.getString("Date");
            String comment = rs.getString("Comment");
            Attachment newAttachment = new Attachment(id,contactId,name,date,comment);
            listAttachment.add(newAttachment);
        }

        rs.close();
        statement.close();

        disconnect();

        return listAttachment;
    }

    private int noOfRecords;

    public List<Contact> listAllContacts(int offset, int noOfRecords) throws SQLException {
        List<Contact> listContact = new ArrayList<>();

        String sql = "SELECT SQL_CALC_FOUND_ROWS * FROM contact LIMIT " + offset + ", " + noOfRecords;

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
        rs = statement.executeQuery("SELECT FOUND_ROWS()");
        if(rs.next())
            this.noOfRecords = rs.getInt(1);
        statement.close();

        disconnect();

        return listContact;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public boolean deleteContact(Contact contact) throws SQLException {
        deleteNumbers(contact.getId());
        deleteAttachments(contact.getId());

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
        String sql = "UPDATE users.Contact SET Name =?, Surname=?, Patronymic=?, Date_of_birth=?, Gender=?, Nationality=?, Marital_status=?, Web_site=?, Email=?, Work_place=?, Country=?, City=?, Address=?, Zip_code=?, IMAGE=?";
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
        pstm.setBytes(15,Base64.getDecoder().decode(contact.getBase64Image()));
        pstm.setInt(16, contact.getId());

        boolean rowUpdated;
        if (pstm.executeUpdate() > 0) rowUpdated = true;
        else rowUpdated = false;
        pstm.close();
        disconnect();
        return rowUpdated;
    }

    public boolean deleteNumbers(int id) throws SQLException {
        String sql = "DELETE FROM users.number where CONTACT_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean deleteAttachments(int id) throws SQLException {
        String sql = "DELETE FROM users.attachment where CONTACT_ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public Contact getContact(int id) throws SQLException, IOException {
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
            Blob blob = rs.getBlob("Image");
            String base64Image=null;
            if (blob!=null) {

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                base64Image = Base64.getEncoder().encodeToString(imageBytes);

                inputStream.close();
                outputStream.close();
            }

            contact = new Contact(id, name, surname, patronymic, dateOfBirth, gender, nationality,
                    maritalStatus, webSite, email, workPlace, country, city, address, zipCode);


            contact.setBase64Image(base64Image);
        }

        rs.close();
        statement.close();

        return contact;
    }

    public List<Contact> getContactsWithBirthday() throws SQLException, IOException {
        List<Contact> listContact = new ArrayList<>();
        String sql = "SELECT * \n" +
                "FROM  contact\n" +
                "WHERE  DATE_ADD(DATE_OF_BIRTH, \n" +
                "                INTERVAL YEAR(CURDATE())-YEAR(DATE_OF_BIRTH)\n" +
                "                         + IF(DAYOFYEAR(CURDATE()) > DAYOFYEAR(DATE_OF_BIRTH),1,0)\n" +
                "                YEAR) =CURDATE()";

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

    public static String encoder(String filePath) {
        String base64File = "";
        File file = new File(filePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a file from file system
            byte fileData[] = new byte[(int) file.length()];
            imageInFile.read(fileData);
            base64File = Base64.getEncoder().encodeToString(fileData);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the file " + ioe);
        }
        return base64File;
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
