package com.cognizant.greengov.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "admins")
public class Admin {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;
 
    @Column(name = "uname", nullable = false, unique = true, length = 100)
    private String uname;
 
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
 
    @Column(name = "role", nullable = false, length = 50)
    private String role = "ADMIN";
 
    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    public String getUname() { return uname; }
    public void setUname(String uname) { this.uname = uname; }
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}