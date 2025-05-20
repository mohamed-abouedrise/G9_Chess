package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    public static final Image WHITE_KING_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/White_King.png")).getImage();
    private static final Image WHITE_QUEEN_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/White_Queen.png")).getImage();
    private static final Image WHITE_BISHOP_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/White_Bishop.png")).getImage();
    private static final Image WHITE_KNIGHT_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/White_Knight.png")).getImage();
    private static final Image WHITE_PAWN_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/White_Pawn.png")).getImage();
    private static final Image WHITE_ROOK_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/White_Rook.png")).getImage();
    private static final Image BLACK_KING_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Black_King.png")).getImage();
    private static final Image BLACK_QUEEN_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Black_Queen.png")).getImage();
    private static final Image BLACK_BISHOP_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Black_Bishop.png")).getImage();
    private static final Image BLACK_KNIGHT_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Black_Knight.png")).getImage();
    private static final Image BLACK_PAWN_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Black_Pawn.png")).getImage();
    private static final Image BLACK_ROOK_IMAGE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Black_Rook.png")).getImage();

    private static final Image MOVE_PIECE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Move_Piece.png")).getImage();
    private static final Image CAPTURE_PIECE = new ImageIcon(Board.class.getResource("/edu/bu/feng/g9/chess/resources/Capture_Piece.png")).getImage();

    private final BitMap[] pieces;
    private boolean isWhiteTurn = true;
    private byte castleRights = 0x0F;
    private int enPassent;

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
        return ((Pieces.WHITE_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[BLACK_PAWNS].getValue())
                | (Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[BLACK_KNIGHTS].getValue())
                | (Pieces.getBishopAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & (this.getPieces()[BLACK_BISHOPS].getValue() | this.getPieces()[BLACK_QUEEN].getValue()))
                | (Pieces.getRookAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & (this.getPieces()[BLACK_ROOKS].getValue() | this.getPieces()[BLACK_QUEEN].getValue()))) != 0;
    }

    public boolean isBlackKingInCheck(){
        int kingLocation = Long.numberOfTrailingZeros(this.getPieces()[BLACK_KING].getValue());
        try {
            return ((Pieces.BLACK_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[WHITE_PAWNS].getValue())
                    | (Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[WHITE_KNIGHTS].getValue())
                    | (Pieces.getBishopAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                    & (this.getPieces()[WHITE_BISHOPS].getValue() | this.getPieces()[WHITE_QUEEN].getValue()))
                    | (Pieces.getRookAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue()
                    & (this.getPieces()[WHITE_ROOKS].getValue() | this.getPieces()[WHITE_QUEEN].getValue()))) != 0;
        }catch (Exception e){
            System.out.println(this);
            return false;
        }
    }

    public Image getPiece(int x, int y){
        y++;
        if(this.getPieces()[WHITE_PAWNS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.WHITE_PAWN_IMAGE;

        if(this.getPieces()[BLACK_PAWNS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.BLACK_PAWN_IMAGE;

        if(this.getPieces()[WHITE_ROOKS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.WHITE_ROOK_IMAGE;

        if(this.getPieces()[BLACK_ROOKS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.BLACK_ROOK_IMAGE;

        if(this.getPieces()[WHITE_KNIGHTS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.WHITE_KNIGHT_IMAGE;

        if(this.getPieces()[BLACK_KNIGHTS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.BLACK_KNIGHT_IMAGE;

        if(this.getPieces()[WHITE_BISHOPS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.WHITE_BISHOP_IMAGE;

        if(this.getPieces()[BLACK_BISHOPS].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.BLACK_BISHOP_IMAGE;

        if(this.getPieces()[WHITE_QUEEN].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.WHITE_QUEEN_IMAGE;

        if(this.getPieces()[BLACK_QUEEN].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.BLACK_QUEEN_IMAGE;

        if(this.getPieces()[WHITE_KING].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.WHITE_KING_IMAGE;

        if(this.getPieces()[BLACK_KING].getBit(BitMap.toIndex((char)('a' + x), y)))
            return Board.BLACK_KING_IMAGE;

        return null;
    }

    public int[] getMoves(int x, int y){

        y++;
        char file = (char) (x + 'a');
        if(!this.getPieces()[OCCUPIED_SQUARES].getBit(BitMap.toIndex(file, y)))
            return null;

        if(this.getPieces()[WHITE_PAWNS].getBit(BitMap.toIndex(file, y)) && isWhiteTurn)
            return getWhitePawnMoves(y, file);

        if(this.getPieces()[BLACK_PAWNS].getBit(BitMap.toIndex(file, y)) && !isWhiteTurn)
            return getBlackPawnMoves(y, file);

        if(this.getPieces()[WHITE_KNIGHTS].getBit(BitMap.toIndex(file, y)) && isWhiteTurn)
            return getWhiteKnightMoves(y, file);


        if(this.getPieces()[BLACK_KNIGHTS].getBit(BitMap.toIndex(file, y)) && !isWhiteTurn)
            return getBlackKnightMoves(y, file);

        if(this.getPieces()[WHITE_ROOKS].getBit(BitMap.toIndex(file, y)) && isWhiteTurn)
            return getWhiteRookMoves(y, file);


        if(this.getPieces()[BLACK_ROOKS].getBit(BitMap.toIndex(file, y)) && !isWhiteTurn)
            return getBlackRookMoves(y, file);

        if(this.getPieces()[WHITE_BISHOPS].getBit(BitMap.toIndex(file, y)) && isWhiteTurn)
            return getWhiteBishopMoves(y, file);

        if(this.getPieces()[BLACK_BISHOPS].getBit(BitMap.toIndex(file, y)) && !isWhiteTurn)
            return getBlackBishopMoves(y, file);

        if(this.getPieces()[WHITE_QUEEN].getBit(BitMap.toIndex(file, y)) && isWhiteTurn)
            return getWhiteQueenMoves(y, file);

        if(this.getPieces()[BLACK_QUEEN].getBit(BitMap.toIndex(file, y)) && !isWhiteTurn)
            return getBlackQueenMoves(y, file);

        if(this.getPieces()[WHITE_KING].getBit(BitMap.toIndex(file, y)) && isWhiteTurn)
            return getWhiteKingMoves(y, file);

        if(this.getPieces()[BLACK_KING].getBit(BitMap.toIndex(file, y)) && !isWhiteTurn)
            return getBlackKingMoves(y, file);

        return null;

    }

    private int[] getBlackKingMoves(int y, char file) {
        long attackMap = this.attackBitMap().getValue();
        BitMap castleSquares = new BitMap();
        castleSquares.setBit(BitMap.toIndex('f', 8));
        castleSquares.setBit(BitMap.toIndex('g', 8));
        castleSquares.setBit(BitMap.toIndex('b', 8));
        castleSquares.setBit(BitMap.toIndex('c', 8));
        castleSquares.setBit(BitMap.toIndex('d', 8));
        castleSquares = new BitMap(castleSquares.getValue() & ~attackMap
                & ~this.getPieces()[BLACK_PIECES].getValue());

        long castle = 0L;
        if((castleRights & (1 << 2)) != 0 && castleSquares.getBit(BitMap.toIndex('f', 8))
                && castleSquares.getBit(BitMap.toIndex('g', 8))) {
            castle |= 1L << 6;
        }
        if((castleRights & (1 << 3)) != 0&& castleSquares.getBit(BitMap.toIndex('b', 8))
                && castleSquares.getBit(BitMap.toIndex('c', 8))
                && castleSquares.getBit(BitMap.toIndex('d', 8))) {
            castle |= 1L << 2;
        }

        if(isBlackKingInCheck())
            castle = 0L;
        return new BitMap(((Pieces.KING_MOVES[BitMap.toIndex(file, y)].getValue()
                & ~this.getPieces()[BLACK_PIECES].getValue())
                & ~attackMap)
                | castle).toIntArray();
    }

    private int[] getWhiteKingMoves(int y, char file) {
        long attackMap = this.attackBitMap().getValue();
        BitMap castleSquares = new BitMap();
        castleSquares.setBit(BitMap.toIndex('f', 1));
        castleSquares.setBit(BitMap.toIndex('g', 1));
        castleSquares.setBit(BitMap.toIndex('b', 1));
        castleSquares.setBit(BitMap.toIndex('c', 1));
        castleSquares.setBit(BitMap.toIndex('d', 1));
        castleSquares = new BitMap(castleSquares.getValue() & ~attackMap
                & ~this.getPieces()[WHITE_PIECES].getValue());

        long castle = 0L;
        if((castleRights & 1) != 0 && castleSquares.getBit(BitMap.toIndex('f', 1))
                && castleSquares.getBit(BitMap.toIndex('g', 1))) {
            castle |= 1L << 6;
        }
        if((castleRights & (1 << 1)) != 0&& castleSquares.getBit(BitMap.toIndex('b', 1))
                && castleSquares.getBit(BitMap.toIndex('c', 1))
                && castleSquares.getBit(BitMap.toIndex('d', 1))) {
            castle |= 1L << 2;
        }

        if(isWhiteKingInCheck())
            castle = 0L;

        BitMap castleBitMap = new BitMap(castle);
        return new BitMap(((Pieces.KING_MOVES[BitMap.toIndex(file, y)].getValue()
                & ~this.getPieces()[WHITE_PIECES].getValue())
                & ~attackMap)
                | castleBitMap.getValue()).toIntArray();
    }

    private int[] getBlackQueenMoves(int y, char file) {
        long moves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & ~this.getPieces()[BLACK_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[BLACK_KING].getValue();
        long whitePiecesDiagonals = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_BISHOPS].getValue());
        long whitePiecesLines = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (whitePiecesDiagonals | whitePiecesLines)) != 0L){
            if(((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()));
            return validateMoves(moves);
        }
        return validateMoves(moves);
    }

    private int[] getWhiteQueenMoves(int y, char file) {
        long moves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & ~this.getPieces()[WHITE_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[WHITE_KING].getValue();
        long blackPiecesDiagonals = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_BISHOPS].getValue());
        long blackPiecesLines = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (blackPiecesDiagonals | blackPiecesLines)) != 0L){
            if(((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()));
            return validateMoves(moves);
        }
        return validateMoves(moves);
    }

    private int[] getBlackBishopMoves(int y, char file) {
        long moves = Pieces.getBishopAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & ~this.getPieces()[BLACK_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[BLACK_KING].getValue();
        long whitePiecesDiagonals = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_BISHOPS].getValue());
        long whitePiecesLines = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (whitePiecesDiagonals | whitePiecesLines)) != 0L){
            boolean isPinnedLine = ((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0);
            if(isPinnedLine)
                return validateMoves(0L);
            if(((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()));
            return validateMoves(moves);
        }
        return validateMoves(moves);
    }

    private int[] getWhiteBishopMoves(int y, char file) {
        long moves = Pieces.getBishopAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & ~this.getPieces()[WHITE_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[WHITE_KING].getValue();
        long blackPiecesDiagonals = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_BISHOPS].getValue());
        long blackPiecesLines = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (blackPiecesDiagonals | blackPiecesLines)) != 0L){
            boolean isPinnedLine = ((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0);
            if(isPinnedLine)
                return validateMoves(0L);
            if(((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()));
            return validateMoves(moves);
        }
        return validateMoves(moves);
    }

    private int[] getBlackRookMoves(int y, char file) {
        long moves = Pieces.getRookAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & ~this.getPieces()[BLACK_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[BLACK_KING].getValue();
        long whitePiecesDiagonals = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_BISHOPS].getValue());
        long whitePiecesLines = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (whitePiecesDiagonals | whitePiecesLines)) != 0L){
            boolean isPinnedDiagonal = ((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0);
            if(isPinnedDiagonal)
                return validateMoves(0L);
            if(((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()));
            return validateMoves(moves);
        }
        return validateMoves(moves);
    }

    private int[] getWhiteRookMoves(int y, char file) {
        long moves = Pieces.getRookAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue()
                & ~this.getPieces()[WHITE_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[WHITE_KING].getValue();
        long blackPiecesDiagonals = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_BISHOPS].getValue());
        long blackPiecesLines = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (blackPiecesDiagonals | blackPiecesLines)) != 0L){
            boolean isPinnedDiagonal = ((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0);
            if(isPinnedDiagonal)
                return validateMoves(0L);
            if(((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()));
            if(((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L))
                return validateMoves(moves & (Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()
                        | Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()));
            return validateMoves(moves);
        }
        return validateMoves(moves);
    }

    private int[] getBlackKnightMoves(int y, char file) {
        long moves = Pieces.KNIGHT_MOVES[BitMap.toIndex(file, y)].getValue()
                & ~this.getPieces()[BLACK_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[BLACK_KING].getValue();
        long whitePiecesDiagonals = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_BISHOPS].getValue());
        long whitePiecesLines = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (whitePiecesDiagonals | whitePiecesLines)) != 0L){
            boolean isPinnedLine = ((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0);
            boolean isPinnedDiagonal = ((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0);
            if(isPinnedDiagonal | isPinnedLine)
                return validateMoves(0L);
        }
        return validateMoves(moves);
    }

    private int[] getWhiteKnightMoves(int y, char file) {
        long moves = Pieces.KNIGHT_MOVES[BitMap.toIndex(file, y)].getValue()
                & ~this.getPieces()[WHITE_PIECES].getValue();
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[WHITE_KING].getValue();
        long blackPiecesDiagonals = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_BISHOPS].getValue());
        long blackPiecesLines = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (blackPiecesDiagonals | blackPiecesLines)) != 0L){
            boolean isPinnedLine = ((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0);
            boolean isPinnedDiagonal = ((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L)
                    | ((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0)
                    | ((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0);
            if(isPinnedDiagonal | isPinnedLine)
                return validateMoves(0L);
        }
        return validateMoves(moves);
    }

    private int[] getBlackPawnMoves(int y, char file) {
        long singlePush = (Pieces.BLACK_PAWN_MOVES[BitMap.toIndex(file, y)].getValue()
                & ~this.getPieces()[OCCUPIED_SQUARES].getValue());
        long doublePush = 0L;
        if(y == 7)
            doublePush = (singlePush >> 8) & ~this.getPieces()[OCCUPIED_SQUARES].getValue();

        long enPassentCapture = 0L;
        if(y == BitMap.getRank(this.enPassent)
                && (file + 1 == BitMap.getFile(this.enPassent) | file - 1 == BitMap.getFile(this.enPassent))){
            enPassentCapture = Pieces.BLACK_PAWN_CAPTURE[BitMap.toIndex(file, y)].getValue() & (1L << (enPassent - 8));
        }
        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[BLACK_KING].getValue();
        if(enPassentCapture != 0){
            long slidingPieces = this.getPieces()[WHITE_ROOKS].getValue() | this.getPieces()[WHITE_QUEEN].getValue();
            long queenEnPassentMoves = Pieces.getQueenAttacks(enPassent
                    , this.getPieces()[OCCUPIED_SQUARES]).getValue();

            if((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenEnPassentMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }else if((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenEnPassentMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }else if((queenEnPassentMoves & this.getPieces()[WHITE_KING].getValue()
                    & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }else if((queenEnPassentMoves & this.getPieces()[WHITE_KING].getValue()
                    & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }}

        long moves = ((singlePush | doublePush)
                | enPassentCapture
                | (Pieces.BLACK_PAWN_CAPTURE[BitMap.toIndex(file, y)].getValue()
                & this.getPieces()[WHITE_PIECES].getValue()))
                & ~this.getPieces()[BLACK_PIECES].getValue();

        long whitePiecesDiagonals = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_BISHOPS].getValue());
        long whitePiecesLines = queenMoves
                & (this.getPieces()[WHITE_QUEEN].getValue() | this.getPieces()[WHITE_ROOKS].getValue());

        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (whitePiecesDiagonals | whitePiecesLines)) != 0L){

            if((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(0L)
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(0L)
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (whitePiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue())
                        ;
            }

        }

        return validateMoves(moves);
    }

    private int[] getWhitePawnMoves(int y, char file) {
        long singlePush = (Pieces.WHITE_PAWN_MOVES[BitMap.toIndex(file, y)].getValue()
                & ~this.getPieces()[OCCUPIED_SQUARES].getValue());
        long doublePush = 0L;
        if(y == 2)
            doublePush = (singlePush << 8) & ~this.getPieces()[OCCUPIED_SQUARES].getValue();

        long enPassentCapture = 0L;
        if(y == BitMap.getRank(this.enPassent)
                && (file + 1 == BitMap.getFile(this.enPassent) | file - 1 == BitMap.getFile(this.enPassent))){
            enPassentCapture = Pieces.WHITE_PAWN_CAPTURE[BitMap.toIndex(file, y)].getValue() & (1L << (enPassent + 8));
        }

        long queenMoves = Pieces.getQueenAttacks(BitMap.toIndex(file, y)
                , this.getPieces()[OCCUPIED_SQUARES]).getValue();
        long king = queenMoves & this.getPieces()[WHITE_KING].getValue();
        if(enPassentCapture != 0){
            long slidingPieces = this.getPieces()[BLACK_ROOKS].getValue() | this.getPieces()[BLACK_QUEEN].getValue();
            long queenEnPassentMoves = Pieces.getQueenAttacks(enPassent
                    , this.getPieces()[OCCUPIED_SQUARES]).getValue();

            if((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenEnPassentMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }else if((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenEnPassentMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }else if((queenEnPassentMoves & this.getPieces()[WHITE_KING].getValue()
                    & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }else if((queenEnPassentMoves & this.getPieces()[WHITE_KING].getValue()
                    & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0
                    && (queenMoves & slidingPieces
                    & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0){
                enPassentCapture = 0L;
            }}
        long moves = ((singlePush | doublePush)
                | enPassentCapture
                | (Pieces.WHITE_PAWN_CAPTURE[BitMap.toIndex(file, y)].getValue() &
                this.getPieces()[BLACK_PIECES].getValue()))
                & ~this.getPieces()[WHITE_PIECES].getValue();

        long blackPiecesDiagonals = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_BISHOPS].getValue());
        long blackPiecesLines = queenMoves
                & (this.getPieces()[BLACK_QUEEN].getValue() | this.getPieces()[BLACK_ROOKS].getValue());
        if(king == 0L)
            return validateMoves(moves);
        else if((queenMoves & (blackPiecesDiagonals | blackPiecesLines)) != 0L){

            if((king & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[4][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[0][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(0L)
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[6][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesLines & Pieces.KING_PIN_MASKS[2][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(0L)
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[5][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[1][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue())
                        ;
            }

            if((king & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue()) != 0L
                    && (blackPiecesDiagonals & Pieces.KING_PIN_MASKS[3][BitMap.toIndex(file, y)].getValue()) != 0L){
                return validateMoves(moves & Pieces.KING_PIN_MASKS[7][BitMap.toIndex(file, y)].getValue())
                        ;
            }

        }

        return validateMoves(moves);
    }

    public Image getMoveImage(int end, int start) {
        if(this.getPieces()[OCCUPIED_SQUARES].getBit(end))
            return CAPTURE_PIECE;
        else
            return MOVE_PIECE;
    }

    public boolean movePiece(int startingSquare, int endSquare) {
        int pieceType = -1;

        if(this.getPieces()[WHITE_KING].getBit(endSquare) || this.getPieces()[BLACK_KING].getBit(endSquare))
            throw new IllegalArgumentException();

        if(BitMap.getRank(endSquare) == 8 && this.getPieces()[WHITE_PAWNS].getBit(startingSquare)){
            return true;
        }

        if(BitMap.getRank(endSquare) == 1 && this.getPieces()[BLACK_PIECES].getBit(startingSquare)){
            return true;
        }

        int lastEnPassent = enPassent;

        if(this.getPieces()[WHITE_PAWNS].getBit(startingSquare) && BitMap.getRank(startingSquare) == 2
                && BitMap.getRank(endSquare) == 4)
            this.enPassent = endSquare;
        else if(this.getPieces()[BLACK_PIECES].getBit(startingSquare) && BitMap.getRank(startingSquare) == 7
                && BitMap.getRank(endSquare) == 5)
            this.enPassent = endSquare;
        else
            this.enPassent = -1;

        if(this.getPieces()[WHITE_KING].getBit(BitMap.toIndex(BitMap.getFile(startingSquare), BitMap.getRank(startingSquare)))){
            if(endSquare == BitMap.toIndex('g', 1) && (castleRights & 1) != 0){
                pieceType = WHITE_KING;
                this.getPieces()[WHITE_KING].clearBit(startingSquare);
                this.getPieces()[WHITE_ROOKS].clearBit(BitMap.toIndex('h', 1));
                this.getPieces()[WHITE_ROOKS].setBit(BitMap.toIndex('f', 1));
            }else if(endSquare == BitMap.toIndex('c', 1) && (castleRights & 1 << 1) != 0){
                pieceType = WHITE_KING;
                this.getPieces()[WHITE_KING].clearBit(startingSquare);
                this.getPieces()[WHITE_ROOKS].clearBit(BitMap.toIndex('a', 1));
                this.getPieces()[WHITE_ROOKS].setBit(BitMap.toIndex('d', 1));
            }
        }
        if(this.getPieces()[BLACK_KING].getBit(BitMap.toIndex(BitMap.getFile(startingSquare), BitMap.getRank(startingSquare)))){
            if(endSquare == BitMap.toIndex('g', 8) && (castleRights & 1 << 2) != 0){
                pieceType = BLACK_KING;
                this.getPieces()[BLACK_KING].clearBit(startingSquare);
                this.getPieces()[BLACK_ROOKS].clearBit(BitMap.toIndex('h', 8));
                this.getPieces()[BLACK_ROOKS].setBit(BitMap.toIndex('f', 8));
            }else if(endSquare == BitMap.toIndex('c', 8) && (castleRights & 1 << 3) != 0){
                pieceType = BLACK_KING;
                this.getPieces()[BLACK_KING].clearBit(startingSquare);
                this.getPieces()[BLACK_ROOKS].clearBit(BitMap.toIndex('a', 8));
                this.getPieces()[BLACK_ROOKS].setBit(BitMap.toIndex('d', 8));
            }
        }

        for (int i = 0; i < NORMAL_CHESS_PIECE_TYPES; i++) {
            if (this.getPieces()[i].getBit(startingSquare)) {
                this.getPieces()[i].clearBit(startingSquare);
                this.updateUtilsBitMap();
                pieceType = i;
            }
        }

        if (this.getPieces()[OCCUPIED_SQUARES].getBit(endSquare)){
            for (int i = 0; i < NORMAL_CHESS_PIECE_TYPES; i++) {
                if (this.getPieces()[i].getBit(endSquare)) {
                    this.getPieces()[i].clearBit(endSquare);
                }
            }
        }

        if (pieceType == WHITE_PAWNS && endSquare == lastEnPassent + 8) {
            this.getPieces()[BLACK_PAWNS].clearBit(lastEnPassent);
        } else if (pieceType == BLACK_PAWNS && endSquare == lastEnPassent - 8) {
            this.getPieces()[WHITE_PAWNS].clearBit(lastEnPassent);
        }

        this.getPieces()[pieceType].setBit(endSquare);
        this.isWhiteTurn = !isWhiteTurn;
        this.updateUtilsBitMap();
        testCastleRights();
        return false;

    }

    private void testCastleRights() {
        if(!this.getPieces()[WHITE_ROOKS].getBit(BitMap.toIndex('h', 1)))
            this.castleRights &= ~1;

        if(!this.getPieces()[WHITE_ROOKS].getBit(BitMap.toIndex('a', 1)))
            this.castleRights &= ~(1 << 1);

        if(!this.getPieces()[WHITE_KING].getBit(BitMap.toIndex('e', 1)))
            this.castleRights &= ~(1 | 1 << 1);

        if(!this.getPieces()[BLACK_ROOKS].getBit(BitMap.toIndex('h', 8)))
            this.castleRights &= ~(1 << 2);

        if(!this.getPieces()[BLACK_ROOKS].getBit(BitMap.toIndex('a', 8)))
            this.castleRights &= ~(1 << 3);

        if(!this.getPieces()[BLACK_KING].getBit(BitMap.toIndex('e', 8)))
            this.castleRights &= ~(1 << 2 | 1 << 3);
    }

    public boolean isWhiteKing(int x, int y){
        return this.getPieces()[WHITE_KING].getBit(BitMap.toIndex((char) ('a' + x), y + 1));
    }

    public boolean isBlackKing(int x, int y){
        return this.getPieces()[BLACK_KING].getBit(BitMap.toIndex((char) ('a' + x), y + 1));
    }

    public int getKingIndex(){

        int colorSpecificOffset = 0;
        if(!isWhiteTurn)
            colorSpecificOffset = 6;

        return Long.numberOfTrailingZeros(this.getPieces()[WHITE_KING + colorSpecificOffset].getValue());
    }

    public BitMap attackBitMap(){

        long map = 0L;
        int colorSpecificOffset = 0;
        BitMap occupancy;
        if(isWhiteTurn) {
            occupancy = new BitMap(this.getPieces()[OCCUPIED_SQUARES].getValue()
                    & ~this.getPieces()[WHITE_KING].getValue());
            colorSpecificOffset = 6;
        }else{
            occupancy = new BitMap(this.getPieces()[OCCUPIED_SQUARES].getValue()
                    & ~this.getPieces()[BLACK_KING].getValue());
        }

        int pawns = Long.bitCount(this.getPieces()[WHITE_PAWNS + colorSpecificOffset].getValue());
        long pawnsMap = this.getPieces()[WHITE_PAWNS + colorSpecificOffset].getValue();
        while (pawns != 0) {
            int index = Long.numberOfTrailingZeros(pawnsMap);
            if(isWhiteTurn)
                map |= Pieces.BLACK_PAWN_CAPTURE[index].getValue();
            else
                map |= Pieces.WHITE_PAWN_CAPTURE[index].getValue();
            pawnsMap ^= 1L << index;
            pawns--;
        }

        int rooks = Long.bitCount(this.getPieces()[WHITE_ROOKS + colorSpecificOffset].getValue());
        long rooksMap = this.getPieces()[WHITE_ROOKS + colorSpecificOffset].getValue();
        while (rooks != 0) {
            int index = Long.numberOfTrailingZeros(rooksMap);
            map |= Pieces.getRookAttacks(index, occupancy).getValue();
            rooksMap ^= 1L << index;
            rooks--;
        }

        int bishops = Long.bitCount(this.getPieces()[WHITE_BISHOPS + colorSpecificOffset].getValue());
        long bishopsMap = this.getPieces()[WHITE_BISHOPS + colorSpecificOffset].getValue();
        while (bishops != 0) {
            int index = Long.numberOfTrailingZeros(bishopsMap);
            map |= Pieces.getBishopAttacks(index, occupancy).getValue();
            bishopsMap ^= 1L << index;
            bishops--;
        }

        int queen = Long.bitCount(this.getPieces()[WHITE_QUEEN + colorSpecificOffset].getValue());
        long queenMap = this.getPieces()[WHITE_QUEEN + colorSpecificOffset].getValue();
        while (queen != 0) {
            int index = Long.numberOfTrailingZeros(queenMap);
            map |= Pieces.getQueenAttacks(index, occupancy).getValue();
            queenMap ^= 1L << index;
            queen--;
        }

        int knight = Long.bitCount(this.getPieces()[WHITE_KNIGHTS + colorSpecificOffset].getValue());
        long knightMap = this.getPieces()[WHITE_KNIGHTS + colorSpecificOffset].getValue();
        while (knight != 0) {
            int index = Long.numberOfTrailingZeros(knightMap);
            map |= Pieces.KNIGHT_MOVES[index].getValue();
            knightMap ^= 1L << index;
            knight--;
        }

        int king = Long.bitCount(this.getPieces()[WHITE_KING + colorSpecificOffset].getValue());
        long kingMap = this.getPieces()[WHITE_KING + colorSpecificOffset].getValue();
        while (king != 0) {
            int index = Long.numberOfTrailingZeros(kingMap);
            map |= Pieces.KING_MOVES[index].getValue();
            kingMap ^= 1L << index;
            king--;
        }

        return new BitMap(map);
    }

    public int evaluate(){
        if(this.isCheckMate() && this.isWhiteTurn)
            return -1000000;
        else if(this.isCheckMate() && !this.isWhiteTurn)
            return 1000000;
        else if(this.isStaleMate())
            return 0;
        int value = Long.bitCount(this.getPieces()[WHITE_PAWNS].getValue());
        value -= Long.bitCount(this.getPieces()[BLACK_PAWNS].getValue());

        value += 5 * Long.bitCount(this.getPieces()[WHITE_ROOKS].getValue());
        value -= 5 * Long.bitCount(this.getPieces()[BLACK_ROOKS].getValue());

        value += 9 * Long.bitCount(this.getPieces()[WHITE_QUEEN].getValue());
        value -= 9 * Long.bitCount(this.getPieces()[BLACK_QUEEN].getValue());

        value += 3 * Long.bitCount(this.getPieces()[WHITE_BISHOPS].getValue());
        value -= 3 * Long.bitCount(this.getPieces()[BLACK_BISHOPS].getValue());

        value += 3 * Long.bitCount(this.getPieces()[WHITE_KNIGHTS].getValue());
        value -= 3 * Long.bitCount(this.getPieces()[BLACK_KNIGHTS].getValue());

        value *= 100;

        boolean turn = isWhiteTurn;
        isWhiteTurn = true;
        value += Long.bitCount(this.attackBitMap().getValue()) * 10;
        isWhiteTurn = false;
        value -= Long.bitCount(this.attackBitMap().getValue()) * 10;
        isWhiteTurn = turn;

        return value;
    }

    public Board moveAndClone(int move){

        int startingSquare = move & 0x3F;
        int endSquare = (move >> 6) & 0x3F;
        Board newBoard = new Board();
        for(int i = 0; i < this.getPieces().length; i++){
            newBoard.getPieces()[i] = new BitMap(this.getPieces()[i].getValue());
        }
        newBoard.isWhiteTurn = isWhiteTurn;
        newBoard.enPassent = enPassent;
        newBoard.castleRights = castleRights;
        if(newBoard.movePiece(startingSquare, endSquare))
            newBoard.movePieceAndPromote(startingSquare, endSquare, (move >> 12) & 0b111);
        return newBoard;
    }

    public boolean isWhiteTurn(){
        return isWhiteTurn;
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

    public void movePieceAndPromote(int startingSquare, int endSquare, int type) {
        for(int i = 0; i < NORMAL_CHESS_PIECE_TYPES; i++){
            this.getPieces()[i].clearBit(endSquare);
        }
        if(isWhiteTurn){
            this.getPieces()[WHITE_PAWNS].clearBit(startingSquare);
            switch (type){
                case 1:
                    this.getPieces()[WHITE_QUEEN].setBit(endSquare);
                    break;
                case 2:
                    this.getPieces()[WHITE_ROOKS].setBit(endSquare);
                    break;
                case 3:
                    this.getPieces()[WHITE_KNIGHTS].setBit(endSquare);
                    break;
                case 4:
                    this.getPieces()[WHITE_BISHOPS].setBit(endSquare);
                    break;
            }
        }else{
            this.getPieces()[BLACK_PAWNS].clearBit(startingSquare);
            switch (type){
                case 1:
                    this.getPieces()[BLACK_QUEEN].setBit(endSquare);
                    break;
                case 2:
                    this.getPieces()[BLACK_ROOKS].setBit(endSquare);
                    break;
                case 3:
                    this.getPieces()[BLACK_KNIGHTS].setBit(endSquare);
                    break;
                case 4:
                    this.getPieces()[BLACK_BISHOPS].setBit(endSquare);
                    break;
            }
        }
        this.isWhiteTurn = !isWhiteTurn;
        this.updateUtilsBitMap();
        testCastleRights();
    }

    public int[] validateMoves(long moves){
        if(isWhiteTurn && isWhiteKingInCheck()){

            int kingLocation = getKingIndex();
            int numOfAttackers = 0;

            long queenMoves = Pieces.getQueenAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue();
            long northLane = queenMoves & Pieces.KING_PIN_MASKS[0][kingLocation].getValue();
            long northEastLane = queenMoves & Pieces.KING_PIN_MASKS[1][kingLocation].getValue();
            long eastLane = queenMoves & Pieces.KING_PIN_MASKS[2][kingLocation].getValue();
            long southEastLane = queenMoves & Pieces.KING_PIN_MASKS[3][kingLocation].getValue();
            long southLane = queenMoves & Pieces.KING_PIN_MASKS[4][kingLocation].getValue();
            long southWestLane = queenMoves & Pieces.KING_PIN_MASKS[5][kingLocation].getValue();
            long westLane = queenMoves & Pieces.KING_PIN_MASKS[6][kingLocation].getValue();
            long northWestLane = queenMoves & Pieces.KING_PIN_MASKS[7][kingLocation].getValue();
            long diagonalPieces = this.getPieces()[BLACK_BISHOPS].getValue() | this.getPieces()[BLACK_QUEEN].getValue();
            long straightPieces = this.getPieces()[BLACK_ROOKS].getValue() | this.getPieces()[BLACK_QUEEN].getValue();

            if((Pieces.WHITE_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[BLACK_PAWNS].getValue()) != 0) {
                numOfAttackers++;
                moves &= Pieces.WHITE_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[BLACK_PAWNS].getValue();
            }

            if((Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[BLACK_KNIGHTS].getValue()) != 0){
                numOfAttackers++;
                moves &= Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[BLACK_KNIGHTS].getValue();
            }

            if((northLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= northLane;
            }

            if((eastLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= eastLane;
            }

            if((southLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= southLane;
            }

            if((westLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= westLane;
            }

            if((northEastLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= northEastLane;
            }

            if((southEastLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= southEastLane;
            }

            if((southWestLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= southWestLane;
            }

            if((northWestLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= northWestLane;
            }

            if(numOfAttackers > 1)
                return new BitMap().toIntArray();
            return new BitMap(moves).toIntArray();
        }else if(!isWhiteTurn && isBlackKingInCheck()){

            int kingLocation = getKingIndex();
            int numOfAttackers = 0;

            long queenMoves = Pieces.getQueenAttacks(kingLocation, this.getPieces()[OCCUPIED_SQUARES]).getValue();
            long northLane = queenMoves & Pieces.KING_PIN_MASKS[0][kingLocation].getValue();
            long northEastLane = queenMoves & Pieces.KING_PIN_MASKS[1][kingLocation].getValue();
            long eastLane = queenMoves & Pieces.KING_PIN_MASKS[2][kingLocation].getValue();
            long southEastLane = queenMoves & Pieces.KING_PIN_MASKS[3][kingLocation].getValue();
            long southLane = queenMoves & Pieces.KING_PIN_MASKS[4][kingLocation].getValue();
            long southWestLane = queenMoves & Pieces.KING_PIN_MASKS[5][kingLocation].getValue();
            long westLane = queenMoves & Pieces.KING_PIN_MASKS[6][kingLocation].getValue();
            long northWestLane = queenMoves & Pieces.KING_PIN_MASKS[7][kingLocation].getValue();
            long diagonalPieces = this.getPieces()[WHITE_BISHOPS].getValue() | this.getPieces()[WHITE_QUEEN].getValue();
            long straightPieces = this.getPieces()[WHITE_ROOKS].getValue() | this.getPieces()[WHITE_QUEEN].getValue();
            if((Pieces.BLACK_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[WHITE_PAWNS].getValue()) != 0) {
                numOfAttackers++;
                moves &= Pieces.BLACK_PAWN_CAPTURE[kingLocation].getValue() & this.getPieces()[WHITE_PAWNS].getValue();
            }

            if((Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[WHITE_KNIGHTS].getValue()) != 0){
                numOfAttackers++;
                moves &= Pieces.KNIGHT_MOVES[kingLocation].getValue() & this.getPieces()[WHITE_KNIGHTS].getValue();
            }

            if((northLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= northLane;
            }

            if((eastLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= eastLane;
            }

            if((southLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= southLane;
            }

            if((westLane & straightPieces) != 0){
                numOfAttackers++;
                moves &= westLane;
            }

            if((northEastLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= northEastLane;
            }

            if((southEastLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= southEastLane;
            }

            if((southWestLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= southWestLane;
            }

            if((northWestLane & diagonalPieces) != 0){
                numOfAttackers++;
                moves &= northWestLane;
            }

            if(numOfAttackers > 1)
                return new BitMap().toIntArray();
            return new BitMap(moves).toIntArray();

        }
        return new BitMap(moves).toIntArray();
    }

    public int[] getLegalMoves(){

        List<Integer> moves = new ArrayList<>();

        int colorSpecificOffset = 0;
        if(!isWhiteTurn)
            colorSpecificOffset = 6;

        long pawns = this.getPieces()[WHITE_PAWNS + colorSpecificOffset].getValue();
        while(pawns != 0){
            int index = Long.numberOfTrailingZeros(pawns);
            int[] moveList = getMoves(BitMap.getFile(index) - 'a', BitMap.getRank(index) - 1);
            for(int m : moveList) {
                int promotion = 0;
                int promotionRank;
                if(isWhiteTurn)
                    promotionRank = 8;
                else
                    promotionRank = 1;
                if(BitMap.getRank(m) == promotionRank){
                    for(int i = 0; i < 4; i++){
                        promotion++;
                        moves.add(index | (m << 6) | (promotion << 12));
                    }
                }else {
                    moves.add(index | (m << 6));
                }
            }
            pawns ^= 1L << index;
        }

        long rooks = this.getPieces()[WHITE_ROOKS + colorSpecificOffset].getValue();
        while(rooks != 0){
            int index = Long.numberOfTrailingZeros(rooks);
            for(int m : getMoves(BitMap.getFile(index) - 'a', BitMap.getRank(index) - 1)) {
                moves.add(index | (m << 6));
            }
            rooks ^= 1L << index;
        }

        long knight = this.getPieces()[WHITE_KNIGHTS + colorSpecificOffset].getValue();
        while(knight != 0){
            int index = Long.numberOfTrailingZeros(knight);
            for(int m : getMoves(BitMap.getFile(index) - 'a', BitMap.getRank(index) - 1)) {
                moves.add(index | (m << 6));
            }
            knight ^= 1L << index;
        }

        long bishop = this.getPieces()[WHITE_BISHOPS + colorSpecificOffset].getValue();
        while(bishop != 0){
            int index = Long.numberOfTrailingZeros(bishop);
            for(int m : getMoves(BitMap.getFile(index) - 'a', BitMap.getRank(index) - 1)) {
                moves.add(index | (m << 6));
            }
            bishop ^= 1L << index;
        }

        long queen = this.getPieces()[WHITE_QUEEN + colorSpecificOffset].getValue();
        while(queen != 0){
            int index = Long.numberOfTrailingZeros(queen);
            for(int m : getMoves(BitMap.getFile(index) - 'a', BitMap.getRank(index) - 1)) {
                moves.add(index | (m << 6));
            }
            queen ^= 1L << index;
        }

        long king = this.getPieces()[WHITE_KING + colorSpecificOffset].getValue();
        while(king != 0){
            int index = Long.numberOfTrailingZeros(king);
            for(int m : getMoves(BitMap.getFile(index) - 'a', BitMap.getRank(index) - 1)) {
                moves.add(index | (m << 6));
            }
            king ^= 1L << index;
        }

        int[] result = new int[moves.size()];
        for(int i = 0; i < result.length; i++){
            result[i] = moves.get(i);
        }
        return result;
    }

    public boolean isCheckMate(){
        if(isWhiteTurn && !isWhiteKingInCheck())
            return false;
        if(!isWhiteTurn && !isBlackKingInCheck())
            return false;
        return this.getLegalMoves().length == 0;
    }

    public boolean isStaleMate(){
        if(isWhiteTurn && isWhiteKingInCheck())
            return false;
        if(!isWhiteTurn && isBlackKingInCheck())
            return false;
        return this.getLegalMoves().length == 0;
    }

    public void setTurn(boolean white){
        this.isWhiteTurn = white;
    }

}