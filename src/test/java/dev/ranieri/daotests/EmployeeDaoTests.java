package dev.ranieri.daotests;

import dev.ranieri.daos.EmployeeDAO;
import dev.ranieri.daos.EmployeeDaoPostgres;
import dev.ranieri.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @Test
    void get_employee_by_username(){
        Employee employee = employeeDAO.getEmployeeByUsername("gatorFan");
        System.out.println(employee);
        Assertions.assertEquals("Adam",employee.getFname());
    }
}
