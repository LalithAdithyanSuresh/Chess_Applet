import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import dependecies.*;
public class Main extends JFrame implements MouseListener {
    // Game Settings
    private int totalTime = 600;

    // Game Objects
    private board Board;
    private Image[] pieces;
    private player Player1;
    private player Player2;
    private Rook rook;

    public Main() {
        setSize(800, 1200);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        loadImages();

        // Get player names and initialize them only once
        String player1Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 1:");
        String player2Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 2:");

        Board = new board();
        Player1 = new player(player1Name, true, totalTime, this, 0, 0, 200, 800);
        Player2 = new player(player2Name, false, totalTime, this, 0, 1000, 200, 800);
        rook = new Rook(true, pieces[7], 0, 200);
        repaint(); // Call repaint after initialization
    }

    private void loadImages() {
        pieces = new Image[13]; // Create an array for your images
        String[] imagePaths = {
            "/assets/black-bishop.png",
            "/assets/black-king.png",
            "/assets/black-knight.png",
            "/assets/black-pawn.png",
            "/assets/black-queen.png",
            "/assets/black-rook.png",
            "/assets/white-bishop.png",
            "/assets/white-king.png",
            "/assets/white-knight.png",
            "/assets/white-pawn.png",
            "/assets/white-queen.png",
            "/assets/white-rook.png",
            "/assets/ISW_Logo.jpg"
        };

        MediaTracker tracker = new MediaTracker(this);
        
        for (int i = 0; i < imagePaths.length; i++) {
            try {
                pieces[i] = ImageIO.read(getClass().getResource(imagePaths[i]));
                tracker.addImage(pieces[i], i);
            } catch (IOException e) {
                System.err.println("Error loading image: " + imagePaths[i]);
                e.printStackTrace();
            }
        }

        try {
            tracker.waitForAll(); // Wait for images to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g); // Call to superclass to clear the background
        Board.DrawBoard(g, Color.black, Color.gray, 0, 200, 100);
        // Draw players
        Player1.PrintPlayer(g);
        Player2.PrintPlayer(g);
        if (pieces[11] != null) { // Changed index to 11 for the last image
            System.out.println(pieces[11]); // This should print a non-null reference if the image is loaded correctly.
            g.drawImage(pieces[11], 400, 500, 100, 100, this); // Transparent parts should render correctly.
        } else {
            System.out.println("Image not loaded correctly.");
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
}
