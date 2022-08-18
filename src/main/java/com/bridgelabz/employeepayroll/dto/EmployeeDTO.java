package com.bridgelabz.employeepayroll.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO {

    @NotNull
    private String firstName;
    private String lastName;
    private String companyName;
    private long salary;
    private String emailId;
    private String password;

}
