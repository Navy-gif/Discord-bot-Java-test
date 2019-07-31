package com.github.navy.discordbot.framework.structures;

import java.util.Optional;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.framework.Client;

public interface CommandInterface {
	
	public void call(Message message, String[] args, TextChannel channel, Optional<Server> guild, Client client);

}
