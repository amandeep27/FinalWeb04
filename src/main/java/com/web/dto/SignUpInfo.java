package com.web.dto;

import java.io.Serializable;

public class SignUpInfo implements Serializable {

	private static final long serialVersionUID = 3404772639167097672L;

	private int userId;
	private String userName;
	private String password;
	private String repeatPassword;
	private String userRole;
	private String language;

	public SignUpInfo() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((repeatPassword == null) ? 0 : repeatPassword.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SignUpInfo other = (SignUpInfo) obj;
		if (language == null) {
			if (other.language != null) {
				return false;
			}
		} else if (!language.equals(other.language)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (repeatPassword == null) {
			if (other.repeatPassword != null) {
				return false;
			}
		} else if (!repeatPassword.equals(other.repeatPassword)) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (userRole == null) {
			if (other.userRole != null) {
				return false;
			}
		} else if (!userRole.equals(other.userRole)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append(":");
		sb.append(" User Id = < " + this.userId);
		sb.append(" >, Name = < " + this.userName);
		sb.append(" >, Role = < " + this.userRole);
		sb.append(" >, Password = < " + this.password);
		sb.append(" >, Repeat Password = < " + this.repeatPassword);
		sb.append(" >, Language = < " + this.language + " >");
		return sb.toString();
	}

}
