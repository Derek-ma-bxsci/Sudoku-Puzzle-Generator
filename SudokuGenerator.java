import java.util.Scanner;
public class SudokuGenerator{
    public static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        //SudokuBoard puzzle = new SudokuBoard();
        //printSudoku(puzzle.getBoard());

        System.out.print("Welcome to Sudoku!" + "\n" + 
                            "Please enter a difficulty: " + "\n" +
                            "E for Easy (20 missing numbers, 3 mistakes) " + "\n" +
                            "N for Normal (30 missing numbers, 3 mistakes)" + "\n" +
                            "H for Hard (40 missing numbers, 2 mistakes)" + "\n" +
                            "I for Insane (50 missing numbers, 1 mistake)" + "\n");

        String difficulty = sc.nextLine();
        SolvableBoard sudokuGame = new SolvableBoard(difficulty);
        printSudoku(sudokuGame.getUnsolvedBoard());
        while (!sudokuGame.isLost() && !sudokuGame.isWon()){
            int value = -1, row = -1, col = -1;
            while (value == -1){
                boolean validInput = false;
                System.out.print("Enter the row and column of your guess (separate with a comma, must be between 1 and 9): ");
                String[] guess = sc.nextLine().split(",");
                while (!validInput){ // makes sure that the entered value is makes sense and is in range
                    if (guess.length < 2 || guess[0].equals("") || guess[1].equals("") || 
                        !isNumber(guess[0]) || !isNumber(guess[1]) ) {
                        System.out.print("Please enter a valid index: ");
                        guess = sc.nextLine().split(",");
                    } else {
                        row = Integer.valueOf(guess[0].trim()) - 1;
                        col = Integer.valueOf(guess[1].trim()) - 1;
                        if (!isFrom1to9(row + 1) || !isFrom1to9(col + 1)){
                            System.out.print("Please enter indexes that are between 1 to 9: ");
                            guess = sc.nextLine().split(",");
                        } else if (sudokuGame.isOccupied(row,col)){
                            System.out.print("That cell is occupied. Please enter another index: ");
                            guess = sc.nextLine().split(",");
                        } else {
                            validInput = true;
                        }
                    }
                }
                validInput = false;

                System.out.print("Guessed value (If you want to go back to picking an index, type \"return\"): ");
                String input = sc.nextLine();
                while (!validInput || !input.toLowerCase().equals("return")){
                    if (!input.toLowerCase().equals("return")){
                        if (isNumber(input)){
                            value = Integer.parseInt(input);
                                if (isFrom1to9(value)){
                                    validInput = true;
                                    break;
                                } else {
                                    value = -1;
                                    System.out.print("Please enter a valid guess: ");
                                    input = sc.nextLine();
                                }
                        } else {
                            System.out.print("Please enter a valid guess: ");
                            input = sc.nextLine();
                        }
                    }
                }
            }
            if (sudokuGame.isCorrectGuess(value, row, col)){
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            System.out.println("Current mistakes amount: " + sudokuGame.getMistakes() + "/" + sudokuGame.getMaxMistakes());
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

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFrom1to9(int num){
        return num >= 1 && num <= 9;
    }
}