package com.github.navy.discordbot.framework.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.CertainMessageEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.structures.Command;
import com.github.navy.discordbot.structures.Handler;
import com.github.navy.discordbot.structures.HandlerInterface;
import com.github.navy.discordbot.structures.Response;

public class CommandHandler extends Handler implements HandlerInterface {
	
	private boolean debug = true;
	private String[] prefixes;
	
	public CommandHandler(Client client) {
		
		super("command_handler", client);
		System.out.println("Initiating command handler.");
		setUpListener();
		
	}
	
	public void setPrefix() {
		
		prefixes = new String[] { client.getPrefix(), "<@"+client.client_user.getIdAsString()+">", "<@!"+client.client_user.getIdAsString()+">" };
		
	}

	/**
	 * Command & arg parser
	 * Takes in a Message event
	 */
	@Override
	public void handle(Event event) {

		if(debug) System.out.println("[Message] Message came through.");
		
		Message message = ((CertainMessageEvent) event).getMessage();
		
		if(message.getAuthor().isBotUser()) {
			if(debug) System.out.println("[Message] User is bot.");
			return;
		}
		
		String content = message.getContent().toLowerCase();
		TextChannel channel = message.getChannel();
		Server guild = message.getServer().isPresent() ? message.getServer().get() : null;
		
		if(content.length() == 0) {
			if(debug) System.out.println("[Message] No message content.");
			return;
		}
		
		if(debug) System.out.println("[Message] Message content: " + content);
		
		boolean found_pre = false;
		for(String prefix : prefixes) {
			if(content.startsWith(prefix)) {
				found_pre = true;
				content = content.replaceFirst(prefix, "").trim();
				break;
			}
		}
		
		if(!found_pre) return;
		
		if(debug) System.out.println("[Message] Content after prefix: " + content);
		
		String reg = "(\"[^\"]*\"|[^\"\\s]+)(\\s+|$)(?i)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(content);
		ArrayList<String> args_al = new ArrayList<String>();
		
		while(matcher.find()) args_al.add(matcher.group().replace("\"","").trim());
		
		String command_str = args_al.get(0);
		args_al.remove(0);
		String[] args = args_al.toArray(new String[args_al.size()]);

		//System.out.println("[Message] args: " + Arrays.toString(args));
		
		if(debug) System.out.println("[Message] Command: " + command_str + ", args: " + Arrays.toString(args));
		
		Command command = client.registry.getCommand(command_str);
		
		if(command == null) {
			if(debug) System.out.println("[Message] No command found for " + command_str);
			return;
		}
		
		Response response = command.call(message, args, channel, guild, client);
		
		if(response == null) return;
		if(!channel.canYouWrite()) {
			if(this.debug) System.out.println("Missing perms to send in channel."); 
			return;
		}
		
		if(response.em) message.getChannel().sendMessage(response.embed);
		else if(response.em && response.text != null) message.getChannel().sendMessage(response.text, response.embed);
		else message.getChannel().sendMessage(response.text);
		
	}

	private class onMessageCreateListener implements MessageCreateListener {

		public void onMessageCreate(MessageCreateEvent event) {

			if(debug) System.out.println("[Message] Should move to handler from here.");
			handle(event);
			
		}
		
	}

//	public MessageCreateListener getListener() {
//		
//		return new onMessageCreateListener();
//		
//	}

	@Override
	public void setUpListener() {

		client.preLogin.addMessageCreateListener(new onMessageCreateListener());
		
	}

}
