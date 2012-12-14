package guimanager;
import presentation.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class GUIManager
{    
	private static GUIManager instance;
    private JPanel content;
    GridBagConstraints c;
    CardLayout cl;
    private PManager presentation = new PManager();
    
    private JFrame frame;
    private JMenu users, itemTypes, view, help;    
    private JMenuItem systemInfo, logout, startSystem, stopSystem; 
    private ImageIcon quitImg = new ImageIcon("images/systemMenu/quit.png");
    
    public GUIManager() 
    {
        frame = new JFrame("Central Storage System - Presentation Manager");
        frame.setSize(700, 450);
        frame.setResizable(false);

        try {
            frame.setLayout(new GridBagLayout());
            
            content = new JPanel();
            cl = new CardLayout();
            content.setLayout(cl); 
            content.add(new SystemStart(), "SystemStart");           
            content.add(new ViewOrders(), "ViewOrders");
            
            
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.weightx = 1;
            c.weighty = 1;
            c.fill = GridBagConstraints.BOTH;
            c.anchor = GridBagConstraints.NORTHWEST;
            frame.add(content, c);    
            
            JMenuBar menuBar = new JMenuBar();//Create the menu bar.           
            
            //_________SYSTEM_______________________
            JMenu system = new JMenu("System");//Build the first menu.    
            system.setMnemonic(KeyEvent.VK_S);
            menuBar.add(system);
            
            JMenuItem quit = new JMenuItem("Quit program",quitImg);
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
            
            frame.setJMenuBar(menuBar);
 
        } catch (IOException ex) {
            
        }  

    }
    
    
    
    public void showCard(String cardName)
    {
        cl.show(content,cardName);
    }
    
    public static GUIManager getInstance()
    {
        
		if(instance == null){instance = new GUIManager();}
        return instance;
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
                	showCard("SystemStart");
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
                    
                    showCard("SystemStart");
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
            currentOrders = presentation.viewOrders(state);
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
            for (int i = 0; i < gridSize; i++)
            {
                items[i] = new JPanel();
                items[i].setLayout(null);
                items[i].setPreferredSize(new Dimension(660,22));
                if(i%2 == 0)
                {                    
                    items[i].setBackground(new Color(255,255,255));
                }
                else
                {
                    items[i].setBackground(new Color(243,243,243));
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
    
    public static void main(String[] args)
    {
        GUIManager gui = new GUIManager();        
        JFrame frame = gui.getFrame();
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
			Thread.sleep(2000);
			gui.showCard("ViewOrders");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
