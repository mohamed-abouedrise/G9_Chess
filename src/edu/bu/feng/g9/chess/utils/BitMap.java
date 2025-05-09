package edu.bu.feng.g9.chess.utils;

public class BitMap {

    private long Map;

    public BitMap(){
        this.Map = 0L;
    }

    public void setBit(char file, int rank){
        if(validateFile(file) && validateRank(rank))
            this.Map |= 1L << (((rank - 1) << 3) + file - 'a');
    }

    public void clearBit(char file, int rank){
        if(validateFile(file) && validateRank(rank))
            this.Map &= ~(1L << (((rank - 1) << 3) + file - 'a'));
    }

    public boolean getBit(char file, int rank){
        if(validateFile(file) && validateRank(rank))
            return (this.Map >>> (((rank - 1) << 3) + file - 'a') & 1L) == 1;

        return false;
    }

    private static boolean validateFile(char file) {
        return file >= 'a' && file <= 'h';
    }

    private static boolean validateRank(int rank){
        return rank >= 1 && rank <= 8;
    }

    public static boolean validateIndex(int index){
        return validateRank(getRank(index)) && validateFile(getFile(index));
    }

    public static int toIndex(char file, int rank){
        if(validateRank(rank) && validateFile(file))
            return ((rank - 1) << 3) + file - 'a';
        return -1;
    }

    public static char getFile(int index){
        return (char) (index % 8 + 'a');
    }

    public static int getRank(int index){
        return index / 8 + 1;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int i = 8; i > 0; i--){
            for(int j = 0; j < 8; j++){
                result.append(this.getBit((char)(j + 'a'), i)?"1":"0");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
