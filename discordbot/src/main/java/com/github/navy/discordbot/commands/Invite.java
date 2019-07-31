package com.github.navy.discordbot.commands;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;
import org.javacord.api.entity.server.Server;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.structures.Command;
import com.github.navy.discordbot.structures.CommandInterface;
import com.github.navy.discordbot.structures.Response;

public class Invite extends Command implements CommandInterface {

	public Invite() {
		
		super("invite", "misc");
		
	}
	
	public Response call(Message message, String[] args, TextChannel channel, Server guild, Client client) {
		
		if(args.length > 0) {
			if(args[0].equals("admin") || args[0].equals("administrator")) {
				return new Response("[Invite the bot using this link](" + client.api.createBotInvite((new PermissionsBuilder()).setAllowed(PermissionType.ADMINISTRATOR).build()) + ")", true);
			}
		} else {
			return new Response("[Invite the bot using this link](" + client.api.createBotInvite() + ")", true);
		}
		return null; //my IDE was screaming at me to add this
		
	}

}
