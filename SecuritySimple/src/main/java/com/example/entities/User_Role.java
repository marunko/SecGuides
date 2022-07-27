package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_role")
//@AllArgsConstructor
@Getter
@Setter
public class User_Role {

	  	@Id
	  	@GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name = "Id", nullable = false)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "User_Id", nullable = false)
	    private AppUser appUser;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "Role_Id", nullable = false)
	    private Role appRole;
	
	    @Override
	    public String toString() {
	    	return this.appUser.getName() +"  --->>>  "+ this.appRole.getName();
	    }
}
