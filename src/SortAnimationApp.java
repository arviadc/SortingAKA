import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class SortAnimationApp extends JFrame
{
  
  public static final int  WIDTH = 750;                              
  public static final int  HEIGHT = 550;
  SortPanel st;                                                    
  SortPanel st1;
  
  public static void main(String args[])
  {  
   SortAnimationApp animationFrame = new SortAnimationApp();                      
   animationFrame.setLayout(new GridBagLayout());                                
   animationFrame.setSize(WIDTH,HEIGHT);                                        
   animationFrame.setVisible(true);                                             
   animationFrame.pack();                                                      
   animationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  }
  
  SortAnimationApp()
   {
      super("Sorting Comparison Insertion Sort and Gnome Sort");                                             
      st = new SortPanel();                                                   
      st1 = new SortPanel();      
      add(st,BorderLayout.EAST);
      add(Box.createRigidArea(new Dimension(20,50)));
      add(st1,BorderLayout.WEST);
   }

  
}