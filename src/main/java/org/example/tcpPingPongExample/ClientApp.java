package org.example.tcpPingPongExample;

import java.io.*;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String [] args)throws IOException {
        while(true) {
            Scanner scan = new Scanner(System.in);
            String content = scan.next();
            if (content.equals("Exit")) {
                return;
            }
            Socket socket=new Socket("localhost",3000);
            OutputStream socketOut = socket.getOutputStream();
            InputStream socketIn = socket.getInputStream();

            DataOutputStream dos = new DataOutputStream(socketOut);
            DataInputStream dis = new DataInputStream(socketIn);

            dos.writeUTF(content);

            String st = new String(dis.readUTF());
            System.out.println(st);

            dos.close();
            socketOut.close();
            dis.close();
            socketIn.close();
            socket.close();
        }
    }
}
