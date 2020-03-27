import java.util.Scanner;

/**
 * @author Nicholas Cottrell
 * @version 1.0
 */
public class TicTacToe {
    /** The board used for the entire game */
    private static String[][] board = new String[3][3];

    public static void main(String args[]) {
        initBoard();
        construct();
        while (true) {
            chooseCoord("x");
            construct();
            if (!(decideWin().equals("e")))
                break;
            chooseCoord("o");
            construct();
            if (!decideWin().equals("e"))
                break;
        }
        System.out.println(decideWin() + " is the winner!");
    }

    /**
     * Initializes the board to blanks to begin the game.
     * 
     * @author Nicholas Cottrell
     * @version 1.0
     */
    private static void initBoard() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[r][c] = " ";
    }

    /**
     * Prints a display of the board and its current set up.
     * 
     * @author Nicholas Cottrell
     * @version 1.0
     */
    private static void construct() {
        System.out.println("    A   B   C");
        for (int row = 0; row < 3; row++) {
            System.out.println("  +---+---+---+");
            System.out.print(row);
            for (int col = 0; col < 3; col++)
                System.out.print(" | " + board[row][col]);
            System.out.println(" |");
        }
        System.out.println("  +---+---+---+");
    }

    /**
     * Places a given tile in the place requested. It makes sure that it can be
     * placed and will use recursive if it cannot be placed in the required position
     * (eg, the player asks to place a tile on another tile.) This will place the
     * tile directly on the board.
     * 
     * @param tile the symbol of the tile to be placed. This is often an "X" or an
     *             "O"
     * @author Nicholas Cottrell
     * @version 1.0
     */
    private static void chooseCoord(String tile) {
        Scanner s = new Scanner(System.in);
        System.out.print("Choose Coordinates: ");
        String coord = s.nextLine();
        s.close();

        // Collects the second character for row
        int row = Integer.parseInt(coord.substring(1));
        if (row > 2) {
            chooseCoord(tile);
            return;
        }
        int col = -1;
        if (coord.substring(0, 1).toUpperCase().equals("A"))
            col = 0;
        else if (coord.substring(0, 1).toUpperCase().equals("B"))
            col = 1;
        else if (coord.substring(0, 1).toUpperCase().equals("C"))
            col = 2;
        else { // if it cannot decude the column given, ask again.
            chooseCoord(tile);
            return;
        }
        if (board[row][col].equals(" "))
            board[row][col] = tile;
        else { // ask again if tile isnt empty
            chooseCoord(tile);
            return;
        }
    }

    /**
     * Does the math to decide if a winner can be declared.
     * 
     * @return returns the symbol of the winner.
     * @author Nicholas Cottrell
     * @version 1.0
     */
    private static String decideWin() {
        int x = 0;
        int o = 0;
        int s = 0;

        // determining if theres a win by rows
        for (int row = 0; row < 3; row++) {
            x = 0;
            o = 0;
            for (int col = 0; col < 3; col++) {
                x += (board[row][col].equals("x")) ? 1 : 0;
                o += (board[row][col].equals("o")) ? 1 : 0;
            }
            if (x == 3)
                return "x";
            else if (o == 3)
                return "o";
        }

        // determining if theres a win by columns
        for (int col = 0; col < 3; col++) {
            x = 0;
            o = 0;
            for (int row = 0; row < 3; row++) {
                x += (board[row][col].equals("x")) ? 1 : 0;
                o += (board[row][col].equals("o")) ? 1 : 0;
            }
            if (x == 3)
                return "x";
            else if (o == 3)
                return "o";
        }

        x = 0;
        o = 0;
        // checking first pair of diagnols
        for (int diag = 0; diag < 3; diag++) {
            x += (board[diag][diag].equals("x")) ? 1 : 0;
            o += board[diag][diag].equals("o") ? 1 : 0;
        }
        if (x == 3)
            return "x";
        else if (o == 3)
            return "o";

        x = 0;
        o = 0;
        // checking 2nd pair of diagnols
        for (int cdiag = 0; cdiag < 3; cdiag++) {
            x += board[cdiag][2 - cdiag].equals("x") ? 1 : 0;
            o += board[cdiag][2 - cdiag].equals("o") ? 1 : 0;
        }
        if (x == 3)
            return "x";
        else if (o == 3)
            return "o";

        // determining if theres any empty spaces left
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                s += (board[row][col].equals(" ")) ? 1 : 0;

        if (s == 0) // if there are no empty spaces left, tie
            return "nobody";
        else
            return "e"; // means that match isnt over yet. someone could still win
    }

}