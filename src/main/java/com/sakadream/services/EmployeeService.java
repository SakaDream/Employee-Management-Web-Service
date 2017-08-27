package com.sakadream.services;

import com.sakadream.models.Employee;

import java.util.List;

/**
 * Created by Phan Ba Hai on 25/08/2017.
 */
public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee findById(int id);
    Employee findByName(String name);
    Boolean addEmployee(Employee employee);
    Boolean updateEmployee(int id, Employee employee);
    Boolean deleteEmployee(int id);
}
