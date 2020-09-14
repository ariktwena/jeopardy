package jeopardy.server;

import jeopardy.questionReader.InputStreamFromFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

public class MyServer implements Runnable{

    //We create a socket so we can have more connections
    private final Socket socket;

    //We create a constructor for the socket
    public MyServer(Socket socket) {
        this.socket = socket;
    }

    //This code handles the thread via the runnable method (Runnable)
    @Override
    public void run() {

        //Handle IO exception
        try{
            //We put the socket to a scanner, so we can visually se the messges from the socket
            Scanner scanner = new Scanner(socket.getInputStream());

            //We use the "printOut" to write visual messages (Like System.out.println())
            //WE CAN'T USE System.out.println for client messages
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            //We create the InputStreamFromFile class
            InputStreamFromFile inputStreamFromFile = new InputStreamFromFile();

            //We execute the reader that starts our program
            inputStreamFromFile.injectInputStreamToReader(scanner, writer);

            //We close the socket (We get to is when we write "quit")
            socket.close();

        } catch (IOException | ParseException e) {

            //The error message
            e.printStackTrace();

        }

    }

    //The overall RUN variable
    public static volatile boolean keepRunning = true;

    public static void main(String[] args) throws IOException {

        //Localhost message
        System.out.println("Connect: telnet 127.0.0.1 6060");

        //Local port: There is op to 1000. After 1000 we need special permission.
        final int port = 6060;

        //Create "main" socket from port for a 1 to 1 connection
        final ServerSocket serverSocket = new ServerSocket(port);

        //The server keeps listening for connections
        while (keepRunning){

            //Creates the specific socket connection
            Socket socket = serverSocket.accept();

            //Get connection message and IP from client and server port from client
            System.out.println("[CONECTED] client IP: " + socket.getInetAddress() + " client port: " + socket.getPort());
            System.out.println("From local IP: " + socket.getLocalAddress() + " local port: " + socket.getLocalPort());

            //We create a thread from the MyFirstServer constructor
            Thread thread = new Thread(new MyServer(socket));

            //We start the thread
            thread.start();

        }

    }

}
