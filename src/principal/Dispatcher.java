package principal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase encargada 
public class Dispatcher implements Runnable {
    private Socket socket;
    private static String action;

    public Dispatcher(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run() {
        System.out.println("Atendiendo");
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream  out = new ObjectOutputStream(socket.getOutputStream());
            String line = (String)in.readObject();
            System.out.println(line);
            while(!line.equals("quit")){
                switch(line){
                    case "ready":
                        if (ServerCore.actualPlayer <ServerCore.limitPlayer){
                            out.writeObject(ServerCore.actualPlayer + "");
                            ServerCore.actualPlayer++;
                            if(ServerCore.actualPlayer == ServerCore.limitPlayer){
                                System.out.println("==");
                                ServerCore.shutdown = false;
                            }else{
                                while(ServerCore.shutdown){
                                    System.out.print(".");
                                }
                                System.out.println("");
                            }
                            
                            out = new ObjectOutputStream(socket.getOutputStream());
                            out.writeObject("start");
                        }else{
                            System.out.println("Ya no hay cupo");
                            out.writeObject("-1");
                        }
                        break;
                    case "getSP":
                        System.out.println("getSP");
                        int n  = Integer.parseInt(in.readObject().toString()); //Lee el jugador que se le esta solicitando.
                        respond(ServerCore.getPlayer(n));
                        
                        break;
                    case "setSP":
                        System.out.println("setSP");
                        Player p = (Player)in.readObject();
                        System.out.println("Player: " + p);
                    
                }
                line = (String)in.readObject();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void respond(Object data) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(data);
    }
    public void notifyAction(String action){
        this.action = action;
    }
    
    
}
