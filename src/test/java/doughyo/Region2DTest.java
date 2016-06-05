package doughyo;

import org.junit.Test;

/**
 * Created by Tommy Ettinger on 6/4/2016.
 */
public class Region2DTest {
    boolean[][] data = new boolean[][]{
            new boolean[]{false, false, false, false, false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, true , true , true , false},
            new boolean[]{false, false, false, false, false},
    };
    public String binaryInt(int i)
    {
        String bin = Integer.toBinaryString(i);
        return "00000000000000000000000000000000".substring(0, 32 - bin.length()) + bin;
    }
    @Test
    public void testRegion2D()
    {
        Region2D r2 = new Region2D(data, false);
        int[][] coded = r2.getData();
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
            for (int j = 0; j < coded[i].length; j++) {
                System.out.println(binaryInt(coded[i][j]));
            }
        }
    }
}
