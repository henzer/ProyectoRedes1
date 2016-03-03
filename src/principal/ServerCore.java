package principal;

import java.util.ArrayList;

public class ServerCore {
    public static ArrayList<int[][]> games;
    public static ArrayList<Player> positions = new ArrayList();
    public static ArrayList<Thread> threads = new ArrayList();
    public static int limitPlayer; //Numero de jugadores en el servidor.
    public static int actualPlayer; //Numero de jugadores en el servidor.
    public static boolean shutdown = true;
    
    public ServerCore(int n){
        games = new ArrayList();
        positions = new ArrayList();
        limitPlayer = n; //Establece el numero maximo de jugadores
        //Dependiendo el numero de jugadores, así será el numero de laberintos.
        for(int i = 0; i<n; i++){ 
            games.add(new int[40][40]);
            positions.add(new Player(0, 0, 0));
        }
    }
    
    public static void addPlayer(Player b){
        positions.add(b);
    }
    
    
    
    public static Player getPlayer(int n){
        return positions.get(n);
    }
    
    public static void setPlayer(Player p){
        positions.add(p);
    }

    public static ArrayList<Thread> getThreads() {
        return threads;
    }

    public static void addThread(Thread th) {
        ServerCore.threads.add(th);
    }
    
    
    
}
