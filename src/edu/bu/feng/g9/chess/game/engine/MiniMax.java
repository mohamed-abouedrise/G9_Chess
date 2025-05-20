package edu.bu.feng.g9.chess.game.engine;

import edu.bu.feng.g9.chess.game.Board;
import edu.bu.feng.g9.chess.utils.BitMap;

import java.util.Random;

public class MiniMax {

    private final int searchDepth;
    int pathCounter = 0;

    public MiniMax(final int searchDepth) {
        this.searchDepth = searchDepth;
    }


    public Board execute(Board board){
        int bestMove = -1;
        int highestSeenValue = Integer.MIN_VALUE;
        int lowestSeenValue = Integer.MAX_VALUE;
        int currentValue;
        long initialTime = System.nanoTime();
        pathCounter = 0;
        if(searchDepth == 0){
            pathCounter++;
            int[] moves = board.getLegalMoves();
            Random rand = new Random();
            int randomIndex = rand.nextInt(moves.length);
            return board.moveAndClone(moves[randomIndex]);
        }
        for (final int move : board.getLegalMoves()) {
                currentValue = board.isWhiteTurn() ?
                        max(board.moveAndClone(move), this.searchDepth - 1):
                        min(board.moveAndClone(move), this.searchDepth - 1);
                if (board.isWhiteTurn() &&
                        currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                    bestMove = move;
                } else if (!board.isWhiteTurn() &&
                        currentValue <= lowestSeenValue) {
                    lowestSeenValue = currentValue;
                    bestMove = move;
                }

        }
        long finalTime = System.nanoTime();
        long timeDifference = finalTime - initialTime;

        double nanoseconds = timeDifference;
        double microseconds = timeDifference / 1_000.0;
        double milliseconds = timeDifference / 1_000_000.0;
        double seconds = timeDifference / 1_000_000_000.0;

// Print results
        System.out.println("Time difference:");
        System.out.printf("Nanoseconds:  %.0f ns\n", nanoseconds);
        System.out.printf("Microseconds: %.3f µs\n", microseconds);
        System.out.printf("Milliseconds: %.3f ms\n", milliseconds);
        System.out.printf("Seconds:      %.6f s\n", seconds);
        System.out.println(pathCounter);
        return board.moveAndClone(bestMove);
    }

    private int min(final Board board,
                    final int depth) {
        if(depth <= 0 || isEndGameScenario(board)) {
            pathCounter++;
            return board.evaluate();
        }

        int highestSeenValue = Integer.MIN_VALUE;
        for (final int move : board.getLegalMoves()) {
            try {
                final int currentValue = max(board.moveAndClone(move), depth - 1);
                if (currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                }
            }catch (Exception ignored){
                System.out.println(board.isWhiteTurn());
                System.out.println(board);
            }

        }
        return highestSeenValue;
    }

    private int max(final Board board,
                    final int depth) {
        if(depth <= 0 || isEndGameScenario(board)) {
            pathCounter++;
            return board.evaluate();
        }

        int lowestSeenValue = Integer.MAX_VALUE;
        for (final int move : board.getLegalMoves()) {
            try {
                final int currentValue = min(board.moveAndClone(move), depth - 1);
                if (currentValue <= lowestSeenValue) {
                    lowestSeenValue = currentValue;
                }
            }catch (Exception ignored){
                System.out.println(board.isWhiteTurn());
                System.out.println(board);
            }

        }
        return lowestSeenValue;
    }

    private static boolean isEndGameScenario(final Board board) {
        return board.isCheckMate() || board.isStaleMate();
    }

}
