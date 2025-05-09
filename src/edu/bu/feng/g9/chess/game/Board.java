package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

public class Board {

    public enum Color{
        WHITE, BLACK;
    }

    public enum Pieces{

        WHITE_PAWNS(0, 0),
        BLACK_PAWNS(1, 0),
        WHITE_ROOKS(2, 1),
        BLACK_ROOKS(3, 1),
        WHITE_KNIGHTS(4, 2),
        BLACK_KNIGHTS(5, 2),
        WHITE_BISHOPS(6, 3),
        BLACK_BISHOPS(7, 3),
        WHITE_QUEEN(8, 4),
        BLACK_QUEEN(9, 4),
        WHITE_KING(10, 5),
        BLACK_KING(11, 5);

        private int index;
        private int type;

        Pieces(int index, int type){
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }

        public int getType() {
            return this.type;
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

    public static Board defaultBoard(){
        Board board = new Board();

        for(int i = 0; i < 8; i++)
            board.getPieces()[Pieces.WHITE_PAWNS.getIndex()].setBit((char)(i + 'a'), 2);

        board.getPieces()[Pieces.WHITE_ROOKS.getIndex()].setBit('a', 1);
        board.getPieces()[Pieces.WHITE_ROOKS.getIndex()].setBit('h', 1);

        board.getPieces()[Pieces.WHITE_KNIGHTS.getIndex()].setBit('b', 1);
        board.getPieces()[Pieces.WHITE_KNIGHTS.getIndex()].setBit('g', 1);

        board.getPieces()[Pieces.WHITE_BISHOPS.getIndex()].setBit('c', 1);
        board.getPieces()[Pieces.WHITE_BISHOPS.getIndex()].setBit('f', 1);

        board.getPieces()[Pieces.WHITE_QUEEN.getIndex()].setBit('d', 1);
        board.getPieces()[Pieces.WHITE_KING.getIndex()].setBit('e', 1);

        for(int i = 0; i < 8; i++)
            board.getPieces()[Pieces.BLACK_PAWNS.getIndex()].setBit((char)(i + 'a'), 7);

        board.getPieces()[Pieces.BLACK_ROOKS.getIndex()].setBit('a', 8);
        board.getPieces()[Pieces.BLACK_ROOKS.getIndex()].setBit('h', 8);

        board.getPieces()[Pieces.BLACK_KNIGHTS.getIndex()].setBit('b', 8);
        board.getPieces()[Pieces.BLACK_KNIGHTS.getIndex()].setBit('g', 8);

        board.getPieces()[Pieces.BLACK_BISHOPS.getIndex()].setBit('c', 8);
        board.getPieces()[Pieces.BLACK_BISHOPS.getIndex()].setBit('f', 8);

        board.getPieces()[Pieces.BLACK_QUEEN.getIndex()].setBit('d', 8);
        board.getPieces()[Pieces.BLACK_KING.getIndex()].setBit('e', 8);

        return board;

    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 64; i++){
            if(this.getPieces()[Pieces.WHITE_PAWNS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("P");
            else if(this.getPieces()[Pieces.BLACK_PAWNS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("P");
            else if(this.getPieces()[Pieces.WHITE_ROOKS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("R");
            else if(this.getPieces()[Pieces.BLACK_ROOKS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("R");
            else if(this.getPieces()[Pieces.WHITE_KNIGHTS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("N");
            else if(this.getPieces()[Pieces.BLACK_KNIGHTS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("N");
            else if(this.getPieces()[Pieces.WHITE_BISHOPS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("B");
            else if(this.getPieces()[Pieces.BLACK_BISHOPS.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("B");
            else if(this.getPieces()[Pieces.WHITE_QUEEN.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("Q");
            else if(this.getPieces()[Pieces.BLACK_QUEEN.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("Q");
            else if(this.getPieces()[Pieces.WHITE_KING.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("K");
            else if(this.getPieces()[Pieces.BLACK_KING.getIndex()].getBit(BitMap.getFile(i), BitMap.getRank(i)))
                result.append("K");
            else
                result.append("0");
            if((i + 1) % 8 == 0)
                result.append("\n");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Board b = Board.defaultBoard();
        int[] m = edu.bu.feng.g9.chess.game.Pieces.knightMoves('b', 1, b, Color.WHITE);
        for(int i : m){
            System.out.println(String.valueOf(BitMap.getFile(i)) + BitMap.getRank(i));
        }
    }

}
