package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    private EmployeeService employeeService;

    // constructor injection
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        // get the employees from the db
        List<Employee> theEmployees = employeeService.findAllOrderByLastname();
        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
    // create model attribute to bind form data
    Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        // save the employeee
        employeeService.save(theEmployee);
        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel ){
        // get the emlpoyee from the service
        Employee theEmployee = employeeService.findById(theId);
        // set employee in the model to pre populate the form
        theModel.addAttribute("employee",theEmployee);
        // send over to your from
        return"employees/employee-form" ;
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        // delete the employee
        employeeService.deleteById(theId);
        // redirect to the /employees/list
        return "redirect:/employees/list";
    }
}









