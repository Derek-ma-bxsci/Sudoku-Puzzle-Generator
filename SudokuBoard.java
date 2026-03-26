import java.util.ArrayList;
public class SudokuBoard{
    public int[][] board; // public because its a parent class
    public SudokuBoard(){
        board = createValidBoard();
    }

    public int[][] getBoard(){
        return board;
    }

    public int[][] createValidBoard(){
        int[][] newBoard = new int[9][9];

        // keep filling board with random nums that are not already used
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
    
    public ArrayList<Integer> randomNumList(){
        ArrayList<Integer> list = new ArrayList<>(); 
        for (int i = 1; i <= 9; i++){
            list.add(i);
        }
        list = shuffle(list);
        return list;
    }

    public ArrayList<Integer> shuffle(ArrayList<Integer> list){
        for (int i = 0; i < 50; i++){
            int index = (int)(Math.random() * 9);
            int num = list.get(index);
            list.remove(index);
            list.add(num);
        }
        return list;
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

    public boolean isValid(int[][] board, int num, int row, int col) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        // Check box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}