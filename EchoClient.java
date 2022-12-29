/**
 * An echo client. The client enters data to the server, and the
 * server echoes the data back to the client.
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient
{
	public static void main(String[] args) throws IOException{

		try{
			/* Connects to server to the client */
			Socket echoSocket = new Socket("127.0.0.1",6007);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in =  new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

			
			System.out.println("Server Connected\nInput anything and the server will echo\nType exit to close the connection");
			/* Uses scanner object to get client input */
			Scanner scanner = new Scanner(System.in);
			while(true){
				System.out.print("Client: ");
				String input = scanner.nextLine();
				if("exit".equalsIgnoreCase(input)){
					break;
				}
				out.println(input);
				String response = in.readLine();
				System.out.println("From server: " + response);
			}
			/* Closes socket */
			scanner.close();
			echoSocket.close();
		}

		catch (UnknownHostException e){
			System.err.println("Host wrong");
			System.exit(1);
		}
		catch (IOException e){
			System.err.println("Couldn't get I/O for the connection to host");
			System.exit(1);
		}
	}
}