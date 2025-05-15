package edu.bu.feng.g9.chess.utils;

public class BitMap {

    private long map;

    public BitMap() {
        this.map = 0L;
    }

    public BitMap(long value) {
        this.map = value;
    }

    public void setBit(int index) {
        if (validateIndex(index))
            this.map |= 1L << index;
    }

    public void clearBit(int index) {
        if (validateIndex(index))
            this.map &= ~(1L << index);
    }

    public boolean getBit(int index) {
        if (validateIndex(index))
            return (this.map >>> index & 1L) == 1;
        return false;
    }

    public long getValue(){
        return this.map;
    }

    public void setValue(long value){
        this.map = value;
    }

    public static boolean validateIndex(int index) {
        return index >= 0 && index < 64;
    }

    public static int toIndex(char file, int rank) {
        if (file >= 'a' && file <= 'h' && rank >= 1 && rank <= 8)
            return ((rank - 1) << 3) + (file - 'a');
        return -1;
    }

    public static char getFile(int index) {
        return (char) ((index % 8) + 'a');
    }

    public static int getRank(int index) {
        return (index / 8) + 1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                int index = (rank << 3) + file;
                result.append(getBit(index) ? "1" : "0");
            }
            result.append("\n");
        }
        return result.toString();
    }

}