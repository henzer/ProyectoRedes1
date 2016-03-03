/*
* Autor: Henzer García
* 
*/

package principal;

import javax.swing.JPanel;

public class RefreshOponents implements Runnable{
    private JPanel panel;

    public RefreshOponents(JPanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void run() {
        while(true){
            panel.repaint();
        }
    }

}
