package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.SignInBO;
import edu.ijse.mvc.finalproject.dao.AdminDAO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dto.SignInDto;
import edu.ijse.mvc.finalproject.entity.Admin;

import java.sql.SQLException;

public class SignInBOImpl implements SignInBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public boolean save(SignInDto signInDto) {
        return adminDAO.save(new Admin(signInDto.getAdminId(), signInDto.getName(), signInDto.getAddress(), signInDto.getPassword()));
    }

    @Override
    public String generateNewId() throws SQLException {
        return adminDAO.generateNewId();
    }
}
