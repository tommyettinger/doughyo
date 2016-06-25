package doughyo;

/**
 * Region encoding of a 64x64 area as a {@code long[64]}; uncompressed (fatty), but fast (greased lightning).
 * Created by Tommy Ettinger on 6/24/2016.
 */
public class Grease {
    public static long[] encode(boolean[][] bits)
    {
        long[] data = new long[64];
        for (int x = 0; x < bits.length && x < 64; x++) {
            for (int y = 0; y < bits[x].length && y < 64; y++) {
                if(bits[x][y]) data[x] |= 1L << y;
            }
        }
        return data;
    }
    public static long[] encode(boolean[] bits)
    {
        long[] data = new long[64];
        for (int x = 0; x < bits.length && x < 4096; x++) {
            if(bits[x]) data[x >>> 6] |= 1L << (x & 63);
        }
        return data;
    }
    public static boolean[][] decode(long[] original)
    {
        boolean[][] data = new boolean[64][64];
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 64; y++) {
                data[x][y] = (original[x] & (1L << y)) != 0;
            }
        }
        return data;
    }

    public static long[] or(long[] left, long[] right)
    {
        long[] data = new long[64];
        for (int i = 0; i < 64; i++) {
            data[i] = left[i] | right[i];
        }
        return data;
    }

    public static long[] and(long[] left, long[] right)
    {
        long[] data = new long[64];
        for (int i = 0; i < 64; i++) {
            data[i] = left[i] & right[i];
        }
        return data;
    }

    public static long[] andNot(long[] left, long[] right)
    {
        long[] data = new long[64];
        for (int i = 0; i < 64; i++) {
            data[i] = left[i] & ~right[i];
        }
        return data;
    }

    public static long[] xor(long[] left, long[] right)
    {
        long[] data = new long[64];
        for (int i = 0; i < 64; i++) {
            data[i] = left[i] ^ right[i];
        }
        return data;
    }

    public static long[] translate(long[] original, int x, int y)
    {
        long[] data = new long[64];
        int start = Math.max(0, x), len = Math.min(64, 64 + x) - start;
        System.arraycopy(original, 0, data, start, len);
        if(y < 0) {
            for (int i = start; i < len; i++) {
                data[i] >>>= -y;
            }
        }
        else if(y > 0) {
            for (int i = start; i < len; i++) {
                data[i] <<= y;
            }
        }
        return data;
    }

    public static long[] expand(long[] original)
    {
        long[] data = new long[64];
        System.arraycopy(original, 0, data, 0, 64);
        data[0] |= (original[0] << 1) | (original[0] >>> 1) | (original[1]);
        data[63] |= (original[63] << 1) | (original[63] >>> 1) | (original[62]);
        for (int i = 1; i < 63; i++) {
            data[i] |= (original[i] << 1) | (original[i] >>> 1) | (original[i - 1]) | (original[i + 1]);
        }
        return data;
    }

    public static long[] retract(long[] original)
    {
        long[] data = new long[64];
        System.arraycopy(original, 1, data, 1, 62);
        for (int i = 1; i < 63; i++) {
            data[i] &= (original[i] << 1) & (original[i] >>> 1) & (original[i - 1]) & (original[i + 1]);
        }
        return data;
    }

}
