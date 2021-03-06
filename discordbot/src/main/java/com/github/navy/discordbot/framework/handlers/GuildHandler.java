package com.github.navy.discordbot.framework.handlers;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.structures.Handler;
import com.github.navy.discordbot.structures.HandlerInterface;

import org.javacord.api.entity.server.Server;
import org.javacord.api.event.Event;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;

import java.util.Collection;

public class GuildHandler extends Handler implements HandlerInterface {

    private boolean debug = true;
    private String debugPrefix = "[GuildHandler] ";

    public GuildHandler(Client client) {

        super("guild_handler", client);
        System.out.println("Initiating guild handler.");
        setUpListener();

    }

    @Override
    public void handle(Event event) {

        if(debug) System.out.println(debugPrefix + "Guild join came through.");
        Server server = ((ServerJoinEvent) event).getServer();

        client.addGuildData(server);

    }

    private class onServerJoinListener implements ServerJoinListener {

        public void onServerJoin(ServerJoinEvent event) {

            if(debug) System.out.println(debugPrefix + "Should move to handler from here.");
            handle(event);

        }
    }

    @Override
    public void setUpListener() {

        client.preLogin.addServerJoinListener(new onServerJoinListener());

    }

    public void setUpGuildData() {
        for (Server server : client.api.getServers()) {

            client.addGuildData(server);

        }
    }
}
