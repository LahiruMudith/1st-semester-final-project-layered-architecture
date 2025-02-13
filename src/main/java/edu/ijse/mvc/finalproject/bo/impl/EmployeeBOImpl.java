package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.EmployeeBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.impl.EmployeeDAOImpl;
import edu.ijse.mvc.finalproject.dao.impl.FitnessCenterDAOImpl;
import edu.ijse.mvc.finalproject.dao.impl.PositionItemDAOImpl;
import edu.ijse.mvc.finalproject.dto.EmployeeDto;
import edu.ijse.mvc.finalproject.dto.FitnessCenterDto;
import edu.ijse.mvc.finalproject.dto.PositionItemDto;
import edu.ijse.mvc.finalproject.entity.Employee;
import edu.ijse.mvc.finalproject.entity.FitnessCenter;
import edu.ijse.mvc.finalproject.entity.PositionItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAOImpl employeeDAO = (EmployeeDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);
    FitnessCenterDAOImpl fitnessCenterDAO = (FitnessCenterDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FITNESS_CENTER);
    PositionItemDAOImpl positionDAO = (PositionItemDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.POSITION_ITEM);

    @Override
    public boolean save(EmployeeDto employeeDto) {
        return employeeDAO.save(new Employee(
                employeeDto.getEmployee_id(),
                employeeDto.getCenter_id(),
                employeeDto.getName(),
                employeeDto.getPhone_number(),
                employeeDto.getDate_of_hire(),
                employeeDto.getPosition(),
                employeeDto.getAge(),
                employeeDto.getAddress()
        ));
    }

    @Override
    public boolean delete(String text) {
        return employeeDAO.delete(text);
    }

    @Override
    public boolean update(EmployeeDto employeeDto) {
        return employeeDAO.update(new Employee(
                employeeDto.getEmployee_id(),
                employeeDto.getCenter_id(),
                employeeDto.getName(),
                employeeDto.getPhone_number(),
                employeeDto.getDate_of_hire(),
                employeeDto.getPosition(),
                employeeDto.getAge(),
                employeeDto.getAddress()
        ));
    }

    @Override
    public ArrayList<FitnessCenterDto> getCenterDetails() throws SQLException {
        ArrayList<FitnessCenterDto> list = new ArrayList<>();
        ArrayList<FitnessCenter> fitnessCenterDtos = fitnessCenterDAO.getAll();
        for (FitnessCenter fitnessCenter : fitnessCenterDtos) {
            list.add(new FitnessCenterDto(
                    fitnessCenter.getCenter_id(),
                    fitnessCenter.getAdmin_id(),
                    fitnessCenter.getCenter_name(),
                    fitnessCenter.getLocation()
            ));
        };
        return list;
    }

    @Override
    public ArrayList<EmployeeDto> getEmployee() throws SQLException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            String phoneNum = employee.getPhone_number();
            employee.setPhone_number("0"+phoneNum);
            employeeDtos.add(new EmployeeDto(
                    employee.getEmployee_id(),
                    employee.getCenter_id(),
                    employee.getName(),
                    employee.getPhone_number(),
                    employee.getDate_of_hire(),
                    employee.getPosition(),
                    employee.getAge(),
                    employee.getAddress()
            ));
        };
        return employeeDtos;
    }

    @Override
    public ArrayList<PositionItemDto> getPositions() throws SQLException {
        ArrayList<PositionItem> all = positionDAO.getAll();
        ArrayList<PositionItemDto> positionItemDtos = new ArrayList<>();
        for (PositionItem positionItem : all) {
            positionItemDtos.add(new PositionItemDto(
                    positionItem.getPositionName()
            ));
        };
        return positionItemDtos;
    }

    @Override
    public String getNextEmployeeId() throws SQLException {
        return employeeDAO.generateNewId();
    }
}
