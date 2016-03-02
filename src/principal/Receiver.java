package principal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver implements Runnable{
    private String IP;
    private int PORT;

    public Receiver(String IP, int PORT) {
        this.IP = IP;
        this.PORT = PORT;
    }
    @Override
    public void run() {
        try {
            SocketChannel sChannel = SocketChannel.open();
            sChannel.configureBlocking(true);
            if (sChannel.connect(new InetSocketAddress(IP, PORT))) {
                ObjectInputStream in = new ObjectInputStream(sChannel.socket().getInputStream());
                DataOutputStream  out = new DataOutputStream(sChannel.socket().getOutputStream());
                Player player;
                try {
                    out.writeBytes("1\n");
                    player = (Player)in.readObject();
                    while(player!=null){
                        System.out.println(player);
                        out.writeBytes("1\n");
                        player = (Player)in.readObject();
                    }
                    
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
