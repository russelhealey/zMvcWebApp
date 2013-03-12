package org.rmcmj.controller;

public class AuthenticationToken {
	private boolean authenticated;

	public AuthenticationToken(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

}
