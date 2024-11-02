package dependecies;

import java.awt.*;

public class clock {
    private boolean White;
    private int timeLeft;
    private boolean active;
    private Component component; 
    private int posX;
    private int posY;
    private Integer M1, M2, S1, S2;
    private Thread countdownThread; 
    private final int width = 200; 
    private final int height = 60; 

    public clock(int TotalTime, boolean White, Component component, int posX, int posY) {
        this.active = false;
        this.White = White;
        this.timeLeft = TotalTime;
        this.component = component; 
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
                    component.repaint(posX, posY, width, height); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (timeLeft <= 0) {
                active = false;
                component.repaint(posX, posY, width, height); 
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

    public void DrawClock(Graphics g) {
        if(active){
            g.setColor(Color.yellow);
        }else{
            g.setColor(Color.red);

        }
        g.fillRoundRect(posX, posY, width, height, 10, 10);
        g.setColor(Color.GRAY);
        g.fillRoundRect(posX + 10, posY + 10, width - 20, height - 20, 10, 10);
        PrintTime(g);
    }

    private void PrintTime(Graphics g) {
        S1 = (timeLeft % 60) / 10;
        S2 = (timeLeft % 60) % 10;
        M1 = (timeLeft / 60) / 10;
        M2 = (timeLeft / 60) % 10;
        g.setColor(Color.WHITE);
        if (timeLeft <= 30) g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(":", posX + (width - g.getFontMetrics().stringWidth(":")) / 2, posY + 40);
        g.drawString(M1.toString(), posX + 20 + (30 - g.getFontMetrics().stringWidth(M1.toString())) / 2, posY + 40);
        g.drawString(M2.toString(), posX + 50 + (30 - g.getFontMetrics().stringWidth(M2.toString())) / 2, posY + 40);
        g.drawString(S1.toString(), posX + 120 + (30 - g.getFontMetrics().stringWidth(S1.toString())) / 2, posY + 40);
        g.drawString(S2.toString(), posX + 150 + (30 - g.getFontMetrics().stringWidth(S2.toString())) / 2, posY + 40);
    }
}
