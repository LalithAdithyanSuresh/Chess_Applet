package dependecies;
import java.awt.*;


public class board{
    boolean white;

    public board(){
        white = true;
    }

    private void colorSwitch(){
        if(white){
            white = false;
        }else{
            white = true;
        }
    }

    public void DrawBoard(Graphics g,Color a,Color b, int posX,int posY,int size){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(white){
                    colorSwitch();
                    g.setColor(a);
                    g.fillRect(posX + (100 * i), posY + (100*j), size, size);
                }else{
                    colorSwitch();
                    g.setColor(b);
                    g.fillRect(posX + (100 * i), posY + (100*j), size, size);
                }
            }
            colorSwitch();
        }
    }
}
