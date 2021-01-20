import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

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

		ArrayList<String> routes = new ArrayList<String>();
		routes.add("ADD");
		routes.add("CHANGE");
		routes.add("REMOVE");
		routes.add("SHOW");

		while (true)
		{
			try {
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

				outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				clientSentence = inFromClient.readLine();

				String[] request = clientSentence.split("#", 2);

				if(routes.get(0).equals(request[0]))
					System.out.println("Método: " + request[0] + ", Parâmetros: " + request[1]);
				else if(routes.get(1).equals(request[0]))
					System.out.println("Método: " + request[0] + ", Parâmetros: " + request[1]);
				else if(routes.get(2).equals(request[0]))
					System.out.println("Método: " + request[0] + ", Parâmetros: " + request[1]);
				else if(routes.get(3).equals(request[0]))
					System.out.println("Método: " + request[0]);

				capitalizedSentence = clientSentence.toUpperCase() + '\n';

				outToClient.writeBytes(capitalizedSentence);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
