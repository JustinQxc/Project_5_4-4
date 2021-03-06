import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.util.*;
import java.lang.reflect.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;

/**
 * RunLocalTest
 * <p>
 * The run test class to test fields
 * methods and classes in this
 * project
 *
 * @author Alex Frey, Justin Leddy, Maeve Tra, Yifei Mao, Naveena Erranki
 * @version December 7th, 2020
 */
public class RunLocalTest {

    /**
     * Main Method to Run Tests
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    /**
     * Class to test all methods, fields, and classes in this project
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        /**
         * Req 1: Each class must have a test verifying that it exists
         * and inherits from the correct superclass
         */
        @Test(timeout = 1000)
        public void testMessageHandlerExists() {
            try {
                Class<?> clazz = Class.forName("MessageHandler");
                if (!Runnable.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `MessageHandler` implements Runnable");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `MessageHandler` class");

            }
        }

        @Test(timeout = 1000)
        public void testMessageClientExists() {
            try {
                Class<?> clazz = Class.forName("MessageClient");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure that `MessageClient` extends `Object`");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `MessageClient` class");

            }
        }

        @Test(timeout = 1000)
        public void testMessageServerExists() {
            try {
                Class<?> clazz = Class.forName("MessageServer");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `MessageServer` extends Object");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `MessageServer` class");

            }
        }

        @Test(timeout = 1000)
        public void testLoginGUIExists() {
            try {
                Class<?> clazz = Class.forName("LoginGUI");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `LoginGUI` extends JFrame");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `LoginGUI` class");

            }
        }

        @Test(timeout = 1000)
        public void testChatGUIExists() {
            try {
                Class<?> clazz = Class.forName("ChatGUI");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `ChatGUI` extends JFrame");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `ChatGUI` class");

            }
        }

        @Test(timeout = 1000)
        public void testDisplayMessageGUIExists() {
            try {
                Class<?> clazz = Class.forName("DisplayMessageGUI");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `DisplayMessageGUI` extends JPanel");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `DisplayMessageGUI` class");

            }
        }

        @Test(timeout = 1000)
        public void testClientManagerExists() {
            try {
                Class<?> clazz = Class.forName("ClientManager");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `ClientManager` extends Object");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `ClientManager` class");

            }
        }

        @Test(timeout = 1000)
        public void testConversationExists() {
            try {
                Class<?> clazz = Class.forName("Conversation");
                if (!Object.class.isAssignableFrom(clazz)) {
                    Assert.fail("Make sure `Conversation` extends Object");
                }
            } catch (ClassNotFoundException e) {
                Assert.fail("Cannot find `Conversation` class");

            }
        }


        /**
         * Req 2: Each field in every class must have a test verifying that it exists,
         * along with verifying it has the correct type and access modifier.
         */

        //For MessageHandler
        @Test(timeout = 1000)
        public void testMessageHandlerDeclarations() {
            // accountList
            try {
                Field field = MessageHandler.class.getDeclaredField("accountList");
                if (field.getType() != File.class) {
                    fail("The field `accountList` in MessageHandler is not a type of File");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `accountList` in MessageHandler is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `accountList` in MessageHandler");
                e.printStackTrace();
                return;
            }

            // clientSocket
            try {
                Field field = MessageHandler.class.getDeclaredField("clientSocket");
                if (field.getType() != Socket.class) {
                    fail("The field `clientSocket` in MessageHandler is not a type of Socket");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `clientSocket` in MessageHandler is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientSocket` in MessageHandler");
                e.printStackTrace();
                return;
            }

            // 
            try {
                Field field = MessageHandler.class.getDeclaredField("gateKeeper");
                if (field.getType() != Object.class) {
                    fail("The field `gateKeeper` in MessageHandler is not a type of Object");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `gateKeeper` in MessageHandler is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `gateKeeper` in MessageHandler");
                e.printStackTrace();
                return;
            }

            // clientWriter
            try {
                Field field = MessageHandler.class.getDeclaredField("clientWriter");
                if (field.getType() != BufferedWriter.class) {
                    fail("The field `clientWriter` in MessageHandler is not a type of BufferedWriter");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `clientWriter` in MessageHandler is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientWriter` in MessageHandler");
                e.printStackTrace();
                return;
            }

            //clientMessage
            try {
                Field field = MessageHandler.class.getDeclaredField("clientMessage");
                if (field.getType() != String.class) {
                    fail("The field `clientMessage` in MessageHandler is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `clientMessage` in MessageHandler is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientMessage` in MessageHandler");
                e.printStackTrace();
                return;
            }

            //currentClientUsername
            try {
                Field field = MessageHandler.class.getDeclaredField("currentClientUsername");
                if (field.getType() != String.class) {
                    fail("The field `currentClientUsername` in MessageHandler is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `currentClientUsername` in MessageHandler is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `currentClientUsername` in MessageHandler");
                e.printStackTrace();
                return;
            }
        }

        //For MessageClient
        @Test(timeout = 1000)
        public void testMessageClientDeclarations() {
            //clientMessage
            try {
                Field field = MessageClient.class.getDeclaredField("clientMessage");
                if (field.getType() != String.class) {
                    fail("The field `clientMessage` in MessageClient is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `clientMessage` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientMessage` in MessageClient");
                e.printStackTrace();
                return;
            }

            //clientUsername
            try {
                Field field = MessageClient.class.getDeclaredField("clientUsername");
                if (field.getType() != String.class) {
                    fail("The field `clientUsername` in MessageClient is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `clientUsername` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientUsername` in MessageClient");
                e.printStackTrace();
                return;
            }

            //TITLE
            try {
                Field field = MessageClient.class.getDeclaredField("TITLE");
                if (field.getType() != String.class) {
                    fail("The field `TITLE` in MessageClient is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC + Modifier.FINAL) {
                    fail("The field `TITLE` in MessageClient is not private, static, and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `TITLE` in MessageClient");
                e.printStackTrace();
                return;
            }

            // chatGUI
            try {
                Field field = MessageClient.class.getDeclaredField("chatGUI");
                if (field.getType() != ChatGUI.class) {
                    fail("The field `chatGUI` in MessageClient is not a type of ChatGUI");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `chatGUI` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `chatGUI` in MessageClient");
                e.printStackTrace();
                return;
            }

            //socket
            try {
                Field field = MessageClient.class.getDeclaredField("socket");
                if (field.getType() != Socket.class) {
                    fail("The field `socket` in MessageClient is not a type of Socket");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `socket` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `socket` in MessageClient");
                e.printStackTrace();
                return;
            }

            //reader
            try {
                Field field = MessageClient.class.getDeclaredField("reader");
                if (field.getType() != BufferedReader.class) {
                    fail("The field `reader` in MessageClient is not a type of BufferedReader");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `reader` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `reader` in MessageClient");
                e.printStackTrace();
                return;
            }

            //writer
            try {
                Field field = MessageClient.class.getDeclaredField("writer");
                if (field.getType() != BufferedWriter.class) {
                    fail("The field `writer` in MessageClient is not a type of BufferedWriter");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `writer` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `writer` in MessageClient");
                e.printStackTrace();
                return;
            }

            //conversations
            try {
                Field field = MessageClient.class.getDeclaredField("conversations");
                if (field.getType() != ArrayList.class) {
                    fail("The field `conversations` in MessageClient is not a type of ArrayList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `conversations` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `conversations` in MessageClient");
                e.printStackTrace();
                return;
            }

            //loginOrRegister
            try {
                Field field = MessageClient.class.getDeclaredField("loginOrRegister");
                if (field.getType() != AtomicBoolean.class) {
                    fail("The field `loginOrRegister` in MessageClient is not a type of AtomicBoolean");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `loginOrRegister` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `loginOrRegister` in MessageClient");
                e.printStackTrace();
                return;
            }

            //loginRegisterClicked
            try {
                Field field = MessageClient.class.getDeclaredField("loginRegisterClicked");
                if (field.getType() != AtomicBoolean.class) {
                    fail("The field `loginRegisterClicked` in MessageClient is not a type of AtomicBoolean");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `loginRegisterClicked` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `loginRegisterClicked` in MessageClient");
                e.printStackTrace();
                return;
            }

            //sendMessageClicked
            try {
                Field field = MessageClient.class.getDeclaredField("sendMessageClicked");
                if (field.getType() != AtomicBoolean.class) {
                    fail("The field `sendMessageClicked` in MessageClient is not a type of AtomicBoolean");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `sendMessageClicked` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `sendMessageClicked` in MessageClient");
                e.printStackTrace();
                return;
            }

            //checkUserAccountsExisting
            try {
                Field field = MessageClient.class.getDeclaredField("checkUserAccountsExisting");
                if (field.getType() != AtomicBoolean.class) {
                    fail("The field `checkUserAccountsExisting` in MessageClient is not a type of AtomicBoolean");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `checkUserAccountsExisting` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `checkUserAccountsExisting` in MessageClient");
                e.printStackTrace();
                return;
            }

            //userAccountsExist
            try {
                Field field = MessageClient.class.getDeclaredField("userAccountsExist");
                if (field.getType() != AtomicBoolean.class) {
                    fail("The field `userAccountsExist` in MessageClient is not a type of AtomicBoolean");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `userAccountsExist` in MessageClient is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `userAccountsExist` in MessageClient");
                e.printStackTrace();
                return;
            }
        }

        //For MessageServer
        @Test(timeout = 1000)
        public void testMessageServerDeclarations() {
            // serverSocket
            try {
                Field field = MessageServer.class.getDeclaredField("serverSocket");
                if (field.getType() != ServerSocket.class) {
                    fail("The field `serverSocket` in MessageServer is not a type of ServerSocket");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `serverSocket` in MessageServer is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `serverSocket` in MessageServer");
                e.printStackTrace();
                return;
            }

            //identity
            try {
                Field field = MessageServer.class.getDeclaredField("identity");
                if (field.getType() != String.class) {
                    fail("The field `identity` in MessageServer is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `identity` in MessageServer is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `identity` in MessageServer");
                e.printStackTrace();
                return;
            }
        }

        //For LoginGUI
        @Test(timeout = 1000)
        public void testLoginGUIDeclarations() {
            //client
            try {
                Field field = LoginGUI.class.getDeclaredField("client");
                if (field.getType() != MessageClient.class) {
                    fail("The field `client` in LoginGUI is not a type of MessageClient");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `client` in LoginGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `client` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //TITLE
            try {
                Field field = LoginGUI.class.getDeclaredField("TITLE");
                if (field.getType() != String.class) {
                    fail("The field `TITLE` in LoginGUI is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL + Modifier.STATIC) {
                    fail("The field `TITLE` in LoginGUI is not private and/or final and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `TITLE` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //frame
            try {
                Field field = LoginGUI.class.getDeclaredField("frame");
                if (field.getType() != JFrame.class) {
                    fail("The field `frame` in LoginGUI is not a type of JFrame");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `frame` in LoginGUI is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `frame` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //userLbl
            try {
                Field field = LoginGUI.class.getDeclaredField("userLbl");
                if (field.getType() != JLabel.class) {
                    fail("The field `userLbl` in LoginGUI is not a type of JLabel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `frame` in LoginGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `frame` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //passLbl
            try {
                Field field = LoginGUI.class.getDeclaredField("passLbl");
                if (field.getType() != JLabel.class) {
                    fail("The field `passLbl` in LoginGUI is not a type of JLabel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `passLbl` in LoginGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `passLbl` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //loginBtn
            try {
                Field field = LoginGUI.class.getDeclaredField("loginBtn");
                if (field.getType() != JButton.class) {
                    fail("The field `loginBtn` in LoginGUI is not a type of JButton");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `loginBtn` in LoginGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `loginBtn` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //userText
            try {
                Field field = LoginGUI.class.getDeclaredField("userText");
                if (field.getType() != JTextField.class) {
                    fail("The field `userText` in LoginGUI is not a type of JTextField");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `userText` in LoginGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `userText` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //passText
            try {
                Field field = LoginGUI.class.getDeclaredField("passText");
                if (field.getType() != JPasswordField.class) {
                    fail("The field `passText` in LoginGUI is not a type of JPasswordField");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `passText` in LoginGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `passText` in LoginGUI");
                e.printStackTrace();
                return;
            }

            //actionListener
            try {
                Field field = LoginGUI.class.getDeclaredField("actionListener");
                if (field.getType() != ActionListener.class) {
                    fail("The field `actionListener` in LoginGUI is not a type of ActionListener");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `actionListener` in LoginGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `actionListener` in LoginGUI");
                e.printStackTrace();
                return;
            }
        }

        //For ChatGUI
        @Test(timeout = 1000)
        public void testChatGUIDeclarations() {
            //messageClient
            try {
                Field field = ChatGUI.class.getDeclaredField("messageClient");
                if (field.getType() != MessageClient.class) {
                    fail("The field `messageClient` in ChatGUI is not a type of MessageClient");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `messageClient` in ChatGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messageClient` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //conversation
            try {
                Field field = ChatGUI.class.getDeclaredField("conversations");
                if (field.getType() != ArrayList.class) {
                    fail("The field `conversation` in ChatGUI is not a type of ArrayList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `conversation` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `conversation` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //clientUsername
            try {
                Field field = ChatGUI.class.getDeclaredField("clientUsername");
                if (field.getType() != String.class) {
                    fail("The field `clientUsername` in ChatGUI is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `clientUsername` in ChatGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientUsername` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //usersToSend
            try {
                Field field = ChatGUI.class.getDeclaredField("usersToSend");
                if (field.getType() != ArrayList.class) {
                    fail("The field `usersToSend` in ChatGUI is not a type of ArrayList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `usersToSend` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `usersToSend` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //allMessages
            try {
                Field field = ChatGUI.class.getDeclaredField("allMessages");
                if (field.getType() != Map.class) {
                    fail("The field `allMessages` in ChatGUI is not a type of Map");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `allMessages` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `allMessages` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //middlePanel
            try {
                Field field = ChatGUI.class.getDeclaredField("middlePanel");
                if (field.getType() != JPanel.class) {
                    fail("The field `middlePanel` in ChatGUI is not a type of JPanel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `middlePanel` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `frame` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //sendButton
            try {
                Field field = ChatGUI.class.getDeclaredField("sendButton");
                if (field.getType() != JButton.class) {
                    fail("The field `sendButton` in ChatGUI is not a type of JButton");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `sendButton` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `sendButton` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //newChatButton
            try {
                Field field = ChatGUI.class.getDeclaredField("newChatButton");
                if (field.getType() != JButton.class) {
                    fail("The field `newChatButton` in ChatGUI is not a type of JButton");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `newChatButton` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `newChatButton` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //messageText
            try {
                Field field = ChatGUI.class.getDeclaredField("messageText");
                if (field.getType() != JTextArea.class) {
                    fail("The field `messageText` in ChatGUI is not a type of JTextArea");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `messageText` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messageText` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //sentText
            try {
                Field field = ChatGUI.class.getDeclaredField("sentText");
                if (field.getType() != JTextArea.class) {
                    fail("The field `sentText` in ChatGUI is not a type of JTextArea");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `sentText` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `sentText` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //inboxList
            try {
                Field field = ChatGUI.class.getDeclaredField("inboxList");
                if (field.getType() != JList.class) {
                    fail("The field `inboxList` in ChatGUI is not a type of JList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `inboxList` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `inboxList` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //inboxes
            try {
                Field field = ChatGUI.class.getDeclaredField("inboxes");
                if (field.getType() != DefaultListModel.class) {
                    fail("The field `inboxes` in ChatGUI is not a type of DefaultListModel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `inboxes` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `inboxes` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //messageFrame
            try {
                Field field = ChatGUI.class.getDeclaredField("messageFrame");
                if (field.getType() != JFrame.class) {
                    fail("The field `messageFrame` in ChatGUI is not a type of JFrame");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `messageFrame` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messageFrame` in ChatGUI");
                e.printStackTrace();
                return;
            }
            //messageField
            try {
                Field field = ChatGUI.class.getDeclaredField("messageField");
                if (field.getType() != DisplayMessageGUI.class) {
                    fail("The field `messageField` in ChatGUI is not a type of DisplayMessageGUI");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `messageField` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messageField` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //deleteInstruction
            try {
                Field field = ChatGUI.class.getDeclaredField("deleteInstruction");
                if (field.getType() != JLabel.class) {
                    fail("The field `deleteInstruction` in ChatGUI is not a type of JLabel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `deleteInstruction` in ChatGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `deleteInstruction` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //editAccount
            try {
                Field field = ChatGUI.class.getDeclaredField("editAccount");
                if (field.getType() != JLabel.class) {
                    fail("The field `editAccount` in ChatGUI is not a type of JLabel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `editAccount` in ChatGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `editAccount` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //actionListener
            try {
                Field field = ChatGUI.class.getDeclaredField("actionListener");
                if (field.getType() != ActionListener.class) {
                    fail("The field `actionListener` in ChatGUI is not a type of ActionListener");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `actionListener` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `actionListener` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //focusListener
            try {
                Field field = ChatGUI.class.getDeclaredField("focusListener");
                if (field.getType() != FocusListener.class) {
                    fail("The field `focusListener` in ChatGUI is not a type of FocusListener");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `focusListener` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `focusListener` in ChatGUI");
                e.printStackTrace();
                return;
            }

            //mouseListener
            try {
                Field field = ChatGUI.class.getDeclaredField("mouseListener");
                if (field.getType() != MouseListener.class) {
                    fail("The field `mouseListener` in ChatGUI is not a type of MouseListener");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `mouseListener` in ChatGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `mouseListener` in ChatGUI");
                e.printStackTrace();
                return;
            }
        }

        //For DisplayMessageGUI
        @Test(timeout = 1000)
        public void testDisplayMessageGUIDeclarations() {
            // client
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("client");
                if (field.getType() != MessageClient.class) {
                    fail("The field `client` in DisplayMessageGUI is not a type of MessageClient");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `client` in DisplayMessageGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `client` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }

            // clientUsername
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("clientUsername");
                if (field.getType() != String.class) {
                    fail("The field `clientUsername` in DisplayMessageGUI is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `clientUsername` in DisplayMessageGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `clientUsername` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }

            // messageLabel
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("messageLabel");
                if (field.getType() != String.class) {
                    fail("The field `messageLabel` in DisplayMessageGUI is not a type of String");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.FINAL) {
                    fail("The field `messageLabel` in DisplayMessageGUI is not private and/or final");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messageLabel` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }

            // conversation
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("conversation");
                if (field.getType() != Conversation.class) {
                    fail("The field `conversation` in DisplayMessageGUI is not a type of Conversation");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `conversation` in DisplayMessageGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `conversation` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }

            // list
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("list");
                if (field.getType() != DefaultListModel.class) {
                    fail("The field `list` in DisplayMessageGUI is not a type of DefaultListModel");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `list` in DisplayMessageGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `list` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }

            // messages
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("messages");
                if (field.getType() != JList.class) {
                    fail("The field `messages` in DisplayMessageGUI is not a type of JList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `messages` in DisplayMessageGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messages` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }

            // mouseListener
            try {
                Field field = DisplayMessageGUI.class.getDeclaredField("mouseListener");
                if (field.getType() != MouseListener.class) {
                    fail("The field `mouseListener` in DisplayMessageGUI is not a type of MouseListener");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `mouseListener` in DisplayMessageGUI is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `mouseListener` in DisplayMessageGUI");
                e.printStackTrace();
                return;
            }
        }

        //For ClientManager
        @Test(timeout = 1000)
        public void testClientManagerDeclarations() {
            // deliverTo
            try {
                Field field = ClientManager.class.getDeclaredField("deliverTo");
                if (field.getType() != HashMap.class) {
                    fail("The field `deliverTo` in ClientManager is not a type of HashMap");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The field `deliverTo` in ClientManager is not private and/or static");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `deliverTo` in ClientManager");
                e.printStackTrace();
                return;
            }
        }

        //For Conversation
        @Test(timeout = 1000)
        public void testConversationDeclarations() {
            // messages
            try {
                Field field = Conversation.class.getDeclaredField("messages");
                if (field.getType() != ArrayList.class) {
                    fail("The field `messages` in Conversation is not a type of ArrayList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `messages` in Conversation is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `messages` in Conversation");
                e.printStackTrace();
                return;
            }

            // members
            try {
                Field field = Conversation.class.getDeclaredField("members");
                if (field.getType() != ArrayList.class) {
                    fail("The field `members` in Conversation is not a type of ArrayList");
                    return;
                }
                if (field.getModifiers() != Modifier.PRIVATE) {
                    fail("The field `members` in Conversation is not private");
                    return;
                }
            } catch (NoSuchFieldException e) {
                fail("Cannot find the field `members` in Conversation");
                e.printStackTrace();
                return;
            }
        }


        /**
         * Req 3: Each method in every class must have a test verifying that it exists,
         * along with verifying it has the correct return type and access modifier.
         */

        //MessageHandler - 5

        //Constructor
        @Test(timeout = 1000)
        public void testMessageHandlerConstructorDeclaration() {
            try {
                Constructor constructor = MessageHandler.class.getDeclaredConstructor(Socket.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `MessageHandler` in MessageHandler is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `MessageHandler` in MessageHandler");
            }
        }

        //run
        @Test(timeout = 1000)
        public void testMessageHandlerMethodOneDeclaration() {
            try {
                Method method = MessageHandler.class.getDeclaredMethod("run");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `run` in MessageHandler is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `run` in MessageHandler does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `run` in MessageHandler");
            }
        }

        //send
        @Test(timeout = 1000)
        public void testMessageHandlerMethodTwoDeclaration() {
            try {
                Method method = MessageHandler.class.getDeclaredMethod("send", String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `send` in MessageHandler is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `send` in MessageHandler does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `send` in MessageHandler");
            }
        }

        //getClientSocket
        @Test(timeout = 1000)
        public void testMessageHandlerMethodThreeDeclaration() {
            try {
                Method method = MessageHandler.class.getDeclaredMethod("getClientSocket");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getClientSocket` in MessageHandler is not public");
                }

                if (method.getReturnType() != Socket.class) {
                    fail("The method `getClientSocket` in MessageHandler does not return type Socket");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getClientSocket` in MessageHandler");
            }
        }

        //getCurrentClientUsername
        @Test(timeout = 1000)
        public void testMessageHandlerMethodFourDeclaration() {
            try {
                Method method = MessageHandler.class.getDeclaredMethod("getCurrentClientUsername");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getCurrentClientUsername` in MessageHandler is not public");
                }

                if (method.getReturnType() != String.class) {
                    fail("The method `getCurrentClientUsername` in MessageHandler does not return type String");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getCurrentClientUsername` in MessageHandler");
            }
        }

        //setCurrentClientUsername
        @Test(timeout = 1000)
        public void testMessageHandlerMethodFiveDeclaration() {
            try {
                Method method = MessageHandler.class.getDeclaredMethod("setCurrentClientUsername", String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setCurrentClientUsername` in MessageHandler is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setCurrentClientUsername` in MessageHandler does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setCurrentClientUsername` in MessageHandler");
            }
        }

        //MessageClient - 21

        //setClientMessageLoginRegister
        @Test(timeout = 1000)
        public void testMessageClientMethodOneDeclaration() {
            try {
                Method method = MessageClient.class
                        .getDeclaredMethod("setClientMessageLoginRegister", boolean.class, String.class, char[].class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageLoginRegister` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageLoginRegister` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageLoginRegister` in MessageClient");
            }
        }

        //setClientMessageMessaging
        @Test(timeout = 1000)
        public void testMessageClientMethodTwoDeclaration() {
            try {
                Method method = MessageClient.class
                        .getDeclaredMethod("setClientMessageMessaging", String.class, ArrayList.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageMessaging` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageMessaging` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageMessaging` in MessageClient");
            }
        }

        //setClientMessageNewChat
        @Test(timeout = 1000)
        public void testMessageClientMethodThreeDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setClientMessageNewChat", ArrayList.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageNewChat` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageNewChat` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageNewChat` in MessageClient");
            }
        }

        //setClientMessageDeleteUser
        @Test(timeout = 1000)
        public void testMessageClientMethodFourDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setClientMessageDeleteUser", Conversation.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageDeleteUser` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageDeleteUser` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageDeleteUser` in MessageClient");
            }
        }

        //setClientMessageUpdateChat
        @Test(timeout = 1000)
        public void testMessageClientMethodFiveDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setClientMessageUpdateChat", Conversation.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageUpdateChat` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageUpdateChat` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageUpdateChat` in MessageClient");
            }
        }

        //message
        @Test(timeout = 1000)
        public void testMessageClientMethodSixDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("message", String.class, int.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `message` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `message` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `message` in MessageClient");
            }
        }

        //connect
        @Test(timeout = 1000)
        public void testMessageClientMethodSevenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("connect");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `connect` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `connect` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `connect` in MessageClient");
            }
        }

        //main
        @Test(timeout = 1000)
        public void testMessageClientMethodEightDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("main", String[].class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `main` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `main` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `main` in MessageClient");
            }
        }

        //updateConversation
        @Test(timeout = 1000)
        public void testMessageClientMethodNineDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("updateConversation", String.class);
                if (method.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The method `updateConversation` in MessageClient is not private and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `updateConversation` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `updateConversation` in MessageClient");
            }
        }

        //initializeConversations
        @Test(timeout = 1000)
        public void testMessageClientMethodTenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("initializeConversations", String.class);
                if (method.getModifiers() != Modifier.PRIVATE + Modifier.STATIC) {
                    fail("The method `initializeConversations` in MessageClient is not private and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `initializeConversations` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `initializeConversations` in MessageClient");
            }
        }

        //getConversations
        @Test(timeout = 1000)
        public void testMessageClientMethodElevenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("getConversations");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getConversations` in MessageClient is not public");
                }

                if (method.getReturnType() != ArrayList.class) {
                    fail("The method `getConversations` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getConversations` in MessageClient");
            }
        }

        //getClientUsername
        @Test(timeout = 1000)
        public void testMessageClientMethodTwelveDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("getClientUsername");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getClientUsername` in MessageClient is not public");
                }

                if (method.getReturnType() != String.class) {
                    fail("The method `getClientUsername` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getClientUsername` in MessageClient");
            }
        }

        //setClientUsername
        @Test(timeout = 1000)
        public void testMessageClientMethodThirteenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setClientUsername", String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setClientUsername` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientUsername` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientUsername` in MessageClient");
            }
        }

        //setLoginRegisterClicked
        @Test(timeout = 1000)
        public void testMessageClientMethodFourteenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setLoginRegisterClicked");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setLoginRegisterClicked` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setLoginRegisterClicked` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setLoginRegisterClicked` in MessageClient");
            }
        }

        //setSendMessageClicked
        @Test(timeout = 1000)
        public void testMessageClientMethodFifteenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setSendMessageClicked", boolean.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setSendMessageClicked` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setSendMessageClicked` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setSendMessageClicked` in MessageClient");
            }
        }

        //setLoginOrRegister
        @Test(timeout = 1000)
        public void testMessageClientMethodSixteenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setLoginOrRegister", boolean.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setLoginOrRegister` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setLoginOrRegister` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setLoginOrRegister` in MessageClient");
            }
        }

        //setCheckUserAccountsExisting
        @Test(timeout = 1000)
        public void testMessageClientMethodSeventeenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setCheckUserAccountsExisting", boolean.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setCheckUserAccountsExisting` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setCheckUserAccountsExisting` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setCheckUserAccountsExisting` in MessageClient");
            }
        }

        //getUserAccountsExist
        @Test(timeout = 1000)
        public void testMessageClientMethodEighteenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("getUserAccountsExist");
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `getUserAccountsExist` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != boolean.class) {
                    fail("The method `getUserAccountsExist` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getUserAccountsExist` in MessageClient");
            }
        }

        //setUserAccountsExists
        @Test(timeout = 1000)
        public void testMessageClientMethodNineteenDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setUserAccountsExists", boolean.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setUserAccountsExists` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setUserAccountsExists` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setUserAccountsExists` in MessageClient");
            }
        }

        //setClientMessageDeleteAccount
        @Test(timeout = 1000)
        public void testMessageClientMethodTwentyDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setClientMessageDeleteAccount");
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageDeleteAccount` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageDeleteAccount` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageDeleteAccount` in MessageClient");
            }
        }

        //setClientMessageChangePassword
        @Test(timeout = 1000)
        public void testMessageClientMethodTwentyOneDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setClientMessageChangePassword", String.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `setClientMessageChangePassword` in MessageClient is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setClientMessageChangePassword` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setClientMessageChangePassword` in MessageClient");
            }
        }

        //getClientMessage
        @Test(timeout = 1000)
        public void testMessageClientMethodTwentyTwoDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("getClientMessage");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getClientMessage` in MessageClient is not public");
                }

                if (method.getReturnType() != String.class) {
                    fail("The method `getClientMessage` in MessageClient does not return type String");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getClientMessage` in MessageClient");
            }
        }

        //setConversations
        @Test(timeout = 1000)
        public void testMessageClientMethodTwentyThreeDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("setConversations", ArrayList.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setConversations` in MessageClient is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setConversations` in MessageClient does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setConversations` in MessageClient");
            }
        }


        //MessageServer - 3

        //Constructor
        @Test(timeout = 1000)
        public void testMessageServerConstructorDeclaration() {
            try {
                Constructor constructor = MessageServer.class.getDeclaredConstructor(int.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `MessageServer` in MessageHandler is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `MessageServer` in MessageServer");
            }
        }

        //serveClient
        @Test(timeout = 1000)
        public void testMessageServerMethodOneDeclaration() {
            try {
                Method method = MessageServer.class.getDeclaredMethod("serveClient");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `serveClient` in MessageServer is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `serveClient` in MessageServer does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `send` in MessageServer");
            }
        }

        //main
        @Test(timeout = 1000)
        public void testMessageServerMethodTwoDeclaration() {
            try {
                Method method = MessageClient.class.getDeclaredMethod("main", String[].class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `main` in MessageServer is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `main` in MessageServer does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `main` in MessageServer");
            }
        }

        //LoginGUI - 5

        //Constructor
        @Test(timeout = 1000)
        public void testLoginGUIConstructorDeclaration() {
            try {
                Constructor constructor = LoginGUI.class.getDeclaredConstructor(MessageClient.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `LoginGUI` in LoginGUI is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `LoginGUI` in LoginGUI");
            }
        }

        //message
        @Test(timeout = 1000)
        public void testLoginGUIMethodOneDeclaration() {
            try {
                Method method = LoginGUI.class.getDeclaredMethod("message", String.class, int.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `message` in LoginGUI is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `message` in LoginGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `message` in LoginGUI");
            }
        }

        //showLogin
        @Test(timeout = 1000)
        public void testLoginGUIMethodTwoDeclaration() {
            try {
                Method method = LoginGUI.class.getDeclaredMethod("showLogin");
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `showLogin` in LoginGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `showLogin` in LoginGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `showLogin` in LoginGUI");
            }
        }

        //close
        @Test(timeout = 1000)
        public void testLoginGUIMethodThreeDeclaration() {
            try {
                Method method = LoginGUI.class.getDeclaredMethod("close");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `close` in LoginGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `close` in LoginGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `close` in LoginGUI");
            }
        }

        //actionPerformed
        @Test(timeout = 1000)
        public void testLoginGUIMethodFourDeclaration() {
            try {
                Method method = Class.forName("LoginGUI$1").getDeclaredMethod("actionPerformed", ActionEvent.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `actionPerformed` in LoginGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `actionPerformed` in LoginGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `actionPerformed` in LoginGUI");
            }
        }

        //ChatGUI - 16

        //Constructor
        @Test(timeout = 1000)
        public void testChatGUIConstructorDeclaration() {
            try {
                Constructor constructor = ChatGUI.class.getDeclaredConstructor(MessageClient.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `ChatGUI` in ChatGUI is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `ChatGUI` in ChatGUI");
            }
        }

        //findLabelIndex
        @Test(timeout = 1000)
        public void testChatGUIMethodOneDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("findLabelIndex", String.class);
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `findLabelIndex` in ChatGUI is not private");
                }

                if (method.getReturnType() != int.class) {
                    fail("The method `findLabelIndex` in ChatGUI does not return type int");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `findLabelIndex` in ChatGUI");
            }
        }

        //showMessagePanel
        @Test(timeout = 1000)
        public void testChatGUIMethodTwoDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("showMessagePanel");
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `showMessagePanel` in ChatGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `showMessagePanel` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `showMessagePanel` in ChatGUI");
            }
        }

        //createPanel
        @Test(timeout = 1000)
        public void testChatGUIMethodThreeDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("createPanel", Conversation.class);
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `createPanel` in ChatGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `createPanel` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `createPanel` in ChatGUI");
            }
        }

        //displayMessage
        @Test(timeout = 1000)
        public void testChatGUIMethodFourDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("displayMessage", String.class);
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `displayMessage` in ChatGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `displayMessage` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `displayMessage` in ChatGUI");
            }
        }

        //updateChat
        @Test(timeout = 1000)
        public void testChatGUIMethodFiveDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("updateChat", Conversation.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `updateChat` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `updateChat` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `updateChat` in ChatGUI");
            }
        }

        //setUsersToSend
        @Test(timeout = 1000)
        public void testChatGUIMethodSixDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("setUsersToSend", String.class, String.class);
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `setUsersToSend` in ChatGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setUsersToSend` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setUsersToSend` in ChatGUI");
            }
        }

        //removeConversation
        @Test(timeout = 1000)
        public void testChatGUIMethodSevenDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("removeConversation", String.class, int.class);
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `removeConversation` in ChatGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `removeConversation` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `removeConversation` in ChatGUI");
            }
        }

        //editChat
        @Test(timeout = 1000)
        public void testChatGUIMethodEightDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("updateChat", Conversation.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `editChat` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `editChat` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the editChat `updateChat` in ChatGUI");
            }
        }

        //actionPerformed
        @Test(timeout = 1000)
        public void testChatGUIMethodNineDeclaration() {
            try {
                Method method = Class.forName("ChatGUI$2").getDeclaredMethod("actionPerformed", ActionEvent.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `actionPerformed` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `actionPerformed` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `actionPerformed` in ChatGUI");
            }
        }

        //focusGained
        @Test(timeout = 1000)
        public void testChatGUIMethodTenDeclaration() {
            try {
                Method method = Class.forName("ChatGUI$3").getDeclaredMethod("focusGained", FocusEvent.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `focusGained` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `focusGained` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `focusGained` in ChatGUI");
            }
        }

        //focusLost
        @Test(timeout = 1000)
        public void testChatGUIMethodElevenDeclaration() {
            try {
                Method method = Class.forName("ChatGUI$3").getDeclaredMethod("focusLost", FocusEvent.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `focusLost` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `focusLost` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `focusLost` in ChatGUI");
            }
        }

        //mouseClicked
        @Test(timeout = 1000)
        public void testChatGUIMethodTwelveDeclaration() {
            try {
                Method method = Class.forName("ChatGUI$4").getDeclaredMethod("mouseClicked", MouseEvent.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `mouseClicked` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `mouseClicked` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `mouseClicked` in ChatGUI");
            }
        }

        //userLeft
        @Test(timeout = 1000)
        public void testChatGUIMethodThirteenDeclaration() {
            try {
                Method method = ChatGUI.class.getDeclaredMethod("userLeft", Conversation.class, String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `userLeft` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `userLeft` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `updateChat` in ChatGUI");
            }
        }

        //run
        @Test(timeout = 1000)
        public void testChatGUIMethodFourteenDeclaration() {
            try {
                Method method = Class.forName("ChatGUI$1").getDeclaredMethod("run");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `run` in ChatGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `run` in ChatGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `run` in ChatGUI");
            }
        }

        //DisplayMessageGUI - 12

        //Constructor
        @Test(timeout = 1000)
        public void testDisplayMessageGUIConstructorDeclaration() {
            try {
                Constructor constructor = DisplayMessageGUI.class
                        .getDeclaredConstructor(Conversation.class, MessageClient.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `DisplayMessageGUI` in DisplayMessageGUI is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `DisplayMessageGUI` in DisplayMessageGUI");
            }
        }

        //initializeList
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodOneDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("initializeList");
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `initializeList` in DisplayMessageGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `initializeList` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `initializeList` in DisplayMessageGUI");
            }
        }

        //setMessageLabel
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodTwoDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("setMessageLabel");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setMessageLabel` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != String.class) {
                    fail("The method `setMessageLabel` in DisplayMessageGUI does not return type String");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setMessageLabel` in DisplayMessageGUI");
            }
        }

        //getMessageLabel
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodThreeDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("getMessageLabel");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getMessageLabel` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != String.class) {
                    fail("The method `getMessageLabel` in DisplayMessageGUI does not return type String");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getMessageLabel` in DisplayMessageGUI");
            }
        }

        //getConversation
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodFourDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("getConversation");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getConversation` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != Conversation.class) {
                    fail("The method `getConversation` in DisplayMessageGUI does not return type Conversation");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getConversation` in DisplayMessageGUI");
            }
        }

        //updateMessage
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodFiveDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("updateMessage", Conversation.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `updateMessage` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `updateMessage` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `updateMessage` in DisplayMessageGUI");
            }
        }

        //run in updateMessage
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodSixDeclaration() {
            try {
                Method method = Class.forName("DisplayMessageGUI$1").getDeclaredMethod("run");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `run` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `run` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `run` in DisplayMessageGUI");
            }
        }

        //notifyUserLeft
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodSevenDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("notifyUserLeft", String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `notifyUserLeft` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `notifyUserLeft` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `notifyUserLeft` in DisplayMessageGUI");
            }
        }

        //run in notifyUserLeft
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodEightDeclaration() {
            try {
                Method method = Class.forName("DisplayMessageGUI$2").getDeclaredMethod("run");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `run` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `run` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `run` in DisplayMessageGUI");
            }
        }

        //mouseClicked
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodNineDeclaration() {
            try {
                Method method = Class.forName("DisplayMessageGUI$3")
                        .getDeclaredMethod("mouseClicked", MouseEvent.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `mouseClicked` in DisplayMessageGUI is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `mouseClicked` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException | ClassNotFoundException e) {
                fail("Cannot find the method `mouseClicked` in DisplayMessageGUI");
            }
        }

        //notifyChange
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodTenDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("notifyChange");
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `notifyChange` in DisplayMessageGUI is not private");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `notifyChange` in DisplayMessageGUI does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `notifyChange` in DisplayMessageGUI");
            }
        }

        //checkUser
        @Test(timeout = 1000)
        public void testDisplayMessageGUIMethodElevenDeclaration() {
            try {
                Method method = DisplayMessageGUI.class.getDeclaredMethod("checkUser", String.class);
                if (method.getModifiers() != Modifier.PRIVATE) {
                    fail("The method `checkUser` in DisplayMessageGUI is not private");
                }

                if (method.getReturnType() != boolean.class) {
                    fail("The method `checkUser` in DisplayMessageGUI does not return type boolean");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `checkUser` in DisplayMessageGUI");
            }
        }

        //ClientManager - 2

        //addTrace
        @Test(timeout = 1000)
        public void testClientManagerMethodOneDeclaration() {
            try {
                Method method = ClientManager.class.getDeclaredMethod("addTrace", String.class, MessageHandler.class);
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `addTrace` in ClientManager is not public and/or static");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `addTrace` in ClientManager does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `addTrace` in ClientManager");
            }
        }

        //getDeliverTo
        @Test(timeout = 1000)
        public void testClientManagerMethodTwoDeclaration() {
            try {
                Method method = ClientManager.class.getDeclaredMethod("getDeliverTo");
                if (method.getModifiers() != Modifier.PUBLIC + Modifier.STATIC) {
                    fail("The method `getDeliverTo` in ClientManager is not public and/or static");
                }

                if (method.getReturnType() != HashMap.class) {
                    fail("The method `getDeliverTo` in ClientManager does not return type HashMap");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getDeliverTo` in ClientManager");
            }
        }


        //Conversation - 10

        //Constructor #1
        @Test(timeout = 1000)
        public void testConversationConstructorOneDeclaration() {
            try {
                Constructor constructor = Conversation.class.getDeclaredConstructor(ArrayList.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `Conversation` in Conversation is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `Conversation` in Conversation");
            }
        }

        //Constructor #2
        @Test(timeout = 1000)
        public void testConversationConstructorTwoDeclaration() {
            try {
                Constructor constructor = Conversation.class.getDeclaredConstructor(ArrayList.class, ArrayList.class);
                if (constructor.getModifiers() != Modifier.PUBLIC) {
                    fail("The constructor `Conversation` in Conversation is not public");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the constructor `Conversation` in Conversation");
            }
        }

        //removeMessageAtIndex
        @Test(timeout = 1000)
        public void testConversationMethodOneDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("removeMessageAtIndex", int.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `removeMessageAtIndex` in Conversation is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `removeMessageAtIndex` in Conversation does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `removeMessageAtIndex` in Conversation");
            }
        }

        //removeMemberWithName
        @Test(timeout = 1000)
        public void testConversationMethodTwoDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("removeMemberWithName", String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `removeMemberWithName` in Conversation is not public");
                }
                if (method.getReturnType() != void.class) {
                    fail("The method `removeMemberWithName` in Conversation does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `removeMemberWithName` in Conversation");
            }
        }

        //editMessageAtIndex
        @Test(timeout = 1000)
        public void testConversationMethodThreeDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("editMessageAtIndex", int.class, String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `editMessageAtIndex` in Conversation is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `editMessageAtIndex` in Conversation does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `editMessageAtIndex` in Conversation");
            }
        }

        //addMessage
        @Test(timeout = 1000)
        public void testConversationMethodFourDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("addMessage", String.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `addMessage` in Conversation is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `addMessage` in Conversation does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `addMessage` in Conversation");
            }
        }

        //getMessages
        @Test(timeout = 1000)
        public void testConversationMethodFiveDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("getMessages");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getMessages` in Conversation is not public");
                }

                if (method.getReturnType() != ArrayList.class) {
                    fail("The method `getMessages` in Conversation does not return type ArrayList");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getMessages` in Conversation");
            }
        }

        //getMembers
        @Test(timeout = 1000)
        public void testConversationMethodSixDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("getMembers");
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `getMembers` in Conversation is not public");
                }

                if (method.getReturnType() != ArrayList.class) {
                    fail("The method `getMembers` in Conversation does not return type ArrayList");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `getMembers` in Conversation");
            }
        }

        //setMembers
        @Test(timeout = 1000)
        public void testConversationMethodSevenDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("setMembers", ArrayList.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setMembers` in Conversation is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setMembers` in Conversation does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setMembers` in Conversation");
            }
        }

        //setMessages
        @Test(timeout = 1000)
        public void testConversationMethodEightDeclaration() {
            try {
                Method method = Conversation.class.getDeclaredMethod("setMessages", ArrayList.class);
                if (method.getModifiers() != Modifier.PUBLIC) {
                    fail("The method `setMessages` in Conversation is not public");
                }

                if (method.getReturnType() != void.class) {
                    fail("The method `setMessages` in Conversation does not return type void");
                }
            } catch (NoSuchMethodException e) {
                fail("Cannot find the method `setMessages` in Conversation");
            }
        }


        /**
         * Req 4: Each method in every class must have two implementation tests:
         * One that verifies it works correctly with proper input,
         * and another that tests that it fails with improper input.
         */

        //no testing for gui or thread methods - only for
        //Focus on writing automated tests for non-GUI client methods
        // and any helper methods on the server that don't use threads.


        //::MessageHandler - 3
        @Test(timeout = 1000)
        public void testMessageHandler() {
            //General Class: MessageHandler
            Socket socket;
            String testing = "testingString";

            try {
                socket = new Socket("localhost", 0);
            } catch (IOException e) {
                return;
            }

            MessageHandler messageHandler = new MessageHandler(socket);

            //getClientSocket
            assertEquals(socket, messageHandler.getClientSocket());

            //getCurrentClientUsername & setCurrentClientUsername
            String currentClientUsername = "test";

            messageHandler.setCurrentClientUsername(currentClientUsername);

            assertEquals(currentClientUsername, messageHandler.getCurrentClientUsername());

        }

        //::MessageClient - 18
        @Test(timeout = 1000)
        public void testMessageClient() {
            /* List of all client -> messageHandler formats:
             * loginRegister: L|username|password, R|username|password
             * sendMessage: M|clientUsername|arrayOfMembers|message
             * Update message history in chat: U<*>currentMember1|currentMember2|currentMember3<*>allMessages
             * Delete user from chat: U<*>currentMember1|currentMember2|currentMember3<*>memberToDelete<*>allMessages
             * delete account: D|username
             * change password: P|username|newPassword
             * check if users exist: C|user1,user2,user3
             *
             * List of all messageHandler -> client formats:
             * initialize conversation array:
             * Member1|Member2|Member3<*>Username|Message&%Username|Message<&*>conversation2<&*>conversation3
             * loginOrRegister:
             * login: true if logged in, false if not, Register: true if the account exists, false if register success
             * Check if user exists: true if they all exists, false if there is one thats not.
             */
            MessageClient messageClient = new MessageClient();
            ArrayList<String> members = new ArrayList<>(Arrays.asList("member1", "member2", "member3"));
            ArrayList<String> messages = new ArrayList<>(Arrays.asList("member1|message1", "member2|message2"));
            Conversation conversation1 = new Conversation(members);
            Conversation conversation2 = new Conversation(members, messages);
            String clientMessageMessaging = "M|username|member1,member2,member3|message";
            String clientMessageDeleteAccount = "D|username";
            String clientMessageLogin = "L|username|password";
            String clientMessageRegister = "R|username|password";
            String clientMessageChangePassword = "P|username|password";
            String clientMessageUpdateChat = "U<*>member1|member2|member3|username<*>" +
                    "member1|message1%&member2|message2";
            String clientMessageNewChat = "C|member1,member2,member3";
            String clientMessageDeleteUser = "U<*>member1|member2|member3<*>" +
                    "username<*>member1|message1%&member2|message2";
            String username = "username";
            String password = "password";
            String member = "member";
            String message = "message";

            //getClientMessage is tested through all setClientMessage type methods

            //getClientUsername & setClientUsername
            messageClient.setClientUsername(username);

            assertEquals(username, messageClient.getClientUsername());

            //setLoginRegisterClicked
            AtomicBoolean loginRegisterClicked;

            messageClient.setLoginRegisterClicked();

            try {
                Field field = MessageClient.class.getDeclaredField("loginRegisterClicked");
                field.setAccessible(true);
                loginRegisterClicked = (AtomicBoolean) field.get(messageClient);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return;
            }

            assertTrue(loginRegisterClicked.get());

            //setSendMessageClicked
            AtomicBoolean sendMessageClicked;

            messageClient.setSendMessageClicked(false);

            try {
                Field field = MessageClient.class.getDeclaredField("sendMessageClicked");
                field.setAccessible(true);
                sendMessageClicked = (AtomicBoolean) field.get(messageClient);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return;
            }

            assertFalse(sendMessageClicked.get());

            //setLoginOrRegister
            AtomicBoolean loginOrRegister;

            messageClient.setLoginOrRegister(false);

            try {
                Field field = MessageClient.class.getDeclaredField("loginOrRegister");
                field.setAccessible(true);
                loginOrRegister = (AtomicBoolean) field.get(messageClient);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return;
            }

            assertFalse(loginOrRegister.get());

            //setCheckUserAccountsExisting
            AtomicBoolean checkUserAccountsExisting;

            messageClient.setCheckUserAccountsExisting(false);

            try {
                Field field = MessageClient.class.getDeclaredField("checkUserAccountsExisting");
                field.setAccessible(true);
                checkUserAccountsExisting = (AtomicBoolean) field.get(messageClient);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return;
            }

            assertFalse(checkUserAccountsExisting.get());

            //getUserAccountsExist & setUserAccountsExists
            messageClient.setUserAccountsExists(false);

            assertFalse(messageClient.getUserAccountsExist());

            //setClientMessageLoginRegister
            char[] pass = password.toCharArray();
            messageClient.setClientMessageLoginRegister(true, username, pass);

            assertEquals(clientMessageLogin, messageClient.getClientMessage());

            messageClient.setClientMessageLoginRegister(false, username, pass);

            assertEquals(clientMessageRegister, messageClient.getClientMessage());

            //setClientMessageMessaging
            messageClient.setClientUsername(username);
            messageClient.setClientMessageMessaging(message, members);

            assertEquals(clientMessageMessaging, messageClient.getClientMessage());

            //setClientMessageDeleteAccount
            messageClient.setClientMessageDeleteAccount();

            assertEquals(clientMessageDeleteAccount, messageClient.getClientMessage());

            //setClientMessageChangePassword
            messageClient.setClientMessageChangePassword(password);

            assertEquals(clientMessageChangePassword, messageClient.getClientMessage());

            //setClientMessageDeleteUser
            messageClient.setClientMessageDeleteUser(conversation2);

            assertEquals(clientMessageDeleteUser, messageClient.getClientMessage());

            //setClientMessageUpdateChat
            messageClient.setClientMessageUpdateChat(conversation2);

            assertEquals(clientMessageUpdateChat, messageClient.getClientMessage());

            //setClientMessageNewChat

            //initializeConversations is private and run in the run method of MessageHandler
            //Further details in README or comments of MessageHandler
            //getConversations & setConversations
            ArrayList<Conversation> conversationsTest = new ArrayList<>(Arrays.asList(conversation1, conversation2));

            messageClient.setConversations(conversationsTest);

            assertEquals(conversationsTest, messageClient.getConversations());

        }

        //::MessageServer - 0

        //::DisplayMessageGUI - 5
        @Test(timeout = 1000)

        public void testDisplayMessageGUI() {
            ArrayList<String> members = new ArrayList<String>(Arrays.asList("member1", "member2", "member3"));
            ArrayList<String> messages = new ArrayList<String>(
                    Arrays.asList("member1|message1", "member2|message2", "member3|message3"));
            Conversation conversation = new Conversation(members, messages);
            MessageClient messageClient = new MessageClient();
            DisplayMessageGUI displayMessageGUI = new DisplayMessageGUI(conversation, messageClient);

            //setMessageLabel
            assertEquals("member1 | member2 | member3", displayMessageGUI.setMessageLabel());

            //getMessageLabel
            assertEquals("member1 | member2 | member3", displayMessageGUI.getMessageLabel());

            //getConversation
            assertEquals(conversation, displayMessageGUI.getConversation());
        }


        //::ClientManager - 5
        @Test(timeout = 1000)
        public void testClientManager() {
            //General Class: ClientManager
            ClientManager clientManager = new ClientManager();
            Field field;
            String key = "test";
            Socket socket;
            HashMap<String, MessageHandler> deliverTo;
            MessageHandler messageHandler;

            try {
                socket = new Socket("localhost", 0);
                messageHandler = new MessageHandler(socket);
            } catch (IOException e) {
                return;
            }

            try {
                field = ClientManager.class.getDeclaredField("deliverTo");
                field.setAccessible(true);
                deliverTo = (HashMap<String, MessageHandler>) field.get(clientManager);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return;
            }

            //addTrace
            clientManager.addTrace(key, messageHandler);

            assertEquals(deliverTo.get(key), messageHandler);

            //getDeliverTo
            assertEquals(deliverTo, clientManager.getDeliverTo());
        }

        //::Conversation - 8
        @Test(timeout = 1000)
        public void testConversation() {
            //General Class: Conversation
            ArrayList<String> messages;
            ArrayList<String> members;
            String[] membersArray = {"User1", "User2", "User3"};
            String[] messagesArray = {"Message1", "Message2", "Message3"};
            ArrayList<String> membersList = new ArrayList<>(Arrays.asList(membersArray));
            ArrayList<String> messagesList = new ArrayList<>(Arrays.asList(messagesArray));
            String[] membersArrayTest;
            String[] messagesArrayTest;
            ArrayList<String> membersListTest;
            ArrayList<String> messagesListTest;
            Conversation conversation = new Conversation(null, null);


            //getMembers & setMembers
            conversation.setMembers(membersList);

            assertEquals(membersList, conversation.getMembers());

            //getMessages & setMessages
            conversation.setMessages(messagesList);

            assertEquals(messagesList, conversation.getMessages());

            //removeMessageAtIndex & addMessage
            messagesArrayTest = new String[]{"Message1", "Message2"};
            messagesListTest = new ArrayList<>(Arrays.asList(messagesArrayTest));

            conversation.removeMessageAtIndex(2);

            assertEquals(conversation.getMessages(), messagesListTest);

            //removeMemberWithName
            membersArrayTest = new String[]{"User1", "User3"};
            membersListTest = new ArrayList<>(Arrays.asList(membersArrayTest));

            conversation.removeMemberWithName("User2");

            assertEquals(conversation.getMembers(), membersListTest);

            //editMessageAtIndex
            messagesArrayTest = new String[]{"Message1", "MessageTest"};
            messagesListTest = new ArrayList<>(Arrays.asList(messagesArrayTest));

            conversation.editMessageAtIndex(1, "MessageTest");

            assertEquals(conversation.getMessages(), messagesListTest);

            //addMessage
            messagesArrayTest = new String[]{"Message1", "MessageTest", "AddedMessage"};
            messagesListTest = new ArrayList<>(Arrays.asList(messagesArrayTest));

            conversation.addMessage("AddedMessage");

            assertEquals(conversation.getMessages(), messagesListTest);
        }
    }


}