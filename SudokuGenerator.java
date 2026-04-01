import java.util.Scanner;
public class SudokuGenerator{
    public static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        System.out.print("Welcome to Sudoku!" + "\n" + 
                            "Please enter a difficulty: " + "\n" +
                            "E for Easy (20 missing numbers, 3 mistakes) " + "\n" +
                            "N for Normal (30 missing numbers, 3 mistakes)" + "\n" +
                            "H for Hard (40 missing numbers, 2 mistakes)" + "\n" +
                            "I for Insane (50 missing numbers, 1 mistake)" + "\n"  +
                            "Alternatively type S to see a solved board: ");

        String difficulty = sc.nextLine();
        if (difficulty.toLowerCase().charAt(0) == 's'){
            SudokuBoard solvedBoard = new SudokuBoard();
            System.out.println("Here is your solved sudoku board:");
            printSudoku(solvedBoard.getBoard());
            return; // Stops the program entirely
        }
        boolean repeatInstructions = true;
        boolean gameStopped = false;
        SolvableBoard sudokuGame = new SolvableBoard(difficulty);
        printSudoku(sudokuGame.getUnsolvedBoard());
        System.out.println("Your starting board is printed above. " + "\n" + 
                           "You may type \"quit\" anytime to stop the game." + "\n");
        while (!sudokuGame.isLost() && !sudokuGame.isWon() && !gameStopped){
            int value = -1, row = -1, col = -1;
            while (value == -1){
                boolean validInput = false;
                if (repeatInstructions){
                    System.out.print("Enter the row and column of your guess (separate with a comma, must be between 1 and 9): ");
                } else {
                    System.out.print("Enter the row and column of your guess: ");
                }
                String[] guess = sc.nextLine().split(",");
                outer:
                while (!validInput){ // Prevents the code from moving until a valid input is given
                    if (guess.length == 1 && isStringQuit(guess[0])){ // Stops all the code if the input is quit
                        gameStopped = true;
                        validInput = true;
                        value = 0;
                        System.out.println("Game stopped.");
                        break outer;
                    } else if (guess.length != 2 || !isNumber(guess[0]) || !isNumber(guess[1])) { // Checks if there is only one comma and the inputs are numbers
                        System.out.print("Please enter a valid index: ");
                        guess = sc.nextLine().split(",");
                    } else {
                        row = Integer.valueOf(guess[0].trim()) - 1;
                        col = Integer.valueOf(guess[1].trim()) - 1;
                        if (!isFrom1to9(row + 1) || !isFrom1to9(col + 1)){ // Checks if the coords inputted are between 1 to 9
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
                if (!gameStopped){
                    validInput = false;
                    if (repeatInstructions){
                        System.out.print("Guessed value (If you want to go back to picking an index, type \"return\"): ");
                    } else {
                        System.out.print("Guessed value: ");
                    }
                    String input = sc.nextLine();
                    outer:
                    while (!validInput || !input.toLowerCase().equals("return")){
                        if (isStringQuit(input)){
                            gameStopped = true;
                            validInput = true;
                            value = 0;
                            System.out.println("Game stopped.");
                            break outer;
                        } else if (!input.toLowerCase().equals("return")){
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
                        } else {
                            break;
                        }
                    }
                    if (!gameStopped){
                        System.out.println();
                        if (sudokuGame.isCorrectGuess(value, row, col)){
                            System.out.println("Your guess was correct!");
                        } else {
                            System.out.println("Your guess was incorrect.");
                        }
                        System.out.println("Current mistakes amount: " + sudokuGame.getMistakes() + "/" + sudokuGame.getMaxMistakes());
                        System.out.println("Current state of board: ");
                        printSudoku(sudokuGame.getUnsolvedBoard());
                    }
                    repeatInstructions = false; // Once an attempt at guessing is done, prevent the information in the parentheses from showig up
                }
            }
        }
        if (!gameStopped){
            if (sudokuGame.isWon()){
                System.out.println("Congratulations, you have completed the sudoku puzzle!");
            } else {
                System.out.println("Unfortunately, you have lost the game. Better luck next time!");
            }
        }
    }
    
    public static void printSudoku(int[][] board){ // Prints a Sudoku board in the desired format
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

    public static boolean isNumber(String str) { // Checks if the string is a number
        try {
            Integer.parseInt(str); // Returns true if this line does not throw an error
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFrom1to9(int num){ // Returns true if the number is between 1 to 9
        return num >= 1 && num <= 9;
    }
    
    public static boolean isStringQuit(String str){ // Checks if the string is equal to "quit"
        return str.toLowerCase().equals("quit");
    }
}