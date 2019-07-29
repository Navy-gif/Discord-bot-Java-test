package com.github.navy.discordbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

	public static void main(String[] args) {

		DiscordApi api = new DiscordApiBuilder().setToken("").login().join();
		System.out.println("Logged in");

	}

}
