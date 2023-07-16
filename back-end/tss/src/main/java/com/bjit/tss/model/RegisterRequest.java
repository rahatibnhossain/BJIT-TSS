package com.bjit.tss.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String contactNumber;
    private String degreeName;
    private String educationalInstitute;
    private Float cgpa;
    private Integer passingYear;
    private String presentAddress;
    private String photoUrl;
    private String resumeUrl;
}