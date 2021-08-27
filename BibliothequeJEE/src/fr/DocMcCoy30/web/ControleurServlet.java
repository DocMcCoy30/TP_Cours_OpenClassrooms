package fr.DocMcCoy30.web;

import fr.DocMcCoy30.beans.Livre;
import fr.DocMcCoy30.dao.ILivreDao;
import fr.DocMcCoy30.dao.LivreDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControleurServlet")

public class ControleurServlet extends HttpServlet {
    private ILivreDao metier = new LivreDaoImpl();
    private LivreModele modele = new LivreModele();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet(request, response); // la méthode renvoie vers doGet pour éviter de refaire un bloc code
        String path = request.getServletPath(); // permet de rassembler toutes les vues dans la même servlet

        switch (path) {
            case "/saveAjout.do":
                String titre = request.getParameter("titre");
                String auteur = request.getParameter("auteur");
                String genre = request.getParameter("genre");
                //Integer annee = Integer.parseInt(request.getParameter("annee")) => pour l'exemple dans le cas d'un int
                Livre livre = metier.enregistrer(new Livre(titre, auteur, genre));
                request.setAttribute("livre", livre);
                this.getServletContext().getRequestDispatcher("/WEB-INF/confirmationAjout.jsp").forward(request, response);
                break;
            case "/saveUpdate.do" :
                Integer id = Integer.parseInt(request.getParameter("id"));
                titre = request.getParameter("titre");
                auteur = request.getParameter("auteur");
                genre = request.getParameter("genre");
                livre = new Livre (titre, auteur, genre);
                livre.setId(id);
                metier.update(livre);
                request.setAttribute("livre", livre);
                this.getServletContext().getRequestDispatcher("/WEB-INF/confirmationUpdate.jsp").forward(request, response);
        }
    }

        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String path = request.getServletPath();
            switch (path) {
                //if (path.equals("/index.do")) {
                case "/index.do":
                    modele = new LivreModele();
                    modele.setLivres(metier.lister());
                    request.setAttribute("modele", modele);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
                    break;
                //else if (path.equals("/rechercheTitre.do")) {
                case "/rechercheTitre.do":
                    String motCle = request.getParameter("rechercheTitre");
                    modele.setMotCle(motCle);
                    List<Livre> livres = metier.chercher("%" + motCle + "%");
                    modele.setLivres(livres);
                    request.setAttribute("modele", modele);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
                    break;
                //}else if (path.equals("/rechercheGenre.do")) {
                case "/rechercheGenre.do":
                    motCle = request.getParameter("rechercheGenre");
                    modele = new LivreModele();
                    modele.setMotCle(motCle);
                    livres = metier.chercher("%" + motCle + "%");
                    modele.setMotCle("");
                    modele.setLivres(livres);
                    request.setAttribute("modele", modele);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
                    break;
                //}else if (path.equals("/ajout.do")) {
                case "/ajout.do":
                    Livre livre = new Livre();
                    request.setAttribute("livre", livre);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/ajout.jsp").forward(request, response);
                    break;
                //}else if (path.equals("/saveAjout.do")&&(request.getMethod().equals("POST"))) {
//            case "/saveAjout.do" :
//                String titre = request.getParameter("titre");
//                String auteur = request.getParameter("auteur");
//                String genre = request.getParameter("genre");
//                //Integer annee = Integer.parseInt(request.getParameter("annee")) => pour l'exemple dans le cas d'un int
//                livre = metier.enregistrer(new Livre(titre, auteur, genre));
//                request.setAttribute("livre", livre);
//                this.getServletContext().getRequestDispatcher("/WEB-INF/confirmationAjout.jsp").forward(request, response);
//                break;
                //}else if (path.equals("/delete.do")) {
                case "/delete.do":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    metier.delete(id);
                    modele = new LivreModele();
                    modele.setLivres(metier.lister());
                    request.setAttribute("modele", modele);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
                    //response.sendRedirect("index.do");
                    //}else if (path.equals("/update.do")) {
                case "/update.do":
                    id = Integer.parseInt(request.getParameter("id"));
                    livre = metier.getLivre(id);
                    request.setAttribute("livre", livre);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
                    break;
                //}else if (path.equals("/saveUpdate.do")&&(request.getMethod().equals("POST"))) {
//            case "/saveUpdate.do"
//            Integer id = Integer.parseInt(request.getParameter("id"));
//            String titre = request.getParameter("titre");
//            String auteur = request.getParameter("auteur");
//            String genre = request.getParameter("genre");
//            Livre livre = new Livre(titre, auteur, genre);
//            livre.setId(id);
//            metier.update(livre);
//            request.setAttribute("livre", livre);
//            this.getServletContext().getRequestDispatcher("/WEB-INF/confirmationUpdate.jsp").forward(request, response);
//        }
            }
        }
    }
