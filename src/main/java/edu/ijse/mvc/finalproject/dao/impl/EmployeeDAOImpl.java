package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.EmployeeDAO;
import edu.ijse.mvc.finalproject.dto.EmployeeDto;
import edu.ijse.mvc.finalproject.entity.Employee;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select e.employee_id, fe.center_name, e.name, e.phone_number, e.date_of_hire, e.position, e.age, e.address from employee e left join fitness_center fe on e.center_id = fe.center_id");

        ArrayList<Employee> employeeList = new ArrayList<>();
        while (rst.next()){
            Employee employee = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getString(8)
            );
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public boolean save(Employee entity) {
        return CrudUtil.execute("insert into employee value (?,?,?,?,?,?,?,?)",
                entity.getEmployee_id(),
                entity.getCenter_id(),
                entity.getName(),
                entity.getPhone_number(),
                entity.getDate_of_hire(),
                entity.getPosition(),
                entity.getAge(),
                entity.getAddress()
        );
    }

    @Override
    public boolean update(Employee entity) {
        return CrudUtil.execute("update employee set center_id=?, name=?, phone_number=?, date_of_hire=?, position=?, age=?, address=? WHERE employee_id = ?",
                entity.getCenter_id(),
                entity.getName(),
                entity.getPhone_number(),
                entity.getDate_of_hire(),
                entity.getPosition(),
                entity.getAge(),
                entity.getAddress(),
                entity.getEmployee_id()
        );
    }

    @Override
    public boolean delete(String id) {
        return CrudUtil.execute("delete from employee where employee_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("EMP%03d", newIdIndex);
        }
        return null;
    }
}
