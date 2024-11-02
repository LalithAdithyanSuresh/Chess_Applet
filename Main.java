import java.applet.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import dependecies.*;

public class Main extends Applet implements MouseListener {
    // Game Settings
    private int totalTime = 600;

    // Game Objects
    private board Board;
    private Image[] pieces;
    private player Player1;
    private player Player2;
    private Rook rook;

    public void init() {
        setSize(800, 1200);
        setName("Chess Game");

        addMouseListener(this);
        loadImages();

        // Get player names and initialize them only once
        String player1Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 1:");
        String player2Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 2:");

        Board = new board();
        Player1 = new player(player1Name, true, totalTime, this, 0, 0, 200, 800);
        Player2 = new player(player2Name, false, totalTime, this, 0, 1000, 200, 800);
        rook = new Rook(true,pieces[7],0,200);
    }

    private void loadImages() {
        pieces = new Image[]{
            getImage(getDocumentBase(), "assets/black-bishop.png"),
            getImage(getDocumentBase(), "assets/black-king.png"),
            getImage(getDocumentBase(), "assets/black-knight.png"),
            getImage(getDocumentBase(), "assets/black-pawn.png"),
            getImage(getDocumentBase(), "assets/black-queen.png"),
            getImage(getDocumentBase(), "assets/black-rook.png"),
            getImage(getDocumentBase(), "assets/white-bishop.png"),
            getImage(getDocumentBase(), "assets/white-king.png"),
            getImage(getDocumentBase(), "assets/white-knight.png"),
            getImage(getDocumentBase(), "assets/white-pawn.png"),
            getImage(getDocumentBase(), "assets/white-queen.png"),
            getImage(getDocumentBase(), "assets/white-rook.png")
        };
    }

    public void paint(Graphics g) {
        Board.DrawBoard(g, Color.black, Color.gray, 0, 200, 100);
        // Draw players
        Player1.PrintPlayer(g);
        Player2.PrintPlayer(g);
        rook.drawCoin(g);
    }
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Mouse clicked at (" + x + ", " + y + ")");
    }
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
