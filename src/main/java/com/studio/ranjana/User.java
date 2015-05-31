package com.studio.ranjana;


public class User {
	private String login;
	private long id;
	
	public User () {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		User other = (User) obj;
		if (id != other.id)  {
			return false;
		}	
		return true;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", id=" + id + "]";
	}	
		
}
