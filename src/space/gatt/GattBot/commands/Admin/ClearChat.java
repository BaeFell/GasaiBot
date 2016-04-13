package space.gatt.GattBot.commands.Admin;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageBuilder;
import de.btobastian.javacord.entities.message.MessageDecoration;
import space.gatt.GattBot.Main;
import space.gatt.GattBot.Settings;
import space.gatt.GattBot.utils.*;

import java.util.concurrent.ExecutionException;

/**
 * Created by Zach G on 06-Apr-16.
 */

@Command("clearchat")
@Syntax("clearchat [-silent]")
@Usage("clearchat [-silent]")
@Permissions(ranks = "Bot Commander")
@Description("Clears the channel of the last 1000 messages.")
@Group("Moderation")
@CommandSettings(deleteInitatingMsg = true, sendResponseViaPM = false)
public class ClearChat {
	@IMethod
	public static String command(DiscordAPI api, Message message, User user, String[] args) {
		MessageBuilder builder = new MessageBuilder();
		try {
			for (Message m : message.getChannelReceiver().getMessageHistory(1000).get().getMessagesSorted()) {
				m.delete();
			}
		} catch (ExecutionException | InterruptedException e) {

			builder = new MessageBuilder();
			builder.append("(OH NO! SOMETHING HAPPENED. PRINTING STACK TRACE!)").append(e.getStackTrace().toString());
			Main.adminLogChannel.sendMessage(builder.build());
		}
		if (args.length > 1) {
			if (args[0].equalsIgnoreCase("-silent")) {
			} else {
				builder = new MessageBuilder();
				builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Attempted to clear up to 1000 messages in this channel! (That's all the API will allow at the one time)").appendNewLine().appendDecoration(MessageDecoration.BOLD, "If no messages were removed, that means I don't have the right permissions!");
			}
		} else {
			builder = new MessageBuilder();
			builder.append(Settings.getMsgStarter()).appendUser(message.getAuthor()).append(" Attempted to clear up to 1000 messages in this channel! (That's all the API will allow at the one time)").appendNewLine().appendDecoration(MessageDecoration.BOLD, "If no messages were removed, that means I don't have the right permissions!");
		}

		return builder.build();
	}

}
