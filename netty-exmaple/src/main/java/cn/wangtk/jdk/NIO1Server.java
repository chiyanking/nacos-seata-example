package cn.wangtk.jdk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NIO1Server {

    static List<SocketChannel> channels = new ArrayList<SocketChannel>();


    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(8899));


        ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
        while (true) {
            SocketChannel socket = serverChannel.accept();
            Thread.sleep(200);
            if (socket == null) {
//                System.out.println("socket is null");
                continue;
            }
            System.out.println("socket is " + socket.getLocalAddress());
            socket.configureBlocking(false);
            channels.add(socket);

            while (true){
                for (SocketChannel channel : channels) {
                    buffer.clear();
                    buffer.rewind();
                    int read = channel.read(buffer);
                    if (read > 0) {
                        buffer.flip();
                        System.out.println(Charset.forName("UTF-8").decode(buffer).toString());
                    }
//                    System.out.println("read channel");
                }
            }
        }
    }

}
