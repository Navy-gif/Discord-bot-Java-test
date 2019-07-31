package com.github.navy.discordbot.commands;

import java.util.Optional;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.structures.Command;
import com.github.navy.discordbot.structures.CommandInterface;
import com.github.navy.discordbot.structures.Response;

public class Ping extends Command implements CommandInterface {

	public Ping() {
		
		super("ping", "misc");
		
	}
	
	public Response call(Message message, String[] args, TextChannel channel, Server guild) {
		
		return new Response("Pong!");
		
	}

}
