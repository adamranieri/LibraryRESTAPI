package dev.ranieri.services;

import dev.ranieri.entities.Employee;

public interface LoginService {

    Employee validateUser(String username, String password);
}
