import java.util.ArrayList;

//A MyBoundedGrid is a rectangular grid with a finite number of rows and columns.
public class MyBoundedGrid<E>
{
    private Object[][] occupantArray;  // the array storing the grid elements

    //Constructs an empty MyBoundedGrid with the given dimensions.
    //(Precondition:  rows > 0 and cols > 0.)
    public MyBoundedGrid(int rows, int cols)
    {
        occupantArray = new Object[rows][cols];
    }

    //returns the number of rows
    public int getNumRows()
    {
        int rows=0;
        for(int r=0; r<occupantArray.length; r++)
        {
            rows+=1;
        }
        return rows;
    }

    //returns the number of columns
    public int getNumCols()
    {
        int cols=0;
        for(int c=0; c<occupantArray[0].length; c++)
        {
            cols+=1;
        }
        return cols;  
    }

    //returns true if loc is valid in this gr   id, false otherwise
    //precondition:  loc is not null
    public boolean isValid(Location loc)
    {
        if(loc!=null)
        {
            int r=loc.getRow();
            int c=loc.getCol();
            if(r>=0 && r<getNumRows() && c>=0 && getNumCols()>c)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    //returns the object at location loc (or null if the location is unoccupied)
    //precondition:  loc is valid in this grid
    public E get(Location loc)
    {
        if(isValid(loc))
        {
            int r=loc.getRow();
            int c=loc.getCol();
            return (E)(occupantArray[r][c]);
        }
        else
        {
            return null;
        }
        //(You will need to promise the return value is of type E.)
    }

    //puts obj at location loc in this grid and returns the object previously at that location (or null if the
    //location is unoccupied)
    //precondition:  loc is valid in this grid
    public E put(Location loc, E obj)
    {
        if(isValid(loc))
        {
            int r=loc.getRow();
            int c=loc.getCol();
            if(occupantArray[r][c]!=null)
            {
                Object temp=occupantArray[r][c];
                occupantArray[r][c]=obj;
                return (E)(temp);
            }
            else
            {
                occupantArray[r][c]=obj;
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    //removes the object at location loc from this grid and returns the object that was removed (or null if the
    //location is unoccupied
    //precondition:  loc is valid in this grid
    public E remove(Location loc)
    {
        if(isValid(loc))
        {
            int r=loc.getRow();
            int c=loc.getCol();
            if(occupantArray[r][c]!=null)
            {
                Object temp=occupantArray[r][c];
                occupantArray[r][c]=null;
                return (E)(temp);
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    //returns an array list of all occupied locations in this grid
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs=new ArrayList<Location>();
        for(int r=0; r<occupantArray.length; r++)
        {
            for(int c=0; c<occupantArray[r].length; c++)
            {
                if(occupantArray[r][c]!=null)
                {
                    Location l=new Location(r, c);
                    locs.add(l);
                }
            }
        }
        return locs;
    }
}