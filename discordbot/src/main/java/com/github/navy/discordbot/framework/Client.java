package com.github.navy.discordbot.framework;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.JSONObject;

import com.github.navy.discordbot.framework.handlers.CommandHandler;

public class Client {
	
	private DiscordApiBuilder preLogin;
	DiscordApi api;
	CommandHandler command_handler;
	JSONObject config;
	public Registry registry;
	public User client_user;

	public Client(JSONObject config) {
		
		this.config = config;
		System.out.println("Initiating client");
		preLogin = new DiscordApiBuilder().setToken(config.getString("token"));//.login().join();
		setUpHandlers();
		setUpListeners();
		registry = new Registry();
		login();
		client_user = this.api.getYourself();
		command_handler.setPrefix();
		
	}
	
	/**
	 * After all the handlers & listeners have been loaded, log in to the api
	 */
	public void login() {
		
		System.out.println("Logging in.");
		if(api == null) api = preLogin.login().join();
		System.out.println("Logged in.");
		
	}
	
	private void setUpHandlers() {
		
		System.out.println("Setting up handlers.");
		//TODO set up message handler, command handler etc
		
		command_handler = new CommandHandler(this);
		
	}

	private void setUpListeners() {

		System.out.println("Setting up listeners.");
		//TODO set up listeners that connect the events to handlers
		
		preLogin.addMessageCreateListener((MessageCreateListener) command_handler.getListener());
		
	}

	public String getPrefix() {
		return this.config.getString("prefix");
	}
	
}
