package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name="user", uniqueConstraints = { //
        @UniqueConstraint(columnNames = "name") })
@Getter
@Setter
public class AppUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 50, nullable = false)	
    private String name;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;


    @Override 
    public String toString() {
    	return this.name + " " + this.isEnabled();
    }
	
}
