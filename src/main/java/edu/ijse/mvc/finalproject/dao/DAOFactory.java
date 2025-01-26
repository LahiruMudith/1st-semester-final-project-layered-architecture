package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        return (daoFactory==null) ? daoFactory =new DAOFactory() : daoFactory;
    }
    public enum DAOType{
        ADMIN,MEMBER,EMPLOYEE,FITNESS_CENTER,POSITION_ITEM, DIET_PLAN,SCHEDULE,PAYMENT,PAYMENT_PLAN
    }
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case ADMIN :
                return new AdminDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case FITNESS_CENTER:
                return new FitnessCenterDAOImpl();
            case POSITION_ITEM:
                return new PositionDAOImpl();
            case DIET_PLAN:
                return new DietPlanDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PAYMENT_PLAN:
                return new PaymentPlanDAOImpl();
            default:
                return null;
        }
    }

}
