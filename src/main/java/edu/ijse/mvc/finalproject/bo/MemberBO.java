package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.DietPlanDto;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentPlanDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO{
    boolean save(MemberDto memberDto);

    boolean delete(String id);

    boolean update(MemberDto memberDto);

    ArrayList<ScheduleDto> getSchedule() throws SQLException;

    ArrayList<PaymentPlanDto> getPaymentPlan() throws SQLException;

    String getNextMemberId() throws SQLException;

    ArrayList<MemberDto> getAll() throws Exception;

    ArrayList<DietPlanDto> getDietPlan() throws SQLException;
}
