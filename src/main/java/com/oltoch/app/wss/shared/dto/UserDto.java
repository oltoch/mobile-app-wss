package com.oltoch.app.wss.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 595802215463245073L;

	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String token;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;


}

