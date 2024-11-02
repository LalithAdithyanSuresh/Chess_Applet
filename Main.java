import java.applet.*;
import java.awt.*;
import dependecies.*;
public class Main extends Applet{
    board Board;
    public void init() {
        setSize(1000, 800);
        Board = new board();
    }
    public void paint(Graphics g) 
    {
        Board.DrawBoard(g, Color.black, Color.green, 100, 0, 100);
    }
} 