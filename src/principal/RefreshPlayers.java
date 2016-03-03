/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    private static DataInputStream in;
    private static DataOutputStream out;
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
            //in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            while(true)
            {
                out.writeUTF("getSP");
                System.out.println(getDataPlayer(numJugador));;
            }
            
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
    }   
    
    //Metodo para obtener la informacion del jugador especificado en n.
    public static Player getDataPlayer(int n) throws IOException, ClassNotFoundException
    {
        in = new DataInputStream(socket.getInputStream());
        out.writeUTF(n+"");
        String play = (String)in.readUTF();
        String nuevo[]= play.split(",");
        return new Player(Integer.parseInt(nuevo[0]),Integer.parseInt(nuevo[1]),Integer.parseInt(nuevo[2]));
    }
}
