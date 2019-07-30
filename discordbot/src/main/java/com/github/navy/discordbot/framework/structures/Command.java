package com.github.navy.discordbot.framework.structures;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

public class Command {
	
	public String name;
	public String type;
	
	protected Command(String name, String type) {
	
		this.name = name;
		this.type = type;
		System.out.println("Loading " + this.name + " command");
		
	}
	
	public void call(Message message) {
		
		if(message == null) {
			System.out.println("Null message in command superclass.");
			return;
		}
		TextChannel channel = message.getChannel();
		if(channel.canYouWrite()) channel.sendMessage("This command apparently doesn't have a response yet. :)");
		
	}

}
