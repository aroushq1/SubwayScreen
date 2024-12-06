package ca.ucalgary.edu.ensf380;

import java.sql.*;

/**
 * The AdvertisementDatabase class is responsible for making a connection
 * to a database using the URL, username, and password. This class 
 * is used to interact with the database.
 * 
 * @version 1.0
 * @since 2024-07-20
 */
public class AdvertisementDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/advertisement";
    private static final String USERNAME = "Mahdi";
    private static final String PASSWORD = "ensf380";
    
    private Connection dbConnect;
    private ResultSet results;

    /**
     * Default constructor.
     */
    public AdvertisementDatabase() {
    }

    /**
     * Initializes and returns a connection to the database.
     *
     * @return The database connection.
     * @throws SQLException If a database access error occurs or the URL is null.
     */
    public Connection initializeConnection() throws SQLException {
        try {
            dbConnect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnect;
    }

    /**
     * Selects and returns all the information associated with each advertisement.
     *
     * @return A string containing all advertisement information.
     * @throws SQLException If the fields of the database cannot be accessed.
     */
    public String selectAd() throws SQLException {
        StringBuilder adInfo = new StringBuilder(); 

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM advertisements");

            while (results.next()) {
                System.out.println("SelectAd() print results: " 
                    + results.getString("title") + ", " 
                    + results.getString("media_type") + ", " 
                    + results.getString("filepath"));

                adInfo.append(results.getString("title")).append(", ")
                      .append(results.getString("filepath")).append(", ")
                      .append(results.getString("media_type")).append("\n");
            }

            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adInfo.toString();
    }

    /**
     * Selects and returns the path associated with each advertisement.
     *
     * @return A string containing all advertisement file paths.
     * @throws SQLException If the fields of the database cannot be accessed.
     */
    public String selectPath() throws SQLException {
        StringBuilder adPath = new StringBuilder(); // Use StringBuilder for efficiency

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT filepath FROM advertisements");

            while (results.next()) {
                System.out.println("SelectPath() print results: " 
                    + results.getString("filepath"));

                adPath.append(results.getString("filepath")).append("\n");
            }

            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adPath.toString();
    }

    /**
     * Selects and returns the ID and media type associated with each advertisement.
     *
     * @return A string containing all advertisement IDs and media types.
     * @throws SQLException If the fields of the database cannot be accessed.
     */
    public String selectMediaTypeAndId() throws SQLException {
        StringBuilder adMT = new StringBuilder(); 

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT media_type, id FROM advertisements");

            while (results.next()) {
                System.out.println("SelectMediaTypeAndId() print results: " 
                    + results.getString("id") + ", " 
                    + results.getString("media_type"));

                adMT.append(results.getString("id")).append(", ")
                    .append(results.getString("media_type")).append("\n");
            }

            myStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adMT.toString();
    }

    /**
     * Closes the connection to the database.
     *
     * @throws SQLException If there is an error closing the database connection.
     */
    public void close() throws SQLException {
        try {
            if (results != null) {
                results.close();
            }
            if (dbConnect != null) {
                dbConnect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
}
