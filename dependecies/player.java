package dependecies;

import java.awt.*;

public class player {
    private String Name;
    private boolean White;
    private int timeLeft;
    private coin[] CoinsWon;
    private int CoinWonCount;
    private clock Clock;
    private Graphics g;
    private int posX,posY,Height,width;

    public player(String name,boolean white,int totalTime,Graphics g,int posX,int posY,int Height,int width){
        this.Name = name;
        this.White = white;
        this.timeLeft = totalTime;
        this.CoinsWon = new coin[16];
        this.CoinWonCount = 0;
        this.g = g;
        this.posX = posX;
        this.posY = posY;
        this.Height = Height;
        this.width = width;
    }

    // Printing Stats of the player in the assigned area
    public void PrintPlayer(){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        if(White){
            g.drawString(this.Name, posX + 20, posY + 40);
            Clock = new clock(this.timeLeft,this.White,g, posX + width - 210, posY + Height - 70);
            
        }else{
            g.drawString(this.Name, width - g.getFontMetrics().stringWidth(this.Name) - 20, posY + Height - 40);
            Clock = new clock(this.timeLeft,this.White,g, posX + 10, posY + 10);
        }
        Clock.DrawClock();
        Clock.start();
    }
}
