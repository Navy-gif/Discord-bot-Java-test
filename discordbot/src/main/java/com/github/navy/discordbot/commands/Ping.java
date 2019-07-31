package com.github.navy.discordbot.commands;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import com.github.navy.discordbot.framework.structures.Command;
import com.github.navy.discordbot.framework.structures.CommandInterface;

public class Ping extends Command implements CommandInterface {

	public Ping() {
		
		super("ping", "misc");
		
	}
	
	public void call(Message message, String[] args) {
		
		TextChannel channel = message.getChannel();
		if(channel.canYouWrite()) channel.sendMessage("Pong!");
		
	}

}
