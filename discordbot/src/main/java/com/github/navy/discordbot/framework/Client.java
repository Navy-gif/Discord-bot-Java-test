package com.github.navy.discordbot.framework;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.json.JSONObject;

public class Client {
	
	DiscordApiBuilder preLogin;
	DiscordApi api;

	public Client(JSONObject config) {
		
		System.out.println("Initiating client");
		preLogin = new DiscordApiBuilder().setToken(config.getString("token"));//.login().join();
		
		
	}
	
	/**
	 * After all the listeners have been added, log in to the api
	 */
	private void login() {
		
		System.out.println("Logging in.");
		if(api != null) api = preLogin.login().join();
		System.out.println("Logged in.");
		
	}
	
	public void setUpHandlers() {
		
		//TODO set up message handler, command handler etc
		
	}

	public void setUpListeners() {

		//TODO set up listeners that connect the events to handlers
		
	}
	
}
