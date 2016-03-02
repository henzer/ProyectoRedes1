package principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Server {  
    public void start(int limit) throws IOException{
        ServerSocket serverSocket = null;
        ServerCore sc = new ServerCore(limit);
        
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(true);
        int port = 12345;
        ssChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("Sender Start");
        
        
        while (true) {
            SocketChannel sChannel = ssChannel.accept();
            Thread t = new Thread(new Dispatcher(sChannel.socket()));
            t.start();
        }
    }
    
}
