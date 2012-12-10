import java.awt.*;
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

/**
 * MainFrame.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 05-12-2012
 */

public class MainFrame
{
    private static MainFrame instance;
    private JPanel content;
    GridBagConstraints c;
    CardLayout cl;
    
    private String[] usernames = {"admin" , "admin","admin", "admin"};
    private String[] passwords = {"admin" , "admin","admin", "admin"};
    
    private JComponent currentComponent;
    
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
        frame.setResizable(false);
        
        this.initMenu();
        try {
            frame.setLayout(new GridBagLayout());
            
            content = new JPanel();
            cl = new CardLayout();
            content.setLayout(cl); 
            content.add(new SystemStart(), "SystemStart");
            content.add(new UserLogin(), "UserLogin");
            
            
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.NORTHWEST;
            frame.add(content, c);    
            
            
            
 
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        this.setMenuStatus(false); //disable untill user is logged in
        
        
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
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                             
                showCard("UserLogin");
            }
        });
        
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
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                About temp = new About();
            }
        });
        help.add(about);  
        
        //Set menu-bar on frame
        frame.setJMenuBar(menuBar);
        
    }
    
    public void showCard(String cardName)
    {
        cl.show(content,cardName);
    }
    
    public static MainFrame getInstance()
    {
        if(instance == null){instance = new MainFrame();}
        return instance;
    }
    
    
    public JFrame getFrame()
    {
        return frame;
    }
    
    private void setMenuStatus(boolean state)
    {
        help.setEnabled(state);         
        users.setEnabled(state); 
        itemTypes.setEnabled(state); 
        view.setEnabled(state); 
        help.setEnabled(state);        
        systemInfo.setEnabled(state);
        logout.setEnabled(state);
        startSystem.setEnabled(state); 
        stopSystem.setEnabled(state);
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
    
    public class About extends JFrame 
    {
        private BufferedImage image;
        boolean initiated = false;
        public About()
        {
            JFrame frame = new JFrame();
            try {
                image = ImageIO.read(new File("images/helpMenu/aboutBackground.png"));
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setSize(350, 180);
            this.setLocationRelativeTo(frame);
            this.setVisible(true);
            this.setResizable(false);            
            this.setTitle("Central Storage System v. 1.0");
            
            DrawablePanel panel = new DrawablePanel(); 
         
            this.add(panel);
            this.setAlwaysOnTop(true);
            
            
            JLabel textLabel = new JLabel("",JLabel.CENTER);
            textLabel.setVerticalAlignment(SwingConstants.TOP);
            textLabel.setFont(new Font("Garamond", Font.BOLD, 25));
            textLabel.setText("Central Storage System v. 1.0");
            textLabel.setForeground(new Color(81,63,46));
            panel.add(textLabel);
            
            JLabel nameLabel = new JLabel("",SwingConstants.CENTER);
            
            nameLabel.setFont(new Font("Garamond", Font.BOLD, 15));
            nameLabel.setText("<html><center><br>Nicholaj P. Rasmussen <br>"
                    + "Kristina Hussak <br>"
                    + "Henning Fich <br>"
                    + "Anders Kold</center></html>");
            nameLabel.setForeground(new Color(81,63,46));
            nameLabel.setHorizontalTextPosition(JLabel.CENTER);
            
            panel.add(nameLabel);
            
                    
        }
        
        class DrawablePanel extends JPanel {
            
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }
        
        
        
    }
    
    public class UserLogin extends JPanel
    {
        private BufferedImage image;
        private ImageIcon loginButtonImg = new ImageIcon("images/systemMenu/loginButton.png");
        private ImageIcon loginButtonDownImg = new ImageIcon("images/systemMenu/loginButtonDown.png");
        private ImageIcon clearButtonImg = new ImageIcon("images/systemMenu/clearButton.png");
        private ImageIcon clearButtonDownImg = new ImageIcon("images/systemMenu/clearButtonDown.png");
        public UserLogin()
        {   
            
            //300,225
            try {
                image = ImageIO.read(new File("images/systemMenu/loginBackground.png"));
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            final JTextField usernameField;    
            final JPasswordField passwordField;
            JButton loginButton, clearButton;
            this.setLayout(null);
            
            
            usernameField = new JTextField(); 
            usernameField.setBounds(260,124,215,30);
            usernameField.setBorder(null);
            usernameField.setOpaque(false);
            usernameField.setFont(new Font("SansSerif", Font.BOLD, 14));
            this.add(usernameField);
            
            passwordField = new JPasswordField();
            passwordField.setBounds(260,175,215,30);
            passwordField.setBorder(null);
            passwordField.setOpaque(false);
            passwordField.setFont(new Font("SansSerif", Font.BOLD, 14));            
            this.add(passwordField);
            
            loginButton = new JButton();
            loginButton.setBounds(203,254,121,42);
            loginButton.setIcon(loginButtonImg);
            loginButton.setPressedIcon(loginButtonDownImg);
            loginButton.setBorder(null);
            loginButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    String inputUsername = usernameField.getText();
                    String inputPassword = passwordField.getText();
                            
                    
                    boolean usernameCorrect = false;
                    boolean passwordCorrect = false;
                    for (int i = 0; i < usernames.length && !usernameCorrect; i++)
                    {
                        if(usernames[i].equals(inputUsername))
                        {
                            usernameCorrect = true;
                        }                        
                    }
                    
                    for (int j = 0; j < passwords.length && !passwordCorrect; j++)
                    {
                        if(passwords[j].equals(inputPassword))
                        {
                            passwordCorrect = true;
                        }                        
                    }
                    
                    if(usernameCorrect && passwordCorrect)
                    {
                        setMenuStatus(true);
                        
                        JOptionPane.showMessageDialog(frame,
                                "You know have access to the system",
                                "Login successful",
                                JOptionPane.INFORMATION_MESSAGE);
                        showCard("SystemStart");
                    }     
                    
                    else{
                        JOptionPane.showMessageDialog(frame,
                                "Incorrect login information",
                                "Error in login",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            });
            this.add(loginButton);
            
            clearButton = new JButton();
            clearButton.setBounds(370,254,121,42);
            clearButton.setIcon(clearButtonImg);
            clearButton.setPressedIcon(clearButtonDownImg);
            clearButton.setBorder(null);
            clearButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    usernameField.setText("");
                    passwordField.setText("");
                }
            });
            this.add(clearButton);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
    
    public class createUser extends JPanel
    {
        public createUser()
        {
            
            
        }
    }
}
