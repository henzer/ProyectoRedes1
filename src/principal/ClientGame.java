/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author fred__000
 */
public class ClientGame extends javax.swing.JFrame {

    /**
     * Creates new form ClientGame
     */
    long startTime = System.currentTimeMillis();
    private final SimpleDateFormat date = new SimpleDateFormat("mm.ss.SSS");
    private int numberPlayer;
    
    Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                updateClock();
            }
        });
    private void updateClock(){
        if(ClientCore.isWinner)
            timer.stop();
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long elapsedSeconds1 = elapsedSeconds%60;
        long elapsedMinutes = elapsedSeconds / 60;
        lblTime.setText(elapsedMinutes + " : " + elapsedSeconds1);
    }
    
    
    
    
    public ClientGame(int n, int limit) {
        initComponents();
        this.numberPlayer = n;
        timer.start();
        //Establece el titulo y los valores por defecto
        this.setTitle("Maze Tortrix");
        this.setSize(1050, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        ClientCore.initClientCore(n, 2);
        int sizeCell = 0;
        if(limit==2)
            sizeCell = 10;
        else
            sizeCell = 5;
        //crea los tableros para cada jugador1
        Board board1 = new Board(ClientCore.actualPlayer, 10);
        board1.setBounds(75, 100, 415, 440);
        
        BoardOponent board2 = new BoardOponent(ClientCore.getPlayer(ClientCore.getIndexOponents()[0]));
        board2.setBounds(565, 100, 415, 440);
        //Este es el hilo que se encargara de actualizar al oponente.
        Thread th = new Thread(new RefreshOponents(board2));
        th.start();
        
        this.jPanel1.add(board1);
        this.jPanel1.add(board2);

        
        
        int score1 = board1.getPlayer().getScore();
        int score2 = board2.getPlayer().getScore();

        //this.lblScore1.setText("" + score1);
        //this.lblScore2.setText("" + score2);
        
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        lblTime = new java.awt.Label();
        lblTime1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        label1.setFont(new java.awt.Font("Lucida Console", 0, 36)); // NOI18N
        label1.setText("Player 2");

        label2.setFont(new java.awt.Font("Lucida Console", 0, 36)); // NOI18N
        label2.setText("Player 1");

        lblTime.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N

        lblTime1.setAlignment(java.awt.Label.CENTER);
        lblTime1.setFont(new java.awt.Font("Lucida Console", 0, 24)); // NOI18N
        lblTime1.setText("Time:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(178, 178, 178))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addComponent(lblTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        label1.getAccessibleContext().setAccessibleName("Player 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label lblTime;
    private java.awt.Label lblTime1;
    // End of variables declaration//GEN-END:variables
}
