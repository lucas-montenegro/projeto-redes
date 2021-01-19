import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerActionsThread implements Runnable {

	private Socket connectionSocket;

	public ServerActionsThread(Socket s) {
		this.connectionSocket = s;
	}

	public void run() {
		String clientSentence;
		String capitalizedSentence;

		BufferedReader inFromClient;
		DataOutputStream outToClient;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(
					connectionSocket.getInputStream()));

			outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());

			clientSentence = inFromClient.readLine();
			capitalizedSentence = clientSentence.toUpperCase() + '\n';

			outToClient.writeBytes(capitalizedSentence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
