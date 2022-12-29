/**
 * Quote server listening to port 6017.
 *
 */
import java.util.*;
import java.net.*;
import java.io.*; 

public class QuoteServer
{
  public static void main(String[] args){
    try{
      ServerSocket sock = new ServerSocket(6017);
    
      /* now listen for connections */
      System.out.println("Listening for connections. . .");
      while(true){
        
        Socket client = sock.accept();
        System.out.println("Client is connected. . .");
        PrintWriter pout = new PrintWriter(client.getOutputStream(),true); // Send data to client 
    
        /* write __Quote of the day__ to the socket */
        System.out.println("Administering quote of the day. . .");
        
        File file = new File("quotes.txt"); 
        String filePath = file.getAbsolutePath(); 
        BufferedReader bin = new BufferedReader(new FileReader(filePath));
        List<String> quotes = new ArrayList<String>(); 
        String line = bin.readLine();
        
        /* add quotes to list */
        while(line != null){ 
          quotes.add(line); 
          line = bin.readLine(); 
        }

        /* choose random quote to send */
        Random x = new Random(); 
        String choseQuote = quotes.get(x.nextInt(quotes.size()));
        pout.println(choseQuote);
            
        /* close the socket and resume */
        sock.close();
        System.out.println("Socket is closed. . .");
        /* listening for connections */
        client.close();

        bin.close();
      } 
    }
    catch(IOException ioe){
      System.err.println(ioe); 
    }
  }
}
