package dependecies;

import java.awt.*;

public class clock {
    private boolean White;
    private int timeLeft;

    public clock(int TotalTime,boolean White){
        this.White = White;
        this.timeLeft = TotalTime;
    }

    public void DrawClock(Graphics g,int posX,int posY){
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(posX, posY,200, 60,10,10);
        g.setColor(Color.gray);
        g.fillRoundRect(posX+10, posY+10,180, 40,10,10);
    }
}
