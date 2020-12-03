import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Message Client
 * <p>
 * Deals with most GUI parts of social messaging app.
 * Takes input from user within application and sends to the MessageServer
 * USE PORT 8888 on LOCALHOST
 *
 * @author Alex Frey, Justin Leddy, Maeve Tra, Yifei Mao, Naveena Erranki
 * @version November 30th, 2020
 */
public class MessageClient {
    //Global Fields
    private static String clientMessage;
    private static final String TITLE = "Social Messaging App";
    private static JFrame frame;
    private static String clientUsername;
    private static Socket socket = null;
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static ArrayList<Conversation> conversations = new ArrayList<>();
    private static AtomicBoolean loginOrRegister = new AtomicBoolean(true);
    private static AtomicBoolean loginRegisterClicked = new AtomicBoolean(false);
    private static AtomicBoolean sendMessageClicked = new AtomicBoolean(false);

    //Runs actions for login or button based on true (login) or false (register) param
    public static void setClientMessage(boolean loginOrRegister, String username, char[] passwordArray) {
        //Grab username and password
        String password = "";

        //Turns password into string representation
        for (char character : passwordArray) {
            password += character;
        }

        //Checks if fields are empty and displays message if so
        if (username.isEmpty() || password.isEmpty()) {
            message("Please Fill All Fields", JOptionPane.ERROR_MESSAGE);
        } else if (loginOrRegister) {
            //Send info for login
            clientMessage = String.format("L:%s:%s", username, password);
        } else {
            //Send info for register
            clientMessage = String.format("R:%s:%s", username, password);
        }
    }

    //sets client message for sending a group message
    public static void setClientMessage(String message, ArrayList<String> members) {
        String recipient = Arrays.toString(members.toArray())
                .replaceAll(", ", ",")
                .replaceAll("\\[|\\]", "");
        clientMessage = String.format("M|%s|%s|%s", clientUsername, recipient, message);
    }

    //Method to run message application GUI,
    /*public static void runMessageApp() {
        //wipe and repaint
        frame.getContentPane().removeAll();
        frame.repaint();

        //components for messaging
        JButton sendBtn = new JButton("Send");
        JTextField messageText = new JTextField(10);
        JTextField recipientText = new JTextField(10);
        JLabel recipientLbl = new JLabel("Recipient");

        JPanel panel = new JPanel();

        //action listener
        sendBtn.addActionListener(event -> {

            if (messageText.getText().isEmpty()) {
                message("Fill All Fields", JOptionPane.ERROR_MESSAGE);
            } else {
                setClientMessage(messageText.getText(), recipientText.getText());
                sendMessageClicked.set(true);
            }
        });

        panel.add(messageText);
        panel.add(sendBtn);
        panel.add(recipientLbl);
        panel.add(recipientText);
        frame.add(panel);


        //Set Frame Size, Settings, and Visibility
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

     */

    //Simplifies JOptionPane process
    public static void message(String message, int type) {
        JOptionPane.showMessageDialog(null, message, TITLE, type);
    }

    public void connect() {
        try {
            socket = new Socket("localhost", 8888);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connected!");
        } catch (IOException e) {
            e.printStackTrace(); //TODO: Replace with detailed error popup
        }
    }

    //Main method to run all screens: Login, Register, messageApp
    public static void main(String[] args) {
        MessageClient messageClient = new MessageClient();
        messageClient.connect(); // use Socket to initialize reader and writer
        LoginGUI login = new LoginGUI(messageClient); //display login page
        /*
         * infinite loop for server communication
         * only sends the message if a button has been clicked for login screen
         * or if a button has been clicked for messaging
         */
        while (true) {
            if (loginRegisterClicked.get() || sendMessageClicked.get()) {
                try {
                    if (sendMessageClicked.get()) {
                        sendMessageClicked.set(false);
                    }

                    //Sends message from Client to server
                    if (clientMessage != null && clientMessage.length() > 0) {
                        writer.write(clientMessage);
                        writer.newLine();
                        writer.flush();
                    }

                    // Login
                    if (loginRegisterClicked.get()) {
                        loginRegisterClicked.set(false);
                        //Boolean represents if login credentials exist
                        boolean userExists = Boolean.parseBoolean(reader.readLine());
                        if (userExists) {
                            //If login does exist, check if user is logging in or registering
                            if (loginOrRegister.get()) { //user successfully logged in
                                //Changes window to full message app
                                message("You've successfully logged in!",
                                        JOptionPane.INFORMATION_MESSAGE);
                                login.close(); //close the login page
                                new ChatGUI(messageClient); //open chatGUI
                                //clientUsername = userText.getText();

                                //needs to initialize conversations
                                writer.write("I|" + clientUsername + "\n");
                                writer.flush();
                                initializeConversations(reader.readLine());
                            } else {
                                //Prompts user that entered username is already taken
                                message("Username is already taken, enter a different username",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }


                        } else {
                            //If login does not exist, check if user is logging in or registering
                            if (loginOrRegister.get()) {
                                //Prompts user that login credentials do not exist
                                message("Account with entered username and password does not exist.",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else { //user successfully registered
                                message("You've successfully registered!",
                                        JOptionPane.INFORMATION_MESSAGE);
                                login.close(); //close login page
                                new ChatGUI(messageClient); //open chatGUI
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace(); //TODO: Replace with detailed error popup
                    break;
                }
            }


            /* read messages from server broadcast
             * if the message contains the username of this client in the recipient,
             * then look for a conversation to file this message into
             * it looks by gathering and sorting the list of recipients,
             * then it compares it with every member list in all conversations
             * the user is in.
             * If it cant find a conversation with matching members then that means
             * that its a conversation another user created
             * then it creates a new conversation in the current clients list.
             */
            try {
                if (reader.ready()) { //if there is a message from the server
                    String fromServer = reader.readLine(); //read message
                    if (fromServer.substring(0, 2).equals("M|")) {//if it is in the message format
                        System.out.println("Received this from the server: " + fromServer); //print the message it received from server
                        //M|Sender|Recipient|Message
                        //M|Sender|Recipient1,Recipient2,Recipient3|Message

                        String[] receivedMessage = fromServer.split("\\|");
                        String sender = receivedMessage[1];
                        String recipients = receivedMessage[2];
                        String message = receivedMessage[3];
                        ArrayList<String> membersList = new ArrayList<>();
                        if (recipients.contains(clientUsername)) {
                            boolean conversationExists = false;

                            membersList = (ArrayList<String>) Arrays.asList(recipients.split(","));

                            membersList.add(sender);
                            Collections.sort(membersList); //sort membersList

                            for (Conversation conversation : conversations) {
                                ArrayList<String> members = conversation.getMembers();
                                Collections.sort(members);
                                if (members.equals(membersList)) {
                                    conversation.addMessage(String.format("%s|%s", sender, message));
                                    conversationExists = true;
                                    break;
                                }
                            }

                            if (!conversationExists) { //conversation doesn't exist
                                Conversation conversationToAdd = new Conversation(membersList);
                                //"System|Your conversation has been created" is always the first message in any conversation.
                                conversationToAdd.addMessage(message);
                                conversations.add(conversationToAdd);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(); //TODO: Replace with detailed error popup
            }

        }
    }

    private static void initializeConversations(String readLine) {
        System.out.println("Initialized Conversations with: " + readLine);
        //Member1,Member2,Member<*>Username|Message,Username|Message
        if (readLine != null && readLine.length() > 2) {
            String[] newConversations = readLine.split("<&\\*>");
            for (String conversation : newConversations) { //Member1,Member2,Member3<*>Username|Message%&Username|Message%&Username|Message
                String[] membersAndMessages = conversation.split("<\\*>");
                ArrayList<String> members = new ArrayList<>(Arrays.asList(membersAndMessages[0].split("\\|")));
                ArrayList<String> messages = new ArrayList<>(Arrays.asList(membersAndMessages[1].split("%%&")));

                System.out.println(Arrays.toString(members.toArray()));
                System.out.println(Arrays.toString(messages.toArray()));
                conversations.add(new Conversation(members, messages));
            }
        } else{
            System.out.println("User has no conversations on record.");
        }
    }
    //Special Characters Message: | &*
    //Special Characters Login: , :  (leading and trailing spaces stripped)


    /*
     * Getters and setters for the fields
     */
    public ArrayList<Conversation> getConversations() {
        return conversations;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String username) {
        clientUsername = username;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setLoginRegisterClicked() {
        loginRegisterClicked.set(true);
    }

    public void setSendMessageClicked(boolean value) {
        sendMessageClicked.set(value);
    }


    public void setLoginOrRegister(boolean loginOrRegister) {
        MessageClient.loginOrRegister.set(loginOrRegister);
    }


}


