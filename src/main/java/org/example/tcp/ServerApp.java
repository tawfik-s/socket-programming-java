package org.example.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String args[])throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        while(true){
            Socket clientSocket = serverSocket.accept();
            OutputStream clientSocketOutStream = clientSocket.getOutputStream();
            InputStream clientSocketInStream = clientSocket.getInputStream();
            DataInputStream dis= new DataInputStream(clientSocketInStream);
            System.out.println(dis.readUTF());

            DataOutputStream dos = new DataOutputStream(clientSocketOutStream);
            dos.writeUTF("HI there");
            dis.close();
            dos.close();
            clientSocketInStream.close();
            clientSocketOutStream.close();
            clientSocket.close();
        }

    }
}
