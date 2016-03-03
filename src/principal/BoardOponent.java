package principal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Esta clase es la que maneja dibuja el Laberinto y Maneja los movimientos del jugador.
public class BoardOponent extends JPanel
{
    //Variables a utilizar
    
    //Tiempo de transicion entre casillas.
    private Timer timer;
    //Este es el Laberinto
    private Map map;
    //Jugador
    private Player player;
    //Mensaje a mostrar al ganador.
    private String Message="";
    private Font font = new Font("Sans Serif",Font.BOLD, 48);
    //Determinar si gano o no el juego.
    private boolean win = false;
    private Player temp;

    //Constructor para inicializar todos los procesos.
    public BoardOponent(Player p)
    {
        map = new Map();
        player = p;
        temp = new Player(1, 1, p.getNumJugador());
        setFocusable(true);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(String nameFile) {
        this.map = new Map(nameFile);
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
                        g.drawImage(map.getGrass(), x*10, y*10, null);
                    }
                    if(map.getMap(x, y)==1)
                    {
                        g.drawImage(map.getWall(), x*10, y*10, null);
                    }
                    if(map.getMap(x, y)==4)
                    {
                        g.drawImage(map.getStart(), x*10, y*10, null);
                    }
                    if(map.getMap(x, y)==5)
                    {
                        g.drawImage(map.getFinish(), x*10, y*10, null);
                    }
                }
            } 
           
            //Dibuja el jugador.
            g.drawImage(img.getImage(),player.getTileX()*10, player.getTileY()*10, null);
        }
        
        
    }
    
    public void paintPlayer(){
        Graphics g = this.getGraphics();
        //Verifica si ya gano el jugador.
        
        if(player.getTileX()==39 && player.getTileY()==19){
            //Font para los mensajes
            System.out.println("Ganador");
            System.out.println("");
            g.setColor(Color.yellow);
            g.setFont(font);
            
            g.drawString("Winner", 130,250);
            repaint();
        }else{
            int x = temp.getTileX();
            int y = temp.getTileY();
            if(player.getTileX()!=temp.getTileX() || player.getTileY()!=temp.getTileY()){
                if(map.getMap(x, y)==0)
                    g.drawImage(map.getGrass(), x*10, y*10, null);
                if(map.getMap(x, y)==4)
                    g.drawImage(map.getStart(), x*10, y*10, null);
                if(map.getMap(x, y)==5)
                    g.drawImage(map.getFinish(), x*10, y*10, null);
                temp.setTileX(player.getTileX());
                temp.setTileY(player.getTileY());
            }

            ImageIcon img = new ImageIcon("smile.png");
            g.drawImage(img.getImage(),player.getTileX()*10, player.getTileY()*10, null);
        }
    }
   
}
