package principal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Receiver
{
    public static String IP;
    public static int PORT;
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static BufferedReader read;

    //Constructor de la clase, recibe la IP y el puerto de destino
    public static void initReceiver(String IP2, int PORT2) 
    {
        IP = IP2;
        PORT = PORT2;
        try 
        {
            socket = new Socket(IP, PORT);            
            out = new ObjectOutputStream(socket.getOutputStream());
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    //Metodo para enviar información al servidor.
    public static void send(String type, Object data) throws IOException{
        if(type.equals("String"))
        {
            String s = (String)data;
            out.writeObject(s);
            System.out.println("hola");
            System.out.println("state "+s);
        }
        else if(type.equals("Player"))
        {
            Player p = (Player)data;
            //out.writeObject(p);
        }
    }
    
    //Metodo para obtener Strings del servidor
    //Se hace el while para que siga pidiendo el paquete, en caso se pierda Uno.
    public static Object getData() throws IOException
    {
        while(true)
        {
            try
            {
                in = new ObjectInputStream(socket.getInputStream());
                return in.readObject();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
    }
    
    //Metodo para obtener la informacion del jugador especificado en n.
    public static Player getDataPlayer(int n) throws IOException, ClassNotFoundException
    {
        out.writeInt(n);
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
