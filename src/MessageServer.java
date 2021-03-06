import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Message Server
 * <p>
 * The server for the social messaging application which uses the MessageHandler
 * class to respond to the MessageClient on multiple threads
 * Purpose/Functions (To be implemented from MessageHandler):
 * Check Username and Password Format, if they exist, and then add/reject them to accounts.txt
 * Check if there are conversations and messages within conversations
 * Have add/edit/delete functionality of conversations and messages
 *
 * @author Alex Frey, Justin Leddy, Maeve Tra, Yifei Mao, Naveena Erranki
 * @version December 7th, 2020
 */
public class MessageServer {
    private final ServerSocket serverSocket; //socket for this server
    private String identity;

    /**
     * Constructs a new MethodServer at the given port
     * Sets the server socket at the port
     *
     * @param port port to set the server socket at
     * @throws IOException exception thrown when the socket encounters a connection error
     */
    public MessageServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    /**
     * Spawn a new Thread to serve each Client connect to the Server
     * adds each new client to the HashMap of connected clients ClientManager
     *
     * @throws InterruptedException Exception thrown when there is an interruption in the connection
     */
    public void serveClient() throws InterruptedException {
        InetAddress address;
        String hostName;
        int port;
        Socket clientSocket;
        MessageHandler messageHandler;
        Thread handlerThread;


        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }
        hostName = address.getCanonicalHostName();
        port = this.serverSocket.getLocalPort();
        System.out.printf("Host name: %s, port: %d\n", hostName, port); //host computer info

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();

            } catch (IOException e) {
                System.out.println("Client Disconnected");
                break;
            }
            //Get user ip address & port pair
            InetAddress ip = clientSocket.getInetAddress();
            int portNum = clientSocket.getPort();
            identity = ip.toString() + portNum;
            System.out.println("Identity is: " + portNum);

            messageHandler = new MessageHandler(clientSocket);
            handlerThread = new Thread(messageHandler);

            ClientManager.addTrace(identity, messageHandler);

            handlerThread.start();

        } // end while loop
    }


    /**
     * Main method to run the server
     *
     * @param args arguments for main
     * @throws InterruptedException Exception thrown when there is an interruption in the connection
     */
    public static void main(String[] args) throws InterruptedException {
        MessageServer server;

        try {
            /* use 8888 because Client is using 8888
             */
            server = new MessageServer(8888);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.serveClient();
    }
}







