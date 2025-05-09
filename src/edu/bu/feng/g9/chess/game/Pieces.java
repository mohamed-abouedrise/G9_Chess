package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    public static int[] rookMoves(char file, int rank, Board board, Board.Color color){
        List<Integer> moves = new ArrayList<>();
        for(int i = 1; file + i <= 'h'; i++){
            if(BitMap.toIndex((char)(file + i), rank) != -1 && !checkIfSquareOccupied((char)(file + i), rank, board))
                moves.add(BitMap.toIndex((char)(file + i), rank));
            else
                break;
        }
        for(int i = 1; file - i >= 'a'; i++){
            if(BitMap.toIndex((char)(file - i), rank) != -1 && !checkIfSquareOccupied((char)(file - i), rank, board))
                moves.add(BitMap.toIndex((char)(file - i), rank));
            else
                break;
        }
        for(int i = 1; rank + i <= 8; i++){
            if(BitMap.toIndex(file, rank + i) != -1 && !checkIfSquareOccupied(file, rank + i, board))
                moves.add(BitMap.toIndex(file, rank + i));
            else
                break;
        }
        for(int i = 1; rank - i >= 1; i++){
            if(BitMap.toIndex(file, rank - i) != -1 && !checkIfSquareOccupied(file, rank - i, board))
                moves.add(BitMap.toIndex(file, rank - i));
            else
                break;
        }
        return moves.stream().mapToInt(e -> e).toArray();
    }

    public static int[] bishopMoves(char file, int rank, Board board, Board.Color color){
        List<Integer> moves = new ArrayList<>();
        for(int i = 1; file + i <= 'h'; i++){
            if(BitMap.toIndex((char)(file + i), rank + i) != -1
                    && !checkIfSquareOccupied((char)(file + i), rank + i, board))
                moves.add(BitMap.toIndex((char)(file + i), rank + i));
            else
                break;
        }
        for(int i = 1; file - i >= 'a'; i++){
            if(BitMap.toIndex((char)(file - i), rank + i) != -1
                    && !checkIfSquareOccupied((char)(file - i), rank + i, board))
                moves.add(BitMap.toIndex((char)(file - i), rank + i));
            else
                break;
        }
        for(int i = 1; file + i <= 'h'; i++){
            if(BitMap.toIndex((char)(file + i), rank - i) != -1
                    && !checkIfSquareOccupied((char)(file + i), rank - i, board)) {
                moves.add(BitMap.toIndex((char) (file + i), rank - i));
            }
            else
                break;
        }
        for(int i = 1; file - i >= 'a'; i++){
            if(BitMap.toIndex((char)(file - i), rank - i) != -1
                    && !checkIfSquareOccupied((char)(file - i), rank - i, board))
                moves.add(BitMap.toIndex((char)(file - i), rank - i));
            else
                break;
        }
        return moves.stream().mapToInt(e -> e).toArray();
    }

    public static int[] queenMoves(char file, int rank, Board board, Board.Color color){
        int[] straight = rookMoves(file, rank, board, color);
        int[] diagonal = bishopMoves(file, rank, board, color);
        int[] moves = new int[straight.length + diagonal.length];

        for(int i = 0; i < straight.length; i++)
            moves[i] = straight[i];
        for(int i = 0; i < diagonal.length; i++)
            moves[i + straight.length] = diagonal[i];

        return moves;
    }

    public static int[] knightMoves(char file, int rank, Board board, Board.Color color){
        List<Integer> moves = new ArrayList<>();

        if(BitMap.toIndex((char)(file + 1), rank + 2) != -1
                && !checkIfSquareOccupied((char)(file + 1), rank + 2, board))
            moves.add(BitMap.toIndex((char)(file + 1), rank + 2));
        if(BitMap.toIndex((char)(file + 1), rank - 2) != -1
                && !checkIfSquareOccupied((char)(file + 1), rank - 2, board))
            moves.add(BitMap.toIndex((char)(file + 1), rank - 2));
        if(BitMap.toIndex((char)(file - 1), rank + 2) != -1
                && !checkIfSquareOccupied((char)(file - 1), rank + 2, board))
            moves.add(BitMap.toIndex((char)(file - 1), rank + 2));
        if(BitMap.toIndex((char)(file - 1), rank - 2) != -1
                && !checkIfSquareOccupied((char)(file - 1), rank - 2, board))
            moves.add(BitMap.toIndex((char)(file - 1), rank - 2));

        if(BitMap.toIndex((char)(file + 2), rank + 1) != -1
                && !checkIfSquareOccupied((char)(file + 2), rank + 1, board))
            moves.add(BitMap.toIndex((char)(file + 2), rank + 1));
        if(BitMap.toIndex((char)(file + 2), rank - 1) != -1
                && !checkIfSquareOccupied((char)(file + 2), rank - 1, board))
            moves.add(BitMap.toIndex((char)(file + 2), rank - 1));
        if(BitMap.toIndex((char)(file - 2), rank + 1) != -1
                && !checkIfSquareOccupied((char)(file - 2), rank + 1, board))
            moves.add(BitMap.toIndex((char)(file - 2), rank + 1));
        if(BitMap.toIndex((char)(file - 2), rank - 1) != -1
                && !checkIfSquareOccupied((char)(file - 2), rank - 1, board))
            moves.add(BitMap.toIndex((char)(file - 2), rank - 1));

        return moves.stream().mapToInt(e -> e).toArray();
    }

    public static int[] kingMoves(char file, int rank, Board board, Board.Color color){
        List<Integer> moves = new ArrayList<>();

        if(BitMap.toIndex((char)(file + 1), rank) != -1 && !checkIfSquareOccupied((char)(file + 1), rank, board))
            moves.add(BitMap.toIndex((char)(file + 1), rank));
        if(BitMap.toIndex((char)(file - 1), rank) != -1 && !checkIfSquareOccupied((char)(file - 1), rank, board))
            moves.add(BitMap.toIndex((char)(file - 1), rank));

        if(BitMap.toIndex(file , rank + 1) != -1 && !checkIfSquareOccupied(file, rank + 1, board))
            moves.add(BitMap.toIndex(file, rank + 1));
        if(BitMap.toIndex(file , rank - 1) != -1 && !checkIfSquareOccupied(file, rank - 1, board))
            moves.add(BitMap.toIndex(file, rank - 1));

        if(BitMap.toIndex((char)(file + 1), rank + 1) != -1
                && !checkIfSquareOccupied((char)(file + 1), rank + 1, board))
            moves.add(BitMap.toIndex((char)(file + 1), rank + 1));
        if(BitMap.toIndex((char)(file - 1), rank + 1) != -1
                && !checkIfSquareOccupied((char)(file - 1), rank + 1, board))
            moves.add(BitMap.toIndex((char)(file - 1), rank + 1));

        if(BitMap.toIndex((char)(file + 1), rank - 1) != -1
                && !checkIfSquareOccupied((char)(file + 1), rank - 1, board))
            moves.add(BitMap.toIndex((char)(file + 1), rank - 1));
        if(BitMap.toIndex((char)(file - 1), rank - 1) != -1
                && !checkIfSquareOccupied((char)(file - 1), rank - 1, board))
            moves.add(BitMap.toIndex((char)(file - 1), rank - 1));

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
