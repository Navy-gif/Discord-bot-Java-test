package com.github.navy.discordbot.framework.structures;

import org.javacord.api.event.Event;
import org.javacord.api.listener.GloballyAttachableListener;

public interface Handler {

	/**
	 * @param event The incoming event to handle
	 */
	void handle(Event event);

	GloballyAttachableListener getListener();
	
}
