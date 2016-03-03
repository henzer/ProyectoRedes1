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
            positions.add(new Player(1, 1, 0));
        }
    }

    public static Player getPlayer(int n){
        return positions.get(n);
    }
    
    public static void setPlayer(int x, int y, int num){
        Player p = positions.get(num);
        p.setTileX(x);
        p.setTileY(y);
        p.setNumJugador(num);
    }

    public static ArrayList<Thread> getThreads() {
        return threads;
    }

    public static void addThread(Thread th) {
        ServerCore.threads.add(th);
    }

    public static String _toString() {
        String text = "";
        for(Player p: positions){
            text += p + "\t";
        }
        return text;
    }
    
    
    
    
}
