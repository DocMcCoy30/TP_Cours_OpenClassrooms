package fr.docmccoy30.servlets;

import fr.docmccoy30.dao.*;
import fr.docmccoy30.beans.Langue;
import fr.docmccoy30.beans.Str;
import fr.docmccoy30.beans.StrFile;
import fr.docmccoy30.properties.PropertiesManager;
import fr.docmccoy30.utils.UtilsFichier;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "Files")
public class Files extends HttpServlet {
    private StrFileDAO strFileDAO = new StrFileDAOImpl();
    private StrDAO strDAO = new StrDAOImpl();
    private String targetFolder;

    /**
     * Méthode doGet appelée lorsque lors du premier accès à la page.
     * Le fihier web.xml contient...
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par un flux d'entrée/sortie.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Properties props = PropertiesManager.readFileProperties(this);
        targetFolder = props.getProperty("chemin_fichier");

        try {
            //-- Alimenter la liste de choix des langues à l'écran.
            List<Langue> langues = strFileDAO.getLanguageFromDataBase();

            //-- Alimenter la liste de choix des sauvegarde de fichier en base de données à l'écran.
            StrFile strFileList = new StrFile();

            strFileList.setStrFiles(strFileDAO.listFiles());

            //-- Données à renvoyer à la page
            setCommonResponseAttributes(request, strFileList, langues);

        } catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/files.jsp").forward(request, response);
    }

    /**
     * Méthode doPost appelée lorsque l'on clique sur les différents boutons de la page.
     * Le fihier web.xml contient...
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par une flux d'entrée/sortie.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String reqParam = request.getParameter("files");

            if (reqParam.contains("upload")) {
                manageFileUpload(request, response);
                this.getServletContext().getRequestDispatcher("/WEB-INF/files.jsp").forward(request, response);
            } else if (reqParam.contains("delete")) {
                manageFileDelete(request, response);
                this.getServletContext().getRequestDispatcher("/WEB-INF/files.jsp").forward(request, response);
            } else if (reqParam.contains("update")) {
                manageFileUpdate(request, response);
                this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
            } else if (reqParam.contains("edit")) {
                manageSubtitleEdition(request, response);
                this.getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
            }

        this.getServletContext().getRequestDispatcher("/WEB-INF/files.jsp").forward(request, response);
    }

    /**
     * Gérer l'Upload d'un fichier de sous-titres dans le dossier cible.
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par une flux d'entrée/sortie.
     */
    private void manageFileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilsFichier utils = new UtilsFichier();
        StrFile strFile = new StrFile();
        Part part;
        String message = "";

        try {
            String titre = request.getParameter("titre");
            Integer langue_id = Integer.parseInt(request.getParameter("langue"));
            part = request.getPart("fichier");
            Langue langue = strFileDAO.getLanguageById(langue_id);
            String nomFichier = utils.getNomFichier(part);


            if (!strFileDAO.checkDataBase(nomFichier)) {
                strFile = strFileDAO.setFile(new StrFile(titre, langue, nomFichier));
                message = writeFile(utils, part, nomFichier);
            }
            StrFile strFileList = new StrFile();
            strFileList.setStrFiles(strFileDAO.listFiles());

            //-- Paramètres à retourner à la page
            setCommonResponseAttributes(request, strFileList, strFileDAO.getLanguageFromDataBase());
            request.setAttribute("file", strFile);
            request.setAttribute("message", message);

        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
    }

    /**
     * Gérer la suppression d'un fichier de sous-titres dans la BDD et dans le dossier cible.
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par une flux d'entrée/sortie.
     */
    private void manageFileDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //-- Supprimer en base de données
            Integer fileIdSource = getFileId(request);
            strFileDAO.deleteFile(fileIdSource, targetFolder);

            //-- Afficher la liste des fichiers
            StrFile strFileList = new StrFile();
            strFileList.setStrFiles(strFileDAO.listFiles());

            //-- Paramètres à retourner à la page
            setCommonResponseAttributes(request, strFileList, strFileDAO.getLanguageFromDataBase());

        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
    }

    /**
     * Gérer la modification du fichier : permet la modification du nom et/ou de la langue. Modifie le nom du fichier en conséquence.
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par une flux d'entrée/sortie.
     */
    private void manageFileUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer fileIdSource = getFileId(request);

        try {
            //-- Afficher la liste des fichiers
            StrFile strFileList = new StrFile();
            strFileList.setStrFiles(strFileDAO.listFiles());

            //-- Paramètres à retourner à la page
            setCommonResponseAttributes(request, strFileDAO.getStrFileById(fileIdSource), strFileDAO.getLanguageFromDataBase());
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
    }

    /**
     * Gérer l'édition des sous-titres : renvoie sur la page d'édition et affiche les caractéristiques du fichier et les sous-titres à éditer.
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException L'exception levée par une flux d'entrée/sortie.
     */
    private void manageSubtitleEdition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Str> sousTitresSource = new ArrayList<>();

        Integer fileIdSource = getFileId(request);

        try {
            StrFile strFileSource = strFileDAO.getStrFileById(fileIdSource);

            if (!strDAO.checkDataBase(fileIdSource)) {
                sousTitresSource = strDAO.getSubtitlesFromFile(strFileSource, targetFolder);
                strDAO.addOriginalSubtitlesInDB(sousTitresSource, fileIdSource);
            } else if (strDAO.checkDataBase(fileIdSource)) {
                sousTitresSource = strDAO.getSubtitlesFromDB(fileIdSource);
            }

            //-- Paramètres à retourner à la page
            setCommonResponseAttributes(request, strFileSource, strFileDAO.getLanguageFromDataBase());
            request.setAttribute("fileIdSource", fileIdSource);
            request.setAttribute("sousTitresSource", sousTitresSource);
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
    }

    /**
     * Retourne l'identifiant du fichier traité.
     * @param request La requête HTTP.
     * @return L'identifiant du fichier.
     */
    private Integer getFileId(HttpServletRequest request) {
        String files = request.getParameter("files");
        return Integer.parseInt(files.substring(files.indexOf("+") + 1));
    }

    /**
     * Extrait le nom du fichier de l'entête de la requête HTTP et crée le fichier dans le repertoire cible.
     * @param utils L'objet UtilsFichier.
     * @param part Le fichier à uploader.
     * @param nomFichier Le nom du fichier.
     * @throws IOException L'exception levée par une flux d'entrée/sortie.
     */
    private String writeFile(UtilsFichier utils, Part part, String nomFichier) throws IOException {
        String message = "";
        if (nomFichier != null && !nomFichier.isEmpty()) {
            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);
            message = utils.ecrireFichier(part, nomFichier, targetFolder);
        }
        return message;
    }

    /**
     * Paramètres communs pour la réponse.
     * @param request La requête HTTP.
     * @param strFileSource La liste des fichiers enregistrés dans la BDD.
     * @param langues La liste des langues renseignées dans la BDD.
     */
    private void setCommonResponseAttributes(HttpServletRequest request, StrFile strFileSource, List<Langue> langues) {
        request.setAttribute("langues", langues);
        request.setAttribute("strFileSource", strFileSource);
    }
}
