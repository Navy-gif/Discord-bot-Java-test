package com.github.navy.discordbot.commands;

import com.github.navy.discordbot.framework.Client;
import com.github.navy.discordbot.structures.Command;
import com.github.navy.discordbot.structures.CommandInterface;
import com.github.navy.discordbot.structures.GuildData;
import com.github.navy.discordbot.structures.Response;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;

public class ServerData extends Command implements CommandInterface {

    public ServerData() {

        super("server", "misc");

    }

    @Override
    public Response call(Message message, String[] args, TextChannel channel, Server guild, Client client) {

        GuildData guildData = client.getGuildData(String.valueOf(guild.getId()));

        return new Response(String.format("ID: '%s' Name: '%s' OwnerID: '%s'", guildData.getGuildID(), guildData.getGuildOwnerID()));

    }
}
