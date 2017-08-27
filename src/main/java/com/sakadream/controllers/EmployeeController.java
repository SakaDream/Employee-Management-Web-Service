package com.sakadream.controllers;

import com.sakadream.models.Employee;
import com.sakadream.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Phan Ba Hai on 25/08/2017.
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @RequestMapping(value = "/employees", method = GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        return (employees.isEmpty()) ? new ResponseEntity<List<Employee>>(NO_CONTENT) : new ResponseEntity<List<Employee>>(employees, OK);
    }

    @RequestMapping(value = "/employee/id/{id}", method = GET)
    public ResponseEntity<Employee> getById(@PathVariable("id") int id) {
        Employee employee = service.findById(id);
        return (employee == null) ? new ResponseEntity<Employee>(NOT_FOUND) : new ResponseEntity<Employee>(employee, OK);
    }

    @RequestMapping(value = "/employee/name/{name}", method = GET)
    public ResponseEntity<Employee> getByName(@PathVariable("name") String name) {
        Employee employee = service.findByName(name);
        return (employee.getId() == 0) ? new ResponseEntity<Employee>(NOT_FOUND) : new ResponseEntity<Employee>(employee, OK);
    }

    @RequestMapping(value = "/addEmployee", method = POST)
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        return (service.addEmployee(employee)) ? new ResponseEntity<Void>(CREATED) : new ResponseEntity<Void>(BAD_REQUEST);
    }

    @RequestMapping(value = "/updateEmployee/{id}", method = POST)
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        return (service.updateEmployee(id, employee)) ? new ResponseEntity<Void>(OK) : new ResponseEntity<Void>(NOT_FOUND);
    }

    @RequestMapping(value = "/deleteEmployee/{id}", method = POST)
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
        return (service.deleteEmployee(id)) ? new ResponseEntity<Void>(OK) : new ResponseEntity<Void>(NOT_FOUND);
    }
}
