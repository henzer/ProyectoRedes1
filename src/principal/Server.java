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
        ServerSocket serverSocket = new ServerSocket(12345);
        ServerCore sc = new ServerCore(limit);
        while (true) {
            Socket connection = serverSocket.accept();
            Thread t = new Thread(new Dispatcher(connection));
            ServerCore.addThread(t);
            t.start();
        }
    }
    
}
