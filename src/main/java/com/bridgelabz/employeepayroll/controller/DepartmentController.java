package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.DepartmentDTO;
import com.bridgelabz.employeepayroll.model.EmployeeDepartment;
import com.bridgelabz.employeepayroll.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    IDepartmentService departmentService;

    @PostMapping("/adddepartment")
    public EmployeeDepartment adddepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartment(departmentDTO);
    }

    @PutMapping("/updatedepartment/{id}")
    public EmployeeDepartment updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable Long id) {
        return departmentService.updateDepartment(departmentDTO, id);
    }

    @GetMapping("/getdepartments")
    public List<EmployeeDepartment> getDepartments() {
        return departmentService.getDepartments();
    }

    @DeleteMapping("/deletedepartment/{id}")
    public EmployeeDepartment deletedepartment(@PathVariable Long id) {
        return departmentService.deletedepartment(id);
    }
}
