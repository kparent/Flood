import java.util.Scanner;
import java.util.List;

public class ConsoleFlood {
   
   static final int moves = 22;
   
   ///
   /// Checks if a character equates to one of the
   /// colors in the game
   ///
   public static boolean CheckMove(char c)
   {
      switch (c)
      {
         case 'o': return true; 
         case 'b': return true; 
         case 'g': return true; 
         case 'y': return true; 
         case 'r': return true; 
         case 'p': return true; 
         case 'O': return true; 
         case 'B': return true; 
         case 'G': return true; 
         case 'Y': return true; 
         case 'R': return true; 
         case 'P': return true;
         default: 
            System.out.println("The entered color was not valid.");
            return false;
      }
   }
   
   ///
   /// Scan through the game board for the selected color
   /// and add the appropriate neighboring squares
   ///
   public static void FloodBoard(Board board, char selection)
   {
      // Loop through each square in the game board and check its neighboring squares 
      for (int i = 0; i < board.size; i++)
      {
         for (int j = 0; j < board.size; j++)
         {
            // Get the list of squares already part of the flood
            List<Board.Index> flooded = board.flooded;
            // If the current square is not in the flood then continue to the next square
            if (board.inList(i, j) == -1) continue;
            
            // Check all the squares neighbors
            // Check square above
            if (i != 0 && 
                board.rows[i - 1].colors[j] == selection) flooded.add(board.new Index(i - 1, j));
            // Check square below
            if (i != board.size - 1 && board.rows[i + 1].colors[j] == selection) flooded.add(board.new Index(i + 1, j));
            // Check square to left
            if (j != 0 && board.rows[i].colors[j - 1] == selection) flooded.add(board.new Index(i, j - 1));
            // Check square to the right
            if (j != board.size - 1 && board.rows[i].colors[j + 1] == selection) flooded.add(board.new Index(i, j + 1));
         }
      }
      
      board.changeFloodColor(selection);
   }
   
   // Scan board for a square that has not yet been flooded
   public static boolean hasWon(Board board)
   {
      for (int i = 0; i < board.size; i++)
      {
         for (int j = 0; j < board.size; j++)
         {
            if (board.flooded.indexOf(board.new Index(i, j)) == -1)
               return false;
         }
      }
      return true;
   }

   public static void main(String[] args)
   {
      boolean playing = true;      // Keeps loop going
      int movesLeft = 22;         // Start with 22 moves to take
      Board board = new Board(10);   // Control object for the game
      FloodBoard(board, board.rows[0].colors[0]);    // Make sure any neighboring colors to the 
                                       // starting square are inserted into the flood
      Scanner scan = new Scanner(System.in);
      
      while(playing)
      {   
         // Print the board and user options
         board.PrintBoard();
         System.out.println("Move: " + (movesLeft - moves) + "/" + 22);
         System.out.print("(O) - Orange\n(B) - Blue\n(G) - Green\n"
                     + "(Y) - Yellow\n(R) - Red\n(P) - Pink\n");
         System.out.println("Choose next color:");
         
         // Scan the users next move
         char selection = scan.next().charAt(0);
         boolean valid = CheckMove(selection);
         if (!valid) continue;
         
         FloodBoard(board, selection);
         
         // Check if the game has been won
         if (hasWon(board))
         {
            System.out.println("You won!");
            playing = false;
            break;
         }
         else if (movesLeft == 0)
         {
            System.out.println("You lose!");
            playing = false;
            break;
         }
         
         // User gets one less move
         movesLeft--;
         
      }
      scan.close();
   }
}
