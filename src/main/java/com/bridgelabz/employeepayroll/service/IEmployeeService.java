package com.bridgelabz.employeepayroll.service;


import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.EmployeeModel;
import com.bridgelabz.employeepayroll.util.Response;

import java.util.List;
public interface IEmployeeService {

    EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId);

    EmployeeModel updateEmployee(long id, EmployeeDTO employeeDTO, Long departmentId,String token);

    List<EmployeeModel> getEmployee(String token);

    EmployeeModel deleteEmployee(Long id,String token);

    Response login(String email, String password);
}
