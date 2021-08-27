package fr.docmccoy30.servlets;

import fr.docmccoy30.dao.*;
import fr.docmccoy30.beans.Langue;
import fr.docmccoy30.beans.Str;
import fr.docmccoy30.properties.PropertiesManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "Edit")
public class Edit extends HttpServlet {
    StrFileDAO strFileDAO = new StrFileDAOImpl();
    StrDAO strDAO = new StrDAOImpl();
    Properties props;
    String targetFolder;


    /**
     * Méthode doGet appelée lorsque lors du premier accès à la page.
     * @param request La requète HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par un flux d'entrée/sortie.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        props = PropertiesManager.readFileProperties(this);
        targetFolder = props.getProperty("chemin_fichier");

        try {
            //afficher les langues
            List<Langue> langues = strFileDAO.getLanguageFromDataBase();
            request.setAttribute("langues", langues);
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
    }

    /**
     * Méthode doPost appelée lorsque l'on clique sur les différents boutons de la page.
     * @param request La requète HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par un flux d'entrée/sortie.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            //-- Initialise les paramètres utilisées par les fonction "enregistrer" et "exporter".
            Integer fileId = Integer.parseInt(request.getParameter("fileIdSource"));
            List<Str> sousTitresCible = new ArrayList<>();
            List<Str> sousTitresSource = strDAO.getSubtitlesFromDB(fileId);
            String reqParam = request.getParameter("edit");

            switch (reqParam) {
                //-- Enregistrer les sous-titres édités dans la BDD.
                case "enregistrer":
                    setTranslateSubtitles(request, sousTitresCible, fileId, sousTitresSource);
                    String message = "Les sous-titres édités sont enregistrés dans la base de données.";
                    request.setAttribute("message", message);
                    //-- Paramètres à retourner à la page
                    setCommonResponseAttributes(request, fileId, sousTitresSource);
                    break;

                //-- Enregistrer les sous-titres edités dans un fichier .srt .
                case "exporter":
                    Langue langue = strFileDAO.getLanguageById(Integer.parseInt(request.getParameter("langue")));
                    String nom_fichier =  request.getParameter("titre") + "-" + langue.getLangueStr();
                    strDAO.exportEditedSubtitles(strDAO.getSubtitlesFromDB(fileId), nom_fichier, targetFolder);
                    message = "Le fichier "+nom_fichier+ ".srt est disponible dans le repertoire.";
                    request.setAttribute("message", message);
                    //-- Paramètres à retourner à la page
                    setCommonResponseAttributes(request, fileId, sousTitresSource);
                    break;
            }
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
    }

    /**
     * Enregistre les sous-titres édités dans la base de données
     * @param request La requète HTTP.
     * @param sousTitresCible Les sous-titres édités : tableau d'objet Str.
     * @param fileId L'identifiant du fichier sous-titres.
     * @param sousTitresSource Les sous-titres originaux : tableau d'objet Str.
     * @throws ServletException L'exception levée par la servlet.
     */
    private void setTranslateSubtitles(HttpServletRequest request, List<Str> sousTitresCible, Integer fileId, List<Str> sousTitresSource) throws ServletException{
        String[] sousTitre = new String[2];
        int numPhase = 1;
        for (Str record : sousTitresSource) {
            //-- Récupérer la/les traductions de chaque séquence et les inserer dans la DB
            if (request.getParameter("line" + numPhase + "0") != null) {
                sousTitre[0] = request.getParameter("line" + numPhase + "0");
            }
            if (request.getParameter("line" + numPhase + "1") != null) {
                sousTitre[1] = request.getParameter("line" + numPhase + "1");
            }
            record.setSousTitreTraduit(sousTitre);
            sousTitresCible.add(record);
            sousTitre = new String[2];
            numPhase++;
        }
        try {
            strDAO.addEditedSubtitlesInDB(sousTitresCible, fileId);
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
    }

    /**
     * Paramètres communs pour la réponse à envoyer à la page
     * @param request
     * @param fileId
     * @param sousTitresSource
     */
    private void setCommonResponseAttributes(HttpServletRequest request, Integer fileId, List<Str> sousTitresSource) {
        try {
            request.setAttribute("langues", strFileDAO.getLanguageFromDataBase());
            request.setAttribute("strFileSource", strFileDAO.getStrFileById(fileId));
            request.setAttribute("sousTitresSource", sousTitresSource);
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
    }
}