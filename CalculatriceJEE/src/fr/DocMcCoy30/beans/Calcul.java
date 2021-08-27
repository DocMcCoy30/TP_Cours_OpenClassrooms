package fr.DocMcCoy30.beans;

public class Calcul {

    private Integer nb1;
    private Integer nb2;
    private Integer resultat;
    private String operateur = "+";

    public Integer getNb1() {
        return nb1;
    }

    public void setNb1(Integer nb1) {
        this.nb1 = nb1;
    }

    public Integer getNb2() {
        return nb2;
    }

    public void setNb2(Integer nb2) {
        this.nb2 = nb2;
    }

    public String getResultat () {
        String messageResultat;
        resultat = calculer(nb1,nb2,operateur);
        if (resultat == null) {
            messageResultat = "Division par 0 impossible";
        } else {
            messageResultat = nb1 + " " + operateur + " " + nb2 + " = " + resultat;
        }
        return messageResultat;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    private Integer calculer(Integer nb1, Integer nb2, String operateur) {
        this.nb1 = nb1;
        this.nb2 = nb2;
        this.operateur = operateur;
        try {
            switch (operateur) {
                case "+":
                    resultat = nb1 + nb2;
                    break;
                case "-":
                    resultat = nb1 - nb2;
                    break;
                case "x":
                    resultat = nb1 * nb2;
                    break;
                case "/":
                    resultat = nb1 / nb2;
                    break;
            }
        } catch (ArithmeticException e) {}  // renvoie null
        return resultat;
    }
}
