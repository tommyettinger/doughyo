package doughyo;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Tommy Ettinger on 6/4/2016.
 */
public class Region2DTest {
    static boolean[][] data = new boolean[][]{
            new boolean[]{false, false, false, false, false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, false, false, false, false},
    }, data2 = new boolean[][]{
            new boolean[]{false, false, false, false, false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
    }, dataBig = new boolean[256][256];

    static {
        for (int x = 0; x < 256; x++) {
            Arrays.fill(dataBig[x], true);
        }
    }


    public long arrayMemoryUsage(int length, long bytesPerItem)
    {
        return (((bytesPerItem * length + 12 - 1) / 8) + 1) * 8L;
    }
    public long arrayMemoryUsage2D(int xSize, int ySize, long bytesPerItem)
    {
        return arrayMemoryUsage(xSize, (((bytesPerItem * ySize + 12 - 1) / 8) + 1) * 8L);
    }
    public int arrayMemoryUsageJagged(int[][] arr)
    {
        int ctr = 0;
        for (int i = 0; i < arr.length; i++) {
            ctr += arrayMemoryUsage(arr[i].length, 4);
        }
        return (((ctr + 12 - 1) / 8) + 1) * 8;
    }

    public String binaryInt(int i)
    {
        String bin = Integer.toBinaryString(i);
        return "00000000000000000000000000000000".substring(0, 32 - bin.length()) + bin;
    }
    @Test
    public void testRegion2D()
    {

        runBoolean(data);
        System.out.println();
        runBoolean(data2);
        System.out.println();
        runBoolean(dataBig);
    }
    public void runBoolean(boolean[][] dat)
    {
        Region2D r2 = new Region2D(dat, false);
        int[][] coded = r2.getData();

        System.out.println("BEFORE : Memory usage: " + arrayMemoryUsage2D(dat.length, dat[0].length, 1));
        System.out.println("AFTER  : Memory usage: " + arrayMemoryUsageJagged(coded));

        for (int i = 0; i < coded.length; i++) {
            int curr = coded[i][0], x = curr >>> 20, y = (curr >>> 8) & 0xFFF, span = curr & 0xFF, idx = 1;
            System.out.print("start: x:" + x + ",y:" + y + " with span " + span + ": ");
            for (int b = 0, total = 0; total <= span; total++) {
                System.out.print((coded[i][idx] >>> b++ * 3) & 7);
                System.out.print(", ");
                if (b >= 10) {
                    b = 0;
                    idx++;
                    System.out.print("ENDINT ");
                }
            }
            System.out.println();
            /*
            for (int j = 0; j < coded[i].length; j++) {
                System.out.println(binaryInt(coded[i][j]));
            }
            */
        }
    }
}
