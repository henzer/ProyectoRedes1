/*
* Autor: Henzer García
* 
*/

package principal;

import javax.swing.JPanel;

public class RefreshOponents implements Runnable{
    private BoardOponent panel;

    public RefreshOponents(BoardOponent panel) {
        this.panel = panel;
    }
    
    @Override
    public void run() {
        while(true){
            if(ClientCore.isWinner)break;
            panel.paintPlayer();
        }
    }

}
