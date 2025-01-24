package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.MemberBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.impl.MemberDAOImpl;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.entity.Member;

public class MemberBOImpl implements MemberBO {
    MemberDAOImpl memberDAO = (MemberDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);
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
}
