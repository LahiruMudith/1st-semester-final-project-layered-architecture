package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.MemberBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.impl.DietPlanDAOImpl;
import edu.ijse.mvc.finalproject.dao.impl.MemberDAOImpl;
import edu.ijse.mvc.finalproject.dao.impl.PaymentPlanDAOImpl;
import edu.ijse.mvc.finalproject.dao.impl.ScheduleDAOImpl;
import edu.ijse.mvc.finalproject.dto.DietPlanDto;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentPlanDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;
import edu.ijse.mvc.finalproject.entity.DietPlan;
import edu.ijse.mvc.finalproject.entity.Member;
import edu.ijse.mvc.finalproject.entity.PaymentPlan;
import edu.ijse.mvc.finalproject.entity.Schedule;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBOImpl implements MemberBO {
    MemberDAOImpl memberDAO = (MemberDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);
    ScheduleDAOImpl scheduleDAO = (ScheduleDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    PaymentPlanDAOImpl paymentPlanDAO = (PaymentPlanDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT_PLAN);
    DietPlanDAOImpl dietPlanDAO = (DietPlanDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DIET_PLAN);

    @Override
    public boolean save(MemberDto memberDto) {
        return memberDAO.save(new Member(memberDto.getMember_id(),
                memberDto.getName(),
                memberDto.getAddress(),
                memberDto.getPhone_number(),
                memberDto.getEmail(),
                memberDto.getRegister_date(),
                memberDto.getWeight(),
                memberDto.getHeight(),
                memberDto.getSchedule_id(),
                memberDto.getPlan_id(),
                memberDto.getDiet_plan_id())
        );
    }

    @Override
    public boolean delete(String id) {
        return memberDAO.delete(id);
    }

    @Override
    public boolean update(MemberDto memberDto) {
        return memberDAO.update(
                new Member(
                    memberDto.getMember_id(),
                    memberDto.getName(),
                    memberDto.getAddress(),
                    memberDto.getPhone_number(),
                    memberDto.getEmail(),
                    memberDto.getRegister_date(),
                    memberDto.getWeight(),
                    memberDto.getHeight(),
                    memberDto.getSchedule_id(),
                    memberDto.getPlan_id(),
                    memberDto.getDiet_plan_id()
                )
        );
    }

    @Override
    public ArrayList<ScheduleDto> getSchedule() throws SQLException {
        ArrayList<Schedule> schedules = scheduleDAO.getAll();
        System.out.println("DAO Schedules" + schedules);
        ArrayList<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDtos.add(new ScheduleDto(
                    schedule.getSchedule_id(),
                    schedule.getName(),
                    schedule.getAdmin_id()
            ));
        }
        System.out.println("return Schedules" + scheduleDtos);
        return scheduleDtos;
    }

    @Override
    public ArrayList<PaymentPlanDto> getPaymentPlan() throws SQLException {
        ArrayList<PaymentPlan> paymentPlans = paymentPlanDAO.getAll();
        ArrayList<PaymentPlanDto> paymentPlanDtos = new ArrayList<>();
        for (PaymentPlan paymentPlan : paymentPlans) {
            paymentPlanDtos.add(new PaymentPlanDto(
                    paymentPlan.getPlan_id(),
                    paymentPlan.getPlan_name(),
                    paymentPlan.getPrice()
            ));
        }
        return paymentPlanDtos;
    }

    @Override
    public String getNextMemberId() throws SQLException {
        return memberDAO.generateNewId();
    }

    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
        ArrayList<Member> members = memberDAO.getAll();
        ArrayList<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : members) {
            memberDtos.add(new MemberDto(
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
            ));
        }
        return memberDtos;
    }

    @Override
    public ArrayList<DietPlanDto> getDietPlan() throws SQLException {
        ArrayList<DietPlan> dietPlans = dietPlanDAO.getAll();
        ArrayList<DietPlanDto> dietPlanDtos = new ArrayList<>();
        for (DietPlan dietPlan : dietPlans) {
            dietPlanDtos.add(new DietPlanDto(
                    dietPlan.getDiet_plan_id(),
                    dietPlan.getAdmin_id(),
                    dietPlan.getName(),
                    dietPlan.getDuration(),
                    dietPlan.getDescription()
            ));
        }
        return dietPlanDtos;
    }
}
