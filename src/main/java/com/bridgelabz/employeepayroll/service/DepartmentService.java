package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.DepartmentDTO;
import com.bridgelabz.employeepayroll.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayroll.model.EmployeeDepartment;
import com.bridgelabz.employeepayroll.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public EmployeeDepartment addDepartment(DepartmentDTO departmentDTO) {
        EmployeeDepartment employeeDepartment = new EmployeeDepartment();
        employeeDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        employeeDepartment.setDepartmentDescription(departmentDTO.getDepartmentDescription());
        employeeDepartment.setCreatedTimeStamp(LocalDateTime.now());
        departmentRepository.save(employeeDepartment);
        return employeeDepartment;
    }
    @Override
    public EmployeeDepartment updateDepartment(DepartmentDTO departmentDTO, Long id) {
        Optional<EmployeeDepartment> isDepartmentPresent = departmentRepository.findById(id);
        if (isDepartmentPresent.isPresent()) {
            isDepartmentPresent.get().setDepartmentName(departmentDTO.getDepartmentName());
            isDepartmentPresent.get().setDepartmentDescription(departmentDTO.getDepartmentDescription());

            isDepartmentPresent.get().setUpdatedTimeStamp(LocalDateTime.now());
            departmentRepository.save(isDepartmentPresent.get());
            return isDepartmentPresent.get();
        }
        throw new EmployeeNotFoundException(400, "Department  not found");
    }

    @Override
    public List<EmployeeDepartment> getDepartments()  {
        List<EmployeeDepartment> isDepartmentPresent = departmentRepository.findAll();
        if (isDepartmentPresent.size() > 0)
            return isDepartmentPresent;
        else
            throw new EmployeeNotFoundException(400, "No department found");
    }

    @Override
    public EmployeeDepartment deletedepartment(Long id) {
        Optional<EmployeeDepartment> isDepartmentPresent = departmentRepository.findById(id);
        if (isDepartmentPresent.isPresent()) {
            departmentRepository.delete(isDepartmentPresent.get());
            return isDepartmentPresent.get();
        }
        throw new EmployeeNotFoundException(400, "Department not found");
    }
}
