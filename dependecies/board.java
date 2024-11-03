package dependecies;
import java.awt.*;


public class board{
    boolean white;
    private int posX;
    private int posY;
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
    
    // To draw the checkered board
    public void DrawBoard(Graphics g,Color a,Color b, int posX,int posY,int size){
        this.posX = posX;
        this.posY = posY;
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
    // To draw possible moves nd current selected piece
    public void DrawHiglights(Graphics g,int[][] CoinClick) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (CoinClick[i][j] == 1) {
                    g.setColor(new Color(0, 255, 0, 128));
                    g.fillRect(posX + (100 * i), posY + (100*j), 100,100);
                }else if(CoinClick[i][j] == 2){
                    g.setColor(new Color(255, 0, 0, 90));
                    g.fillRect(posX + (100 * i), posY + (100*j), 100,100);
                }else if(CoinClick[i][j] == 3 ){
                    g.setColor(new Color(255, 255, 0, 90));
                    g.fillOval(posX + 35 + (100 * i), posY + 35 +(100*j), 30,30);
                }
            }
        }
    }
}
