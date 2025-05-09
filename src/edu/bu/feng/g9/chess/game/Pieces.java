package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    public static int[] rookMoves(char file, int rank, Board board, Board.Color color){
        List<Integer> moves = new ArrayList<>();
        for(int i = 1; file + i <= 'h'; i++){
            if(!checkIfSquareOccupied((char)(file + i), rank, board))
                moves.add(BitMap.toIndex((char)(file + i), rank));
            else
                break;
        }
        for(int i = 1; file - i >= 'a'; i++){
            if(!checkIfSquareOccupied((char)(file - i), rank, board))
                moves.add(BitMap.toIndex((char)(file - i), rank));
            else
                break;
        }
        for(int i = 1; rank + i <= 8; i++){
            if(!checkIfSquareOccupied(file, rank + i, board))
                moves.add(BitMap.toIndex(file, rank + i));
            else
                break;
        }
        for(int i = 1; rank - i >= 1; i++){
            if(!checkIfSquareOccupied(file, rank - i, board))
                moves.add(BitMap.toIndex(file, rank - i));
            else
                break;
        }
        return moves.stream().mapToInt(e -> e).toArray();
    }

    private static boolean checkIfSquareOccupied(char file, int rank, Board board){
        boolean result = false;
        BitMap[] pieces = board.getPieces();
        for(BitMap piece : pieces){
            result = result || piece.getBit(file, rank);
        }
        return result;
    }

}
