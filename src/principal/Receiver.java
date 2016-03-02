package principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver{
    private String IP;
    private int PORT;
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    //Constructor de la clase, recibe la IP y el puerto de destino
    public Receiver(String IP, int PORT) {
        this.IP = IP;
        this.PORT = PORT;
        try {
            socket = new Socket(IP, PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    //Metodo para enviar información al servidor.
    public static void send(String type, Object data) throws IOException{
        if(type.equals("String")){
            String s = (String)data;
            out.writeBytes(s);
        }else if(type.equals("Player")){
            Player p = (Player)data;
            out.writeObject(p);
        }
    }
    
    public static String getData() throws IOException{
        return in.readUTF();
    }
    
    public static Player getDataPlayer() throws IOException, ClassNotFoundException{
        Player player = (Player)in.readObject();
        return player;
    }
    
/*
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
*/
}
