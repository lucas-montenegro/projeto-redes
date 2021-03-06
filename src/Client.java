import netscape.javascript.JSObject;

import java.io.*;
import java.net.*;


class Client {
	public static void main(String argv[]) throws Exception {
		Protocol protocol = new Protocol();
		String clientCommand, clientParameter1, clientParameter2, clientMesssage = "", serverResponse;
		boolean connected = true;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream outToServer = null;
		BufferedReader inFromServer = null;
		Socket clientSocket = null;

		try {
			clientSocket = new Socket("localhost", 7000);

			outToServer = new DataOutputStream(clientSocket.getOutputStream());

			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch (Exception e){
			connected = false;
			System.out.println("Impossível conectar a um servidor!");
		}


		while(connected) {
			// Manual about the functions
			System.out.println("Cliente iniciado. Segue os comandos:");
			System.out.println("ADD -> Adiciona uma tarefa com prioridade a lista de tarefas");
			System.out.println("CHANGE -> Altera a prioridade de uma tarefa da lista");
			System.out.println("REMOVE -> Remove uma tarefa da lsita");
			System.out.println("SHOW -> Mosta a lista de tarefas ordenadas de acordo com a prioridade");
			System.out.println("QUIT -> Desconecta do servidor");
			System.out.println("Digite um comando:");

			clientCommand = inFromUser.readLine();
			clientCommand = clientCommand.replace('#', '@');
			clientCommand = clientCommand.toUpperCase(); // changing to upper case just to padronize

			if(clientCommand.equals("ADD")) {
				System.out.println("Digite o nome da tarefa:");
				clientParameter1 = inFromUser.readLine();
				clientParameter1 = clientParameter1.replace('#', '@');

				System.out.println("Digite a prioridade da tarefa:");
				clientParameter2 = inFromUser.readLine();
				clientParameter2 = clientParameter2.replace('#', '@');

				clientMesssage = clientCommand + "#" + clientParameter1 + "#" + clientParameter2;
			}
			else if(clientCommand.equals("CHANGE")) {
				System.out.println("Digite a posição da tarefa a ser alterada na lista (utilize o comando SHOW para saber as posições):");
				clientParameter1 = inFromUser.readLine();
				clientParameter1 = clientParameter1.replace('#', '@');

				System.out.println("Digite a nova prioridade da tarefa escolhida:");
				clientParameter2 = inFromUser.readLine();
				clientParameter2 = clientParameter2.replace('#', '@');

				clientMesssage = clientCommand + "#" + clientParameter1 + "#" + clientParameter2;
			}
			else if(clientCommand.equals("REMOVE")) {
				System.out.println("Digite a posição da tarefa a ser removida na lista:");
				clientParameter1 = inFromUser.readLine();
				clientParameter1 = clientParameter1.replace('#', '@');

				clientMesssage = clientCommand + "#" + clientParameter1;
			}
			else if(clientCommand.equals("SHOW")) {
				clientMesssage = clientCommand;
			}
			else if(clientCommand.equals("QUIT")) {
				clientMesssage = clientCommand;
				connected = false;
			}
			else {
				clientMesssage = clientCommand;
			}

			try{
				outToServer.writeBytes(clientMesssage + '\n');

				serverResponse = inFromServer.readLine();

				serverResponse = protocol.TranslateResponseMessage(serverResponse);
			}catch(Exception e){
				System.out.println("Conexão com o servidor perdida!");
				break;
			}

			System.out.printf("Resposta do servidor:\n" + serverResponse);
		}

		if (clientSocket != null)
			clientSocket.close();

	}
}