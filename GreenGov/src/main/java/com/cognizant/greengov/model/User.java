package com.cognizant.greengov.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;
 
    @Column(name = "uname", nullable = false, unique = true, length = 100)
    private String uname;
 
    @Column(name = "pass", nullable = false, length = 255)
    private String pass;
 
    @Column(name = "role", nullable = false, length = 50)
    private String role = "USER";
 
    @Column(name = "email", length = 150)
    private String email;
 
    public User() {}
    public User(String uname, String pass, String email, String role) {
        this.uname = uname; this.pass = pass; this.email = email; this.role = role;
    }
 
    public Integer getUid() { return uid; }
    public void setUid(Integer uid) { this.uid = uid; }
    public String getUname() { return uname; }
    public void setUname(String uname) { this.uname = uname; }
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}