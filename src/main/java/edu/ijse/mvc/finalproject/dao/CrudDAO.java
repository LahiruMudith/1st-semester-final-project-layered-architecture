package edu.ijse.mvc.finalproject.dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    ArrayList<T> getAll();
    boolean save(T dto);
    boolean update(T dto);
    boolean delete(String id);
    String generateNewId();
}
