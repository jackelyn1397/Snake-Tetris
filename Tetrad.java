import java.awt.Color;
import java.util.ArrayList;
/**
 * Creates a 4-block shape to use in Tetris
 * 
 * @author Jackelyn Shen
 * @version March 27, 2013
 */
public class Tetrad
{
    // instance variables - replace the example below with your own
    private MyBoundedGrid<Block> grid;
    private Block[] blocks;
    private Color[] colors={Color.RED, Color.GRAY, Color.CYAN, Color.YELLOW, Color.MAGENTA,
                            Color.BLUE, Color.GREEN};
    private Location[] I={new Location(0,5), new Location(1,5), new Location(2,5), new Location(3,5)};
    private Location[] T={new Location(0,4), new Location(0,5), new Location(0,6), new Location(1,5)};
    private Location[] O={new Location(0,4), new Location(0,5), new Location(1,4), new Location(1,5)};
    private Location[] L={new Location(0,5), new Location(1,5), new Location(2,5), new Location(2,6)};
    private Location[] J={new Location(0,5), new Location(1,5), new Location(2,5), new Location(2,4)};
    private Location[] S={new Location(0,5), new Location(0,6), new Location(1,4), new Location(1,5)};
    private Location[] Z={new Location(0,4), new Location(0,5), new Location(1,5), new Location(1,6)};
    private Location[][] shapes={I, T, O, L, J, S, Z};
    private Location[] locations;
    int r=(int)(Math.random()*7)+1;

    /**
     * Constructor for objects of class Tetrad
     * 
     * @param gr   the grid that the blocks will be located
     */
    public Tetrad(MyBoundedGrid<Block> gr)
    {
        grid=gr;
        blocks=new Block[4];
        locations=shapes[r-1];
        for(int i=0; i<4; i++)
        {
            blocks[i]=new Block(colors[r-1], locations[i], gr);
        }
        addToLocations(grid, locations);
    }
    /**
     * Adds 4 blocks to the locations specified in the parameter.
     * 
     * @param grid  the grid that the the blocks will be put in
     * @param locs  the locations that the blocks will be put in
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for(int i=0; i<blocks.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
            locations=locs;
        }
    }
    /**
     * Removes the blocks that are currently in locations.
     * 
     * @return  the blocks that were removed
     */
    private Location[] removeBlocks()
    {
        for(int i=0; i< locations.length; i++)
        {
            grid.remove(locations[i]);
        }
        Location[] l=locations;
        locations=null;
        return l;
    }
    /**
     * Determines whether the specified locations are empty.
     * 
     * @param locs  the locations to check
     * @param grid  the grid that the locations are in
     * @return true if the locations are empty and valid in the grid; otherwise false
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        boolean test=true;
        for(int i=0; i<locs.length; i++)
        {
            if(!grid.isValid(locs[i]))
            {
                test=false;
            }
            if(grid.get(locs[i])!=null)
            {
                test=false;
            }
        }
        return test;
    }
    /**
     * Shifts the tetrad deltaRow down and deltaCol to the right if the new locations are empty.
     * 
     * @param deltaRow  the integer that determines how many times to shift down
     * @param deltaCol  the integer that determines how many times to shift right
     * @return true if the tetrad can translate; otherwise false.
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] prevLocs=removeBlocks();
        Location[] newLocs=new Location[4];
        for(int i=0; i<prevLocs.length; i++)
        {
            Location newLoc=new Location(prevLocs[i].getRow()+deltaRow, prevLocs[i].getCol()+deltaCol);
            newLocs[i]=newLoc;
        }
        if(areEmpty(grid, newLocs))
        {
            addToLocations(grid, newLocs);
            locations=newLocs;
            return true;
        }
        else
        {
            addToLocations(grid, prevLocs);
            return false;
        }
    }
    /**
     * Rotates the tetrad clockwise if the new locations are empty.
     * 
     * @return true if the tetrad can be rotated; otherwise false.
     */
    public boolean rotate()
    {
        Location[] prevLocs=removeBlocks();
        Location[] newLocs=new Location[4];
        for(int i=0; i<prevLocs.length; i++)
        {
            int r=blocks[0].getLocation().getRow()-blocks[0].getLocation().getCol()+blocks[i].getLocation().getCol();
            int c=blocks[0].getLocation().getRow()+blocks[0].getLocation().getCol()-blocks[i].getLocation().getRow();
            newLocs[i]=new Location(r,c);
        }
        if(areEmpty(grid, newLocs))
        {
            addToLocations(grid, newLocs);
            locations=newLocs;
            return true;
        }
        else
        {
            addToLocations(grid, prevLocs);
            return false;
        }
    }
}
    
