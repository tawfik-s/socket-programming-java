package org.example.task1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    public static void main(String [] args)throws IOException {
        Socket socket=new Socket("localhost",3000);
        OutputStream socketOut=socket.getOutputStream();
        InputStream socketIn = socket.getInputStream();

        DataOutputStream dos = new DataOutputStream(socketOut);
        DataInputStream dis=new DataInputStream(socketIn);

        Scanner scan=new Scanner(System.in);
        int num1=scan.nextInt();
        int num2=scan.nextInt();
        dos.writeUTF(num1+" "+num2);

        String st = new String(dis.readUTF());
        System.out.println(st);

        dos.close();
        socketOut.close();
        dis.close();
        socketIn.close();
        socket.close();

    }
}
