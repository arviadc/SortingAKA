import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.JApplet;
import java.util.Random;

public class SortPanel extends JPanel
{
  public JButton populate;                               
  public JButton start;
  public JButton resume;
  public JButton stop;
  public JButton pause;
  public JButton ascending;
  public JButton descending;
  public JComboBox<String> sortCombo;
  public String[] sortAlgo = {"Select","Gnome Sort","Insertion Sort"};
  public SortAnimationPanel animationPanel;
  public  JPanel sortpanel;
  Border border;
  final ButtonGroup bg=new ButtonGroup();                 
  GridBagLayout layout1 = new GridBagLayout();          
  GridBagConstraints gbc = new GridBagConstraints(); 
  public boolean orderPanel = true;
  public String algo;
 
  SortPanel()
  {
   super();                                           
   setLayout(new GridLayout(1,2));
   animationPanel = new SortAnimationPanel();                
    createButton();                                    
    createCombo();
    prepareGUI();
    
    populate.setEnabled(true);
    start.setEnabled(false);
    resume.setEnabled(false);
    stop.setEnabled(false);
    pause.setEnabled(false);
    ascending.setEnabled(false);
    descending.setEnabled(false);
    sortCombo.setEnabled(false);
  }
  
  public void createButton()
  {
    populate = new JButton("Generate Array");                  
    populate.setPreferredSize(new Dimension(110,25));
    populate.setBackground(Color.BLACK);
    populate.setForeground(Color.WHITE);
    populate.setBorder(new LineBorder(Color.RED));
    
    start = new JButton("Sort");                            
    start.setPreferredSize(new Dimension(60,25));
    start.setBackground(Color.BLACK);
    start.setForeground(Color.WHITE);
    start.setBorder(new LineBorder(Color.RED));
    
    resume = new JButton("Resume");                       
    resume.setPreferredSize(new Dimension(60,25));
    resume.setBackground(Color.BLACK);
    resume.setForeground(Color.WHITE);
    resume.setBorder(new LineBorder(Color.RED));
    
    pause = new JButton("Pause");                       
    pause.setPreferredSize(new Dimension(60,25));
    pause.setBackground(Color.BLACK);
    pause.setForeground(Color.WHITE);
    pause.setBorder(new LineBorder(Color.RED));
    
    stop = new JButton("Stop");                       
    stop.setPreferredSize(new Dimension(60,25)); 
    stop.setBackground(Color.BLACK);
    stop.setForeground(Color.WHITE);
    stop.setBorder(new LineBorder(Color.RED));
    
    ascending = new JButton("Ascending");           
    ascending.setPreferredSize(new Dimension(110,25));
    ascending.setBackground(Color.BLACK);
    ascending.setForeground(Color.WHITE);
    ascending.setBorder(new LineBorder(Color.RED));
    
    descending = new JButton("Descending");         
    descending.setPreferredSize(new Dimension(110,25));
    descending.setBackground(Color.BLACK);
    descending.setForeground(Color.WHITE);
    descending.setBorder(new LineBorder(Color.RED));        
      
   
      bg.add(populate);
      bg.add(start);
      bg.add(resume);
      bg.add(pause);
      bg.add(stop);      
      bg.add(ascending);
      bg.add(descending);
      
      
    PopulateButtonHandler btn = new PopulateButtonHandler();
    populate.addActionListener(btn);                             

   
    StartButtonHandler btn1 = new StartButtonHandler();
    start.addActionListener(btn1);                              
    ascending.addActionListener(btn1);                         
    descending.addActionListener(btn1);                       
     
  
    ControlButtonHandler btn3 = new ControlButtonHandler();
    pause.addActionListener(btn3);                            
    resume.addActionListener(btn3);                          
    stop.addActionListener(btn3);                            
  }
  
    public class MyItemListener implements ItemListener
  {
      public void itemStateChanged(ItemEvent event)
      {
        sortCombo.setEnabled(false);      
        ascending.setEnabled(true);
        descending.setEnabled(true);
      }
  }
  
    public class ControlButtonHandler implements ActionListener
  {
    @Override
      public void actionPerformed(ActionEvent event)        
      {
         if(event.getSource() == pause)      
        {
         
          animationPanel.checkPaused();              
          pause.setEnabled(false);                 
          resume.setEnabled(true);
          animationPanel.paused = true;
          
        }
        else if(event.getSource() == resume)         
        {
          resume.setEnabled(false);
          pause.setEnabled(true);
          animationPanel.threadResume();           
        }
        else if(event.getSource() == stop)         
        {
         animationPanel.randInt = null;           
         revalidate();
         repaint();
         resume.setEnabled(false);
         pause.setEnabled(true);
         start.setEnabled(false);
         sortCombo.setEnabled(false);
         pause.setEnabled(false);
         populate.setEnabled(true);
         stop.setEnabled(false);
        }
      }
  }
  
  
  public class PopulateButtonHandler implements ActionListener
   {
    @Override
      public void actionPerformed(ActionEvent event)
      {
       populate.setEnabled(false);
       sortCombo.setEnabled(true);
       animationPanel.populateArray();  
      }
   }
  
 public class StartButtonHandler implements ActionListener
  {
    @Override
      public void actionPerformed(ActionEvent event)
      {
        if(event.getSource() == start)                
        {
         descending.setEnabled(false);              
         ascending.setEnabled(false);
         pause.setEnabled(true);
         stop.setEnabled(true);         
         algo = sortCombo.getSelectedItem().toString();    
         animationPanel.intializeThread(algo);                 
         populate.setEnabled(true);         
          revalidate();
          repaint();
        }
        
        else if(event.getSource() == ascending)     
        {
          ascending.setEnabled(false);
          descending.setEnabled(false);
          start.setEnabled(true);
          orderPanel = true;
          animationPanel.orderPanel = true;           
        }
        else if(event.getSource() == descending)     
        {
          ascending.setEnabled(false);
          descending.setEnabled(false);               
          start.setEnabled(true);
          animationPanel.orderPanel = false;
        } 
      }
      
   }
    
 public void createCombo()
  {
    sortCombo = new JComboBox<>(sortAlgo);                   
    sortCombo.setPreferredSize(new Dimension(100,25));
    sortCombo.setBackground(Color.BLACK);
    sortCombo.setForeground(Color.WHITE);
    sortCombo.setBorder(BorderFactory.createLineBorder(Color.red)); 
   
    MyItemListener btn2 = new MyItemListener();
    sortCombo.addItemListener(btn2);
     
  }
 
  public void prepareGUI()
  {
    border= BorderFactory.createRaisedBevelBorder();       
    this.setBorder(border);
    this.setLayout(layout1);                               
    this.setVisible(true);
    
       
    gbc.fill = GridBagConstraints.BOTH;                     
    gbc.gridx =0;
    gbc.gridy =0 ;
    gbc.gridheight = 4;
    gbc.gridwidth = 4;
    this.add(animationPanel, gbc);
     
   
    gbc.gridx = 0;
    gbc.gridy = 4;  
    gbc.gridwidth = 1;
    this.add(populate,gbc);  
   
   
    gbc.gridx = 1;
    gbc.gridy = 4;    
    gbc.gridwidth = 1;
    this.add(start, gbc);
        
     gbc.gridx = 2;
     gbc.gridy = 4;
     gbc.gridheight = 1;
    gbc.gridwidth = 1;
    this.add(sortCombo, gbc);
    
     gbc.gridx = 3;
    gbc.gridy = 4;  
    gbc.gridwidth = 1;
    this.add(stop,gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 8;  
    gbc.gridwidth = 1;
    this.add(ascending,gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 8;  
    gbc.gridwidth = 1;
   this.add(descending,gbc); 
    
    gbc.gridx = 2;
    gbc.gridy = 8;  
    gbc.gridwidth = 1;
    this.add(pause,gbc);     
    
    
     
    gbc.gridx = 3 ;
    gbc.gridy = 8;  
    gbc.gridwidth = 1;
    this.add(resume,gbc);   
    
  }
 
  
}
  
