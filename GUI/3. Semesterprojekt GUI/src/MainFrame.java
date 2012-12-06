import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * MainFrame.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 05-12-2012
 */

public class MainFrame
{
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu system, users, itemTypes, view, help;    
    private JMenuItem systemInfo, login, logout, startSystem, stopSystem, quit;
    private JMenuItem cUser,vUser,eUser,
                      cItemType,vItemType, eItemType,
                      orders,
                      about;   
    
    private ImageIcon logo = new ImageIcon("images/logo.png");
    
    private ImageIcon systemInfoImg = new ImageIcon("images/systemMenu/systemInfo.png");
    private ImageIcon loginImg = new ImageIcon("images/systemMenu/login.png");
    private ImageIcon logoutImg = new ImageIcon("images/systemMenu/logout.png");
    private ImageIcon startSystemImg = new ImageIcon("images/systemMenu/startSystem.png");
    private ImageIcon stopSystemImg = new ImageIcon("images/systemMenu/stopSystem.png");
    private ImageIcon quitImg = new ImageIcon("images/systemMenu/quit.png");
    
    private ImageIcon cUserImg = new ImageIcon("images/usersMenu/cUser.png");    
    private ImageIcon vUserImg = new ImageIcon("images/usersMenu/vUser.png");
    private ImageIcon eUserImg = new ImageIcon("images/usersMenu/eUser.png");
    
    private ImageIcon cItemTypeImg = new ImageIcon("images/itemtypesMenu/cItemType.png");    
    private ImageIcon vItemTypeImg = new ImageIcon("images/itemtypesMenu/vItemType.png");
    private ImageIcon eItemTypeImg = new ImageIcon("images/itemtypesMenu/eItemType.png");
    
    private ImageIcon ordersImg = new ImageIcon("images/viewMenu/orders.png");
    
    private ImageIcon aboutImg = new ImageIcon("images/helpMenu/about.png");
    
    

    public MainFrame() 
    {
        frame = new JFrame("Central Storage System - Presentation Admin");
        frame.setSize(700, 450);
        
        this.initMenu();
        try {
            frame.add(new SystemStart());
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
    private void initMenu()
    {
        menuBar = new JMenuBar();//Create the menu bar.           
        
        //_________SYSTEM_______________________
        system = new JMenu("System");//Build the first menu.    
        system.setMnemonic(KeyEvent.VK_S);
        menuBar.add(system);
        
        systemInfo = new JMenuItem("System Information",systemInfoImg);
        systemInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Event.ALT_MASK | Event.SHIFT_MASK));
        systemInfo.addActionListener(new NotImplementedActionListener("System Information"));
        system.add(systemInfo);        
        
        system.addSeparator();
        
        login = new JMenuItem("User login",loginImg);
        system.add(login);  
        login.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                Event.ALT_MASK | Event.SHIFT_MASK));
        login.addActionListener(new NotImplementedActionListener("User login"));
        logout = new JMenuItem("User logout",logoutImg);
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Event.ALT_MASK | Event.SHIFT_MASK));
        logout.addActionListener(new NotImplementedActionListener("User logout"));
        system.add(logout);
                         
        system.addSeparator(); 
        
        startSystem = new JMenuItem("Start system",startSystemImg);
        startSystem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
                Event.ALT_MASK | Event.SHIFT_MASK));
        startSystem.addActionListener(new NotImplementedActionListener("Start system"));
        system.add(startSystem);        
        
        stopSystem = new JMenuItem("Stop system",stopSystemImg);
        stopSystem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,
                Event.ALT_MASK | Event.SHIFT_MASK));
        stopSystem.addActionListener(new NotImplementedActionListener("Stop system"));
        system.add(stopSystem);
        
        system.addSeparator(); 
        
        quit = new JMenuItem("Quit program",quitImg);
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                Event.ALT_MASK | Event.SHIFT_MASK));
        quit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                int n = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you really wish quit?",
                        "Quit program",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (n==JOptionPane.YES_OPTION){
                    System.exit(0);
                }
                
            }
        });
        
        system.add(quit);  
        
        //_________USERS_______________________
        users = new JMenu("Users");//Build the first menu.   
        users.setMnemonic(KeyEvent.VK_U);
        menuBar.add(users);    
        
        cUser = new JMenuItem("Create new user", cUserImg);
        cUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                Event.ALT_MASK | Event.SHIFT_MASK));
        cUser.addActionListener(new NotImplementedActionListener("Create new user"));
        users.add(cUser);
        
        vUser = new JMenuItem("View existing users", vUserImg);
        vUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                Event.ALT_MASK | Event.SHIFT_MASK));
        vUser.addActionListener(new NotImplementedActionListener("View existing users"));
        users.add(vUser);
        
        eUser = new JMenuItem("Edit existing user", eUserImg);
        eUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                Event.ALT_MASK | Event.SHIFT_MASK));
        eUser.addActionListener(new NotImplementedActionListener("Edit existing user"));
        users.add(eUser);
        
        //_________ITEMTYPES_______________________
        itemTypes = new JMenu("Itemtypes");//Build the first menu.   
        itemTypes.setMnemonic(KeyEvent.VK_I);
        menuBar.add(itemTypes);
        
        cItemType = new JMenuItem("Create new itemtype", cItemTypeImg);
        cItemType.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                Event.ALT_MASK | Event.CTRL_MASK));
        cItemType.addActionListener(new NotImplementedActionListener("Create new itemtype"));
        itemTypes.add(cItemType);
        
        vItemType = new JMenuItem("View existing itemtypes", vItemTypeImg);
        vItemType.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                Event.ALT_MASK | Event.CTRL_MASK));
        vItemType.addActionListener(new NotImplementedActionListener("View existing itemtypes"));
        itemTypes.add(vItemType);
        
        eItemType = new JMenuItem("Edit existing itemtype", eItemTypeImg);
        eItemType.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                Event.ALT_MASK | Event.CTRL_MASK));
        eItemType.addActionListener(new NotImplementedActionListener("View existing itemtype"));
        itemTypes.add(eItemType);
        
        //_________VIEW_______________________
        view = new JMenu("View");//Build the first menu.   
        view.setMnemonic(KeyEvent.VK_V);
        menuBar.add(view);
        
        orders = new JMenuItem("View orders", ordersImg);
        orders.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                Event.ALT_MASK | Event.CTRL_MASK));
        orders.addActionListener(new NotImplementedActionListener("View orders"));
        view.add(orders);
        
        //_________HELP_______________________
        help = new JMenu("Help");//Build the first menu.   
        help.setMnemonic(KeyEvent.VK_H);
        menuBar.add(help);
        
        about = new JMenuItem("About", aboutImg);
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                Event.ALT_MASK | Event.CTRL_MASK));
        about.addActionListener(new NotImplementedActionListener("About"));
        help.add(about);        
        
        //Set menu-bar on frame
        frame.setJMenuBar(menuBar);
        
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
    
    public class NotImplementedActionListener implements ActionListener
    {
        private String title;

        public NotImplementedActionListener(String title) {
            this.title = title;
        }        

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(frame,
                    "This function has not yet been implemented",
                    this.title,
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public class SystemStart extends JPanel
    {
        private BufferedImage image;

        public SystemStart() throws IOException {
           image = ImageIO.read(new File("images/logo.png"));
        }
        
        @Override
        public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
        }
        
    }
}
