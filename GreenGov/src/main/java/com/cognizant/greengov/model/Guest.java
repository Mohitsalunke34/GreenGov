package com.cognizant.greengov.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "guests")
public class Guest {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Integer guestId;
 
    @Column(name = "name", length = 100)
    private String name;
 
    @Column(name = "email", length = 150)
    private String email;
 
    public Integer getGuestId() { return guestId; }
    public void setGuestId(Integer guestId) { this.guestId = guestId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}