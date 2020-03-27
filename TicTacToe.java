import java.util.Scanner;

public class TicTacToe{
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
                board[r][c] = " ";
    }
    private static void construct(){
        System.out.println("    A   B   C");
        for (int row=0; row <3; row++){
            System.out.println("  +---+---+---+");
            System.out.print(row);
            for(int col=0; col<3; col++)
                System.out.print(" | "+board[row][col]);
            System.out.println(" |");
        }
        System.out.println("  +---+---+---+");
    }
    private static void chooseCoord(String tile){
        Scanner s = new Scanner(System.in);
        System.out.print("Choose Coordinates: ");
        String coord = s.nextLine();
        int row = Integer.parseInt(coord.substring(1));
        if (row>2){
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
        else{
            chooseCoord(tile);
            return;
        }
        if (board[row][col].equals(" "))
            board[row][col] = tile;
        else {
            chooseCoord(tile);
            return;
        }
    }
    private static String decideWin(){
        int x = 0;
        int o = 0;
        int s = 0;
        for (int row=0; row<3; row++){
            x = 0;
            o = 0;
            for (int col=0; col<3; col++){
                x += (board[row][col].equals("x")) ? 1 : 0;
                o += (board[row][col].equals("o")) ? 1 : 0;
            }
            if (x==3)
                return "x";
            else if (o==3)
                return "o";
        }
        for (int col=0; col<3; col++){
            x=0;
            o=0;
            for (int row=0; row<3; row++){
                x += (board[row][col].equals("x")) ? 1 : 0;
                o += (board[row][col].equals("o")) ? 1 : 0;
            }
            if (x==3)
                return "x";
            else if (o==3)
                return "0";
        }
        for(int row=0; row<3; row++)
            for(int col=0; col<3; col++)
                s += (board[row][col].equals(" ")) ? 1 : 0;
        x = 0;
        o = 0;
        for (int diag=0; diag<3; diag++){
            x += (board[diag][diag].equals("x")) ? 1: 0;
            o += board[diag][diag].equals("o") ? 1:0;
        }
        if (x==3 || (board[0][2].equals("x") && board[1][1].equals("x") && board[2][0].equals("x")))
            return "x";
        else if (o==3 || (board[0][2].equals("o") && board[1][1].equals("o") && board[2][0].equals("o")))
            return "o";
        else if (s == 0)
            return "nobody";
        else
            return "e";
    }
    public static void main(String args[]){
        initBoard();
        construct();
        while(true){
            chooseCoord("x");
            construct();
            if (!(decideWin().equals("e")))
                break;
            chooseCoord("o");
            construct();
            if(!decideWin().equals("e"))
                break;
        }
        System.out.println(decideWin() + " is the winner!");
    }
}