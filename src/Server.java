import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {

	public static void main(String argv[]) throws Exception {
		
		System.out.println("Servidor iniciou, esperando por uma conexão na porta 6789.");


		ServerSocket serverSocket = new ServerSocket(6789);

		while (true) {
			Socket connectionSocket = serverSocket.accept();
			ServerActionsThread t = new ServerActionsThread(connectionSocket);
			t.start();
		}
	}
}