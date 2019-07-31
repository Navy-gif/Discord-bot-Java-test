package com.github.navy.discordbot.structures;

import java.awt.Color;

import org.javacord.api.entity.message.embed.EmbedBuilder;

public class Response {

	public String text;
	public boolean em = false;
	public EmbedBuilder embed = null;
	
	public Response(String text) {
		
		this.text = text;
		
	}
	
	public Response(String text, boolean embed) {
		
		this.text = text;
		this.em = embed;
		this.embed = new EmbedBuilder();
		this.embed.setColor(Color.GREEN);
		this.embed.setDescription(text);
		
	}
	
	public Response(String text, EmbedBuilder embed) {
		
		this.text = text;
		this.em = true;
		this.embed = embed;
		
	}
	
	public Response(EmbedBuilder embed) {
		
		this.em = true;
		this.embed = embed;
		
	}

}
