public class SolvableBoard extends SudokuBoard{
    private int[][] unsolvedBoard;
    private int mistakes;
    public SolvableBoard(){
        super();
        unsolvedBoard = createSolvableBoard(board);

    }

    public int[][] createSolvableBoard(int[][] originalBoard){
        int[][] unsolved = originalBoard;
        for (int i = 0;)
    }

    public boolean correctGuess(int guessedValue, int row, int col){
        if (unsolvedBoard[row][col] != 0){
            return false;
        }
        if (board[row][col] == guessedValue){
            setBoardValue(guessedValue, row, col);
            return true;
        }
    }
    public int[][] getUnsolvedBoard(){
        return unsolvedBoard;
    }

    public int getMistakes(){
        return mistakes;
    }
    
    public void setBoardValue(int value, int row, int col){
        unsolvedBoard[row][col] = value;
    }
}
