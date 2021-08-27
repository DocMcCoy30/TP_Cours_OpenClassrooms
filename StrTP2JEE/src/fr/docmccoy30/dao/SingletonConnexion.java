package fr.docmccoy30.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection connexion;
    private static String url = "jdbc:postgresql://localhost:5432/DB_SousTitres";
    private static String user = "postgres";
    private static String password = "postgres";
    private static String driver = "org.postgresql.Driver";

    public static Connection getInstance() {
        if (connexion==null) {
            try {
                Class.forName(driver);
                connexion = DriverManager.getConnection(url,user,password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        } return connexion;
    }
}
