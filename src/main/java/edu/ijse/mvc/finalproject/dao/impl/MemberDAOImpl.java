package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.MemberDAO;
import edu.ijse.mvc.finalproject.entity.Member;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public ArrayList<Member> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT" +
                "    m.member_id,\n" +
                "    m.name,\n" +
                "    m.address,\n" +
                "    m.phone_number,\n" +
                "    m.email,\n" +
                "    m.register_date,\n" +
                "    m.weight,\n" +
                "    m.height,\n" +
                "    s.name AS schedule_name,\n" +
                "    pl.plan_name,\n" +
                "    dp.name AS diet_plan_name\n" +
                "FROM\n" +
                "    member m\n" +
                "        LEFT JOIN\n" +
                "    schedule s ON m.schedule_id = s.schedule_id\n" +
                "        LEFT JOIN\n" +
                "    payment_plan pl ON m.plan_id = pl.plan_id\n" +
                "        LEFT JOIN\n" +
                "    diet_plan dp ON m.diet_plan_id = dp.diet_plan_id");
        ArrayList<Member> list = new ArrayList<>();

        while (resultSet.next()){
            Member member = new Member(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11)
            );
            list.add(member);
        }
        return list;
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
    public String generateNewId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select member_id from member order by member_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("M%03d", newIdIndex);
        }
        return "M001";
    }

    @Override
    public ArrayList<Member> getMembers() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT" +
                "    m.member_id,\n" +
                "    m.name,\n" +
                "    m.address,\n" +
                "    m.phone_number,\n" +
                "    m.email,\n" +
                "    m.register_date,\n" +
                "    m.weight,\n" +
                "    m.height,\n" +
                "    s.name AS schedule_name,\n" +
                "    pl.plan_name,\n" +
                "    dp.name AS diet_plan_name\n" +
                "FROM\n" +
                "    member m\n" +
                "        LEFT JOIN\n" +
                "    schedule s ON m.schedule_id = s.schedule_id\n" +
                "        LEFT JOIN\n" +
                "    payment_plan pl ON m.plan_id = pl.plan_id\n" +
                "        LEFT JOIN\n" +
                "    diet_plan dp ON m.diet_plan_id = dp.diet_plan_id");
        ArrayList<Member> list = new ArrayList<>();

        while (resultSet.next()){
            Member member = new Member(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11)
            );
            list.add(member);
        }
        return list;
    }
}
