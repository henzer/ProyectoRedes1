package principal;

import java.io.BufferedReader;
import java.io.DataInputStream;
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
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String line = in.readUTF();
            DataOutputStream  out = new DataOutputStream(socket.getOutputStream());
            System.out.println(line);
            while(!line.equals("quit")){
                switch(line){
                    case "ready":
                        if (ServerCore.actualPlayer <ServerCore.limitPlayer){
                            out.writeUTF(ServerCore.actualPlayer+"," + ServerCore.limitPlayer);
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
                            String s = "start";
                            out.writeUTF(s+"");
                        }else{
                            System.out.println("Ya no hay cupo");
                            out.writeUTF("-1");
                        }
                        break;
                    case "getSP":
                        //System.out.println("getSP");
                        int n  = Integer.parseInt(in.readUTF()); //Lee el jugador que se le esta solicitando.
                        String s = ServerCore.getPlayer(n).toString();
                        out.writeUTF(s);
                        break;
                    case "setSP":
                        String[] sp = in.readUTF().split(",");
                        Player p = new Player(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), Integer.parseInt(sp[2]));
                        ServerCore.setPlayer(Integer.parseInt(sp[0]), Integer.parseInt(sp[1]), Integer.parseInt(sp[2]));
                        break;
                    case "whoIsWinner":
                        out.writeUTF(ServerCore.winner.getNumJugador() + "");
                        break;
                }
                
                line = in.readUTF();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void respond(ObjectOutputStream out, Object data) throws IOException{
        out.reset();
        out.writeObject(data);
    }
    public void notifyAction(String action){
        this.action = action;
    }
    
    
}
