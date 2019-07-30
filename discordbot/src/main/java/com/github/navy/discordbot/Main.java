package com.github.navy.discordbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;
import com.github.navy.discordbot.framework.Client;

public class Main {
	
	static JSONObject config;

	/**
	 * Starts the entire bot
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Starting bot");
		config = getConfig();
		if(config == null) System.exit(0);
		
		Client client = new Client(config);
		client.login();
		
		//Console input for basic console commands
		BufferedReader input = new BufferedReader (new InputStreamReader(System.in));
		try {
			// TODO everything
			String in;
			while((in = input.readLine()) != null)
			System.out.println(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static JSONObject getConfig() {
		
		try {
			
			File file = new File("config.json");
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String rawFile = "", line;
			while((line = reader.readLine()) != null) rawFile += line;
			JSONObject obj = new JSONObject(rawFile);
			
			return obj;
			
		} catch(FileNotFoundException ex) {
			System.out.println("Couldn't find file.");
			return null;
		} catch (IOException e) {
			System.out.println("Failed to read file.");
			return null;
		}
		
	}

}
