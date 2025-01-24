package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminDAO extends CrudDAO<Admin> {
    ArrayList<Admin> loadAdminData() throws SQLException;
}
