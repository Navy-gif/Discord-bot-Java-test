package com.github.navy.discordbot.framework.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javacord.api.entity.message.Message;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.CertainMessageEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.framework.structures.Command;

public class MessageHandler implements Handler {
	
	String name;
	Client client;
	boolean debug = true;
	String[] prefixes;
	
	public MessageHandler(Client client) {
		
		this.client = client;
		System.out.println("Initiating message handler.");
		this.name = "message_handler";
		
	}
	
	public void setPrefix() {
		
		prefixes = new String[] {client.getPrefix(), "<@"+client.client_user.getIdAsString()+">", "<@!"+client.client_user.getIdAsString()+">"};
		
	}

	public void handle(Event event) {

		System.out.println("Message came through");
		Message message = ((CertainMessageEvent) event).getMessage();
		String content = message.getContent();
		
		if(content.length() == 0) {
			if(debug) System.out.println("No message content.");
			return;
		}
		if(debug) System.out.println("Message content: " + content);
		//message.addReaction("😂");
		
		boolean found_pre = false;
		for(String prefix : prefixes) {
			if(content.startsWith(prefix)) {
				found_pre = true;
				content = content.replace(prefix, "").trim();
				break;
			}
		}
		
		if(!found_pre) return;
		
		if(debug) System.out.println("Content after prefix: " + content);
		
		String reg = "(\"[^\"]*\"|[^\"\\s]+)(\\s+|$)(?i)";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(content);
		ArrayList<String> args_al = new ArrayList<String>();
		
		while(matcher.find()) args_al.add(matcher.group().replace("\"",""));
		
		String command_str = args_al.get(0);
		args_al.remove(0);
		String[] args = args_al.toArray(new String[args_al.size()]);
		
		if(debug) System.out.println("Command: " + command_str + ", args: " + Arrays.toString(args));
		
		Command command = client.registry.getCommand(command_str);
		
		if(command == null) {
			if(debug) System.out.println("No command found for " + command_str);
			return;
		}
		
		command.call(message, args);
		
	}

	private class onMessageCreateListener implements MessageCreateListener {

		public void onMessageCreate(MessageCreateEvent event) {

			System.out.println("Should move to handler from here.");
			handle(event);
			
		}
		
	}

	public MessageCreateListener getListener() {
		
		return new onMessageCreateListener();
		
	}

}
