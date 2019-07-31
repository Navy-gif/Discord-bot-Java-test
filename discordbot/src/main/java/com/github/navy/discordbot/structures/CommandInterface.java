package com.github.navy.discordbot.structures;

import java.util.Optional;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.framework.Client;

public interface CommandInterface {
	
	public Response call(Message message, String[] args, TextChannel channel, Server guild, Client client);

}
