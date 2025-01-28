package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException;
    boolean save(T entity) throws SQLException;
    boolean update(T entity);
    boolean delete(String id);
    String generateNewId() throws SQLException;
    T search(String id) throws Exception;
}
