import netscape.javascript.JSObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {

	public static void main(String argv[]) throws Exception {
		String clientCommand, clientParameter1, clientParameter2, clientMesssage = "", serverAnswer;
		boolean connected = true;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
		Socket clientSocket = new Socket("localhost", 6789);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// Manual about the functions
		System.out.println("Cliente iniciado. Segue os comandos:");
		System.out.println("ADD -> Adiciona uma tarefa com prioridade a lista de tarefas");
		System.out.println("CHANGE -> Altera a prioridade de uma tarefa da lista");
		System.out.println("REMOVE -> Remove uma tarefa da lsita");
		System.out.println("SHOW -> Mosta a lista de tarefas ordenadas de acordo com a prioridade");
		System.out.println("QUIT -> Desconecta do servidor");

		//Scanner scanner = new Scanner(System.in); ?

		while(connected) {
			System.out.println("Digite um comando:");
			clientCommand = inFromUser.readLine();
			clientCommand = clientCommand.toUpperCase(); // changing to upper case just to padronize

			if(clientCommand.equals("ADD")) {
				System.out.println("Digite o nome da tarefa (sem #):");
				clientParameter1 = inFromUser.readLine();

				System.out.println("Digite a prioridade da tarefa (apenas números inteiros):");
				clientParameter2 = inFromUser.readLine();

				clientMesssage = clientCommand + "#" + clientParameter1 + "#" + clientParameter2;
			}
			else if(clientCommand.equals("CHANGE")) {
				System.out.println("Digite a posição da tarefa a ser alterada na lista:");
				clientParameter1 = inFromUser.readLine();

				System.out.println("Digite a nova prioridade da tarefa escolhida");
				clientParameter2 = inFromUser.readLine();

				clientMesssage = clientCommand + "#" + clientParameter1 + "#" + clientParameter2;
			}
			else if(clientCommand.equals("REMOVE")) {
				System.out.println("Digite a posição da tarefa a ser removida na lista:");
				clientParameter1 = inFromUser.readLine();

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

			outToServer.writeBytes(clientMesssage + '\n');

			serverAnswer = inFromServer.readLine();

			System.out.println("Resposta do servidor: " + serverAnswer);	
		}

		clientSocket.close();
	}
}