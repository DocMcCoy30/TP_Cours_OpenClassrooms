package fr.docmccoy30.dao;

import fr.docmccoy30.beans.Str;
import fr.docmccoy30.beans.StrFile;

import java.io.IOException;
import java.util.List;

public interface StrDAO {

    void addOriginalSubtitlesInDB(List<Str> sousTitres, Integer id) throws DaoException;
    void addEditedSubtitlesInDB(List<Str> sousTitres, Integer strFileId) throws DaoException;
    List<Str> getSubtitlesFromFile(StrFile strFile, String targetFolder) throws IOException;
    List<Str> getSubtitlesFromDB(Integer strFileId) throws DaoException;
    void exportEditedSubtitles(List<Str> sousTitresCible, String nomFichier, String targetFolder) throws DaoException, IOException;
    boolean checkDataBase(Integer fileId) throws DaoException;
}
