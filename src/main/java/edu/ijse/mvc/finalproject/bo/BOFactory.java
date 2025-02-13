package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getInstance(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }
    public enum BOType{
    SIGNIN, LOGIN, MEMBER,EMPLOYEE,DIET_PLAN,SCHEDULE,HOME_PAGE, POSITION_ITEM,EXERCISE_ADD
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
            case DIET_PLAN:
                return new DietPlanBOImpl();
            case SCHEDULE:
                return new ScheduleBOImpl();
            case HOME_PAGE:
                return new HomePageBOImpl();
            case POSITION_ITEM:
                return new PositionItemItemBOImpl();
            case EXERCISE_ADD:
                return new ExerciseAddBOImpl();
            default:
                return null;
        }
    }
}
