package fr.docmccoy30.dao;

import fr.docmccoy30.beans.Langue;
import fr.docmccoy30.beans.StrFile;

import java.io.IOException;
import java.util.List;

public interface StrFileDAO {

    List<StrFile> listFiles() throws DaoException;
    StrFile setFile(StrFile strFile) throws DaoException;
    void deleteFile(Integer fileId, String targetFolder) throws DaoException, IOException;
    void updateFile(StrFile strFile, String targetFolder) throws DaoException, IOException;
    StrFile getStrFileById(Integer fileId) throws DaoException;
    boolean checkDataBase(String nomFichier) throws DaoException;
    List<Langue> getLanguageFromDataBase() throws DaoException;
    Langue getLanguageById(Integer id) throws DaoException;

}
