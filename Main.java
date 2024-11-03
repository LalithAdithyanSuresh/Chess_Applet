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
    private boolean CurrentWhite;
    private boolean Start;
    private int[] choosenCords;

    public Main() {
        setSize(800, 1300);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        loadImages();
        choosen = false;
        CurrentWhite = true;
        // Get player names and initialize objects
        String player1Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 1:");
        String player2Name = JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 2:");

        Board = new board();
        Player1 = new player(player1Name, true, totalTime, this, 0, 50, 200, 800);
        Player2 = new player(player2Name, false, totalTime, this, 0, 1050, 200, 800);

        COB = new coin[8][8];
        CoinClick = new int[8][8];
        Start = false;

        // Initializing Coins
        COB[0][0] = new coin(false,0,0,pieces[5],0,250,"rook");
        COB[1][0] = new coin(false,1,0,pieces[2],0,250,"knight");
        COB[2][0] = new coin(false,2,0,pieces[0],0,250,"bishop");
        COB[3][0] = new coin(false,3,0,pieces[1],0,250,"king");
        COB[4][0] = new coin(false,4,0,pieces[4],0,250,"queen");
        COB[5][0] = new coin(false,5,0,pieces[0],0,250,"bishop");
        COB[6][0] = new coin(false,6,0,pieces[2],0,250,"knight");
        COB[7][0] = new coin(false,7,0,pieces[5],0,250,"rook");

        COB[0][7] = new coin(true,0,7,pieces[11],0,250,"rook");
        COB[1][7] = new coin(true,1,7,pieces[8],0,250,"knight");
        COB[2][7] = new coin(true,2,7,pieces[6],0,250,"bishop");
        COB[3][7] = new coin(true,3,7,pieces[7],0,250,"king");
        COB[4][7] = new coin(true,4,7,pieces[10],0,250,"queen");
        COB[5][7] = new coin(true,5,7,pieces[6],0,250,"bishop");
        COB[6][7] = new coin(true,6,7,pieces[8],0,250,"knight");
        COB[7][7] = new coin(true,7,7,pieces[11],0,250,"rook");

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
        if(!Start){
            g.setColor(Color.GREEN);
            g.fillRect(300, 600, 200, 100);
            g.setColor(Color.BLACK);
            g.drawString("START", 400 - g.getFontMetrics().stringWidth("START") / 2, 660);
        }
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

    private void CheckCoinClick(int x, int y){
        if(x<=800 && x>=0 && y>=250 &&y<=1050){
            x = x/100;
            y = (y-250)/100;
            if(choosen){
                choosen = false;
                if(CoinClick[x][y] == 2 || CoinClick[x][y] ==3){
                    movePiece(x,y);
                    CurrentWhite = CurrentWhite ? false :true;
                    if(CurrentWhite){
                        Player2.Clock.start();
                        Player1.Clock.stop();
                    }else{
                        Player1.Clock.start();
                        Player2.Clock.stop();
                    }
                }
                CoinClick = new int[8][8];
            }
            if(COB[x][y] != null){
                if(CurrentWhite == COB[x][y].White){
                    CoinClick[x][y] = 1;
                    choosen = true;
                    choosenCords = new int[]{x,y};
                    possibleMovements(x, y);
                }
            }
            repaint();
        }
    }



    private void movePiece(int x,int y){
        coin temp = new coin(COB[choosenCords[0]][choosenCords[1]]);
        temp.X = x;
        temp.Y = y;

        // Remove the piece from the original position
        COB[choosenCords[0]][choosenCords[1]] = null;

        // Check if there's an opponent's piece to capture
        if (COB[x][y] != null) {
            // Capture logic: Add the captured coin to the appropriate player's collection
            if (CurrentWhite) {
                Player2.coinsWon[Player2.coinWonCount] = new coin(COB[x][y]);
                Player2.coinWonCount++;
            } else {
                Player1.coinsWon[Player1.coinWonCount] = new coin(COB[x][y]);
                Player1.coinWonCount++;
            }
        }

        // Place the piece in the new position
        COB[x][y] = temp;

        // Reset the chosen state
        choosen = false;
    }


    private void possibleMovements(int x,int y){
        if(COB[x][y] != null){
            // Rook  Movement
            if(COB[x][y].type.equals("rook")){
                // Right Movement
                for(int i=x+1;i<8;i++){
                    if(COB[i][y] != null){
                        if((COB[i][y].White ^ CurrentWhite)){
                            CoinClick[i][y] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[i][y] = 3;
                    }
                }
                // Left Movement
                for(int i=x-1;i>=0;i--){
                    if(COB[i][y] != null){
                        if((COB[i][y].White ^ CurrentWhite)){
                            CoinClick[i][y] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[i][y] = 3;
                    }
                }
                // Up Movement
                for(int i=y-1;i>=0;i--){
                    if(COB[x][i] != null){
                        if((COB[x][i].White ^ CurrentWhite)){
                            CoinClick[x][i] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[x][i] = 3;
                    }
                }
                // Down Movment
                for(int i=y+1;i<8;i++){
                    if(COB[x][i] != null){
                        if((COB[x][i].White ^ CurrentWhite)){
                            CoinClick[x][i] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[x][i] = 3;
                    }
                }
            }
            // Pawn Movement
            if(COB[x][y].type.equals("pawn")){
                if(COB[x][y].White){
                    // Single Step
                    if(y-1 >= 0 && COB[x][y-1] == null){
                        CoinClick[x][y-1] = 3;
                    }
                    // Double step
                    if(y == 6 && COB[x][y-2] ==null){
                        CoinClick[x][y-2] = 3;
                    }
                    // Diagonal Hits
                    if(x-1 >=0 && y-1 >=0 && COB[x-1][y-1] != null && (COB[x-1][y-1].White ^ CurrentWhite)){
                        CoinClick[x-1][y-1] = 2;
                    }
                    if(x+1 <8 && y-1 >=0 && COB[x+1][y-1] != null && (COB[x+1][y-1].White ^ CurrentWhite)){
                        CoinClick[x+1][y-1] = 2;
                    }
                }else{
                    // Single Step
                    if(y+1 <8 && COB[x][y+1] == null){
                        CoinClick[x][y+1] = 3;
                    }
                    // Double step
                    if(y == 1 && COB[x][y+2] ==null){
                        CoinClick[x][y+2] = 3;
                    }
                    // Diagonal Hits
                    if(x-1 >=0 && y+1 <8 && COB[x-1][y+1] != null && (COB[x-1][y+1].White ^ CurrentWhite)){
                        CoinClick[x-1][y+1] = 2;
                    }
                    if(x+1 <8 && y+1 <8 && COB[x+1][y+1] != null && (COB[x+1][y+1].White ^ CurrentWhite)){
                        CoinClick[x+1][y+1] = 2;
                    }
                }
            }
            // Bishop Movement
            if(COB[x][y].type.equals("bishop")){
                // TOP left
                for(int i=1;i<8;i++){
                    if(x-i >= 0 && y-i >= 0){
                        if(COB[x-i][y-i] != null){
                            if(COB[x-i][y-i].White ^ CurrentWhite){
                                CoinClick[x-i][y-i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x-i][y-i] = 3;
                    }
                }
                // Top Right
                for(int i=1;i<8;i++){
                    if(x+i < 8 && y-i >= 0){
                        if(COB[x+i][y-i] != null){
                            if(COB[x+i][y-i].White ^ CurrentWhite){
                                CoinClick[x+i][y-i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x+i][y-i] = 3;
                    }
                }
                // Bottom Left
                for(int i=1;i<8;i++){
                    if(x-i >= 0 && y+i < 8){
                        if(COB[x-i][y+i] != null){
                            if(COB[x-i][y+i].White ^ CurrentWhite){
                                CoinClick[x-i][y+i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x-i][y+i] = 3;
                    }
                }
                // Bottom Right
                for(int i=1;i<8;i++){
                    if(x+i <8 && y+i <8){
                        if(COB[x+i][y+i] != null){
                            if(COB[x+i][y+i].White ^ CurrentWhite){
                                CoinClick[x+i][y+i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x+i][y+i] = 3;
                    }
                }
            }
            // Queen Movement
            if(COB[x][y].type.equals("queen")){
                // Right Movement
                for(int i=x+1;i<8;i++){
                    if(COB[i][y] != null){
                        if((COB[i][y].White ^ CurrentWhite)){
                            CoinClick[i][y] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[i][y] = 3;
                    }
                }
                // Left Movement
                for(int i=x-1;i>=0;i--){
                    if(COB[i][y] != null){
                        if((COB[i][y].White ^ CurrentWhite)){
                            CoinClick[i][y] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[i][y] = 3;
                    }
                }
                // Up Movement
                for(int i=y-1;i>=0;i--){
                    if(COB[x][i] != null){
                        if((COB[x][i].White ^ CurrentWhite)){
                            CoinClick[x][i] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[x][i] = 3;
                    }
                }
                // Down Movment
                for(int i=y+1;i<8;i++){
                    if(COB[x][i] != null){
                        if((COB[x][i].White ^ CurrentWhite)){
                            CoinClick[x][i] = 2;
                            break;
                        }else {
                            break;
                        }
                    }else{
                        CoinClick[x][i] = 3;
                    }
                }
                // TOP left
                for(int i=1;i<8;i++){
                    if(x-i >= 0 && y-i >= 0){
                        if(COB[x-i][y-i] != null){
                            if(COB[x-i][y-i].White ^ CurrentWhite){
                                CoinClick[x-i][y-i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x-i][y-i] = 3;
                    }
                }
                // Top Right
                for(int i=1;i<8;i++){
                    if(x+i < 8 && y-i >= 0){
                        if(COB[x+i][y-i] != null){
                            if(COB[x+i][y-i].White ^ CurrentWhite){
                                CoinClick[x+i][y-i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x+i][y-i] = 3;
                    }
                }
                // Bottom Left
                for(int i=1;i<8;i++){
                    if(x-i >= 0 && y+i < 8){
                        if(COB[x-i][y+i] != null){
                            if(COB[x-i][y+i].White ^ CurrentWhite){
                                CoinClick[x-i][y+i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x-i][y+i] = 3;
                    }
                }
                // Bottom Right
                for(int i=1;i<8;i++){
                    if(x+i <8 && y+i <8){
                        if(COB[x+i][y+i] != null){
                            if(COB[x+i][y+i].White ^ CurrentWhite){
                                CoinClick[x+i][y+i] = 2;
                                break;
                            }else{
                                break;
                            }
                        }
                        CoinClick[x+i][y+i] = 3;
                    }
                }
            }
            // Knight Movement
            if(COB[x][y].type.equals("knight")){
                
            }
        }
    }




    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(Start){
            CheckCoinClick(x, y);
        }if(x>=300 && x<= 500 && y>=600 && y<=700){
            Start = true;
            Player2.Clock.start();
            Player1.Clock.stop();
            repaint();
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
