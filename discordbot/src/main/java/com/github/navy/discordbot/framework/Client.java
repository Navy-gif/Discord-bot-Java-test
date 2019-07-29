package com.github.navy.discordbot.framework;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.json.JSONObject;

public class Client {

	public Client(JSONObject config) {
		
		System.out.println("Initiating client");
		DiscordApi api = new DiscordApiBuilder().setToken(config.getString("token")).login().join();
		System.out.println("Logged in.");
		
	}
	
}
