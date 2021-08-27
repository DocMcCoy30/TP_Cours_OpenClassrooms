package fr.docmccoy30.servlets;

import fr.docmccoy30.dao.DaoException;
import fr.docmccoy30.dao.StrFileDAO;
import fr.docmccoy30.dao.StrFileDAOImpl;
import fr.docmccoy30.beans.Langue;
import fr.docmccoy30.beans.StrFile;
import fr.docmccoy30.properties.PropertiesManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "Update")
public class Update extends HttpServlet {
    private StrFileDAO strFileDAO = new StrFileDAOImpl();

    /**
     * Méthode doGet inopérante. La page est appelée exclusivement par la méthode doPost
     * @param request La réquète HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la servlet.
     * @throws IOException      L'exception levée par un flux d'entrée/sortie.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
    }

    /**
     * Méthode doPost appelée lorsque l'on clique sur le bouton "modifier" de la page.
     *
     * @param request  La requète HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException L'exception levée par la Servlet.
     * @throws IOException      L'exception levée par un flux d'entrée/sortie.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Properties props = PropertiesManager.readFileProperties(this);
        String targetFolder = props.getProperty("chemin_fichier");

        try {
            Integer fileId = Integer.parseInt(request.getParameter("fileId"));
            String titre = request.getParameter("titre");
            Langue langue = strFileDAO.getLanguageById(Integer.parseInt(request.getParameter("langue")));
            String nomFichier = titre + ".srt";

            strFileDAO.updateFile(new StrFile(fileId, titre, langue, nomFichier), targetFolder);

            //afficher la liste des fichiers
            StrFile strFileSource = new StrFile();
            strFileSource.setStrFiles(strFileDAO.listFiles());
            request.setAttribute("strFileSource", strFileSource);
        }catch (DaoException e) {
            request.setAttribute("erreur", e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/files.jsp").forward(request, response);
    }
}
