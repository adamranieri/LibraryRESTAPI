package dev.ranieri.daos;

import dev.ranieri.entities.Employee;
import dev.ranieri.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoPostgres implements EmployeeDAO{

    @Override
    public Employee getEmployeeByUsername(String username) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Employee employee = new Employee();
            employee.setEmpId(resultSet.getInt("emp_id"));
            employee.setUsername(resultSet.getString("username"));
            employee.setFname(resultSet.getString("fname"));
            employee.setLname(resultSet.getString("lname"));
            employee.setPassword(resultSet.getString("password"));

            return employee;

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
