package com.github.navy.discordbot.framework.handlers;

import com.github.navy.discordbot.framework.Registry;
import com.github.navy.discordbot.framework.structures.Command;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.CertainMessageEvent;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageHandler implements Handler {
	
	String name;
	Registry registry;
	
	public MessageHandler(Registry registry) {
		
		System.out.println("Initiating message handler.");
		this.name = "message_handler";
		this.registry = registry;

	}

	public void handle(Event event) {

		System.out.println("Message came through");
		Message message = ((CertainMessageEvent) event).getMessage();

		Command command = registry.commands.get(message.getContent());

		if (command != null) command.call(message);
		
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
