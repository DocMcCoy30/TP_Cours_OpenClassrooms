package fr.DocMcCoy30.servlets;

import fr.DocMcCoy30.beans.Calcul;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Calculatrice")
public class Calculatrice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //récupération des données du formulaire
        String nb1 = request.getParameter("nb1");
        String nb2 = request.getParameter("nb2");
        String operateur = request.getParameter("operateur");
        //Création d'un objet calcul avec les données du formulaire et gestion des erreurs
        Calcul calcul = new Calcul();
        //Conversion en Integer, gestion des erreurs et "fixation" des données dans le bean
        try {
            calcul.setNb1(Integer.parseInt(nb1));
        } catch (NumberFormatException e) {
            calcul.setNb1(0);
        }
        try {
            calcul.setNb2(Integer.parseInt(nb2));
        } catch (NumberFormatException e) {
            calcul.setNb2(0);
        }
        calcul.setOperateur(operateur);
        //calcul du résultat
        String resultat = calcul.getResultat();
        //création des attributs pour la jsp
        request.setAttribute("calcul", calcul);
        request.setAttribute("resultat", resultat);

        this.getServletContext().getRequestDispatcher("/WEB-INF/calculatrice.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/calculatrice.jsp").forward(request, response);
    }
}
