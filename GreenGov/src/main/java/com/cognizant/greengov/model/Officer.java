package com.cognizant.greengov.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "officers")
public class Officer {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "officer_id")
    private Integer officerId;
 
    @Column(name = "uname", nullable = false, unique = true, length = 100)
    private String uname;
 
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
 
    @Column(name = "role", nullable = false, length = 50)
    private String role = "OFFICER";
 
    @Column(name = "department", length = 100)
    private String department;
 
    public Integer getOfficerId() { return officerId; }
    public void setOfficerId(Integer officerId) { this.officerId = officerId; }
    public String getUname() { return uname; }
    public void setUname(String uname) { this.uname = uname; }
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}