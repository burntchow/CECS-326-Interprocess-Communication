/**
 * An echo server listening on port 6007. This server reads from the client
 * and echoes back the result. 
 */

import java.net.*;
import java.io.*;

public class  EchoServer
{
    public static void main(String[] args) throws IOException{

        System.err.println("Connecting to Client");
        try{
            /* Connects to server to the client */
            ServerSocket serverSocket = new ServerSocket(6007);
            Socket clientSocket = serverSocket.accept();
            System.err.println("Client Connected");
            /* Lets client see output stream and reads text from input */

            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            /* Reads the client input and closes socket */
            String inputLine;
            while ((inputLine = in.readLine()) != null){
                System.out.println("Client input: " + inputLine);
                output.println(inputLine);
            }
            serverSocket.close();
        } 
        catch (IOException e){
            System.out.println("Exception caught when trying to listen on port 6017 or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}