package org.example.udpPingPongExample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ClientApp {


    public static void main(String [] args){
        DatagramSocket datagramSocket=null;
        while(true) {
            try {
                datagramSocket = new DatagramSocket();
                Scanner scan = new Scanner(System.in);
                String content = scan.next();
                if (content.equals("Exit")) {
                    return;
                }
                byte[] m = content.getBytes();
                InetAddress aHost = InetAddress.getLocalHost();
                int serverPort = 3200;
                DatagramPacket request = new DatagramPacket(m, content.length(), aHost, serverPort);
                datagramSocket.send(request);
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));

            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }

        }
    }
}
