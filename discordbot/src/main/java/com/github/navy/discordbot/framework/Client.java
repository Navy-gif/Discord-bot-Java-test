package com.github.navy.discordbot.framework;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.User;
import org.json.JSONObject;


public class Client {
	
	public DiscordApiBuilder preLogin;
	DiscordApi api;
	JSONObject config;
	public Registry registry;
	public User client_user;

	public Client(JSONObject config) {
		
		this.config = config;
		System.out.println("Initiating client");
		preLogin = new DiscordApiBuilder().setToken(config.getString("token"));//.login().join();
		registry = new Registry();
		registry.loadCommands();
		registry.loadHandlers(this);
		//setUpListeners();
		login();
		client_user = this.api.getYourself();
		registry.getHandler("command_handler").setPrefix();
		
	}
	
	/**
	 * After all the handlers & listeners have been loaded, log in to the api
	 */
	public void login() {
		
		System.out.println("Logging in.");
		if(api == null) api = preLogin.login().join();
		System.out.println("Logged in.");
		
	}
	
//			Moved to registry
//	private void setUpHandlers() {
//		
//		//System.out.println("Setting up handlers.");
//		//TODO dynamic handler loading
//		
//		//command_handler = new CommandHandler(this);
//		
//	}

//	private void setUpListeners() {
//
//		System.out.println("Setting up listeners.");
//		//TODO dynamic listener loading
//		
//		//preLogin.addMessageCreateListener((MessageCreateListener) command_handler.getListener());
//		
//	}

	public String getPrefix() {
		return this.config.getString("prefix");
	}
	
}
