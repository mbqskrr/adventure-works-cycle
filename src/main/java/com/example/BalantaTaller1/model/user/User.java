package com.example.BalantaTaller1.model.user;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.BalantaTaller1.model.validation.CompleteInfoValidation;
import com.example.BalantaTaller1.model.validation.CredentialInfoValidation;
import com.example.BalantaTaller1.model.validation.PersonalInfoValidation;

import lombok.Data;

@Entity
@Table(name = "user_table")
@Data
public class User{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(groups = CredentialInfoValidation.class)
	@Size(min = 3, groups = { CredentialInfoValidation.class}, message="User must be at least 3 characters")
	private String username;
	
	@NotNull(groups = CompleteInfoValidation.class, message = "Size must be 8 characters")
	@Size(min = 8, groups = { CredentialInfoValidation.class })
	private String password;
	
	@Transient
	@NotNull(groups = CompleteInfoValidation.class, message = "Passwords doesn't match")
	private String repeatPassword;
	
	@Transient
	@NotNull(groups= {PersonalInfoValidation.class, CompleteInfoValidation.class}, message= "Type can't be null")
	private UserType type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
}
