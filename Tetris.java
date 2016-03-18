import java.util.Scanner;
import java.awt.event.*;
/**
 * Runs the Tetris game.
 * 
 * @author Jackelyn Shen 
 * @version March 27, 2013
 */
public class Tetris implements ArrowListener
{
    // instance variables - replace the example below with your own
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad t;
    int check=0;
    /**
     * Constructor for objects of class Tetris
     */
    public Tetris()
    {
        grid=new MyBoundedGrid<Block>(20, 10);
        display=new BlockDisplay(grid);
        display.setTitle("Tetris");
        t=new Tetrad(grid);
        display.setArrowListener(this);
        display.showBlocks();
    }
    /**
     * Creates a new Tetris and plays the game.
     */
    public static void main(String[] args)
    {
        Tetris hi=new Tetris();
        hi.play();
    }
    /**
     * Makes a tetrad drop down continuously and clears all completed rows.
     */
    public void play()
    {
        try
        {
            if(t.translate(1,0))
            {
                display.showBlocks();
                check=0;
                Thread.sleep(500);
            }
            else
            {
                clearCompletedRows();
                if(check==1)
                {
                    System.out.println("Game Over!");
                    throw new InterruptedException();
                }
                Tetrad newT=new Tetrad(grid);
                t=newT;
                check=1;
            }
            play();
        }
        catch(InterruptedException e)
        {
            
        }
    }
    /**
     * Rotates the tetrad clockwise by 90 degrees whenever the up arrow is pressed.
     */
    public void upPressed()
    {
        t.rotate();
        display.showBlocks();
    }
    /**
     * Shifts the tetrad down by one row whenever the down arrow is pressed.
     */
    public void downPressed()
    {
        t.translate(1,0);
        display.showBlocks();
    }
    /**
     * Shifts the tetrad left by one column whenever the left arrow is pressed.
     */
    public void leftPressed()
    {
        t.translate(0,-1);
        display.showBlocks();
    }
    /**
     * Shifts the tetrad right by one column whenever the right arrow is pressed.
     */
    public void rightPressed()
    {
        t.translate(0,1);
        display.showBlocks();
    }
    /**
     * Determines whether a row is filled with blocks.
     * 
     * @param row  the row to check
     * @return true if the row is filled; otherwise false.
     */
    private boolean isCompletedRow(int row)
    {
        boolean test=true;
        for(int i=0; i<grid.getNumCols(); i++)
        {
            Location loc=new Location(row, i);
            if(grid.get(loc)==null)
            {
                test=false;
            }
        }
        return test;
    }
    /**
     * Clears the row filled with blocks and shifts all other blocks above down.
     * 
     * @param row  the row to be cleared
     */
    private void clearRow(int row)
    {
        int original=row;
        for(int i=0; i<grid.getNumCols(); i++)
        {
            grid.remove(new Location(row, i));
            for(int j=row-1; j>=0; j--)
            {
                if(grid.get(new Location(j, i))!=null)
                {
                    Block b=grid.remove(new Location(j, i));
                    grid.put(new Location(j+1, i), b);
                }
            }
            row=original;
        }
    }
    /**
     * Determines if a row needs to be cleared and clears it.
     */
    private void clearCompletedRows()
    {
        for(int i=0; i<grid.getNumRows(); i++)
        {
            if(isCompletedRow(i))
            {
                clearRow(i);
            }
        }
    }
}
