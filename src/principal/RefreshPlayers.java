/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fred__000
 */
public class RefreshPlayers implements Runnable
{
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private int numJugador;

    public RefreshPlayers(String IP, int port, int numJugador) 
    {
        try 
        {
            socket = new Socket(IP, port);
            this.numJugador = numJugador;
            System.out.println("Constructor RP");
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() 
    {
        try 
        {
            System.out.println("Run RP");
            //in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
            while(true)
            {
                System.out.println("Primero");
                out.writeObject("getSP");
                System.out.println("Segundo");
                System.out.println(getDataPlayer(numJugador));;
            }
            
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RefreshPlayers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    //Metodo para obtener la informacion del jugador especificado en n.
    public static Player getDataPlayer(int n) throws IOException, ClassNotFoundException
    {
        in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(n+"");
        Player player = (Player)in.readObject();
        return player;
    }
}
