package com.github.navy.discordbot.framework.structures;

import org.javacord.api.entity.message.Message;

public interface CommandInterface {
	
	public void call(Message message, String[] args);

}
