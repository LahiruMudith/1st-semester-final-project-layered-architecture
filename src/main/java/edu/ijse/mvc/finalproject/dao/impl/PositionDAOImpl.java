package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.PositionDAO;
import edu.ijse.mvc.finalproject.entity.PositionItem;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PositionDAOImpl implements PositionDAO {
    @Override
    public ArrayList<PositionItem> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from positionitem");

        ArrayList<PositionItem> list = new ArrayList<>();

        while (rst.next()){
            PositionItem positionItemDto = new PositionItem(
                    rst.getString(1)
            );
            list.add(positionItemDto);
        }
        return list;
    }

    @Override
    public boolean save(PositionItem entity) {
        return false;
    }

    @Override
    public boolean update(PositionItem entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() {
        return "";
    }

    @Override
    public PositionItem search(String id) throws Exception {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
