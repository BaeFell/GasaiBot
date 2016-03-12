package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Zach on 6/03/2016.
 */
public class MessageReplier implements MessageCreateListener {

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {
        if (message.getContent().contains("(╯°□°）╯︵ ┻━┻")){
            MessageBuilder builder = new MessageBuilder();
            builder.append("┬─┬\uFEFF ノ( ゜-゜ノ)");
            message.reply(builder.build());
            return;
        }
        if (message.getMentions().contains(discordAPI.getYourself())){
            MessageBuilder builder = new MessageBuilder();
            builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" I'm GattBot! Use /help to get a list of all my commands!");
            message.getAuthor().sendMessage(builder.build());
        }
    }
}
