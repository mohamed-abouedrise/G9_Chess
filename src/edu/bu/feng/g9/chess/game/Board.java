package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

public class Board {

    private static final int NORMAL_CHESS_PIECE_TYPES = 12;
    public static final int OCCUPANCY_BITMAP_NUMBER = 3;

    private static final int WHITE_PAWNS = 0;
    private static final int WHITE_ROOKS = 1;
    private static final int WHITE_KNIGHTS = 2;
    private static final int WHITE_BISHOPS = 3;
    private static final int WHITE_QUEEN = 4;
    private static final int WHITE_KING = 5;
    private static final int WHITE_PIECES = 12;

    private static final int BLACK_PAWNS = 6;
    private static final int BLACK_ROOKS = 7;
    private static final int BLACK_KNIGHTS = 8;
    private static final int BLACK_BISHOPS = 9;
    private static final int BLACK_QUEEN = 10;
    private static final int BLACK_KING = 11;
    public static final int BLACK_PIECES = 13;

    public static final int OCCUPIED_SQUARES = 14;

    private final BitMap[] pieces;

    public Board(){
        pieces = new BitMap[NORMAL_CHESS_PIECE_TYPES + OCCUPANCY_BITMAP_NUMBER];

        for(int i = 0; i < pieces.length; i++){
            pieces[i] = new BitMap();
        }
    }

    public Board(int pieceTypes){
        pieces = new BitMap[pieceTypes + OCCUPANCY_BITMAP_NUMBER];

        for(int i = 0; i < pieces.length; i++){
            pieces[i] = new BitMap();
        }
    }

    public BitMap[] getPieces(){
        return this.pieces;
    }

    public void updateUtilsBitMap(){

        this.getPieces()[WHITE_PIECES].setValue(this.getPieces()[WHITE_PAWNS].getValue()
                | this.getPieces()[WHITE_ROOKS].getValue()
                | this.getPieces()[WHITE_KNIGHTS].getValue()
                | this.getPieces()[WHITE_BISHOPS].getValue()
                | this.getPieces()[WHITE_QUEEN].getValue()
                | this.getPieces()[WHITE_KING].getValue());

        this.getPieces()[BLACK_PIECES].setValue(this.getPieces()[BLACK_PAWNS].getValue()
                | this.getPieces()[BLACK_ROOKS].getValue()
                | this.getPieces()[BLACK_KNIGHTS].getValue()
                | this.getPieces()[BLACK_BISHOPS].getValue()
                | this.getPieces()[BLACK_QUEEN].getValue()
                | this.getPieces()[BLACK_KING].getValue());

        this.getPieces()[OCCUPIED_SQUARES].setValue(this.getPieces()[WHITE_PIECES].getValue()
                | this.getPieces()[BLACK_PIECES].getValue());

    }

    public boolean isWhiteKingInCheck(){
        int kingLocation = Long.numberOfTrailingZeros(this.getPieces()[WHITE_KING].getValue());

        return (Pieces.WHITE_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[BLACK_PAWNS].getValue()) != 0
                | (Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[BLACK_KNIGHTS].getValue()) != 0
                | (Pieces.getBishopAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & (this.getPieces()[BLACK_BISHOPS].getValue() | this.getPieces()[BLACK_QUEEN].getValue())) != 0
                | (Pieces.getRookAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & (this.getPieces()[BLACK_ROOKS].getValue() | this.getPieces()[BLACK_QUEEN].getValue())) != 0;
    }

    public boolean isBlackKingInCheck(){
        int kingLocation = Long.numberOfTrailingZeros(this.getPieces()[BLACK_KING].getValue());

        return (Pieces.BLACK_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[WHITE_PAWNS].getValue()) != 0
                | (Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[WHITE_KNIGHTS].getValue()) != 0
                | (Pieces.getBishopAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & (this.getPieces()[WHITE_BISHOPS].getValue() | this.getPieces()[WHITE_QUEEN].getValue())) != 0
                | (Pieces.getRookAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & (this.getPieces()[WHITE_ROOKS].getValue() | this.getPieces()[WHITE_QUEEN].getValue())) != 0;
    }

    public static Board defaultBoard() {
        Board board = new Board();

        for (int i = 0; i < 8; i++)
            board.getPieces()[WHITE_PAWNS].setBit(8 + i);

        board.getPieces()[WHITE_ROOKS].setBit(0);
        board.getPieces()[WHITE_ROOKS].setBit(7);

        board.getPieces()[WHITE_KNIGHTS].setBit(1);
        board.getPieces()[WHITE_KNIGHTS].setBit(6);

        board.getPieces()[WHITE_BISHOPS].setBit(2);
        board.getPieces()[WHITE_BISHOPS].setBit(5);

        board.getPieces()[WHITE_QUEEN].setBit(3);
        board.getPieces()[WHITE_KING].setBit(4);

        for (int i = 0; i < 8; i++)
            board.getPieces()[BLACK_PAWNS].setBit(48 + i);

        board.getPieces()[BLACK_ROOKS].setBit(56);
        board.getPieces()[BLACK_ROOKS].setBit(63);

        board.getPieces()[BLACK_KNIGHTS].setBit(57);
        board.getPieces()[BLACK_KNIGHTS].setBit(62);

        board.getPieces()[BLACK_BISHOPS].setBit(58);
        board.getPieces()[BLACK_BISHOPS].setBit(61);

        board.getPieces()[BLACK_QUEEN].setBit(59);
        board.getPieces()[BLACK_KING].setBit(60);

        board.updateUtilsBitMap();

        return board;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        final String RESET = "\u001B[0m";
        final String WHITE_PIECE_COLOR = "\u001B[47m";
        final String BLACK_PIECE_COLOR = "\u001B[40m";

        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                int i = (rank << 3) + file;

                if (this.getPieces()[WHITE_PAWNS].getBit(i))
                    result.append(WHITE_PIECE_COLOR).append("P").append(RESET);
                else if (this.getPieces()[BLACK_PAWNS].getBit(i))
                    result.append(BLACK_PIECE_COLOR).append("P").append(RESET);
                else if (this.getPieces()[WHITE_ROOKS].getBit(i))
                    result.append(WHITE_PIECE_COLOR).append("R").append(RESET);
                else if (this.getPieces()[BLACK_ROOKS].getBit(i))
                    result.append(BLACK_PIECE_COLOR).append("R").append(RESET);
                else if (this.getPieces()[WHITE_KNIGHTS].getBit(i))
                    result.append(WHITE_PIECE_COLOR).append("N").append(RESET);
                else if (this.getPieces()[BLACK_KNIGHTS].getBit(i))
                    result.append(BLACK_PIECE_COLOR).append("N").append(RESET);
                else if (this.getPieces()[WHITE_BISHOPS].getBit(i))
                    result.append(WHITE_PIECE_COLOR).append("B").append(RESET);
                else if (this.getPieces()[BLACK_BISHOPS].getBit(i))
                    result.append(BLACK_PIECE_COLOR).append("B").append(RESET);
                else if (this.getPieces()[WHITE_QUEEN].getBit(i))
                    result.append(WHITE_PIECE_COLOR).append("Q").append(RESET);
                else if (this.getPieces()[BLACK_QUEEN].getBit(i))
                    result.append(BLACK_PIECE_COLOR).append("Q").append(RESET);
                else if (this.getPieces()[WHITE_KING].getBit(i))
                    result.append(WHITE_PIECE_COLOR).append("K").append(RESET);
                else if (this.getPieces()[BLACK_KING].getBit(i))
                    result.append(BLACK_PIECE_COLOR).append("K").append(RESET);
                else
                    result.append(".");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Board b = defaultBoard();
        System.out.println(b.isWhiteKingInCheck());
    }

}
