package principal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Esta clase es la que maneja dibuja el Laberinto y Maneja los movimientos del jugador.
public class BoardOponent extends JPanel implements ActionListener
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

    //Constructor para inicializar todos los procesos.
    public BoardOponent(Player p)
    {
        map = new Map();
        player = p;

        setFocusable(true);
        //Para realizar movimientos a cada 25 milisegundos.
        timer = new Timer(5,this);
        timer.start();
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
    
    //Cada vez que se realice un movimiento.
    public void actionPerformed(ActionEvent e)
    {
        if(map.getMap(player.getTileX(), player.getTileY())== 5)
        {
            Message = "Winner";
            win = true;
        }
        repaint();
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
        
        if(win)
        {
            //Font para los mensajes.
            g.setColor(Color.yellow);
            g.setFont(font);
            g.drawString(Message, 100,250);
            repaint();
        } 
    }
   
}
