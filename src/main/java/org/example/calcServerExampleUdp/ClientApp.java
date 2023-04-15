package org.example.calcServerExampleUdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientApp {


    public static void main(String [] args){
        DatagramSocket datagramSocket=null;
        try{
            datagramSocket = new DatagramSocket();
//            Scanner scan=new Scanner(System.in);
//            int num1=scan.nextInt();
//            int num2=scan.nextInt();
//            String content=num1+" "+num2;
            String content=50+" "+50;
            byte [] m = content.getBytes();
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 3200;
            DatagramPacket request = new DatagramPacket(m,  args[0].length(), aHost, serverPort);
            datagramSocket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(reply);
            System.out.println("Reply: " + new String(reply.getData()));

        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());}
        catch (IOException e){System.out.println("IO: " + e.getMessage());}


    }
}
