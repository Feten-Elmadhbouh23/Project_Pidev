package services;

import java.sql.SQLException;
import java.util.List;

public interface IZliv <T>{
    void ajouter(T t) throws SQLException;
    List<T> afficher() throws SQLException;
}