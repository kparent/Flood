import java.util.ArrayList;
import java.util.List;
import java.util.Random;

///
/// A two-dimensional array of colors
///
public class Board {
   
   //
   // An two dimensional index within the board
   // 
   class Index {
      int x;
      int y;
      
      // Constructor
      public Index(int horizontal, int vertical)
      {
         this.x = horizontal;
         this.y = vertical;
      }
   }
   
   ///
   /// A row of colors
   ///
   public class Row {
      int    size;      // Size of the row
      char[]    colors;      // Each character in the row indicates a color
      int    boundary;   // The last index in a run of entries of the same color
      
      //
      // Row Constructor
      //
      public Row(int size) 
      {
         this.size = size;
         colors = new char[size];
         this.boundary = -1;
         
         // Generate a random number and mod it to create
         // a random arrangement of colors in each row
         Random rn = new Random();
         for (int i = 0; i < size; i++)
         {
            int colorNum = Math.abs((17 * rn.nextInt() + 11) % 6);
            switch (colorNum) 
            {
            case 0: colors[i] = 'O'; break;
            case 1: colors[i] = 'B'; break;
            case 2: colors[i] = 'G'; break;
            case 3: colors[i] = 'Y'; break;
            case 4: colors[i] = 'R'; break;
            case 5: colors[i] = 'P'; break;
            default: System.out.println("An error occurred with the random number generator: " + colorNum);
            }
         }
      }
   }
   
   List<Index>    flooded;      // List of indices of the squares that have been flooded
   int          size;         // size of one side of the board
                           // total number of squares = size * size
   public Row[]    rows;         // Each row in the board
   public int[]    boundaries;      // Each column's boundary
   
   //
   // Board Constructor
   //
   public Board(int size)
   {
      this.size = size;
      rows = new Row[size];
      // Loop through and initialize each row
      for (int i = 0; i < size; i++)
         rows[i] = new Row(size);
      
      // Initialize flooded list and add top left square
      flooded = new ArrayList<Index>();
      flooded.add(new Index(0, 0));
   }
   
   // 
   // Check if a given index is part of the flood and return its index
   // otherwise return -1
   //
   public int inList (int x, int y)
   {
      for (int i = 0; i < this.flooded.size(); i++)
      {
         if (this.flooded.get(i).x == x && this.flooded.get(i).y == y) return i;
      }
      
      return -1;
   }
   
   //
   // Change every square in the flooded list to the specified color
   //
   public void changeFloodColor(char selection)
   {
      for (int i = 0; i < this.flooded.size(); i++)
      {
         Index index = this.flooded.get(i);
         this.rows[index.x].colors[index.y] = selection;
      }
   }
   
   //
   // Print out the 2D array to look like a square board
   //
   public void PrintBoard()
   {
      for (int i = 0; i < size; i++)
      {
         Row currentRow = rows[i];
         System.out.print("[");
         for (int j = 0; j < size; j++)
         {
            System.out.print(" " + currentRow.colors[j] + " ");
         }
         System.out.println("]");
      }
   }
   
}
