package dependecies;
import java.awt.*;


public class board{
    public void DrawBoard(Graphics g,Color a,Color b, int posX,int posY,int size){
        boolean white = true;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(white){
                    white = false;
                    g.setColor(a);
                    g.fillRect(posX + (100 * i), posY + (100*j), size, size);
                }else{
                    white = true;
                    g.setColor(b);
                    g.fillRect(posX + (100 * i), posY + (100*j), size, size);
                }
            }
            if(white){
                white = false;
            }else{
                white = true;
            }
        }
    }
}
