package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.bo.impl.EmployeeBOImpl;
import edu.ijse.mvc.finalproject.bo.impl.LoginBOImpl;
import edu.ijse.mvc.finalproject.bo.impl.MemberBOImpl;
import edu.ijse.mvc.finalproject.bo.impl.SignInBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getInstance(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOType{
    SIGNIN, LOGIN, MEMBER,EMPLOYEE
    }
    public SuperBO getBO(BOType boType){
        switch (boType){
            case SIGNIN :
                return new SignInBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            default:
                return null;
        }
    }
}
