
import javax.swing.JFrame;


/**
 * FrameDriver.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 05-12-2012
 */

public class FrameDriver
{    
    public static void main(String[] args)
    {
        MainFrame gui = new MainFrame();        
        JFrame frame = gui.getFrame();
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
        
    }
}
