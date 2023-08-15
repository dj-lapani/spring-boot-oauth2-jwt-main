package com.dj.dto;

import com.dj.validator.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatches
public class SignUpRequest {

	@NotEmpty
	private String customerName;

	@NotEmpty
	private String email;

	@Size(min = 6, message = "Minimum 6 chars required")
	private String password;

	@NotEmpty
	private String matchingPassword;

	public SignUpRequest(String customerName, String email, String password, String matchingPassword) {
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
	}
}
