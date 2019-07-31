package com.github.navy.discordbot.framework.structures;

import org.javacord.api.event.Event;

import com.github.navy.discordbot.framework.Client;

public class Handler implements HandlerInterface {
	
	public String name;
	protected Client client;
	
	protected Handler(String name, Client client) {
		
		this.name = name;
		this.client = client;
		
	}

	public void handle(Event event) {
		// TODO Auto-generated method stub

	}

	public void setPrefix() {
		// TODO Auto-generated method stub

	}

	public void setUpListener() {
		// TODO Auto-generated method stub

	}

}
