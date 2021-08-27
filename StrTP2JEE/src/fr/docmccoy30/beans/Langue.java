package fr.docmccoy30.beans;

public class    Langue {

    private Integer langue_id;
    private String langueStr = "";


    public Langue() {
    }

    public Langue(Integer langue_id, String langueStr) {
        this.langue_id = langue_id;
        this.langueStr = langueStr;
    }

    public Langue(String langueStr) {
        this.langueStr = langueStr;
    }

    public Integer getLangue_id() {
        return langue_id;
    }

    public void setLangue_id(Integer langue_id) {
        this.langue_id = langue_id;
    }

    public String getLangueStr() {
        return langueStr;
    }

    public void setLangueStr(String langueStr) {
        this.langueStr = langueStr;
    }

    @Override
    public String toString() {
        return "Langue{" +
                "langue_id=" + langue_id +
                ", langueStr='" + langueStr + '\'' +
                '}';
    }
}
