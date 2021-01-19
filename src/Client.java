import java.io.*;
import java.net.*;

class Client {

	public static void main(String argv[]) throws Exception {
		String clientCommand, serverAnswer;
		boolean closeConnection = false;
	
		Socket clientSocket = new Socket("localhost", 6789);

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


		System.out.println("Cliente iniciado. Segue os formatos dos comandos:");
		System.out.println("ADD#Parametro1#Parametro2");
		System.out.println("CHANGE#Parametro1#Parametro2");
		System.out.println("REMOVE#Parametro1");
		System.out.println("SHOW");
		System.out.println("QUIT");
		

		while(!closeConnection) {
			System.out.println("Digite um comando:");
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			clientCommand = inFromUser.readLine();

			outToServer.writeBytes(clientCommand + '\n');

			serverAnswer = inFromServer.readLine();

			System.out.println("Resposta do servidor: " + serverAnswer);	
			
			if(serverAnswer.equals("CONNECTION CLOSED")) closeConnection = true;
		}

		clientSocket.close();
	}
}