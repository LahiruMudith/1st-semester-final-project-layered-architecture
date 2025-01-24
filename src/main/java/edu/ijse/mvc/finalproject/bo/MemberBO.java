package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.MemberDto;

public interface MemberBO extends SuperBO{
    boolean save(MemberDto memberDto);

    boolean delete(String id);

    boolean update(MemberDto memberDto);
}
