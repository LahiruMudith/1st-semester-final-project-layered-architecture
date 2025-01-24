package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.SignInBO;
import edu.ijse.mvc.finalproject.dao.AdminDAO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dto.SignInDto;
import edu.ijse.mvc.finalproject.entity.Admin;

public class SignInBOImpl implements SignInBO {
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public boolean save(SignInDto signInDto) {
        return adminDAO.save(new Admin(signInDto.getAdminId(), signInDto.getName(), signInDto.getAddress(), signInDto.getPassword()));
    }

    @Override
    public String generateNewId() {
        return adminDAO.generateNewId();
    }
}
