package com.example.ltn_admin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
	private int id;
	private int detailId;
	private String username;
	private int role;
}
