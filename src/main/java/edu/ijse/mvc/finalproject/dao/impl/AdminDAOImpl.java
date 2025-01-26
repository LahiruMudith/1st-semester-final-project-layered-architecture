package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.AdminDAO;
import edu.ijse.mvc.finalproject.dao.CrudDAO;
import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.entity.Admin;
import edu.ijse.mvc.finalproject.util.CrudUtil;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public ArrayList<Admin> getAll() throws SQLException {
        ResultSet rst =CrudUtil.execute("select * from admin");

        ArrayList<Admin> admins = new ArrayList<>();

        while (rst.next()){
            Admin adminDto = new Admin(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            admins.add(adminDto);
        }
        return admins;
    }

    @Override
    public boolean save(Admin entity) {
        System.out.println(entity.toString());
        return CrudUtil.execute("insert into admin values(?,?,?,?)", entity.getAdmin_id(), entity.getName(), entity.getAddress(), entity.getPassword());
    }

    @Override
    public boolean update(Admin entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() {
        try {
            ResultSet resultSet = CrudUtil.execute("select admin_id from admin order by admin_id desc limit 1");
            if(resultSet.next()){
                String lastId = resultSet.getString(1);
                String subString = lastId.substring(1);
                int i =Integer.parseInt(subString);
                int newAdminIndex = i+1;
                return String.format("A%03d", newAdminIndex);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.show();
        }
        return "000";
    }
}
