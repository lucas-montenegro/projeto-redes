import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerActionsThread extends Thread {

	private Socket connectionSocket;
	private Protocol protocol;

	public ServerActionsThread(Socket s) {
		this.connectionSocket = s;
	}

	public void run() {
		System.out.println("Conexão iniciada com " + this.connectionSocket);
		String clientSentence, serverSentence = "", command;
		boolean connected = true;

		ServerFunctionalities serverFunctionalities = new ServerFunctionalities();

		BufferedReader inFromClient;
		DataOutputStream outToClient;

		ArrayList<String> routes = new ArrayList<String>();
		routes.add("ADD");
		routes.add("CHANGE");
		routes.add("REMOVE");
		routes.add("SHOW");
		routes.add("QUIT");

		while (connected)
		{
			try {
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

				outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				clientSentence = inFromClient.readLine();

				String[] request = clientSentence.split("#", 3);
				String response;

				command = request[0];

				if(routes.get(0).equals(command)) {
					response = protocol.add(request);

					if (response != null)
						serverSentence = response;
					else
						serverSentence = serverFunctionalities.AddTask(request[1], request[2]);
				}
				else if(routes.get(1).equals(command)) {
					response = protocol.change(request);

					if (response != null)
						serverSentence = response;
					else 
						serverSentence = serverFunctionalities.ChangeTaskPriority(request[1], request[2]);
				}
				else if(routes.get(2).equals(command)) {
					response = protocol.remove(request);

					if (response != null)
						serverSentence = response;
					else
						serverSentence = serverFunctionalities.RemoveTask(request[1]);

				}
				else if(routes.get(3).equals(command)) {
					response = null; // TODO protocol.change(request);

					if (response != null)
						serverSentence = response;
					else
						serverSentence = serverFunctionalities.ShowTaskList();
				}
				else if(routes.get(4).equals(command)) {
					response = null; // TODO protocol.change(request);
					connected = false; // ends connection
					
					if (response != null)
						serverSentence = response;
					else
						serverSentence = serverFunctionalities.Quit();
				}
				else {
					// TODO response = protocol.change(request);
				}

				outToClient.writeBytes(serverSentence + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Conexão encerrada com " + this.connectionSocket);
	}

}
