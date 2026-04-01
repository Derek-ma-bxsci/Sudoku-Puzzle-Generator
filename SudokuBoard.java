import java.util.ArrayList;
public class SudokuBoard{
    public int[][] board;
    public SudokuBoard(){
        board = createValidBoard();
    }

    public int[][] createValidBoard(){ // Creates a sudoku board that meet the conditions of a sudoku board
        int[][] newBoard = new int[9][9];

        // Fills the empty board with random nums that meet isValid conditions
        while (findNextEmpty(newBoard)[0] != -1){
            boolean availableValuePut = false;
            while (!availableValuePut){
                int[] coords = findNextEmpty(newBoard);
                ArrayList<Integer> shuffledNums = randomNumList();
                for (int i = 0; i < shuffledNums.size() && !availableValuePut; i++){
                    int num = shuffledNums.get(i);
                    if (isValid(newBoard, num, coords[0], coords[1])){
                        newBoard[coords[0]][coords[1]] = num;
                        availableValuePut = true;
                    }
                }
                if (!availableValuePut){
                    return createValidBoard();
                }
            }
        }
        return newBoard;
    }
    
    public ArrayList<Integer> randomNumList(){ // Returns an ArrayList that has numbers 1 to 9 that are shuffled
        ArrayList<Integer> list = new ArrayList<>(); 
        for (int i = 1; i <= 9; i++){
            list.add(i);
        }
        list = shuffle(list);
        return list;
    }

    public ArrayList<Integer> shuffle(ArrayList<Integer> list){ // Shuffles numbers in the ArrayList around
        for (int i = 0; i < 50; i++){
            int index = (int)(Math.random() * list.size()); // Randomly chooses a valid index
            int num = list.get(index);
            list.remove(index);
            list.add(num); // Removes that number from its index and adds it to the end of the ArrayList
        }
        return list;
    }

    public int[] findNextEmpty(int[][] board){ // Finds the coords of the next value that is zero
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == 0){
                    int[] coords = {i, j};
                    return coords;
                }
            }
        }
        int[] coords = {-1, -1};
        return coords; // returns (-1, -1) if not found
    }

    public boolean isValid(int[][] board, int num, int row, int col) { // Checks to see if the number is not present in the row, column, or box it is in
        // Check row and column for repeats
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        // Check box for repeats
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true; // If the number is not found, return true
    }
    
    public int[][] getBoard(){
        return board;
    }
}