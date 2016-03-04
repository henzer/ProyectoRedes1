package principal;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

//Esta clase es la que maneja dibuja el Laberinto y Maneja los movimientos del jugador.
public class Board extends JPanel
{
    //Variables a utilizar
    
    //Tiempo de transicion entre casillas.
    private Timer timer;
    //Este es el Laberinto
    private Map map;
    //Jugador
    private Player player;
    //Size de la casilla.
    private int sizeCell;
    //Mensaje a mostrar al ganador.
    private String Message="";
    private Font font = new Font("Sans Serif",Font.BOLD, 48);
    //Determinar si gano o no el juego.
    private boolean win = false;

    //Constructor para inicializar todos los procesos.
    public Board(Player p, int sizeCell)
    {
        map = new Map();
        player = p;
        this.sizeCell = sizeCell;
        
        addKeyListener(new ActionL());
        setFocusable(true);
        //Para realizar movimientos a cada 25 milisegundos.
        //timer = new Timer(5,this);
       //timer.start();
    }

    public Map getMap() {
        return map;
    }

    public void setMap(String nameFile) {
        this.map = new Map(nameFile);
    }

    public int getSizeCell() {
        return sizeCell;
    }

    public void setSizeCell(int sizeCell) {
        this.sizeCell = sizeCell;
    }

    
    
    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    //Metodo para crear las graficas y la pantalla.
    public void paint(Graphics g)
    {
        //cargamos el constructor
        super.paint(g);

        //Cargar la imagen en el Canvas.
        //Se hace primero Y porque muestra la fila.
        //Cuando se mueve Arriba/Abajo se mueve en X.
        ImageIcon img = new ImageIcon("smile.png");
        
        if(!win)
        {
           for(int y=0; y<40; y++)
            {
                for(int x=0; x<40; x++)
                {
                    /*
                    * 0 = espacios en blanco
                    * 1 = muros
                    * 2 = tunel entrada
                    * 3 = tunel salida
                    * 4 = origen
                    * 5 = destino
                    */
                    if(map.getMap(x, y)==0)
                    {
                        g.drawImage(map.getGrass(), x*sizeCell, y*sizeCell, null);
                    }
                    if(map.getMap(x, y)==1)
                    {
                        g.drawImage(map.getWall(), x*sizeCell, y*sizeCell, null);
                    }
                    if(map.getMap(x, y)==4)
                    {
                        g.drawImage(map.getStart(), x*sizeCell, y*sizeCell, null);
                    }
                    if(map.getMap(x, y)==5)
                    {
                        g.drawImage(map.getFinish(), x*sizeCell, y*sizeCell, null);
                    }
                }
            } 
           
            //Dibuja el jugador.
            g.drawImage(img.getImage(),player.getTileX()*sizeCell, player.getTileY()*sizeCell, null);
        }
        
        if(win)
        {
            try {
                Receiver.send("String", "whoIsWinner");
                int num = Integer.parseInt((String)Receiver.getData());
                if(num==this.player.getNumJugador()){
                    //Font para los mensajes.
                    g.setColor(Color.yellow);
                    g.setFont(font);
                    g.drawString(Message, 100,250);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } 
    }
    
    //Clase para determinar los movimientos con el Teclado.
    public class ActionL extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if(ClientCore.isWinner)return;
            
            int keycode = e.getKeyCode();
            //Flecha Arriba.
            if(keycode == KeyEvent.VK_UP)
            {
                //Validacion para que no se mueva a una pared.
                if(map.getMap(player.getTileX(),player.getTileY()-1)!=1)
                {
                    player.move(0, -1);
                }
            }
            //Flecha Abajo
            if(keycode == KeyEvent.VK_DOWN)
            {
                //Validacion para que no se mueva a una pared.
                if(map.getMap(player.getTileX(),player.getTileY()+1)!=1)
                {
                    player.move(0, 1);
                }
            }
            //Flecha Izquierda.
            if(keycode == KeyEvent.VK_LEFT)
            {
                //Validacion para que no se mueva a una pared.
                if(map.getMap(player.getTileX()-1,player.getTileY())!=1)
                {
                    player.move(-1, 0);
                }
            }
            //Flecha Derecha.
            if(keycode == KeyEvent.VK_RIGHT)
            {
                //Validacion para que no se mueva a una pared.
                if(map.getMap(player.getTileX()+1,player.getTileY())!=1)
                {
                    player.move(1, 0);
                }
            }
            if(map.getMap(player.getTileX(), player.getTileY())== 5){
                Message = "Winner";
                win = true;
                ClientCore.isWinner = true;
                player.setScore(1);
            }
            
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) 
        {
            
        }

        @Override
        public void keyTyped(KeyEvent e)
        {
            
        }
    }
}
