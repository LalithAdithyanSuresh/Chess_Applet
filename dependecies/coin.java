package dependecies;

import java.applet.*;
import java.awt.*;

public class coin {
    private boolean White;
    private int X;
    private int Y;
    private int dX;
    private int dY;
    private Image coinImage;

    public coin(boolean white,int x,int y,Image cI,int dX,int dY){
        this.White = white;
        this.X = x;
        this.Y = y;
        this.coinImage = cI;
        this.dX = dX;
        this.dY = dY;

    }

    public void ChangePos(int X,int Y){
        this.X = X;
        this.Y = Y;
    }

    public void drawCoin(Graphics g){
        g.drawImage(coinImage, dX + ((X-1)*100), dY + ((Y-1)*100),100,100, null);
    }

}

