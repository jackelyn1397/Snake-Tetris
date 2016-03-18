/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * 
 * @modifier Jackelyn Shen
 * 
 * @version July 18, 2012
 * 
 * Students:  make modifications to the code where you see ...
 */


import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.awt.Color;

/**
* Runs a game including Tile objects.
*/
public class TileGame extends World<Tile>
{
   private Tile firstTile;
   private Tile secondTile;
   private boolean first;
   /**
   * Sets the color and the number of tiles and initializes the message.
   */
   public TileGame()
   {
      Color[] colors = 
      { 
            Color.RED, Color.BLUE, Color.GREEN, Color.CYAN,
            Color.PINK, Color.ORANGE, Color.GRAY, Color.MAGENTA,
            Color.YELLOW
      };
      for (int i = 0 ; i < colors.length ; i++)
      {
         Color color = colors[i];
         add(getRandomEmptyLocation(), new Tile(color));
         add(getRandomEmptyLocation(), new Tile(color));
      }
      setMessage("Click on the first tile");
      first = true;
   }
   /**
   * Determines if two tiles facing upwards have the same color.
   * 
   * @return true if same color; otherwise false
   * @param       the location of the tile
   */
   public boolean locationClicked(Location loc)
   {
      Grid<Tile> gr = getGrid();
      Tile t = gr.get(loc);
      if (t != null) 
      { 
         t.flip( );
         if (first)
         {
            if (firstTile != null) 
            {
               // ... flip both the firstTile and secondTile
               firstTile.flip();
               secondTile.flip();
            }
            // ... set the firstTile to t
            firstTile=t;
            setMessage("Click on the second tile");
            // ... set first to false
            first=false;
         }
         else
         {            
            // test whether the firstTile's Color is
            // equal to t's color
            if(firstTile.getColor()==t.getColor())
            {               
               // ...if true, set both firstTile and secondTile to null
               firstTile=null;
               secondTile=null;
            }
            else
            {// ...else set secondTile to t
                secondTile=t;
            }
  
            // set message to "Click on the first tile"
            setMessage("Click on the first tile");
            // ... set first to true
            first=true;
         }                  
      }
      return true;      
   }
   /**
   * Determines if a key has been pressed.
   * 
   * @param loc            the location of the tile
   * @param description    the description of the tile
   * @return true          if a key has been pressed; otherwise false
   */
   public boolean keyPressed(String description, Location loc)
   {
       if (description.equals("SPACE"))      
           return locationClicked(loc);
       else
           return false;
   }
   /**
   * Displays a new tile game.
   */
   public static void main(String[] args)
   {
      new TileGame().show();
   }
   

}
