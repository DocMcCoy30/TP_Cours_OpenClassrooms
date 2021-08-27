package fr.DocMcCoy30.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    // Version cours Youtube
    private static Connection connexion;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DB_Bibliotheque", "adm_bibliotheque", "adm_bibliotheque");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnexion() {
        return connexion;
    }
}

// Version Cours OC
    /*private String url;
    private String user;
    private String password;

    public DaoFactory(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DaoFactory instance = new DaoFactory("jdbc:postgresql://localhost:5432/DB_Bibliotheque", "adm_bibliotheque", "adm_bibliotheque");
        return instance;
    }

    public Connection getConnection () throws SQLException {
            Connection connexion = DriverManager.getConnection(url, user, password);
        return connexion;
    }

    public ILivreDao getILivreDao () {
        return new LivreDaoImpl(this);
    }*/