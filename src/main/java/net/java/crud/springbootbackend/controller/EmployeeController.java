package net.java.crud.springbootbackend.controller;

import net.java.crud.springbootbackend.exception.ResourceNotFoundException;
import net.java.crud.springbootbackend.model.Employee;
import net.java.crud.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired

    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();

    }

    // build create employee REST API

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);

    }

    // build get employee by id REST API

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {

        Employee employee = employeeRepository.findById(id).
                orElseThrow( () -> new ResourceNotFoundException("Employee with id " + id + " does not exist"));
        return ResponseEntity.ok(employee);

    }

    // build update employee REST API

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {

        Employee updateEmployee = employeeRepository.findById(id).
                orElseThrow( () -> new ResourceNotFoundException("Employee with id " + id + " does not exist") );

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);

    }

    // build delete employee REST API

    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Employee with id " + id + " does not exist" ) );

        employeeRepository.delete(employee);

        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);

    }

}
