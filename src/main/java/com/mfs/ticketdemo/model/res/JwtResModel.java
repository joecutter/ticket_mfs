package com.mfs.ticketdemo.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResModel implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;

	private String username;
	private String phone;
	private String accessToken;
	private String type = "Bearer";
	private List<String> roles;

	public JwtResModel(String accessToken, List<String> roles, String username, String phone) {
		this.username = username;
		this.phone = phone;
		this.accessToken = accessToken;
		this.roles = roles;
	}
}
