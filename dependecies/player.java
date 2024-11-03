package dependecies;

import java.awt.*;

public class player {
    private String name;
    private boolean white;
    private int timeLeft;
    public coin[] coinsWon;
    public int coinWonCount;
    public clock Clock;
    private int posX, posY, height, width;

    public player(String name, boolean white, int totalTime, Component component, int posX, int posY, int height, int width) {
        this.name = name;
        this.white = white;
        this.timeLeft = totalTime;
        this.coinsWon = new coin[16];
        this.coinWonCount = 0;
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.Clock = new clock(this.timeLeft, this.white, component, posX + (white ? width - 210 : 10), posY + (white ? height - 70 : 10));
        Clock.start();
    }


    // Printing Stats of the player in the assigned area
    public void PrintPlayer(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 32));
        g.drawString(this.name, (white ? posX + 20 : width - g.getFontMetrics().stringWidth(this.name) - 20), posY + (white ? 40 : height - 40));
        Clock.DrawClock(g);
    }
}
