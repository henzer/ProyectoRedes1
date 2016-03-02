package principal;

import java.util.ArrayList;

public class ServerCore {
    public static ArrayList<int[][]> games;
    public static ArrayList<Player> positions = new ArrayList();
    
    public ServerCore(int n){
        games = new ArrayList();
        positions = new ArrayList();
        
        //Dependiendo el numero de jugadores, así será el numero de laberintos.
        for(int i = 0; i<n; i++){ 
            games.add(new int[40][40]);
            positions.add(new Player(0, 0));
        }
    }
    
    public static void addPlayer(Player b){
        positions.add(b);
    }
    
    public static Player getPlayer(int n){
        return positions.get(n);
    }
}
