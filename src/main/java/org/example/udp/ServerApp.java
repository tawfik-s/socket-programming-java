package org.example.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerApp {
    static Integer parseAndGetSum(String st){
        int num1=0;
        int num2=0;
        boolean flag=true;
        for(int i=0;i<st.length();i++){
            if(st.charAt(i)>='0'&&st.charAt(i)<='9'){
                if(flag){
                    num1=num1*10 +(int)(st.charAt(i)-'0');
                }else{
                    num2=num2*10 +(int)(st.charAt(i)-'0');
                }
            }else if(st.charAt(i)==' '){
                flag=false;
            }
        }
        return num1+num2;
    }
    public static void main(String [] args){
        DatagramSocket aSocket = null;
        try{
            aSocket = new DatagramSocket(3200); // fixed port number
            byte[] buffer = new byte[1000];
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String resData = parseAndGetSum(new String(request.getData(),0,request.getLength()))
                        .toString();
                DatagramPacket reply = new DatagramPacket(resData.getBytes(),
                        resData.length(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());}
        catch (IOException e) {System.out.println("IO: " + e.getMessage());}
        finally {if(aSocket != null) aSocket.close();}
    }
}
