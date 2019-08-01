package com.github.navy.discordbot.framework;

import com.github.navy.discordbot.framework.handlers.CommandHandler;
import com.github.navy.discordbot.framework.handlers.GuildHandler;
import com.github.navy.discordbot.structures.GuildData;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Client {
	
	public DiscordApiBuilder preLogin;
	public DiscordApi api;
	JSONObject config;
	public Registry registry;
	public User client_user;
	private Map<String, GuildData> DataStore;

	public Client(JSONObject config) {

		System.out.println("Initiating client");
		this.config = config;
		DataStore = new HashMap<String, GuildData>();
		preLogin = new DiscordApiBuilder().setToken(config.getString("token"));//.login().join();
		registry = new Registry();
		registry.loadCommands();
		registry.loadHandlers(this);
		//setUpListeners();
		login();
		client_user = this.api.getYourself();
		
		((CommandHandler) registry.getHandler("command_handler")).setPrefix();
		((GuildHandler) registry.getHandler("guild_handler")).setUpGuildData();
		
	}
	
	/**
	 * After all the handlers & listeners have been loaded, log in to the api
	 */
	public void login() {
		
		System.out.println("Logging in.");
		if(api == null) api = preLogin.login().join();
		System.out.println("Logged in.");
		
	}

	public String getPrefix() {
		return this.config.getString("prefix");
	}

	public void addGuildData(Server server) {
		DataStore.put(server.getIdAsString(), new GuildData(server));
	}

	public GuildData getGuildData(String id) {
		return DataStore.get(id);
	}
}
