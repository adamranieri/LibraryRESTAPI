package dev.ranieri.services;

import dev.ranieri.daos.EmployeeDAO;
import dev.ranieri.entities.Employee;

public class LoginServiceImpl  implements LoginService{

    private EmployeeDAO employeeDAO;

    public LoginServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }


    @Override
    public Employee validateUser(String username, String password) {
        Employee employee = this.employeeDAO.getEmployeeByUsername(username);

        if(employee == null){
            throw new RuntimeException("No employee found with that username");
        }

        if(!employee.getPassword().equals(password)){
            throw new RuntimeException("password does not match");
        }

       return employee;
    }
}
