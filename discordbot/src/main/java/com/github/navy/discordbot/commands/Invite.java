package com.github.navy.discordbot.commands;

import java.util.Optional;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.framework.structures.Command;
import com.github.navy.discordbot.framework.structures.CommandInterface;

public class Invite extends Command implements CommandInterface {

	public Invite() {
		
		super("invite", "misc");
		
	}
	
	public void call(Message message, String[] args, TextChannel channel, Optional<Server> guild, Client client) {
		
		if(!channel.canYouWrite()) return;
		if(args.length > 0) {
			if(args[0].equals("admin") || args[0].equals("administrator")) {
				channel.sendMessage("<" + client.api.createBotInvite((new PermissionsBuilder()).setAllowed(PermissionType.ADMINISTRATOR).build()) + ">");
			}
		}
		else channel.sendMessage("<" + client.api.createBotInvite() + ">");
		
	}

}
