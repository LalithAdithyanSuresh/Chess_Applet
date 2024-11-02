package dependecies;

import java.awt.*;

public class player {
    private String Name;
    private boolean White;
    private int timeLeft;
    private coin[] CoinsWon;
    private int CoinWonCount;
    private clock Clock;
    public player(String name,boolean white,int totalTime){
        this.Name = name;
        this.White = white;
        this.timeLeft = totalTime;
        this.CoinsWon = new coin[16];
        this.CoinWonCount = 0;
        Clock = new clock(this.timeLeft,this.White);
    }
    public void PrintPlayer(Graphics g,int posX,int posY,int Height,int width){
        g.setFont(new Font("Arial", Font.BOLD, 32));
        if(White){
            g.drawString(this.Name, posX + 20, posY + 40);
            Clock.DrawClock(g, posX + width - 210, posY + Height - 70);

        }else{
            g.drawString(this.Name, width - g.getFontMetrics().stringWidth(this.Name) - 20, posY + Height - 40);
            Clock.DrawClock(g, posX + 10, posY + 10);
        }
    }
}
