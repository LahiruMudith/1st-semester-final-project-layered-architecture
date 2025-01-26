package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.entity.Member;

import java.util.ArrayList;

public interface MemberDAO extends CrudDAO<Member> {
    ArrayList<Member> getMembers() throws Exception;
}
