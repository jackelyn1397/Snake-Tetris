import java.awt.Color;
import java.util.ArrayList;
/**
 * Makes a snake object
 * 
 * @author Jackelyn and Helen
 * @version 6/3/13
 */
public class Snake
{
    // instance variables - replace the example below with your own
    private ArrayList<Location> locations=new ArrayList<Location>();
    private MyBoundedGrid<Block> grid;
    private int direction;
    private Location endLoc;

    /**
     * Constructor for objects of class Snake
     * 
     * @param gr the grid that the snake is in
     */
    public Snake(MyBoundedGrid<Block> gr)
    {
        grid=gr;
        int startRow=grid.getNumRows()/2;
        int startCol=grid.getNumCols()/2;
        Location loc=new Location(startRow,startCol);
        for(int i=0; i<3; i++)
        {
            //blocks.add(new Block(Color.RED, new Location(startRow, startCol+i), gr));
            locations.add(new Location(startRow, startCol+i));
        }
        direction=Location.LEFT;
        addToLocations(grid, locations);
    }
    /**
     * Adds blocks to the locations locs in the grid gr.
     * 
     * @param locs the locations where the blocks are added
     * @param gr the grid that holds the locations
     */
    public void addToLocations(MyBoundedGrid<Block> gr, ArrayList<Location> locs)
    {
         for(int i=0; i<locs.size(); i++)
        {
            Block newBlock = new Block(Color.RED, locs.get(i),grid);
            newBlock.putSelfInGrid(grid,locs.get(i));
            //blocks.get(i).putSelfInGrid(grid, locs.get(i));
            locations=locs;
        }
    }
    /**
     * Gets the locations the blocks are currently in
     * 
     * @return the locations of the blocks
     */
    public ArrayList<Location> getLocations()
    {
        return locations;
    }
    /**
     * Gets the direction the snake is currently going in
     * 
     * @return the direction of the snake
     */
    public int getDirection()
    {
        return direction;
    }
   /**
     * Sets the direction the snake is currently going in
     * 
     * @return the new direction of the snake
     */
    public void setDirection(int dir)
    {
        direction=dir;
    }
    /**
     * Determines if the locations in a grid are valid and empty.
     * 
     * @param locs the locations to check
     * @param grid the grid that the locations are in
     * @return true if the locations are valid and empty; otherwise false
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, ArrayList<Location> locs)
    {
        boolean test=true;
        for(int i=0; i<locs.size(); i++)
        {
            if(!grid.isValid(locs.get(i)))
            {
                test=false;
            }
            if(grid.get(locs.get(i))!=null && grid.get(locs.get(i)).getColor()!=Color.BLUE)
            {
                test=false;
            }
            //if(grid.get(locs.get(i))!=null && grid.get(locs.get(i)).getColor()
        }
        return test;
    }
    /**
     * Removes all the blocks comprising of the snake
     * 
     * @return the removed blocks' locations 
     */
    public ArrayList<Location> removeBlocks()
    {
        for(int i=0; i< locations.size(); i++)
        {
            if(grid.get(locations.get(i)) != null)
            {
                grid.get(locations.get(i)).removeSelfFromGrid();
            }
        }
        ArrayList<Location> l=locations;
        locations=new ArrayList<Location>();
        return l;
    }
    /**
     * Attempts to move all the blocks comprising of the snake in a specified direction
     * 
     * @return true if the snake successfully moved; otherwise false
     */
    public boolean move()
    {
        Location newLoc=locations.get(0).getAdjacentLocation(direction);
        ArrayList<Location> newLocations=new ArrayList<Location>();
        newLocations.add(newLoc);
        if(grid.get(newLoc) != null && grid.get(newLoc).getColor() == Color.RED)
        {
            return false;
        }
        for(int i=1; i<locations.size(); i++)
        {
            newLocations.add(locations.get(i-1));
        }
        ArrayList<Location> prevLocs=removeBlocks();
        endLoc = prevLocs.get(prevLocs.size()-1);
        if(areEmpty(grid, newLocations))
        {
            addToLocations(grid, newLocations);
            return true;
        }
        addToLocations(grid, prevLocs);
        return false;
    }
    /**
     * Adds a block to the end of the snake
     */
    public void addBlock()
    {
        Block b=new Block(Color.RED, endLoc, grid);
        b.putSelfInGrid(grid,endLoc);
        //blocks.add(b);
        locations.add(endLoc);
    }
    /**
     * Determines whether the adjacent location of the first block in a specified location is valid in the grid.
     * 
     * @return true if the next location of the first block is valid; otherwise false.
     */
    public boolean isFirstLoc()
    {
        return grid.isValid(locations.get(0).getAdjacentLocation(direction));
    }
    /**
     * Removes the blocks comprising of the snake when the game resets.
     * 
     * @return the locations of the removed blocks
     */
    public ArrayList<Location> removeBlocksReset()
    {
        for(int i=0; i< locations.size(); i++)
        {
            if(grid.get(locations.get(i)) != null)
            {
                grid.get(locations.get(i)).removeSelfFromGrid();
            }
        }
        //blocks=new ArrayList<Block>();
        ArrayList<Location> l=locations;
        locations=new ArrayList<Location>();
        return l;
    }
}
