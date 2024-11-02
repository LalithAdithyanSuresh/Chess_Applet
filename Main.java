import java.applet.*;
import java.awt.*;

import javax.swing.JOptionPane;

import dependecies.*;

public class Main extends Applet{

    // Game Settings
    int TotalTime = 600;


    board Board;
    player Player1;
    player Player2;
    

    public void init() {
        setSize(800, 1200);
        setName("Chess Game");
        Player1 = new player(JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 1 :"),true,TotalTime);
        Player2 = new player(JOptionPane.showInputDialog(this, "Please enter Name of PLAYER 2 :"),false,TotalTime);

        Board = new board();
    }
    public void paint(Graphics g) 
    {
        Board.DrawBoard(g, Color.black, Color.gray, 0, 200, 100);
        Player1.PrintPlayer(g,0,0,200,800);
        Player2.PrintPlayer(g,0,1000,200,800);
    }
} 