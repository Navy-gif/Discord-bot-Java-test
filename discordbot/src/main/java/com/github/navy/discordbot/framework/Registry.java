package com.github.navy.discordbot.framework;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import com.github.navy.discordbot.framework.structures.Command;

public class Registry {
	
	Map<String, Command> commands;
	
	public Registry() {
		
		System.out.println("Loading command registry.");
		commands = new HashMap<String, Command>();
		loadCommands();
		
	}

	/**
	 * Dynamic command loading.
	 * All command classes are loaded in, new ones can simply be created as long as they are within the commands package
	 */
	private void loadCommands() {

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
				//cmd.call(null);
				
				this.commands.put(cmd.name, cmd);
				
				//System.out.println(loader);
			} catch (MalformedURLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
