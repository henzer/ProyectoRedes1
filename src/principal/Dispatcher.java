package principal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase encargada 
public class Dispatcher implements Runnable {
    private Socket socket;
    private String action;

    public Dispatcher(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run() {
        System.out.println("Atendiendo");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());
            String line = in.readLine();
            int n = Integer.parseInt(line);
            int c = 0;
            while(line!=null){
                Player p = new Player(ServerCore.getPlayer(n).getTileX(), ServerCore.getPlayer(n).getTileY());
                out.writeObject(p);
                line = in.readLine();
                n = Integer.parseInt(line);
                System.out.println(line);
                c++;
                //this.wait();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void notifyAction(String action){
        this.action = action;
    }
    
    
}
