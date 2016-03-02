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
            
            while(!line.equals("quit")){
                switch(line){
                    case "ready":
                        if (ServerCore.actualPlayer <ServerCore.limitPlayer){
                            out.writeBytes(ServerCore.actualPlayer + "\n");
                            ServerCore.actualPlayer++;
                        }else{
                            out.writeBytes(-1 + "\n");
                        }
                }
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void notifyAction(String action){
        this.action = action;
    }
    
    
}
