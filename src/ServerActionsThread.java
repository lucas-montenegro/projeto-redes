import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerActionsThread extends Thread {

	private Socket connectionSocket;
	private Protocol protocol = new Protocol();

	public ServerActionsThread(Socket s) {
		this.connectionSocket = s;
	}

	public void run() {
		System.out.println("Conexão iniciada com " + this.connectionSocket);
		String clientSentence, serverSentence = "", command;
		int taskListSize;
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

		while (connected && !connectionSocket.isClosed())
		{
			try {
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

				outToClient = new DataOutputStream(connectionSocket.getOutputStream());

				clientSentence = inFromClient.readLine();

				if (clientSentence == null)
					break;

				String[] request = clientSentence.split("#", 3);
				String response;

				command = request[0];

				if(routes.get(0).equals(command)) {
					response = protocol.add(request);

					if (response != null) {
						serverSentence = response;
					}
					else {
						serverSentence = protocol.ValidMessage();
						serverSentence += "#" + serverFunctionalities.AddTask(request[1], request[2]);
					}
				}
				else if(routes.get(1).equals(command)) {
					taskListSize = serverFunctionalities.GetTaskListSize();
					response = protocol.change(request, taskListSize);

					if (response != null) {
						serverSentence = response;
					}
					else {
						serverSentence = protocol.ValidMessage();
						serverSentence += "#" + serverFunctionalities.ChangeTaskPriority(request[1], request[2]);
					}
				}
				else if(routes.get(2).equals(command)) {
					taskListSize = serverFunctionalities.GetTaskListSize();
					response = protocol.remove(request, taskListSize);

					if (response != null) {
						serverSentence = response;
					}
					else {
						serverSentence = protocol.ValidMessage();
						serverSentence += "#" + serverFunctionalities.RemoveTask(request[1]);
					}

				}
				else if(routes.get(3).equals(command)) {
					serverSentence = protocol.ValidMessage();
					serverSentence += "#" + serverFunctionalities.ShowTaskList();
				}
				else if(routes.get(4).equals(command)) {
					connected = false; // ends connection
					serverSentence = protocol.ValidMessage();
					serverSentence += "#" + serverFunctionalities.Quit();
				}
				else {
					serverSentence = protocol.InvalidCommand();
				}

				outToClient.writeBytes(serverSentence + "\n");
			} catch (Exception e) {
				System.out.println("Cliente forçou o encerramento da conexão");
				connected = false;
				//e.printStackTrace();
			}
		}

		System.out.println("Conexão encerrada com " + this.connectionSocket);
	}
}
