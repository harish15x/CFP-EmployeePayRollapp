package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.DepartmentDTO;
import com.bridgelabz.employeepayroll.model.EmployeeDepartment;

import java.util.List;
public interface IDepartmentService {

    EmployeeDepartment addDepartment(DepartmentDTO departmentDTO);

    EmployeeDepartment updateDepartment(DepartmentDTO departmentDTO, Long id);

    List<EmployeeDepartment> getDepartments();

    EmployeeDepartment deletedepartment(Long id);
}
