package space.gatt.GattBot.utils;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;

/**
 * Created by Zach G on 06-Apr-16.
 */
public interface ICommand {

	@IMethod
	public static String command(DiscordAPI api, Message responseMessage, User user, String[] args){
		return "Default";
	}

}
