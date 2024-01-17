package org.example;


import org.example.configuration.MyConfig;
import org.example.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        //Get all employees
        List<Employee> allEmps = communication.showAllEmployees();
        for(Employee item : allEmps)
            System.out.println(item);

        //Get emp by id
        Employee employee = communication.getEmployee(4);
        System.out.println(employee);

        //Save ar update employee
        Employee employee2 = new Employee("Sveta", "Sokolova", "HR", 1200);
        employee.setId(8);
        communication.saveOrUpdateEmpl(employee2);

        //delete employee
        communication.delEmployee(5);



    }
}
