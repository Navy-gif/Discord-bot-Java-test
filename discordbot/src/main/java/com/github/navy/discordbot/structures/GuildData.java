package com.github.navy.discordbot.structures;

import org.javacord.api.entity.server.Server;

public class GuildData {
	
    private String guildID;
    private String guildOwnerID;

    public GuildData(Server server) {

        this.guildID = server.getIdAsString();
        this.guildOwnerID = server.getOwner().getIdAsString();
        
    }

    public String getGuildID() {
        return guildID;
    }

    public String getGuildOwnerID() {
        return guildOwnerID;
    }
    
}
