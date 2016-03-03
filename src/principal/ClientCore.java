package principal;

import java.util.ArrayList;
import static principal.ServerCore.positions;

//Estructura para almacenar la informacion de los oponentes. 
public class ClientCore {
    public static ArrayList<int[][]> boards = new ArrayList();
    public static ArrayList<Player> positions = new ArrayList();
    public static Player actualPlayer = new Player();
    public static Player winner;
    public static boolean isWinner;

    public static void initClientCore(int number, int limit){
        for(int i=0; i<limit;i++){
            positions.add(new Player(1, 1, i));
            if(i==number)
                actualPlayer = positions.get(i);
        }
    }
    
    public static void addPlayer(Player b){
        positions.add(b);
        boards.add(new int[40][40]);
    }

    public static Player getPlayer(int n){
        return positions.get(n);
    }
    
    public static void setPlayer(Player pl){
        Player p = positions.get(pl.getNumJugador());
        if(p.getTileX()==39 && p.getTileY()==19){
            winner = p;
        }else{
            p.setTileX(pl.getTileX());
            p.setTileY(pl.getTileY());
            p.setNumJugador(pl.getNumJugador());
        }
    }
    
    public static int[] getIndexOponents(){
        int[] a = new int[positions.size()-1];
        int c = 0;
        for(Player p: positions){
            if(p.getNumJugador()!= actualPlayer.getNumJugador())//Se agregan los indices de los rivales, excepto del jugador actual
                a[c++] = p.getNumJugador();
        }
        return a;
    }
    
    public static String _toString() {
        String text = "";
        for(Player p: positions){
            text += p + "\t";
        }
        return text;
    }
    
}
