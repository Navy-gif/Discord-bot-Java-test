package com.github.navy.discordbot.commands;

import com.github.navy.discordbot.framework.structures.Command;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

public class EpicGamer extends Command {

    public EpicGamer() {
        super("epic", "misc");
    }

    @Override
    public void call(Message message) {
        if(message == null) {
            System.out.println("Null message");
            return;
        }

        TextChannel channel = message.getChannel();
        if(channel.canYouWrite()) channel.sendMessage("It is truly epic gamer moments right now!");
    }
}
