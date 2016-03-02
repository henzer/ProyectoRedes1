/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/*
* Esta clase carga el laberinto y las imagenes a utilizar en el mismo.
*/
public class Map 
{
    //Para leer el archivo a ingresar.
    private Scanner map;
    //Arreglo que contendra el laberinto.
    private int[][] Map = new int[40][40];
    //Variables para las imagenes en el Laberinto.
    private Image grass, wall, finish, start;
    
    public Map()
    {
        //Imagen de la grama.
        ImageIcon img = new ImageIcon("grass.png");
        grass = img.getImage();
        
        //Imagen de la Pared.
        img = new ImageIcon("wall.png");
        wall = img.getImage();
        
        //Imagen de la Pared.
        img = new ImageIcon("start.png");
        start = img.getImage();
        
        //Imagen de Fin.
        img = new ImageIcon("finish.png");
        finish = img.getImage();
        //Funciones para cargar el archivo
        openFile();
        readFile();
        closeFile();
    }
    
    //Metodo para devolver la imagen de Grama.
    public Image getGrass()
    {
        return grass;
    }
    
    //Metodo para devolver la imagen de la Pared.
    public Image getWall()
    {
        return wall;
    }
    
    //Metodo para devolver la imagen de Start.
    public Image getStart()
    {
        return start;
    }
    
    //Metodo para devolver la imagen de Finish.
    public Image getFinish()
    {
        return finish;
    }
    //Obtengo la letra que esta en esa posicion.
    //Este metodo se utilizara en el momento de pintar el Canvas.
    public int getMap(int x, int y)
    {
        //String index = Map[y].substring(x,x+1);
        return Map[x][y];
    }

    //Metodo para abrir el archivo.
    private void openFile() 
    {
        try 
        {
            map = new Scanner(new File("matrizLaberinto.txt"));
        } 
        catch (Exception e) 
        {
            System.out.println("Error cargando el Laberinto");
        }
    }

    //Metodo para leer el archivo y guardarlo en una lista de String.
    private void readFile() 
    {
        while(map.hasNext())
        {
            for(int i=0; i<40; i++)
            {
                String line = map.next();
                for(int j=0; j<line.length(); j++){
                    Map[i][j] = Integer.parseInt(line.charAt(j) + "");
                }
                //Map[i] = map.next();
            }
        }
    }

    //Metodo para cerrar el archivo.
    private void closeFile() 
    {
        map.close();
    }
}
