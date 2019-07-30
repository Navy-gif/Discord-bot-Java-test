package com.github.navy.discordbot.framework;

import java.util.HashMap;
import java.util.Map;

import com.github.navy.discordbot.framework.structures.Command;

public class Registry {
	
	Map<String, Command> commands;
	
	public Registry() {
		
		System.out.println("Loading registry.");
		commands = new HashMap<String, Command>();
		
	}

}
