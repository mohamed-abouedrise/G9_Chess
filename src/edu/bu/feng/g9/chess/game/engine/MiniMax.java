//package edu.bu.feng.g9.chess.game.engine;
//
//import edu.bu.feng.g9.chess.game.Board;
//
//public class MiniMax {
//
//    private final int searchDepth;
//
//    public MiniMax(final int searchDepth) {
//        this.searchDepth = searchDepth;
//    }
//
//
//    public int execute(Board board){
//        int bestMove = -1;
//        int highestSeenValue = Integer.MIN_VALUE;
//        int lowestSeenValue = Integer.MAX_VALUE;
//        int currentValue;
//
//        for (final int move : board.getLegalMoves()) {
//
//                currentValue = board.isWhiteTurn() ?
//                        max(board.moveAndClone(move), this.searchDepth - 1) :
//                        min(board.moveAndClone(move), this.searchDepth - 1);
//
//                if (board.isWhiteTurn() &&
//                        currentValue >= highestSeenValue) {
//                    highestSeenValue = currentValue;
//                    bestMove = move;
//                } else if (!board.isWhiteTurn() &&
//                        currentValue <= lowestSeenValue) {
//                    lowestSeenValue = currentValue;
//                    bestMove = move;
//                }
//
//        }
//
//        return bestMove;
//    }
//
//    private int min(final Board board,
//                    final int depth) {
//        if(depth == 0 && isEndGameScenario(board)) {
//            return board.evaluate();
//        }
//
//        int lowestSeenValue = Integer.MAX_VALUE;
//        for (final int move : board.getLegalMoves()) {
//            final int currentValue = max(board.moveAndClone(move), depth - 1);
//            if (currentValue <= lowestSeenValue) {
//                lowestSeenValue = currentValue;
//            }
//
//        }
//        return lowestSeenValue;
//    }
//
//    private int max(final Board board,
//                    final int depth) {
//        if(depth == 0 && isEndGameScenario(board)) {
//            return board.evaluate();
//        }
//
//        int highestSeenValue = Integer.MIN_VALUE;
//        for (final int move : board.getLegalMoves()) {
//            final int currentValue = max(board.moveAndClone(move), depth - 1);
//            if (currentValue >= highestSeenValue) {
//                highestSeenValue = currentValue;
//            }
//
//        }
//        return highestSeenValue;
//    }
//
//    private static boolean isEndGameScenario(final Board board) {
//        return board.isCheckMate() || board.isStaleMate();
//    }
//
//}
