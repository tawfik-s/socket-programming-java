package org.example.calcServerExampleTcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    static int parseAndGetSum(String st){
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
    public static void main(String args[])throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        while(true){
            Socket clientSocket = serverSocket.accept();
            OutputStream clientSocketOutStream = clientSocket.getOutputStream();
            InputStream clientSocketInStream = clientSocket.getInputStream();
            DataInputStream dis= new DataInputStream(clientSocketInStream);
            DataOutputStream dos = new DataOutputStream(clientSocketOutStream);

            String clientSt = dis.readUTF();

            int res= parseAndGetSum(clientSt);
            System.out.println("I am server res is "+res);
            dos.writeUTF(Integer.toString(res));
            dis.close();
            dos.close();
            clientSocketInStream.close();
            clientSocketOutStream.close();
            clientSocket.close();
        }

    }
}
