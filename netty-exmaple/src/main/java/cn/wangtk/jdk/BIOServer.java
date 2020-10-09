package cn.wangtk.jdk;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BIOServer {

    public static void main(String[] args) throws Exception {
        List<Socket> socketList = new ArrayList();
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8899));
        while (true) {
            Socket socket = serverSocket.accept();
            socketList.add(socket);
            System.out.println("连接已经建立");
            InputStream inputStream = socket.getInputStream();
            System.out.println("获取输入流");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] bytes = new byte[2048];
            while (dataInputStream.read(bytes) >= 0) {
                System.out.println(new String(bytes, "utf-8"));
            }
        }


    }
}
