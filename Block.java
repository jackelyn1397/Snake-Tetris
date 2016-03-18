import java.awt.Color;

public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;

    //constructs a blue block, because blue is the greatest color ever!
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
    public Block(Color c, Location loc, MyBoundedGrid<Block> gr)
    {
        color = c;
        grid = gr;
        location = loc;
    }

    //gets the color of this block
    public Color getColor()
    {
        return color;
    }

    //sets the color of this block to newColor.
    public void setColor(Color newColor)
    {
        color=newColor;
    }

    //gets the grid of this block, or null if this block is not contained in a grid
    public MyBoundedGrid<Block> getGrid()
    {
        if(grid!=null)
        {
            return grid;
        }
        else
        {
            return null;
        }
    }

    //gets the location of this block, or null if this block is not contained in a grid
    public Location getLocation()
    {
        if(grid!=null)
        {
            return location;
        }
        else
        {
            return null;
        }
    }

    //removes this block from its grid
    //precondition:  this block is contained in a grid
    public void removeSelfFromGrid()
    {
        if(grid!=null)
        {
            grid.remove(location);
            grid=null;
            location=null;
        }
    }

    //puts this block into location loc of grid gr
    //if there is another block at loc, it is removed
    //precondition:  (1) this block is not contained in a grid
    //               (2) loc is valid in gr
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        if(gr!=null && gr.isValid(loc))
        {
            grid=gr;
            location=loc;
            if(gr.get(loc)!=null)
            {
                gr.get(loc).removeSelfFromGrid();
            }
            gr.put(loc, this);
        }
    }

    //moves this block to newLocation
    //if there is another block at newLocation, it is removed
    //precondition:  (1) this block is contained in a grid
    //               (2) newLocation is valid in the grid of this block
    public void moveTo(Location newLocation)
    {
        if(grid!=null && grid.isValid(newLocation) && grid.isValid(location))
        {
            grid.remove(location);
            if(grid.get(newLocation)!=null)
            {
                grid.get(newLocation).removeSelfFromGrid();
            }
            location=newLocation;
            grid.put(location, this);
        }
    }

    //returns a string with the location and color of this block
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}