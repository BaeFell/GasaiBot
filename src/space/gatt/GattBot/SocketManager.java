package space.gatt.GattBot;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Zach G on 06-Mar-16.
 */
public class SocketManager implements Runnable {

	@Override
	public void run() {
		try {
			int port = 1337;
			InetAddress thisIp = null;
			ServerSocket ss = new ServerSocket(port);

			System.out.println("*************************************");
			System.out.println("Socket Listener listens port: " + port);
			System.out.println("*************************************");

			while (true) {
				Socket s = ss.accept();
				String address = s.getRemoteSocketAddress().toString();
				System.out.println("new client has been detected:");
				System.out.println("Socket. Remote Address: " + address);

				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

				oos.writeObject("Request time: "+ new Date());
				oos.writeObject("Socket. Remote Address: " + address);
			}
		} catch (IOException ex) {
		}
	}
}
