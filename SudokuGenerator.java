public class SudokuGenerator{
    public static void printSudoku(SudokuBoard sudoku){
        int[][] board = sudoku.getBoard();
        for (int i = 0; i < 3; i++){
            System.out.println("+-------+-------+-------+");
            for (int j = 0; j < 3; j++){
                int row = j + (i * 3);
                for (int k = 0; k < 9; k++){
                    if (k % 3 == 0){
                        System.out.print("| ");
                    }
                    System.out.print(board[row][k] + " ");
                }
                System.out.println("|");
            }
            System.out.print("+-------+-------+-------+");
        }
    }
    public static void main (String[] args){
        SudokuBoard puzzle = new SudokuBoard();
        printSudoku(puzzle);
    }
}