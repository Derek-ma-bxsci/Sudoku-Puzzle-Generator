import java.util.Arrays;
public class SudokuBoard{
    private int[][] board; 
    public SudokuBoard(){
        initializeBoard(board);
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
    public void createValidBoard(int[][] board){
        // first fill all numbers with 0
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = 0;
            }
        }

        // keep filling board with random nums that are not already used
        while (findNextEmpty(board)[0] != -1){
            boolean availableValuePut = false;
            while (availableValuePut){
                int num = random9();
                int[] coords = findNextEmpty(board);
                if (!binaryContains(board, num, coords[0], coords[1])){
                    board[coords[0]][coords[1]] = num;
                    availableValuePut = true;
                }
            }
        }
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

    public int random9(){
        return (int)(Math.random() * 9) + 1;
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

        // if the num is in any of them, return true;
        if (binaryIsIn(row, num) || binaryIsIn(col, num) || binaryIsIn(square, num))
            return true;
        
        // else return false
        return false;
    }
    
    public boolean binaryIsIn(int[] array, int num){
        Arrays.sort(array);
        int left = 0;
        int right = array.length;
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