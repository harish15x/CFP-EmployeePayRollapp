package com.bridgelabz.employeepayroll.model;

import com.bridgelabz.employeepayroll.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "department")
public class EmployeeDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String departmentName;
    private String departmentDescription;
    private LocalDateTime createdTimeStamp;
    private LocalDateTime updatedTimeStamp;

    public EmployeeDepartment(DepartmentDTO departmentDTO) {
        this.departmentName = departmentDTO.getDepartmentName();
        this.departmentDescription = departmentDTO.getDepartmentDescription();
    }

    public EmployeeDepartment() {

    }
}
