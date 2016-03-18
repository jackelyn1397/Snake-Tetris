import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JOptionPane;
/**
 * Runs Snake game.
 * 
 * @author Jackelyn and Helen
 * @version 6/3/13
 */
public class SnakeRunner implements ArrowListener
{
    // instance variables - replace the example below with your own
    private Block food;
    private Snake s;
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private int level;
    private int speed;
    private int score=0;
    private int multiplyCount=0;
    private JFrame frame;
    private JPanel infoPanel;
    private JPanel p;
    private JTextField myScore;
    private boolean isDone = false;
    /**
     * Constructor for objects of class SnakeRunner
     */
    public SnakeRunner()
    {
        frame=new JFrame();
        p=new JPanel();
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3,1));
        p = new JPanel();
        myScore = new JTextField(5);
        myScore.setEditable(false);
        p.add(new JLabel("Score"));
        p.add(myScore);
        infoPanel.add(p);
        
        JPanel buttons = new JPanel();
        
        JButton reset = new JButton("Reset");
        // add an inner class to handle the action
        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                reset();
            }
        });
        buttons.add(reset);
        
        frame.add(infoPanel, BorderLayout.SOUTH);
        frame.pack();        
        frame.add(buttons, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        updateInfo();
        
        level=0;
        speed=100;
        grid=new MyBoundedGrid<Block>(30, 50);
        display=new BlockDisplay(grid);
        display.setTitle("Snake");
        s=new Snake(grid);
        createFood();
        display.setArrowListener(this);
        display.showBlocks();
    }
    /**
     * Creates a food block for the snake to eat.
    */ 
    public void createFood()
    {
        int foodRow=(int)(Math.random()*grid.getNumRows());
        int foodCol=(int)(Math.random()*grid.getNumCols());
        Location foodLoc=new Location(foodRow, foodCol);
        while(grid.get(foodLoc)!=null)
        {
            foodRow=(int)(Math.random()*grid.getNumRows());
            foodCol=(int)(Math.random()*grid.getNumCols());
            foodLoc=new Location(foodRow, foodCol);
        }
        food=new Block(Color.BLUE, foodLoc, grid);
        grid.put(foodLoc, food);
    }
    /**
     * Runs the game.
    */ 
    public static void main(String[] args)
    {
        SnakeRunner hi=new SnakeRunner();
        hi.play();
    }
    /**
     * Increases the speed and level and determines whether to end the game and 
    */ 
    public void play()
    {
        while(true)
        {
            try
            {
                if(speed > 20 && level%5==0)
                {
                    level++;
                    multiplyCount++;
                    speed-=10;
                }
                Thread.sleep(speed);
                if(!s.move())
                {
                    if(!s.isFirstLoc())
                    {
                        JOptionPane.showMessageDialog(null, "OUT OF BOUNDS");
                        isDone = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "CANNIBALISM");
                        isDone = true;
                    }
                    return;
                }
                if(food.getLocation()==null)
                {
                    createFood();
                    s.addBlock();
                    level++;
                    score+=multiplyCount;
                    updateInfo();
                }
                display.showBlocks();
            }
            catch(InterruptedException e)
            {
                
            }
        }
    }
    /**
     * Gets whether is game is or is not done.
     * 
     * @returns true if the game is done; otherwise false.
    */ 
    public boolean getIsDone()
    {
        return isDone;
    }
    /**
     * Changes the value of isDone to b.
     * 
     * @param b a boolean that determines the value of isDone
    */ 
    public void setIsDone(boolean b)
    {
        isDone = b;
    }
    /**
     * Changes the direction of the snake to up if the original direction was not down.
    */ 
    public void upPressed()
    {
        if(s.getDirection()!=Location.SOUTH)
        {
            s.setDirection(Location.NORTH);
        }
        display.showBlocks();
    }
    /**
     * Changes the direction of the snake to down if the original direction was not up.
    */ 
    public void downPressed()
    {
        if(s.getDirection()!=Location.NORTH)
        {
            s.setDirection(Location.SOUTH);
        }
        display.showBlocks();
    }
    /**
     * Changes the direction of the snake to left if the original direction was not right.
    */ 
    public void leftPressed()
    {
        if(s.getDirection()!=Location.RIGHT)
        {
            s.setDirection(Location.LEFT);
        }
        display.showBlocks();
    }
    /**
     * Changes the direction of the snake to right if the original direction was not left.
    */ 
    public void rightPressed()
    {
        if(s.getDirection()!=Location.LEFT)
        {
            s.setDirection(Location.RIGHT);
        }
        display.showBlocks();
    }
    /**
     * Update's the player's score.
    */ 
    private void updateInfo()
    {
        myScore.setText(score+ " points");
    }
    /**
     * Resets the SnakeRunner game.
    */ 
    public void reset()
    {
       
       score = 0;
       level = 0;
       multiplyCount=0;
       speed=100;
       s.removeBlocksReset();
       display.showBlocks();
       food.removeSelfFromGrid();
       display.showBlocks();
       createFood();
       display.showBlocks();
       s = new Snake(grid);
       display.showBlocks();
       this.play();
    }
}
