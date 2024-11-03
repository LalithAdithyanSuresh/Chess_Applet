package dependecies;

import java.awt.*;

public class coin {
    public boolean White;
    public int X;
    public int Y;
    private int dX;
    private int dY;
    private Image coinImage;
    public String type;

    public coin(boolean white,int x,int y,Image cI,int dX,int dY,String Type){
        this.White = white;
        this.X = x;
        this.Y = y;
        this.coinImage = cI;
        this.dX = dX;
        this.dY = dY;
        this.type = Type;
    }

    // Another constructor to create a new coin
    public coin(coin other) {
        this.White = other.White;
        this.X = other.X;
        this.Y = other.Y;
        this.dX = other.dX;
        this.dY = other.dY;
        this.coinImage = other.coinImage; 
        this.type = new String(other.type); 
    }
    // To draw coin in the grid
    public void drawCoin(Graphics g){
        g.drawImage(coinImage, dX + ((X)*100), dY + ((Y)*100),100,100, null);
    }

}

