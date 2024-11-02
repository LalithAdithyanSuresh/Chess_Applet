import java.applet.*;
import java.awt.*;

public class Main extends Applet{
    public void init() {
        setSize(1000, 800);
    }
    public void paint(Graphics g) 
    {
        g.drawString("Hello World", 20, 20);
        g.setColor(Color.black);
        g.drawRect(100, 0, 800, 800);
    }
} 