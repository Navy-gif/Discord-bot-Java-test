package com.github.navy.discordbot.commands;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import com.github.navy.discordbot.framework.structures.Command;

public class Ping extends Command {

	public Ping() {
		
		super("ping", "misc");
		
	}
	
	public void call(Message message) {
		
		TextChannel channel = message.getChannel();
		if(channel.canYouWrite()) channel.sendMessage("Pong!");
		
	}

}
