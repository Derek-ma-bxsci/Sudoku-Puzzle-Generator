public class SolvableBoard extends SudokuBoard{
    private int[][] unsolvedBoard;
    private int mistakes;
    public SolvableBoard(){
        super();
        unsolvedBoard = createSolvableBoard(board);
        mistakes = 0;
    }
    
    public int[][] createSolvableBoard(int[][] originalBoard){
        int[][] unsolved = new int[originalBoard.length][originalBoard[0].length];
        for (int i = 0; i < originalBoard.length; i++) { // references for some reason
            unsolved[i] = originalBoard[i].clone(); 
        }
        int emptyCount = 0;
        while (emptyCount <= 40){
            outer:
            for (int r = 0; r < originalBoard.length; r++){
                for (int c = 0; c < originalBoard[0].length; c++){
                    if (unsolved[r][c] != 0 && (int)(Math.random() * 3) == 1){
                        unsolved[r][c] = 0;
                        emptyCount++;
                        if (emptyCount >= 40) break outer;
                    }
                }
            }
        }
        return unsolved;
    }

    public boolean isCorrectGuess(int guessedValue, int row, int col){
        if (board[row][col] == guessedValue){
            setBoardValue(guessedValue, row, col);
            return true;
        }
        mistakes++;
        return false;
    }

    public int getMistakes(){
        return mistakes;
    }
    
    public int[][] getBoard(){
        return board;
    }

    public void setBoardValue(int value, int row, int col){
        unsolvedBoard[row][col] = value;
    }

    public int[][] getUnsolvedBoard(){
        return unsolvedBoard;
    }
    
    public boolean isLost(){
        return mistakes >= 3;
    }
    public boolean isWon(){
        int[] availableCoords = findNextEmpty(unsolvedBoard);
        return availableCoords[0] == -1 && mistakes < 3;
    }
}
