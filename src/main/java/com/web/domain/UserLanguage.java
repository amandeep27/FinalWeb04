package com.web.domain;

import java.io.Serializable;

public class UserLanguage implements Serializable {

	private static final long serialVersionUID = -5354306806218124030L;

	private int langId;
	private String langType;
	private String country;

	public UserLanguage() {

	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + langId;
		result = prime * result + ((langType == null) ? 0 : langType.hashCode());
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
		UserLanguage other = (UserLanguage) obj;
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (langId != other.langId) {
			return false;
		}
		if (langType == null) {
			if (other.langType != null) {
				return false;
			}
		} else if (!langType.equals(other.langType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append(":");
		sb.append(" Lang Id = < " + this.langId);
		sb.append(" >, Lang Type = < " + this.langType);
		sb.append(" >, Country = < " + this.country + " >");
		return sb.toString();
	}

}
