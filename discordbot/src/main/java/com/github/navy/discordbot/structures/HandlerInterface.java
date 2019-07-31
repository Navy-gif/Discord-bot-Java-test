package com.github.navy.discordbot.structures;

import org.javacord.api.event.Event;

public interface HandlerInterface {

	/**
	 * @param event The incoming event to handle
	 */
	void handle(Event event);

	//GloballyAttachableListener getListener();

	void setPrefix();
	
	void setUpListener();
	
}
