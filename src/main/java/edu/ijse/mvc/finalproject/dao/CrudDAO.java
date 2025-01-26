package edu.ijse.mvc.finalproject.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException;
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(String id);
    String generateNewId() throws SQLException;
}
