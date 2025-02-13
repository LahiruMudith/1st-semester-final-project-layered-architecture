package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.PositionItemDto;

import java.sql.SQLException;

public interface PositionItemBO extends SuperBO{
    boolean setPosition(PositionItemDto positionItemDto) throws SQLException, ClassNotFoundException;
}
