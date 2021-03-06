package principal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Receiver
{
    public static String IP;
    public static int PORT;
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static BufferedReader read;

    //Constructor de la clase, recibe la IP y el puerto de destino
    public static void initReceiver(String IP2, int PORT2) 
    {
        IP = IP2;
        PORT = PORT2;
        try 
        {
            socket = new Socket(IP, PORT);            
            out = new DataOutputStream(socket.getOutputStream());
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
            out.writeUTF(s);
        }
        else if(type.equals("Player"))
        {
            Player p = (Player)data;
            //out.writeBytes(p);
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
                in = new DataInputStream(socket.getInputStream());
                return in.readUTF();
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
        //Player player = (Player)in.readUTF();
        return null;
    }
    
/*
    public void run() {
        try {
            SocketChannel sChannel = SocketChannel.open();
            sChannel.configureBlocking(true);
            if (sChannel.connect(new InetSocketAddress(IP, PORT))) {
                DataInputStream in = new DataInputStream(sChannel.socket().getInputStream());
                DataOutputStream  out = new DataOutputStream(sChannel.socket().getOutputStream());
                Player player;
                try {
                    out.writeBytes("1\n");
                    player = (Player)in.readUTF();
                    while(player!=null){
                        System.out.println(player);
                        out.writeBytes("1\n");
                        player = (Player)in.readUTF();
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
