package space.gatt.GattBot;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zach G on 25-Mar-16.
 */
public class ConsoleLogger implements Runnable {

	@Override
	public void run() {
		String prevLine = "THIS IS THE STARTING LINE OR SOMETHING";
		while(true){
			String line = System.console().readLine();
			if (prevLine != line){
				Main.adminLogChannel.sendMessage("```" + line + "```");
				prevLine = line;
			}
		}
	}
}
