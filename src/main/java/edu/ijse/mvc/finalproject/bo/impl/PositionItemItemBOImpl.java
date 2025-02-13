package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.PositionItemBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.PositionItemDAO;
import edu.ijse.mvc.finalproject.dto.PositionItemDto;
import edu.ijse.mvc.finalproject.entity.PositionItem;

import java.sql.SQLException;

public class PositionItemItemBOImpl implements PositionItemBO {
    PositionItemDAO positionItemDAO = (PositionItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.POSITION_ITEM);
    @Override
    public boolean setPosition(PositionItemDto positionItemDto) throws SQLException, ClassNotFoundException {
        return positionItemDAO.save(new PositionItem(
                positionItemDto.getPositionName()
        ));
    }
}
