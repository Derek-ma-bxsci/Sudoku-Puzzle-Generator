import java.util.ArrayList;
import java.util.Arrays;
public class SudokuBoard{
    private int[][] board; 

    public SudokuBoard(){
        board = createValidBoard();
    }

    public int[][] getBoard(){
        return board;
    }

    // REMINDER THAT METHODS CANT ACTUALLY CHANGE VARIABLES IMMEDIATELY YOU HAVE TO MAKE ANOTHER THEN SET IT TO THAT ONE
    public void initializeBoard(int[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = 0;
            }
        }
    }

    public int[][] createValidBoard(){
        int[][] board = new int[9][9];
        // first fill all numbers with 0
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = 0;
            }
        }

        // keep filling board with random nums that are not already used
        while (findNextEmpty(board)[0] != -1){
            boolean availableValuePut = false;
            ArrayList<Integer> usedNums = new ArrayList<>();
            while (!availableValuePut){
                int num = randomDistinct9(usedNums);
                int[] coords = findNextEmpty(board);
                if (!binaryContains(board, num, coords[0], coords[1])){
                    board[coords[0]][coords[1]] = num;
                    availableValuePut = true;
                }
                usedNums.add(num);
            }
        }
        return board;
    }
    public int[] findNextEmpty(int[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 0){
                    int[] coords = {i, j};
                    return coords;
                }
            }
        }
        int[] coords = {-1, -1};
        return coords;
    }

    public int randomDistinct9(ArrayList<Integer> usedNums){
        int randomNum = (int)(Math.random() * 9) + 1;
        while (usedNums.contains(randomNum)){
            randomNum = (int)(Math.random() * 9) + 1;
        }
        return randomNum;
    }
    public boolean binaryContains(int[][] a, int num, int rowIndex, int colIndex){
        // finds values in row
        int[] row = a[rowIndex];

        // finds values in column
        int[] col = new int[9];
        for (int i = 0; i < a.length; i++){
            col[i] = a[i][colIndex];
        }

        // finds values in square
        int[] square = new int[9];
        int startRowIndex = (rowIndex / 3) * 3;
        int startColIndex = (colIndex / 3) * 3;
        int regIndex = 0; 
        for (int i = startRowIndex; i < startRowIndex + 3; i++){
            for (int j = startColIndex; j < startColIndex + 3; j++){
                square[regIndex] = a[i][j];
                regIndex++;
            }
        }

        return (binaryIsIn(row, num) || binaryIsIn(col, num) || binaryIsIn(square, num));
    }
    
    public boolean binaryIsIn(int[] array, int num){
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (num == array [middle])
                return true;
            else if (num < array[middle])
                right = middle - 1;
            else if (num > array[middle])
                left = middle + 1;
        }
        return false;
    }
}