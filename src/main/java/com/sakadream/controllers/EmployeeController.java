package com.sakadream.controllers;

import com.sakadream.models.Employee;
import com.sakadream.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Phan Ba Hai on 25/08/2017.
 */
@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @RequestMapping(value = "/employees", method = GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        return (employees.isEmpty()) ? new ResponseEntity<List<Employee>>(NO_CONTENT) : new ResponseEntity<List<Employee>>(employees, OK);
    }

    @RequestMapping(value = "/employees", params = "id", method = GET)
    public ResponseEntity<Employee> getById(@RequestParam int id) {
        Employee employee = service.findById(id);
        return (employee == null) ? new ResponseEntity<Employee>(NOT_FOUND) : new ResponseEntity<Employee>(employee, OK);
    }

    @RequestMapping(value = "/employee", params = "name", method = GET)
    public ResponseEntity<Employee> getByName(@RequestParam String name) {
        Employee employee = service.findByName(name);
        return (employee.getId() == 0) ? new ResponseEntity<Employee>(NOT_FOUND) : new ResponseEntity<Employee>(employee, OK);
    }

    @RequestMapping(value = "/addEmployee", method = POST)
    public ResponseEntity<Void> addEmployee(@RequestParam("token") String token, @RequestBody Employee employee) {
        if(!token.equals(System.getenv("APP_TOKEN")))
            return new ResponseEntity<Void>(BAD_REQUEST);
        return (service.addEmployee(employee)) ? new ResponseEntity<Void>(CREATED) : new ResponseEntity<Void>(BAD_REQUEST);
    }

    @RequestMapping(value = "/updateEmployee", method = PUT)
    public ResponseEntity<Void> updateEmployee(@RequestParam("id") int id, @RequestParam("token") String token, @RequestBody Employee employee) {
        if(!token.equals(System.getenv("APP_TOKEN")))
            return new ResponseEntity<Void>(BAD_REQUEST);
        return (service.updateEmployee(id, employee)) ? new ResponseEntity<Void>(OK) : new ResponseEntity<Void>(NOT_FOUND);
    }

    @RequestMapping(value = "/deleteEmployee", method = DELETE)
    public ResponseEntity<Void> deleteEmployee(@RequestParam("id") int id, @RequestParam("token") String token) {
        if(!token.equals(System.getenv("APP_TOKEN")))
            return new ResponseEntity<Void>(BAD_REQUEST);
        return (service.deleteEmployee(id)) ? new ResponseEntity<Void>(OK) : new ResponseEntity<Void>(NOT_FOUND);
    }
}
