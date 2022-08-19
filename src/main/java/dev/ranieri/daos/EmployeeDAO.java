package dev.ranieri.daos;

import dev.ranieri.entities.Employee;

public interface EmployeeDAO {

    Employee getEmployeeByUsername(String username);
}
