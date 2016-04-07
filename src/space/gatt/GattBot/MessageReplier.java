package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Zach on 6/03/2016.
 */
public class MessageReplier implements MessageCreateListener {

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {

        if (Main.afk.contains(message.getAuthor())) {
            Main.afk.remove(message.getAuthor());
            message.getAuthor().sendMessage("You're no longer AFK since you sent a message!");
        }

        if (message.getAuthor() != discordAPI.getYourself()) {
            for (User u : Main.afk) {
                if (message.getMentions().contains(u)) {
                    MessageBuilder builder = new MessageBuilder();
                    builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Hey! ").appendUser(u).append(" is currently AFK!");
                    message.reply(builder.build());
                }
            }
        }

        if (message.getMentions().contains(discordAPI.getYourself())){
            MessageBuilder builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" I'm Gasai Bot! Use `" + Settings.getCommandStarter() + "help` to get a list of all my commands!");
            message.getAuthor().sendMessage(builder.build());
        }
    }
}
