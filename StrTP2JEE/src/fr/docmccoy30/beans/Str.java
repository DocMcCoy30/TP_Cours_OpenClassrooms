package fr.docmccoy30.beans;

import java.util.Arrays;

public class Str {
    private Integer strId = 0;
    private Integer index = 0;
    private String timeStamp = "";
    private String [] sousTitreOriginal = {"",""};
    private String [] sousTitreTraduit = {"",""};
    private Integer strFileId = 0;

    public Str() {
    }

    public Str(Integer index, String timeStamp, String[] sousTitreOriginal, String[] sousTitreTraduit, Integer strFileId) {
        this.index = index;
        this.timeStamp = timeStamp;
        this.sousTitreOriginal = sousTitreOriginal;
        this.sousTitreTraduit = sousTitreTraduit;
        this.strFileId = strFileId;
    }

    public Integer getStrId() {
        return strId;
    }

    public void setStrId(Integer strId) {
        this.strId = strId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String[] getSousTitreOriginal() {
        return sousTitreOriginal;
    }

    public void setSousTitreOriginal(String[] sousTitreOriginal) {
        this.sousTitreOriginal = sousTitreOriginal;
    }

    public String[] getSousTitreTraduit() {
        return sousTitreTraduit;
    }

    public void setSousTitreTraduit(String[] sousTitreTraduit) {
        this.sousTitreTraduit = sousTitreTraduit;
    }

    public Integer getStrFileId() {
        return strFileId;
    }

    public void setStrFileId(Integer strFileId) {
        this.strFileId = strFileId;
    }

    @Override
    public String toString() {
        return "Str{" +
                "strId=" + strId +
                ", index=" + index +
                ", timeStamp='" + timeStamp + '\'' +
                ", sousTitreOriginal=" + Arrays.toString(sousTitreOriginal) +
                ", sousTitreTraduit=" + Arrays.toString(sousTitreTraduit) +
                ", strFileId=" + strFileId +
                '}';
    }
}

