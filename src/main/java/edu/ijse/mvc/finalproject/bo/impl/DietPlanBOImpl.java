package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.DietPlanBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.DietPlanDAO;
import edu.ijse.mvc.finalproject.dto.DietPlanDto;
import edu.ijse.mvc.finalproject.entity.DietPlan;

import java.sql.SQLException;
import java.util.ArrayList;

public class DietPlanBOImpl implements DietPlanBO {
    DietPlanDAO dietPlanDAO = (DietPlanDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DIET_PLAN);

    @Override
    public boolean add(DietPlanDto dietPlanDto) throws SQLException, ClassNotFoundException {
        return dietPlanDAO.save(new DietPlan(
                dietPlanDto.getDiet_plan_id(),
                dietPlanDto.getAdmin_id(),
                dietPlanDto.getName(),
                dietPlanDto.getDuration(),
                dietPlanDto.getDescription()
        ));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return dietPlanDAO.delete(id);
    }

    @Override
    public boolean update(DietPlanDto dietPlanDto) throws SQLException, ClassNotFoundException {
        return dietPlanDAO.update(new DietPlan(
                dietPlanDto.getDiet_plan_id(),
                dietPlanDto.getAdmin_id(),
                dietPlanDto.getName(),
                dietPlanDto.getDuration(),
                dietPlanDto.getDescription()
        ));
    }

    @Override
    public ArrayList<DietPlanDto> loadTable() throws SQLException, ClassNotFoundException {
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
        System.out.println("Diet Plan Dtos " + dietPlanDtos);
        return dietPlanDtos;
    }
}
