package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;;
    boolean delete(String id) throws SQLException, ClassNotFoundException;;
    String generateNewId() throws SQLException, ClassNotFoundException;
    T search(String id) throws Exception, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}
