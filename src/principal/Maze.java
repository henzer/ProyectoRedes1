package principal;

import javax.swing.*;

public class Maze
{
    //Metodo que inicializa TODO.
    public static void main(String[] args)
    {
        new Maze();
    }
    
    public Maze()
    {
        ClientGame cg = new ClientGame(0);
        
        cg.setTitle("Maze Tortrix");
        cg.setSize(1355, 740);
        cg.setLocationRelativeTo(null);
        cg.setVisible(true);
        cg.setDefaultCloseOperation(cg.EXIT_ON_CLOSE);
        
        //Se crea el Frame
//        JFrame frame = new JFrame();
//        frame.setTitle("Maze Tortrix");
//        frame.add(new Board());
//        frame.setSize(415,440);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
}