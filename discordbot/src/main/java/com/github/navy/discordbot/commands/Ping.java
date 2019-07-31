package com.github.navy.discordbot.commands;

import java.util.Date;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.structures.Command;
import com.github.navy.discordbot.structures.CommandInterface;
import com.github.navy.discordbot.structures.Response;

public class Ping extends Command implements CommandInterface {

	public Ping() {
		
		super("ping", "misc");
		
	}
	
	public Response call(Message message, String[] args, TextChannel channel, Server guild, Client client) {
		
		if(!channel.canYouWrite()) return null;
		
		long now = (new Date()).getTime();
		Message msg = channel.sendMessage("Pinging...").join();
		long end = (new Date()).getTime();
		msg.edit("**Pong!**\nTook " + (end-now) + "ms to complete.");
		
		return null;
		
	}

}
