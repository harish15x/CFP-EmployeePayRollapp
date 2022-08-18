package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayroll.model.EmployeeDepartment;
import com.bridgelabz.employeepayroll.model.EmployeeModel;
import com.bridgelabz.employeepayroll.repository.DepartmentRepository;
import com.bridgelabz.employeepayroll.repository.EmployeeRepository;
import com.bridgelabz.employeepayroll.util.Response;
import com.bridgelabz.employeepayroll.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    MailService mailService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId) {
        Optional<EmployeeDepartment> isDepartmentPresent = departmentRepository.findById(departmentId);
        EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
        if (isDepartmentPresent.isPresent()) {
            employeeModel.setEmployeeDepartment(isDepartmentPresent.get());
            employeeModel.setRegisterDate(LocalDateTime.now());
            employeeRepository.save(employeeModel);
            String body = "Employee is added successfully with employeeId " + employeeModel.getEmployeeId();
            String subject = "Employee registration successful";
            mailService.send(employeeModel.getEmailId(), subject, body);
            return employeeModel;
        }
        throw new EmployeeNotFoundException(400, "Department not present");
    }


    @Override
    public EmployeeModel updateEmployee(long id, EmployeeDTO employeeDTO, Long departmentId,String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployee = employeeRepository.findById(empId);
        if (isEmployee.isPresent()) {
            Optional<EmployeeDepartment> isDepartmentPresent = departmentRepository.findById(departmentId);
            if (isDepartmentPresent.isPresent()) {
                Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
                if (isEmployeePresent.isPresent()) {
                    isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
                    isEmployeePresent.get().setLastName(employeeDTO.getLastName());
                    isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
                    isEmployeePresent.get().setSalary(employeeDTO.getSalary());
                    isEmployeePresent.get().setUpdateDate(LocalDateTime.now());
                    employeeRepository.save(isEmployeePresent.get());
                    return isEmployeePresent.get();
                }
                throw new EmployeeNotFoundException(400, "Employee Not Present");
            }
            throw new EmployeeNotFoundException(400, "Department not present");
        }
        throw new EmployeeNotFoundException(400, "Token is wrong");
    }

    @Override
    public List<EmployeeModel> getEmployee(String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
        if (isEmployeePresent.isPresent()) {
            List<EmployeeModel> getallemployee = employeeRepository.findAll();
            if (getallemployee.size() > 0)
                return getallemployee;
            else
                throw new EmployeeNotFoundException(400, "No DATA Present");
        }
        throw new EmployeeNotFoundException(400, "Employee Not found");
    }

    @Override
    public EmployeeModel deleteEmployee(Long id,String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployee = employeeRepository.findById(empId);
        if (isEmployee.isPresent()) {
            Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
            if (isEmployeePresent.isPresent()) {
                employeeRepository.delete(isEmployeePresent.get());
                return isEmployeePresent.get();
            } else {
                throw new EmployeeNotFoundException(400, "Employee not Present");
            }
        }
        throw new EmployeeNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response login(String email, String password) {
        Optional<EmployeeModel> isEmailPresent = employeeRepository.findByEmailId(email);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getEmployeeId());
                return new Response(200, "login succesfull", token);
            }
            throw new EmployeeNotFoundException(400, "Invald credentials");
        }
        throw new EmployeeNotFoundException(400, "Employee not found");
    }


}
