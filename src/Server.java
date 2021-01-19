import java.net.ServerSocket;
import java.net.Socket;

class Server {

	public static void main(String argv[]) throws Exception {
		
		System.out.println("SERVIDOR INICIOU, ESPERANDO CONEXÃO NA PORTA 6789!");
		
		ServerSocket welcomeSocket = new ServerSocket(6789);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			Thread t = new Thread(new ServerActionsThread(connectionSocket));
			t.start();
		}
	}
}