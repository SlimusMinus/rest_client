package org.example;

import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate template;

    private final String URL = "http://localhost:2808/api/employees";

    public List<Employee> showAllEmployees() {
        ResponseEntity<List<Employee>> response = template.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });
        List<Employee> allEmployees = response.getBody();
        return allEmployees;
    }

    public Employee getEmployee(int id) {
        Employee employee = template.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }

    public void saveOrUpdateEmpl(Employee employee) {

        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> response = template.postForEntity(URL, employee, String.class);
            System.out.println("New employee added is ");
            System.out.println(response.getBody());
        } else {
            template.put(URL, employee);
            System.out.println("Employee with " + id + " id is change");
        }

    }

    public void delEmployee(int id) {
        template.delete(URL + "/" + id);
        System.out.println("Employee with " + id + " id deleted");

    }
}
