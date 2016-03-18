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
 * @version July 18, 2012
 */

import java.awt.Color;
/**
 * This class contains Tile objects.
 */
public class Tile
{
   private Color color;
   private boolean up;
   /**
   * The Tile class contructor constructs Tile objects so that the tiles are a specific color facing downwards.
   */
   public Tile(Color color)
   {
      up = false;
      this.color = color;
   }
   /**
   * Gets the color of the tile.
   * 
   * @return the color of the tile if the tile is facing upwards; otherwise black.
   */
   public Color getColor()
   {
      if (up)
         return color;
      else 
         return Color.BLACK;
   }
   /**
   * Sets the color of the tile to the specified color in the parameter.
   * 
   * @param color is a specified color
   */
   public void setColor(Color color)
   {
      this.color = color;
   }
   /**
   * Gets text.
   * 
   * @return an empty String
   */
   public String getText() { return ""; } 
   /**
   * Flips the tile the opposite side of what it originally was: upwards or downwards
   */
   public void flip()
   {
      up = !up;
   }
}
