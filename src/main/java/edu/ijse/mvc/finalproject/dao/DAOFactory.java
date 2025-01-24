package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.dao.impl.AdminDAOImpl;
import edu.ijse.mvc.finalproject.dao.impl.MemberDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return (daoFactory==null) ? daoFactory =new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        ADMIN,MEMBER
    }
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case ADMIN :
                return new AdminDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            default:
                return null;
        }
    }

}
