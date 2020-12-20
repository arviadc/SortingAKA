import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.awt.Graphics;


public class SortAnimationPanel extends JPanel implements Runnable
{
  public int randInt[];
  private String algo;
  private Thread thread;
  private String comboAlgorithm;
  private int[] array;
  private int[] tempMergArr;
  private int length;
  private int lowerIndex,
            higherIndex;
  public boolean orderPanel = true;
  private volatile boolean running = true;
  public volatile boolean paused = false;
  private volatile boolean suspended = false;
  private final Object pauseLock = new Object();
  
  

  SortAnimationPanel()
  {
    super();                                                        
    super.setPreferredSize(new Dimension(400,400));
    super.setVisible(true);
  }
 
  public void populateArray()
  {  
    Random rand = new Random();                                   
    randInt = new int[this.getWidth()];                           
    rand.setSeed(System.currentTimeMillis());
    for(int i = 0; i < this.getWidth();i++)                       
    {
      randInt[i] = rand.nextInt(this.getHeight() -1) + 1;
    }
    this.repaint();                                               
  }
  
 @Override
    protected void paintComponent(Graphics g) 
     {
       super.paintComponent(g);                               
       g.clearRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());
       g.fillRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());
       g.setColor(Color.BLACK);
       this.repaint();   
       if(randInt==null) return;                              
        for(int i = 0;i <this.getWidth();i++)                 
        {
            g.setColor(Color.RED);
            g.drawLine(i,randInt[i],i,this.getHeight() );
        }
    }
  
  

  public void intializeThread(String algorithm)
  {
      thread = new Thread(this);                                   
      thread.start();                                              
      this.comboAlgorithm = algorithm;
      this.setThreadState();                                       
  }
 
   
  public void setThreadState()
  {
    synchronized(pauseLock)
    {
      try
      {
        while(suspended)
        {
        pauseLock.wait();     
        }
        if(paused)             
        pauseLock.wait();     
      } 
       catch(InterruptedException e)
       {
         System.out.println("Exception occured" + e);
       }
      }
        
    }
 
 
 
  public void run()
  {      
    if(comboAlgorithm.equals("Gnome Sort"))
    {
      this.gnomesort(randInt);
    }     
    if(comboAlgorithm.equals("Insertion Sort"))
    {
      this.insertionSort(randInt);
    }    
  }
 
    public void insertionSort(int[] arr) 
    {
      int i, j, newValue;
      try
      { 
        for (i = 1; i < arr.length; i++) 
        {
          thread.sleep(100);
          newValue = arr[i];
          j = i;
          if(!orderPanel)                                
          {
            while (j > 0 && arr[j - 1] > newValue) 
            {
              arr[j] = arr[j - 1];                                 
              repaint();                                      
              this.setThreadState();
              j--;
            }
           }
          else                                            
          {
            while (j > 0 && arr[j - 1] < newValue) 
            {
              arr[j] = arr[j - 1];
              repaint();                                    
              this.setThreadState();
              j--;
            }
          }
          arr[j] = newValue;
            
        }
      }
      catch(Exception e)
      {
        System.out.println("Exception occured" + e); 
      }
     
      
    }
  
  public void gnomesort(int[] arr) 
    {
      try
      { 
		int index = 0; 
  
        while (index < arr.length - 1) { 
            thread.sleep(100);	
            this.setThreadState();	
            if (index == 0) 
                index++; 
				
			if(!orderPanel){
				if (arr[index] >= arr[index - 1]) 
					index++; 
				else { 
					int temp = 0; 
					temp = arr[index]; 
					arr[index] = arr[index - 1]; 
					arr[index - 1] = temp; 					
					repaint();					
					index--; 
				} 
			} else {
				if (arr[index] <= arr[index - 1]) 
					index++; 
				else { 
					int temp = 0; 
					temp = arr[index]; 
					arr[index] = arr[index - 1]; 
					arr[index - 1] = temp; 					
					repaint();					
					index--; 
				} 
			}            
        } 
      }
      catch(Exception e)
      {
        System.out.println("Exception occured" + e); 
      }
     
      
    }
            
    
     public void checkPaused()
     {
       paused = true;
     }
          
     public void threadResume()
     {
       synchronized(pauseLock)
       {
         try
         {
         paused = false;
         pauseLock.notifyAll();                
         }
         catch(Exception e)
         {
         System.out.println("Exception occured" + e);
         }
       }
       
     }
     
     
}