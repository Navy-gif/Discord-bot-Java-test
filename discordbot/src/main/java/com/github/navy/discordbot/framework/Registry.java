package com.github.navy.discordbot.framework;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import com.github.navy.discordbot.structures.Command;
import com.github.navy.discordbot.structures.Handler;
import com.github.navy.discordbot.structures.HandlerInterface;

public class Registry {
	
	private Map<String, Command> commands;
	private Map<String, HandlerInterface> handlers;
	
	public Registry() {
		
		System.out.println("Loading command registry.");
		commands = new HashMap<String, Command>();
		handlers = new HashMap<String, HandlerInterface>();
		//loadCommands();
		
	}

	/**
	 * Dynamic command loading.
	 * All command classes are loaded in, new ones can simply be created as long as they are within the commands package
	 */
	void loadCommands() {

		//commands directory, all the command files are here
		File commands_dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\github\\navy\\discordbot\\commands");
		for(File command : commands_dir.listFiles()) {
			//System.out.println(command.getName());
			try {
				ClassLoader loader = URLClassLoader.newInstance(
						new URL[] {command.toURI().toURL()},
						getClass().getClassLoader()
				);
				
				//The command class
				Class<?> clazz = Class.forName("com.github.navy.discordbot.commands."+command.getName().replace(".java",""), true, loader); 
				Constructor<?> constructor = clazz.getConstructor(); //Class constructor
				Command cmd = (Command) constructor.newInstance(); //Command instance object
				
				//System.out.println(cmd.name);
				this.commands.put(cmd.name, cmd);
				
				//System.out.println(loader);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}

	public Command getCommand(String key) {
		return this.commands.get(key);
	}
	
	void loadHandlers(Client client) {
		
		File handlers_dir = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\github\\navy\\discordbot\\framework\\handlers");
		
		for(File handler : handlers_dir.listFiles()) {
			//System.out.println(command.getName());
			try {
				ClassLoader loader = URLClassLoader.newInstance(
						new URL[] {handler.toURI().toURL()},
						getClass().getClassLoader()
				);
				
				//The command class
				Class<?> clazz = Class.forName("com.github.navy.discordbot.framework.handlers."+handler.getName().replace(".java",""), true, loader); 
				Constructor<?> constructor = clazz.getConstructors()[0]; //Class constructor
				Handler hndlr = (Handler) constructor.newInstance(client); //Handler instance object
				
				this.handlers.put(hndlr.name, hndlr);
				
				//System.out.println(loader);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public HandlerInterface getHandler(String key) {
		return this.handlers.get(key);
	}
	
}
