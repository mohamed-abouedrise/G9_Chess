package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private static final int BOARD_SIZE = 64;

    public static final BitMap[] KNIGHT_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] WHITE_PAWN_CAPTURE = new BitMap[BOARD_SIZE];
    public static final BitMap[] BLACK_PAWN_CAPTURE = new BitMap[BOARD_SIZE];
    public static final BitMap[] WHITE_PAWN_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] BLACK_PAWN_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] KING_MOVES = new BitMap[BOARD_SIZE];

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            KNIGHT_MOVES[i] = new BitMap();

            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 2), BitMap.getRank(i) + 1));
            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 2), BitMap.getRank(i) - 1));

            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 2), BitMap.getRank(i) + 1));
            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 2), BitMap.getRank(i) - 1));

            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) + 2));
            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) - 2));

            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) + 2));
            KNIGHT_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) - 2));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            WHITE_PAWN_CAPTURE[i] = new BitMap();

            if(BitMap.getRank(i) == 1 || BitMap.getRank(i) == 8)
                continue;

            WHITE_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) + 1));
            WHITE_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) + 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            WHITE_PAWN_MOVES[i] = new BitMap();

            if(BitMap.getRank(i) == 1 || BitMap.getRank(i) == 8)
                continue;

            if(BitMap.getRank(i) == 2){
                WHITE_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 2));
                WHITE_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 2));
            }

            WHITE_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 1));
            WHITE_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            BLACK_PAWN_CAPTURE[i] = new BitMap();

            if(BitMap.getRank(i) == 1 || BitMap.getRank(i) == 8)
                continue;

            BLACK_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) - 1));
            BLACK_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) - 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            BLACK_PAWN_MOVES[i] = new BitMap();

            if(BitMap.getRank(i) == 1 || BitMap.getRank(i) == 8)
                continue;

            if(BitMap.getRank(i) == 7){
                BLACK_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) - 2));
                BLACK_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) - 2));
            }

            BLACK_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) - 1));
            BLACK_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) - 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            KING_MOVES[i] = new BitMap();

            KING_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) + 1));
            KING_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) - 1));

            KING_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) + 1));
            KING_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) - 1));

            KING_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 1));
            KING_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) - 1));

            KING_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i)));
            KING_MOVES[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i)));

        }
    }

}