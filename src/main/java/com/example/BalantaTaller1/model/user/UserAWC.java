package com.example.BalantaTaller1.model.user;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.BalantaTaller1.model.validation.CompleteInfoValidation;
import com.example.BalantaTaller1.model.validation.CredentialInfoValidation;
import com.example.BalantaTaller1.model.validation.PersonalInfoValidation;

/*import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/
import lombok.Data;

@Entity
@Table(name = "user_table")
@Data
public class UserAWC{
	
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
	
	@NotNull(groups= {PersonalInfoValidation.class, CompleteInfoValidation.class}, message= "Type can't be null")
	private UserType type;
}
