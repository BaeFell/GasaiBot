package space.gatt.GattBot;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/**
 * Created by Zach G on 12-Mar-16.
 */
public class PersonalMessageReplier implements MessageCreateListener {

	@Override
	public void onMessageCreate(DiscordAPI discordAPI, Message message) {

		if (message.isPrivateMessage()){


		}
	}
}
