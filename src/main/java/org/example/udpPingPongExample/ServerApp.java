package org.example.udpPingPongExample;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerApp {
    static String parseAndGetRes(String st){
        if(st.equals("Ping")){
            return "Pong";
        }else {
            return "Not the right message";
        }
    }
    public static void main(String [] args){
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(3200); // fixed port number
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String resData = parseAndGetRes(new String(request.getData(),0,request.getLength()));
                DatagramPacket reply = new DatagramPacket(resData.getBytes(),
                        resData.length(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());}
        catch (IOException e) {System.out.println("IO: " + e.getMessage());}
        finally {if(aSocket != null) aSocket.close();}
    }
}
