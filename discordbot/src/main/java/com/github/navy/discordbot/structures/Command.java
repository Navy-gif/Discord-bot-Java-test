package com.github.navy.discordbot.structures;

import java.util.Optional;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.framework.Client;

public class Command implements CommandInterface {
	
	public String name;
	public String type;
	
	protected Command(String name, String type) {
	
		this.name = name;
		this.type = type;
		System.out.println("Loading " + this.name + " command.");
		
	}

	public Response call(Message message, String[] args, TextChannel channel, Server guild, Client client) {

		if(message == null) {
			System.out.println("Null message in command superclass.");
			return null;
		}
		
		return new Response("This command apparently doesn't have a response yet. :)");
		
	}

}
