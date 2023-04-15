package org.example.tcpPingPongExample;

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
            DataOutputStream dos = new DataOutputStream(clientSocketOutStream);

            if(dis.readUTF().equals("Ping")) {
                dos.writeUTF("Pong");
            }else{
                dos.writeUTF("Not the right message");
            }
            dis.close();
            dos.close();
            clientSocketInStream.close();
            clientSocketOutStream.close();
            clientSocket.close();
        }

    }
}
