package presentation;
import control.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import acquaintance.IAComponent;

/**
 * MainFrame.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 05-12-2012
 */

public class PAdmin implements Observer
{
    private static PAdmin instance;
    private JPanel content;
    GridBagConstraints c;
    CardLayout cl; 
    private CFacade controlInterface = new CFacade();
    
    private String[] usernames = {"admin" , "admin","admin", "admin"};
    private String[] passwords = {"admin" , "admin","admin", "admin"};
    
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu system, users, itemTypes, view, help;    
    private JMenuItem systemInfo, login, logout, startSystem, stopSystem, quit;
    private JMenuItem cUser,vUser,eUser,
                      cItemType,vItemType, eItemType,
                      orders,
                      about;   
    
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
    
    private SystemStart sysStartPanel;
    private UserLogin userLoginPanel;
    private ViewOrders viewOrdersPanel;
    
    
    

    public PAdmin() 
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
            
            sysStartPanel = new SystemStart();
            userLoginPanel = new UserLogin();
            viewOrdersPanel = new ViewOrders();
            
            content.add(sysStartPanel, "SystemStart");
            content.add(userLoginPanel, "UserLogin");            
            content.add(viewOrdersPanel, "ViewOrders");
            
            
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.NORTHWEST;
            frame.add(content, c);    
            
            
            
 
        } catch (IOException ex) {
            Logger.getLogger(PAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        //this.setMenuStatus(false); //disable untill user is logged in
        
        
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
        startSystem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("*** Initiating Startup Procedures ***");
				CSystemStart temp = new CSystemStart();				
				temp.systemStart();
				
				
			}});
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
        orders.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {                             
                showCard("ViewOrders");
            }
        });
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
    
    public static PAdmin getInstance()
    {
        if(instance == null){instance = new PAdmin();}
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
                Logger.getLogger(PAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(PAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public class ViewOrders extends JPanel
    {
        private BufferedImage image;
        private  ArrayList<ArrayList<String>> currentOrders;
        JPanel ordersPanel;
        JButton searchButton, refreshButton;
        JComboBox dropdown;
        
        private ImageIcon searchButtonImg = new ImageIcon("images/viewMenu/searchButton.png");
        private ImageIcon searchButtonDownImg = new ImageIcon("images/viewMenu/searchButtonDown.png");
        private ImageIcon refreshButtonImg = new ImageIcon("images/viewMenu/refreshButton.png");
        private ImageIcon refreshButtonDownImg = new ImageIcon("images/viewMenu/refreshButtonDown.png");      
        
        private ImageIcon viewItemsWhiteImg = new ImageIcon("images/viewMenu/viewItemsWhite.png");  
        private ImageIcon viewItemsWhiteDownImg = new ImageIcon("images/viewMenu/viewItemsWhiteDown.png");  
        private ImageIcon viewItemsGreyImg = new ImageIcon("images/viewMenu/viewItemsGrey.png");  
        private ImageIcon viewItemsGreyDownImg = new ImageIcon("images/viewMenu/viewItemsGreyDown.png"); 
        
        private ImageIcon processWhiteImg = new ImageIcon("images/viewMenu/processWhite.png");  
        private ImageIcon processWhiteDownImg = new ImageIcon("images/viewMenu/processWhiteDown.png");  
        private ImageIcon processGreyImg = new ImageIcon("images/viewMenu/processGrey.png");  
        private ImageIcon processGreyDownImg = new ImageIcon("images/viewMenu/processGreyDown.png"); 
        
        
        public ViewOrders()
        {      
            try {
                image = ImageIO.read(new File("images/viewMenu/viewOrdersBackground.png"));
            } catch (IOException ex) {
                Logger.getLogger(PAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //headline: 218,218,218
            //dark order: 243,243,243
            //light order: 255,255,255
            
            this.setLayout(null);
            this.setBackground(new Color(240,238,238));  
            
            refreshButton = new JButton();
            refreshButton.setBounds(82,45,70,26);
            refreshButton.setBorder(null);
            refreshButton.setIcon(refreshButtonImg);
            refreshButton.setPressedIcon(refreshButtonDownImg);
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	if(dropdown.getSelectedIndex() == 0)
                	{
                		refreshOrders(IAComponent.ORDER_OPEN);
                	}
                	else
                	{
                		refreshOrders(dropdown.getSelectedIndex()+100);  
                	}
                	showCard("UserLogin");
                    showCard("ViewOrders");  
                }
            });
            this.add(refreshButton);
            
            searchButton = new JButton();
            searchButton.setBounds(7,45,70,26);
            searchButton.setBorder(null);
            searchButton.setIcon(searchButtonImg);
            searchButton.setPressedIcon(searchButtonDownImg);
            searchButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	                    
                }
            });
            this.add(searchButton);
            
            String[] filter = { "---  filter  ---", "Open orders", "Closed orders"};
            
           
            UIManager.put("ComboBox.foreground", new Color(20,20,20));//Text color general
            UIManager.put("ComboBox.background", new Color(240,240,240));//Text color general
            UIManager.put("ComboBox.selectionBackground", new Color(215,215,215)); //Background color in selection
            UIManager.put("ComboBox.selectionForeground", new Color(20,20,20)); //Text color in selection            
            
            dropdown = new JComboBox(filter);
            dropdown.setBounds(573,50,110,18);
            dropdown.setBorder(null);
            dropdown.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(dropdown.getSelectedIndex() == 1)
                    {
                        refreshOrders(IAComponent.ORDER_OPEN);                        
                    }
                    
                    else if(dropdown.getSelectedIndex() == 2)
                    {
                        refreshOrders(IAComponent.ORDER_CLOSED);
                    }
                    
                    showCard("UserLogin");
                    showCard("ViewOrders");  
                }
            });
            this.add(dropdown);
            
            UIManager.put("ScrollBar.width", new Integer(12));
            UIManager.put("ScrollBar.thumbDarkShadow", new Color(150,150,150));
            UIManager.put("ScrollBar.thumb", new Color(240,240,240));
            UIManager.put("ScrollBar.thumbHighlight", new Color(240,240,240));
            UIManager.put("ScrollBar.thumbShadow", new Color(220,220,220));
            
            UIManager.put("ScrollBar.track", new Color(255,255,255));
            UIManager.put("ScrollBar.trackForeground", new Color(255,255,255));
            UIManager.put("ScrollBar.trackHighlight", new Color(240,240,240));
            UIManager.put("ScrollBar.trackHighlightForeground", new Color(240,240,240));
            
            //UIManager.put("ScrollBar.shadow", Color.YELLOW); 
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBorder(null);

            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(10,105,674,275);
            
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createButton();
                }
                @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createButton();
                }
                private JButton createButton() {
                    JButton jbutton = new JButton();
                    jbutton.setPreferredSize(new Dimension(0, 0));
                    jbutton.setMinimumSize(new Dimension(0, 0));
                    jbutton.setMaximumSize(new Dimension(0, 0));
                    return jbutton;
                }
            });
              
            
            
            this.add(scrollPane);
            
            ordersPanel = new JPanel();
            scrollPane.setViewportView(ordersPanel);
            ordersPanel.setBackground(Color.WHITE);
            
            refreshOrders(IAComponent.ORDER_OPEN);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }

        private void refreshOrders(int state) {
        	ordersPanel.removeAll();
            currentOrders = controlInterface.viewOrders(state);
            System.out.println("refreshing orders: " +state);
            int gridSize = 0;
            
            if(currentOrders.size() < 12){gridSize = 12;}
            else{gridSize = currentOrders.size();}
            ordersPanel.setLayout(new GridLayout(gridSize,1));
            
            JPanel[] order = new JPanel[gridSize];
            boolean isWhite = true;
             
            for (int i = 0; i < gridSize; i++)
            {
                order[i] = new JPanel();
                order[i].setLayout(null);
                order[i].setPreferredSize(new Dimension(660,22));
                if(i%2 == 0)
                {
                    
                    order[i].setBackground(new Color(255,255,255));
                    isWhite = true;
                }
                else
                {
                    order[i].setBackground(new Color(243,243,243));
                    isWhite = false;
                }
                
                ordersPanel.add(order[i]);  
                if(i < currentOrders.size()){
                String delimiter = ";";
                String[] temp = currentOrders.get(i).get(0).split(delimiter);

                JLabel orderID = new JLabel(temp[0], JLabel.CENTER);
                orderID.setBounds(5,0,45,20);
                order[i].add(orderID);
                
                JLabel storeInfo = new JLabel(temp[1]);
                storeInfo.setBounds(57,0,108,20);
                order[i].add(storeInfo);
                
                JLabel receivalDate = new JLabel(temp[2]);
                receivalDate.setBounds(167,0,120,20);
                order[i].add(receivalDate);
                
                JLabel shippingDate = new JLabel(temp[3], JLabel.CENTER);
                shippingDate.setBounds(295,0,90,20);
                order[i].add(shippingDate);
                
                Color orderColor = new Color(0,0,0);                
                if(temp[4].equals("Open")){orderColor = new Color(35,150,0);}
                else if(temp[4].equals("Closed")){orderColor = new Color(170,0,0);}
                
                JLabel orderState = new JLabel(temp[4], JLabel.CENTER);
                orderState.setBounds(402,0,80,15);
                orderState.setForeground(orderColor);
                order[i].add(orderState);
                
                JButton viewOrderButton = new JButton();
                viewOrderButton.setBounds(495,0,70,20);                
                viewOrderButton.setBorder(null);
                if(isWhite)
                {
                    viewOrderButton.setIcon(viewItemsWhiteImg);
                    viewOrderButton.setPressedIcon(viewItemsWhiteDownImg);
                }
                
                else{
                    viewOrderButton.setIcon(viewItemsGreyImg);
                    viewOrderButton.setPressedIcon(viewItemsGreyDownImg);                    
                }
                
                viewOrderButton.addActionListener(new viewItemsActionListener(currentOrders.get(i)));                
                order[i].add(viewOrderButton);
                
                JButton processButton = new JButton();
                processButton.setBounds(580,0,70,20);                
                processButton.setBorder(null);
                if(isWhite)
                {
                    processButton.setIcon(processWhiteImg);
                    processButton.setPressedIcon(processWhiteDownImg);
                }
                
                else{
                    processButton.setIcon(processGreyImg);
                    processButton.setPressedIcon(processGreyDownImg);                    
                }
                
                processButton.addActionListener(new processActionListener(currentOrders.get(i).get(0)));                
                order[i].add(processButton);       
                }
                
            }
        } 
        
        class viewItemsActionListener implements ActionListener
        {
        	ArrayList<String> order;
            public viewItemsActionListener(ArrayList<String> order) {
            	this.order = order;
            }
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ViewItems items = new ViewItems(order);                
                items.setVisible(true);
            }            
        }
        
        class processActionListener implements ActionListener
        {
            public processActionListener(String currentOrderInfo) {}
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "Processing order");
            }            
        }
        
    }
    
    public class ViewItems extends JFrame
    {
        private BufferedImage image;
        
        ArrayList<String> order;
        JPanel ordersPanel;
        JButton searchButton, refreshButton;        
        DrawablePanel mainPanel;    
        JLabel orderDescription;
        
        public ViewItems(ArrayList<String> order)
        {    
        	this.order = order;
            try {
                image = ImageIO.read(new File("images/viewMenu/viewItemsBackground.png"));
            } catch (IOException ex) {
                Logger.getLogger(PAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            this.setSize(550, 330);
            this.setResizable(false);
            this.setLocationRelativeTo(frame);
            
            mainPanel = new DrawablePanel();
            mainPanel.setLayout(null);
            mainPanel.setBackground(new Color(240,238,238));  
            this.add(mainPanel);            

            UIManager.put("ScrollBar.width", new Integer(12));
            UIManager.put("ScrollBar.thumbDarkShadow", new Color(150,150,150));
            UIManager.put("ScrollBar.thumb", new Color(240,240,240));
            UIManager.put("ScrollBar.thumbHighlight", new Color(240,240,240));
            UIManager.put("ScrollBar.thumbShadow", new Color(220,220,220));
            
            UIManager.put("ScrollBar.track", new Color(255,255,255));
            UIManager.put("ScrollBar.trackForeground", new Color(255,255,255));
            UIManager.put("ScrollBar.trackHighlight", new Color(240,240,240));
            UIManager.put("ScrollBar.trackHighlightForeground", new Color(240,240,240));
            
            //UIManager.put("ScrollBar.shadow", Color.YELLOW); 
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBorder(null);        
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(7,79,529,210);
            
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createButton();
                }
                @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createButton();
                }
                private JButton createButton() {
                    JButton jbutton = new JButton();
                    jbutton.setPreferredSize(new Dimension(0, 0));
                    jbutton.setMinimumSize(new Dimension(0, 0));
                    jbutton.setMaximumSize(new Dimension(0, 0));
                    return jbutton;
                }
            });
              
            
            
            mainPanel.add(scrollPane);
            
            orderDescription = new JLabel("Viewing blabla bla for bla bla by blabla");
            orderDescription.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            orderDescription.setBounds(237,40,300,15);
            mainPanel.add(orderDescription);
            
            ordersPanel = new JPanel();
            scrollPane.setViewportView(ordersPanel);
            ordersPanel.setBackground(Color.WHITE);
            
            refreshItems();
        }        
        

        private void refreshItems() {
        	
        	ArrayList<String> currentItems = order;
            int gridSize = 0;            
            if(currentItems.size() < 9){gridSize = 9;}
            else{gridSize = currentItems.size();}
        	        	
            String[] tempOrder = currentItems.get(0).split(";");
            orderDescription.setText("Viewing items for order: "+tempOrder[0]+ ", by " +tempOrder[1]+".");
            
            ordersPanel.setLayout(new GridLayout(gridSize,1));
            
            JPanel[] items = new JPanel[gridSize];
            boolean isWhite = true;
             
            
            for (int i = 0; i < gridSize; i++)
            {
                items[i] = new JPanel();
                items[i].setLayout(null);
                items[i].setPreferredSize(new Dimension(660,22));
                if(i%2 == 0)
                {                    
                    items[i].setBackground(new Color(255,255,255));
                    isWhite = true;
                }
                else
                {
                    items[i].setBackground(new Color(243,243,243));
                    isWhite = false;
                }
                
                ordersPanel.add(items[i]);  
                
                
                
                if(i < currentItems.size() && i > 0){
                String delimiter = ";";
                String[] temp = currentItems.get(i).split(delimiter); 
                
                JLabel stockPosition = new JLabel(temp[1], JLabel.CENTER);
                stockPosition.setBounds(5,0,90,20);
                items[i-1].add(stockPosition);
                
                JLabel name = new JLabel(temp[0]);
                name.setBounds(110,0,220,20);
                items[i-1].add(name);
                
                JLabel barcode = new JLabel(temp[2]);
                barcode.setBounds(338,0,100,20);
                items[i-1].add(barcode);
                
                JLabel price = new JLabel(temp[3], JLabel.CENTER);
                price.setBounds(445,0,80,20);
                items[i-1].add(price);
                
               
                
                }
            }
            
        } 
        
        class DrawablePanel extends JPanel {
            
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        }
        
    }
    
    @Override
	public void update(Observable arg0, Object arg1) {		
		int tempOrderState;
		if(viewOrdersPanel.dropdown.getSelectedIndex() == 0)
		{tempOrderState= 101;}
		else
		{tempOrderState = viewOrdersPanel.dropdown.getSelectedIndex()+100;}		
		viewOrdersPanel.refreshOrders(tempOrderState);		
	}    
    
    public static void main(String[] args)
    {
        PAdmin gui = new PAdmin(); 
        
        JFrame frame = gui.getFrame();
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	
}
