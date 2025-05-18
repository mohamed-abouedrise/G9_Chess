package edu.bu.feng.g9.chess.engine;

import edu.bu.feng.g9.chess.game.Board;

public class MiniMax {
    private final int searchDepth;

    public MiniMax(final int searchDepth) {
        this.searchDepth = searchDepth;
    }


    public Move execute(Board board){
        Move bestMove = null;
        int highestSeenValue = Integer.MIN_VALUE;
        int lowestSeenValue = Integer.MAX_VALUE;
        int currentValue;

        final Board nextBoard = board.moveAndClone;
        for (final Move move : board.getLegalMoves()) {

                currentValue = board.isWhite() ?
                        max(nextBoard, this.searchDepth - 1) :
                        min(nextBoard, this.searchDepth - 1);

                if (board.isWhite() &&
                        currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                    bestMove = move;
                } else if (!board.isWhite() &&
                        currentValue <= lowestSeenValue) {
                    lowestSeenValue = currentValue;
                    bestMove = move;
                }

        }

        return bestMove;
    }

    private int min(final Board board,
                    final int depth) {
        if(depth == 0 && isEndGameScenario(board)) {
            return board.evaluate;
        }

        int lowestSeenValue = Integer.MAX_VALUE;
        final Board nextBoard = board.moveAndClone;
        for (final Move move : board.getLegalMoves()) {
            final int currentValue = max(nextBoard, depth - 1);
            if (currentValue <= lowestSeenValue) {
                lowestSeenValue = currentValue;
            }

        }
        return lowestSeenValue;
    }

    private int max(final Board board,
                    final int depth) {
        if(depth == 0 && isEndGameScenario(board)) {
            return board.evaluate;
        }

        int highestSeenValue = Integer.MIN_VALUE;
        final Board nextBoard = board.moveAndClone;
        for (final Move move : board.getLegalMoves()) {
            final int currentValue = max(nextBoard, depth - 1);
            if (currentValue >= highestSeenValue) {
                highestSeenValue = currentValue;
            }

        }
        return highestSeenValue;
    }

    private static boolean isEndGameScenario(final Board board) {
        return board.isInCheckMate() || board.isInStaleMate();
    }

}
