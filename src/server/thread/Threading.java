package server.thread;

import java.io.*;
import java.net.Socket;

import communication.request.Request;
import communication.response.Response; 
import server.protocol.ProtocolInterface;

public class Threading extends Thread {
	private Socket socket;
	private ProtocolInterface protocol;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/**
	 * Threading's constructor.
	 * @param socket socket
	 * @param protocol communication server.protocol.
	 */
	public Threading(Socket socket, ProtocolInterface protocol) throws IOException {
		super();
		this.protocol = protocol; 
		this.socket = socket;
	}

	@Override
	public void run() {
        System.out.println("start thread client : " + socket.getLocalAddress() + ":" + socket.getLocalPort());
        boolean running = true;

        try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		while (running) {
			try {
				Request input = (Request) in.readObject();
				Response output = protocol.handleInput(input);

				if (output.getContent().equals("BYE")) {
				    running = false;
					close();
					break;
				}
				out.writeObject(output);
			} catch (IOException | ClassNotFoundException e) {
				try {
					close();
					running = false;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void close() throws IOException {
		out.close();
		in.close();
		socket.close();
	}
}
