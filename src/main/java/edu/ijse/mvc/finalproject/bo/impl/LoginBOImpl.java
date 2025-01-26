package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.LoginBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.impl.AdminDAOImpl;
import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBOImpl implements LoginBO {
    AdminDAOImpl adminDAO = (AdminDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);

    @Override
    public ArrayList<AdminDto> loadAdminData() throws SQLException {
        ArrayList<Admin> admins = adminDAO.getAll();
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin : admins) {
            adminDtos.add(new AdminDto(admin.getAdmin_id(), admin.getName(), admin.getAddress(), admin.getPassword()));
        };
        return adminDtos;
    }
}
