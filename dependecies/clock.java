package dependecies;

import java.awt.*;

public class clock {
    private boolean White;
    private int timeLeft;
    private boolean active;
    private Graphics g;
    private int posX;
    private int posY;
    private Integer M1,M2,S1,S2;
    private Thread countdownThread; 

    public clock(int TotalTime,boolean White,Graphics g,int posX,int posY){
        this.active = false;
        this.White = White;
        this.timeLeft = TotalTime;
        this.g = g;
        this.posX = posX;
        this.posY = posY;
    }

    public void start() {
        this.active = true;
        countdownThread = new Thread(() -> {
            while (active && timeLeft > 0) {
                try {
                    Thread.sleep(1000);
                    timeLeft--;
                    System.out.println(timeLeft);
                    // g.clearRect(posX, posY, 200, 60);
                    DrawClock();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (timeLeft <= 0) {
                active = false;
                // g.clearRect(posX, posY, 200, 60);
                DrawClock();
            }
        });
        countdownThread.start();
    }

    public void stop() {
        this.active = false;
        if (countdownThread != null) {
            countdownThread.interrupt();
        }
    }

    private void PrintTime(){
        S1 = (timeLeft%60) /10;
        S2 = (timeLeft%60) %10;
        M1 = (timeLeft/60) /10;
        M2 = (timeLeft/60) %10;
        g.setColor(Color.WHITE);
        if(timeLeft <=30)g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(":", posX + (200 - g.getFontMetrics().stringWidth(":"))/2, posY + 40);
        g.drawString(M1.toString(), posX + 20 + (30 - g.getFontMetrics().stringWidth(M1.toString()))/2, posY + 40);
        g.drawString(M2.toString(), posX + 50 + (30 - g.getFontMetrics().stringWidth(M2.toString()))/2, posY + 40);
        g.drawString(S1.toString(), posX + 120 + (30 - g.getFontMetrics().stringWidth(S1.toString()))/2, posY + 40);
        g.drawString(S2.toString(), posX + 150 + (30 - g.getFontMetrics().stringWidth(S2.toString()))/2, posY + 40);
        
    }

    // Printing Clock
    public void DrawClock(){
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(posX, posY,200, 60,10,10);
        g.setColor(Color.gray);
        g.fillRoundRect(posX+10, posY+10,180, 40,10,10);
        PrintTime();
    }
}
