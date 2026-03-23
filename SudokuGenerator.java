import java.util.Scanner;
public class SudokuGenerator{
    public static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        //SudokuBoard puzzle = new SudokuBoard();
        //printSudoku(puzzle.getBoard());
        // difficulty?

        System.out.println("Welcome to sudoku!" + "\n" + 
                            "Please enter a difficulty: " + "\n" +
                            "E for Easy (20 missing numbers, 3 mistakes) " + "\n" +
                            "N for Normal (30 missing numbers, 3 mistakes)" + "\n" +
                            "H for Hard (40 missing numbers, 2 mistakes)" + "\n" +
                            "I for Insane (50 missing numbers, 1 mistake)" + "\n");

        String difficulty = sc.nextLine();
        SolvableBoard sudokuGame = new SolvableBoard(difficulty);
        printSudoku(sudokuGame.getUnsolvedBoard());
        while (!sudokuGame.isLost() && !sudokuGame.isWon()){
            System.out.print("Row and col of guess (separate with a comma (1-9)): ");
            String[] guess = sc.nextLine().split(",");
            int row = Integer.valueOf(guess[0].trim()) - 1;
            int col = Integer.valueOf(guess[1].trim()) - 1;

            System.out.print("Guessed value: ");
            int value = sc.nextInt();
            if (sudokuGame.isCorrectGuess(value, row, col)){
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            System.out.println("Current mistakes amount: " + sudokuGame.getMistakes() + "/3");
            printSudoku(sudokuGame.getUnsolvedBoard());
            sc.nextLine();
        }
        if (sudokuGame.isWon()){
            System.out.println("nice");
        } else {
            System.out.println("you lost");
        }
    }
    
    public static void printSudoku(int[][] board){
        for (int i = 0; i < 3; i++){
            System.out.println("+-------+-------+-------+");
            for (int j = 0; j < 3; j++){
                int row = j + (i * 3);
                for (int col = 0; col < 9; col++){
                    if (col % 3 == 0){
                        System.out.print("| ");
                    }
                    if (board[row][col] == 0){
                        System.out.print("- ");
                    } else {
                        System.out.print(board[row][col] + " ");
                    }
                }
                System.out.println("|");
            }
        }
        System.out.println("+-------+-------+-------+");
        System.out.println();
    }
}