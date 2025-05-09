package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

public class Board {

    public enum Color{
        WHITE, BLACK;
    }

    public enum pieces{

        WHITE_PAWNS(0),
        BLACK_PAWNS(1),
        WHITE_ROOKS(2),
        BLACK_ROOKS(3),
        WHITE_KNIGHTS(4),
        BLACK_KNIGHTS(5),
        WHITE_BISHOPS(6),
        BLACK_BISHOPS(7),
        WHITE_QUEEN(8),
        BLACK_QUEEN(9),
        WHITE_KING(10),
        BLACK_KING(11);

        private int index;

        pieces(int index){
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    private static final int NORMAL_CHESS_PIECE_TYPES = 12;
    private BitMap[] pieces;

    public Board(){
        pieces = new BitMap[NORMAL_CHESS_PIECE_TYPES];

        for(int i = 0; i < pieces.length; i++){
            pieces[i] = new BitMap();
        }
    }

    public Board(int pieceTypes){
        pieces = new BitMap[pieceTypes];

        for(int i = 0; i < pieces.length; i++){
            pieces[i] = new BitMap();
        }
    }

    public BitMap[] getPieces(){
        return this.pieces;
    }

}
