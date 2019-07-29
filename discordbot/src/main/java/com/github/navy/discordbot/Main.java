package com.github.navy.discordbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {

	public static void main(String[] args) {

		DiscordApi api = new DiscordApiBuilder().setToken("MzEyODk1OTA1NDgzNDU2NTEz.XT9Vcw.XJJEdF_M1Y5ctL7M_9ALSzgBG4E").login().join();
		System.out.println("Logged in");

	}

}
