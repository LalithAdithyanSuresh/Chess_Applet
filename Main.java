import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import dependecies.*;
public class Main extends JFrame implements MouseListener {
    // Game Settings
    private int totalTime = 45;

    // Game Objects
    private board Board;
    private Image[] pieces;
    private coin[][] COB;
    private int[][] CoinClick;
    private boolean choosen;
    private player Player1;
    private player Player2;

    public Main() {
        setSize(800, 1300);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        loadImages();
        choosen = false;
        // Get player names and initialize objects
        String player1Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 1:");
        String player2Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 2:");

        Board = new board();
        Player1 = new player(player1Name, true, totalTime, this, 0, 50, 200, 800);
        Player2 = new player(player2Name, false, totalTime, this, 0, 1050, 200, 800);

        COB = new coin[8][8];
        CoinClick = new int[8][8];

        // Initializing Coins
        COB[0][0] = new coin(false,0,0,pieces[5],0,250,"rook");
        COB[1][0] = new coin(false,1,0,pieces[2],0,250,"knight");
        COB[2][0] = new coin(false,2,0,pieces[0],0,250,"bishop");
        COB[3][0] = new coin(false,3,0,pieces[1],0,250,"king");
        COB[4][0] = new coin(false,4,0,pieces[4],0,250,"queen");
        COB[5][0] = new coin(false,5,0,pieces[0],0,250,"bishop");
        COB[6][0] = new coin(false,6,0,pieces[2],0,250,"knight");
        COB[7][0] = new coin(false,7,0,pieces[5],0,250,"rook");

        COB[0][7] = new coin(false,0,7,pieces[11],0,250,"rook");
        COB[1][7] = new coin(false,1,7,pieces[8],0,250,"knight");
        COB[2][7] = new coin(false,2,7,pieces[6],0,250,"bishop");
        COB[3][7] = new coin(false,3,7,pieces[7],0,250,"king");
        COB[4][7] = new coin(false,4,7,pieces[10],0,250,"queen");
        COB[5][7] = new coin(false,5,7,pieces[6],0,250,"bishop");
        COB[6][7] = new coin(false,6,7,pieces[8],0,250,"knight");
        COB[7][7] = new coin(false,7,7,pieces[11],0,250,"rook");

        COB[0][6] = new coin(true,0,6,pieces[9],0,250,"pawn");
        COB[1][6] = new coin(true,1,6,pieces[9],0,250,"pawn");
        COB[2][6] = new coin(true,2,6,pieces[9],0,250,"pawn");
        COB[3][6] = new coin(true,3,6,pieces[9],0,250,"pawn");
        COB[4][6] = new coin(true,4,6,pieces[9],0,250,"pawn");
        COB[5][6] = new coin(true,5,6,pieces[9],0,250,"pawn");
        COB[6][6] = new coin(true,6,6,pieces[9],0,250,"pawn");
        COB[7][6] = new coin(true,7,6,pieces[9],0,250,"pawn");

        COB[0][1] = new coin(false,0,1,pieces[3],0,250,"pawn");
        COB[1][1] = new coin(false,1,1,pieces[3],0,250,"pawn");
        COB[2][1] = new coin(false,2,1,pieces[3],0,250,"pawn");
        COB[3][1] = new coin(false,3,1,pieces[3],0,250,"pawn");
        COB[4][1] = new coin(false,4,1,pieces[3],0,250,"pawn");
        COB[5][1] = new coin(false,5,1,pieces[3],0,250,"pawn");
        COB[6][1] = new coin(false,6,1,pieces[3],0,250,"pawn");
        COB[7][1] = new coin(false,7,1,pieces[3],0,250,"pawn");

        repaint(); 
    }

    private void loadImages() {
        pieces = new Image[13]; 
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
        };


        // Waiting for all images to load

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
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Board.DrawBoard(g, Color.darkGray, Color.gray, 0, 250, 100);
        Board.DrawHiglights(g,CoinClick);
        // Draw players
        Player1.PrintPlayer(g);
        Player2.PrintPlayer(g);
        drawPieces(g);
    }


    private void drawPieces(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (COB[i][j] != null) {
                    COB[i][j].drawCoin(g);
                }
            }
        }
    }






    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(choosen){
            choosen = false;
            CoinClick = new int[8][8];
        }
        if(x<=800 && x>=0 && y>=250 &&y<=1050){
            x = x/100;
            y = (y-250)/100;
            if(COB[x][y] != null){
                CoinClick[x][y] = 1;
                repaint();
            }
            choosen = true;
        }
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
