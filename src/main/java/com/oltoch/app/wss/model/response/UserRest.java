package com.oltoch.app.wss.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRest {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String token;
}

