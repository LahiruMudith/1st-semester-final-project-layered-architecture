package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.MemberDAO;
import edu.ijse.mvc.finalproject.entity.Member;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public ArrayList<Member> getAll() {
        return null;
    }

    @Override
    public boolean save(Member entity) {
        return CrudUtil.execute("insert into member value (?,?,?,?,?,?,?,?,?,?,?)",
                entity.getMember_id(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone_number(),
                entity.getEmail(),
                entity.getRegister_date(),
                entity.getWeight(),
                entity.getHeight(),
                entity.getSchedule_id(),
                entity.getPlan_id(),
                entity.getDiet_plan_id()
        );
    }

    @Override
    public boolean update(Member entity) {
        return CrudUtil.execute("update member set name=?, address=?, phone_number=?, email=?, weight=?, height=?, schedule_id=?, plan_id=?, diet_plan_id=? WHERE member_id = ?",
                entity.getName(),
                entity.getAddress(),
                entity.getPhone_number(),
                entity.getEmail(),
                entity.getWeight(),
                entity.getHeight(),
                entity.getSchedule_id(),
                entity.getPlan_id(),
                entity.getDiet_plan_id(),
                entity.getMember_id()
        );
    }

    @Override
    public boolean delete(String id) {
        return CrudUtil.execute("delete from member where member_id = ?",id);
    }

    @Override
    public String generateNewId() {
        return "";
    }
}
