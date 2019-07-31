package com.github.navy.discordbot.structures;

public class Response {

	String text;
	boolean embed = false;
	
	public Response(String text) {
		
		this.text = text;
		
	}
	
	public Response(String text, boolean embed) {
		
		this.text = text;
		this.embed = embed;
		
	}

}
