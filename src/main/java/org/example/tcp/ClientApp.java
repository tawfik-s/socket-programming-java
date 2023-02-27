package org.example.tcp;

import java.io.*;
import java.net.Socket;

public class ClientApp {
    public static void main(String [] args)throws IOException {
        Socket socket=new Socket("localhost",3000);
        OutputStream socketOut=socket.getOutputStream();
        InputStream socketIn = socket.getInputStream();

        DataOutputStream dos = new DataOutputStream(socketOut);
        DataInputStream dis=new DataInputStream(socketIn);

        dos.writeUTF("welcome from client");

        String st = new String(dis.readUTF());
        System.out.println(st);

        dos.close();
        socketOut.close();
        dis.close();
        socketIn.close();
        socket.close();

    }
}
