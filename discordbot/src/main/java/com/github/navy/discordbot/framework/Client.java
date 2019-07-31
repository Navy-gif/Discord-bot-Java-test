package com.github.navy.discordbot.framework;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.JSONObject;

import com.github.navy.discordbot.framework.handlers.Handler;
import com.github.navy.discordbot.framework.handlers.MessageHandler;

public class Client {
	
	DiscordApiBuilder preLogin;
	DiscordApi api;
	Handler message_handler;
	JSONObject config;
	Registry registry;

	public Client(JSONObject config) {
		
		this.config = config;
		System.out.println("Initiating client");
		preLogin = new DiscordApiBuilder().setToken(config.getString("token"));//.login().join();
		registry = new Registry();
		setUpHandlers();
		setUpListeners();

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
		
		message_handler = new MessageHandler(registry);
		
	}

	private void setUpListeners() {

		System.out.println("Setting up listeners.");
		//TODO set up listeners that connect the events to handlers
		
		preLogin.addMessageCreateListener((MessageCreateListener) message_handler.getListener());
		
	}
	
}
