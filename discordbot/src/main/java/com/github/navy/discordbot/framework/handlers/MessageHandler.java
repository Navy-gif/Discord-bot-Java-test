package com.github.navy.discordbot.framework.handlers;

import org.javacord.api.event.Event;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageHandler implements Handler {
	
	String name;
	
	public MessageHandler() {
		
		System.out.println("Initiating message handler.");
		this.name = "message_handler";
		
	}

	public void handle(Event event) {

		System.out.println("Message came through");
		
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
