package com.github.navy.discordbot.structures;

import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class GuildData {
    private String guildID;
    private String guildName;
    private String guildOwnerID;

    public GuildData(Server server) {
        User owner = server.getOwner();

        this.guildID = String.valueOf(server.getId());
        this.guildName = server.getName();
        this.guildOwnerID = String.valueOf(owner.getId());
    }

    public String getGuildID() {
        return guildID;
    }

    public String getGuildName() {
        return guildName;
    }

    public String getGuildOwnerID() {
        return guildOwnerID;
    }
}
