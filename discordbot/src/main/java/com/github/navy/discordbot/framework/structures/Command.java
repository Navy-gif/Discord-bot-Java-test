package com.github.navy.discordbot.framework.structures;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

public class Command {
	
	String name, type;
	
	Command(String name, String type) {
	
		this.name = name;
		this.type = type;
		
	}
	
	public void call(Message message) {
		
		TextChannel channel = message.getChannel();
		if(channel.canYouWrite()) channel.sendMessage("This command apparently doesn't have a response yet. :)");
		
	}

}
