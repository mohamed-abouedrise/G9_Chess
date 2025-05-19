package edu.bu.feng.g9.chess.game;

import edu.bu.feng.g9.chess.utils.BitMap;

public class Pieces {

    private static final int BOARD_SIZE = 64;

    public static final BitMap[] KNIGHT_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] WHITE_PAWN_CAPTURE = new BitMap[BOARD_SIZE];
    public static final BitMap[] BLACK_PAWN_CAPTURE = new BitMap[BOARD_SIZE];
    public static final BitMap[] WHITE_PAWN_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] BLACK_PAWN_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] KING_MOVES = new BitMap[BOARD_SIZE];
    public static final BitMap[] ROOK_MASKS = new BitMap[64];
    public static final BitMap[] BISHOP_MASKS = new BitMap[64];
    public static final BitMap[][] ROOK_ATTACKS = new BitMap[64][];
    public static final BitMap[][] BISHOP_ATTACKS = new BitMap[64][];
    public static final BitMap[][] KING_PIN_MASKS = new BitMap[8][64];

    public static final long[] ROOK_MAGICS = {
            0xa8002c000108020L, 0x6c00049b0002001L, 0x100200010090040L, 0x2480041000800801L,
            0x280028004000800L, 0x900410008040022L, 0x280020001001080L, 0x2880002041000080L,
            0xa000800080400034L, 0x4808020004000L, 0x2290802004801000L, 0x411000d00100020L,
            0x402800800040080L, 0xb000401004208L, 0x2409000100040200L, 0x1002100004082L,
            0x22878001e24000L, 0x1090810021004010L, 0x801030040200012L, 0x500808008001000L,
            0xa08018014000880L, 0x8000808004000200L, 0x201008080010200L, 0x801020000441091L,
            0x800080204005L, 0x1040200040100048L, 0x120200402082L, 0xd14880480100080L,
            0x12040280080080L, 0x100040080020080L, 0x9020010080800200L, 0x813241200148449L,
            0x491604001800080L, 0x100401000402001L, 0x4820010021001040L, 0x400402202000812L,
            0x209009005000802L, 0x810800601800400L, 0x4301083214000150L, 0x204026458e001401L,
            0x40204000808000L, 0x8001008040010020L, 0x8410820820420010L, 0x1003001000090020L,
            0x804040008008080L, 0x12000810020004L, 0x1000100200040208L, 0x430000a044020001L,
            0x280009023410300L, 0xe0100040002240L, 0x200100401700L, 0x2244100408008080L,
            0x8000400801980L, 0x2000810040200L, 0x8010100228810400L, 0x2000009044210200L,
            0x4080008040102101L, 0x40002080411d01L, 0x2005524060000901L, 0x502001008400422L,
            0x489a000810200402L, 0x1004400080a13L, 0x4000011008020084L, 0x26002114058042L
    };

    public static final long[] BISHOP_MAGICS = {
            0x89a1121896040240L, 0x2004844802002010L, 0x2068080051921000L, 0x62880a0220200808L,
            0x4042004000000L, 0x100822020200011L, 0xc00444222012000aL, 0x28808801216001L,
            0x400492088408100L, 0x201c401040c0084L, 0x840800910a0010L, 0x82080240060L,
            0x2000840504006000L, 0x30010c4108405004L, 0x1008005410080802L, 0x8144042209100900L,
            0x208081020014400L, 0x4800201208ca00L, 0xf18140408012008L, 0x1004002802102001L,
            0x841000820080811L, 0x40200200a42008L, 0x800054042000L, 0x88010400410c9000L,
            0x520040470104290L, 0x1004040051500081L, 0x2002081833080021L, 0x400c00c010142L,
            0x941408200c002000L, 0x658810000806011L, 0x188071040440a00L, 0x4800404002011c00L,
            0x104442040404200L, 0x511080202091021L, 0x4022401120400L, 0x80c0040400080120L,
            0x8040010040820802L, 0x480810700020090L, 0x102008e00040242L, 0x809005202050100L,
            0x8002024220104080L, 0x431008804142000L, 0x19001802081400L, 0x200014208040080L,
            0x3308082008200100L, 0x41010500040c020L, 0x4012020c04210308L, 0x208220a202004080L,
            0x111040120082000L, 0x6803040141280a00L, 0x2101004202410000L, 0x8200000041108022L,
            0x21082088000L, 0x2410204010040L, 0x40100400809000L, 0x822088220820214L,
            0x40808090012004L, 0x910224040218c9L, 0x402814422015008L, 0x90014004842410L,
            0x1000042304105L, 0x10008830412a00L, 0x2520081090008908L, 0x40102000a0a60140L
    };

    public static final int[] ROOK_SHIFTS = {
            52, 53, 53, 53, 53, 53, 53, 52,
            53, 54, 54, 54, 54, 54, 54, 53,
            53, 54, 54, 54, 54, 54, 54, 53,
            53, 54, 54, 54, 54, 54, 54, 53,
            53, 54, 54, 54, 54, 54, 54, 53,
            53, 54, 54, 54, 54, 54, 54, 53,
            53, 54, 54, 54, 54, 54, 54, 53,
            52, 53, 53, 53, 53, 53, 53, 52
    };

    public static final int[] BISHOP_SHIFTS = {
            58, 59, 59, 59, 59, 59, 59, 58,
            59, 59, 59, 59, 59, 59, 59, 59,
            59, 59, 57, 57, 57, 57, 59, 59,
            59, 59, 57, 55, 55, 57, 59, 59,
            59, 59, 57, 55, 55, 57, 59, 59,
            59, 59, 57, 57, 57, 57, 59, 59,
            59, 59, 59, 59, 59, 59, 59, 59,
            58, 59, 59, 59, 59, 59, 59, 58
    };

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

            WHITE_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) + 1));
            WHITE_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) + 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            WHITE_PAWN_MOVES[i] = new BitMap();

            WHITE_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 1));
            WHITE_PAWN_MOVES[i].setBit(BitMap.toIndex(BitMap.getFile(i), BitMap.getRank(i) + 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            BLACK_PAWN_CAPTURE[i] = new BitMap();

            BLACK_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) + 1), BitMap.getRank(i) - 1));
            BLACK_PAWN_CAPTURE[i].setBit(BitMap.toIndex((char)(BitMap.getFile(i) - 1), BitMap.getRank(i) - 1));

        }
    }

    static{
        for(int i = 0; i < BOARD_SIZE; i++){
            BLACK_PAWN_MOVES[i] = new BitMap();

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

    static{
        for (int i = 0; i < 64; i++) {
            long result = 0L;

            int rank = i / 8;
            int file = i % 8;

            for (int r = rank + 1; r < 7; r++) {
                result |= 1L << (r * 8 + file);
            }

            for (int r = rank - 1; r > 0; r--) {
                result |= 1L << (r * 8 + file);
            }

            for (int f = file + 1; f < 7; f++) {
                result |= 1L << (rank * 8 + f);
            }

            for (int f = file - 1; f > 0; f--) {
                result |= 1L << (rank * 8 + f);
            }
            ROOK_MASKS[i] = new BitMap();
            ROOK_MASKS[i].setValue(result);
        }
    }

    static{

        for(int i = 0; i < 64; i++){

            long result = 0L;

            int rank = i / 8;
            int file = i % 8;

            for (int r = rank + 1, f = file + 1; r < 7 && f < 7; r++, f++) {
                result |= 1L << (r * 8 + f);
            }

            for (int r = rank + 1, f = file - 1; r < 7 && f > 0; r++, f--) {
                result |= 1L << (r * 8 + f);
            }

            for (int r = rank - 1, f = file + 1; r > 0 && f < 7; r--, f++) {
                result |= 1L << (r * 8 + f);
            }

            for (int r = rank - 1, f = file - 1; r > 0 && f > 0; r--, f--) {
                result |= 1L << (r * 8 + f);
            }

            BISHOP_MASKS[i] = new BitMap();
            BISHOP_MASKS[i].setValue(result);

        }

    }

    static{
        for (int square = 0; square < 64; square++) {
            int rookBits = Long.bitCount(ROOK_MASKS[square].getValue());
            int bishopBits = Long.bitCount(BISHOP_MASKS[square].getValue());

            ROOK_ATTACKS[square] = new BitMap[1 << rookBits];
            BISHOP_ATTACKS[square] = new BitMap[1 << bishopBits];

            // Generate all possible occupancy patterns for rooks
            long[] rookOccupancies = generateOccupancies(ROOK_MASKS[square].getValue(), rookBits);
            for (int i = 0; i < rookOccupancies.length; i++) {
                long occupancy = rookOccupancies[i];
                long attacks = generateRookAttacks(square, occupancy);

                long index = (occupancy * ROOK_MAGICS[square]) >>> ROOK_SHIFTS[square];
                ROOK_ATTACKS[square][(int)index] = new BitMap();
                ROOK_ATTACKS[square][(int)index].setValue(attacks);
            }

            long[] bishopOccupancies = generateOccupancies(BISHOP_MASKS[square].getValue(), bishopBits);
            for (int i = 0; i < bishopOccupancies.length; i++) {
                long occupancy = bishopOccupancies[i];
                long attacks = generateBishopAttacks(square, occupancy);

                long index = (occupancy * BISHOP_MAGICS[square]) >>> BISHOP_SHIFTS[square];
                BISHOP_ATTACKS[square][(int)index] = new BitMap();
                BISHOP_ATTACKS[square][(int)index].setValue(attacks);
            }
        }
    }

    static{
        for(int i = 0; i < 64; i++){

            char file = BitMap.getFile(i);
            int rank = BitMap.getRank(i);

            KING_PIN_MASKS[0][i] = new BitMap();
            KING_PIN_MASKS[1][i] = new BitMap();
            KING_PIN_MASKS[2][i] = new BitMap();
            KING_PIN_MASKS[3][i] = new BitMap();
            KING_PIN_MASKS[4][i] = new BitMap();
            KING_PIN_MASKS[5][i] = new BitMap();
            KING_PIN_MASKS[6][i] = new BitMap();
            KING_PIN_MASKS[7][i] = new BitMap();

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[0][i].setBit(BitMap.toIndex(file, rank + j));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[1][i].setBit(BitMap.toIndex((char)(file + j), rank + j));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[2][i].setBit(BitMap.toIndex((char)(file + j), rank));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[3][i].setBit(BitMap.toIndex((char)(file + j), rank - j));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[4][i].setBit(BitMap.toIndex(file, rank - j));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[5][i].setBit(BitMap.toIndex((char)(file - j), rank - j));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[6][i].setBit(BitMap.toIndex((char)(file - j), rank));
            }

            for(int j = 1; j <= 8 - rank; j++){
                KING_PIN_MASKS[7][i].setBit(BitMap.toIndex((char)(file - j), rank + j));
            }
        }
    }

    private static long[] generateOccupancies(long mask, int bits) {
        long[] result = new long[1 << bits];

        for (int i = 0; i < (1 << bits); i++) {
            result[i] = 0L;
            int bit = 0;

            // For each bit in the mask
            for (int j = 0; j < 64; j++) {
                if (((mask >>> j) & 1L) == 1L) {
                    // If this bit is on in the index
                    if (((i >>> bit) & 1) == 1) {
                        result[i] |= (1L << j);
                    }
                    bit++;
                }
            }
        }

        return result;
    }

    private static long generateRookAttacks(int square, long occupancy) {
        long result = 0L;

        int rank = square / 8;
        int file = square % 8;

        // North
        for (int r = rank + 1; r < 8; r++) {
            int targetSquare = r * 8 + file;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        // South
        for (int r = rank - 1; r >= 0; r--) {
            int targetSquare = r * 8 + file;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        // East
        for (int f = file + 1; f < 8; f++) {
            int targetSquare = rank * 8 + f;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        // West
        for (int f = file - 1; f >= 0; f--) {
            int targetSquare = rank * 8 + f;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        return result;
    }

    private static long generateBishopAttacks(int square, long occupancy) {
        long result = 0L;

        int rank = square / 8;
        int file = square % 8;

        // Northeast
        for (int r = rank + 1, f = file + 1; r < 8 && f < 8; r++, f++) {
            int targetSquare = r * 8 + f;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        // Northwest
        for (int r = rank + 1, f = file - 1; r < 8 && f >= 0; r++, f--) {
            int targetSquare = r * 8 + f;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        // Southeast
        for (int r = rank - 1, f = file + 1; r >= 0 && f < 8; r--, f++) {
            int targetSquare = r * 8 + f;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        // Southwest
        for (int r = rank - 1, f = file - 1; r >= 0 && f >= 0; r--, f--) {
            int targetSquare = r * 8 + f;
            result |= 1L << targetSquare;

            if (((occupancy >>> targetSquare) & 1L) == 1L) {
                break;
            }
        }

        return result;
    }

    public static BitMap getRookAttacks(int square, BitMap occupancy) {
        long mask = ROOK_MASKS[square].getValue();
        long index = ((occupancy.getValue() & mask) * ROOK_MAGICS[square]) >>> ROOK_SHIFTS[square];
        return ROOK_ATTACKS[square][(int)index];
    }

    public static BitMap getBishopAttacks(int square, BitMap occupancy) {
        long mask = BISHOP_MASKS[square].getValue();
        long index = ((occupancy.getValue() & mask) * BISHOP_MAGICS[square]) >>> BISHOP_SHIFTS[square];
        return BISHOP_ATTACKS[square][(int)index];
    }

    public static BitMap getQueenAttacks(int square, BitMap occupancy) {
        BitMap rookAttacks = getRookAttacks(square, occupancy);
        BitMap bishopAttacks = getBishopAttacks(square, occupancy);
        BitMap queenAttacks = new BitMap();
        queenAttacks.setValue(rookAttacks.getValue() | bishopAttacks.getValue());
        return queenAttacks;
    }

}