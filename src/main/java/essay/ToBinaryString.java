package essay;

/**
 * int ==> binary string
 */
public class ToBinaryString {

    private static final int SIZE = 32;

    static String toBinaryString(int val){
        int shift = 1;
        // assert shift > 0 && shift <=5 : "Illegal shift value";
        int mag = ToBinaryString.SIZE - ToBinaryString.numberOfLeadingZeros(val);
        int chars = Math.max(((mag + (shift - 1)) / shift), 1);
        char[] buf = new char[chars];

        formatUnsignedInt(val, shift, buf, 0, chars);

        // Use special constructor which takes over "buf".
        return new String(buf);
    }

    static int formatUnsignedInt(int val, int shift, char[] buf, int offset, int len) {
        int charPos = len;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[offset + --charPos] = ToBinaryString.digits[val & mask];
            val >>>= shift;
        } while (val != 0 && charPos > 0);

        return charPos;
    }

    /**
     * All possible chars for representing a number as a String
     * base 2 and base 16
     */
    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
     * 返回int数前面的0的个数
     * @param val
     * @return
     */
    public static int numberOfLeadingZeros(int val){
        if(val == 0){
            return 32;
        }
        int n = 1;
        if(val >>> 16 == 0){ n+=16; val<<=16; }
        if(val >>> 24 == 0){ n+=8; val<<=8; }
        if(val >>> 28 == 0){ n+=4; val<<=4; }
        if(val >>> 30 == 0){ n+=2; val<<=2; }
        n-=val>>>31;
        return n;
    }

    public static int bitCount(int i) {
        // HD, Figure 5-2
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }

    public static void main(String[] args){
//        ToBinaryString.numberOfLeadingZeros(-2);
        ToBinaryString.toBinaryString(0);
//        ToBinaryString.bitCount(14);
    }

}
