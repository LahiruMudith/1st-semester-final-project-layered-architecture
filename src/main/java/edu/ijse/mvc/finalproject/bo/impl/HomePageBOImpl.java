package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.HomePageBO;
import edu.ijse.mvc.finalproject.dao.*;
import edu.ijse.mvc.finalproject.dao.impl.PaymentDAOImpl;
import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentDetailDto;
import edu.ijse.mvc.finalproject.dto.tm.PaymentDetailTM;
import edu.ijse.mvc.finalproject.entity.Member;
import edu.ijse.mvc.finalproject.entity.Payment;
import edu.ijse.mvc.finalproject.entity.PaymentDetail;
import edu.ijse.mvc.finalproject.entity.PaymentPlan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePageBOImpl implements HomePageBO {
    PaymentDAO paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    PaymentDetailsDAO paymentDetailsDAO = (PaymentDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT_DETAILS);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    DietPlanDAO dietPlanDAO = (DietPlanDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DIET_PLAN);
    PaymentPlanDAO paymentPlanDAO = (PaymentPlanDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT_PLAN);

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewId();
    }

    @Override
    public boolean markFee(PaymentDetailDto paymentDetailDto) {
        Connection connection = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSavedPaymentTable = paymentDAO.save(new Payment(
                    paymentDetailDto.getPayment_id(),
                    paymentDetailDto.getPayment_date(),
                    paymentDetailDto.getAdmin_id()
            ));
            if (!isSavedPaymentTable){
                connection.rollback();
                return false;
            }

            boolean isSavedPaymentDetailsTable = paymentDetailsDAO.save(new PaymentDetail(
                    paymentDetailDto.getPayment_id(),
                    paymentDetailDto.getMember_id(),
                    paymentDetailDto.getMember_name(),
                    paymentDetailDto.getPayment_date(),
                    paymentDetailDto.getPrice(),
                    paymentDetailDto.getPayment_method(),
                    paymentDetailDto.getMonth()
            ));
            if (!isSavedPaymentDetailsTable){
                connection.rollback();
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MemberDto searchMember(String id) throws Exception {
        Member member = memberDAO.search(id);
        return new MemberDto(
                member.getMember_id(),
                member.getName(),
                member.getAddress(),
                member.getPhone_number(),
                member.getEmail(),
                member.getRegister_date(),
                member.getWeight(),
                member.getHeight(),
                member.getSchedule_id(),
                member.getPlan_id(),
                member.getDiet_plan_id()
        );
    }

    @Override
    public String getPlanPrice(String text) throws Exception {
        PaymentPlan paymentPlan = paymentPlanDAO.search(text);
        System.out.println("plan price is = " + paymentPlan.getPrice());
        return String.valueOf(paymentPlan.getPrice());
    }

    @Override
    public ArrayList<PaymentDetailTM> loadTable(String text) throws Exception {
        ArrayList<PaymentDetail> paymentDetails = paymentDetailsDAO.getPaymentDetails(text);
        ArrayList<PaymentDetailTM> paymentDetailTMS = new ArrayList<>();
        for (PaymentDetail paymentDetail : paymentDetails) {
            paymentDetailTMS.add(new PaymentDetailTM(
                    paymentDetail.getPayment_id(),
                    paymentDetail.getMember_id(),
                    paymentDetail.getMember_name(),
                    paymentDetail.getPayment_date(),
                    paymentDetail.getPrice(),
                    paymentDetail.getPayment_method(),
                    paymentDetail.getMonth()
            ));
        }
        return paymentDetailTMS;
    }

    @Override
    public int getMemberCount() throws SQLException, ClassNotFoundException {
        return memberDAO.getCount();
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCount();
    }

    @Override
    public int getScheduleCount() throws SQLException, ClassNotFoundException {
        return scheduleDAO.getCount();
    }

    @Override
    public int getDietPlanCount() throws SQLException, ClassNotFoundException {
        return dietPlanDAO.getCount();
    }
}
