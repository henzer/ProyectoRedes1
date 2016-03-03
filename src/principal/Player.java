/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author fred__000
 */
public class Player implements Serializable
{
    //Fila y Columna.
    private int tileX, tileY;
    
    private int numJugador;
    
    private int score;

    public Player(int tileX, int tileY, int numJugador) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.numJugador = numJugador;
        this.score = 0;
    }
    
    public Player()
    {
        //Cargamos la imagen del jugador.
        ImageIcon img = new ImageIcon("smile.png");
        //Cantidad de Columnas o Filas entre las que se mueve.
        tileX = 1;
        tileY = 1;
        
        score = 0;
    }
    
    //Devuelve la Posicion en X.
    public int getTileX()
    {
        return tileX;
    }
    
    //Devuelve la Posicion en Y..
    public int getTileY()
    {
        return tileY;
    }

    //Devuelve el punteo del jugador
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int modifyScore(long time){
        return score = (int)time * 10;
    }

    public void setNumJugador(int numJugador) 
    {
        this.numJugador = numJugador;
    }
    
    //Identificador del jugador.
    public int getNumJugador()
    {
        return numJugador;
    }
    
    /*
    * Metodo que indica la direccion en la que se movera el jugador.
    * X=-1 --> Izquierda
    * X=1 --> Derecha
    * Y=-1 --> Abajo
    * Y=1 --> Arriba
    */
    public void move(int dx, int dy)
    {
        tileX += dx;
        tileY += dy;
    }

    @Override
    public String toString() 
    {
        return tileX + "," + tileY + "," + numJugador;
    }
    
    
    
}
