package doughyo;

import org.junit.Test;

/**
 * Created by Tommy Ettinger on 6/24/2016.
 */
public class GreaseTest {
    public boolean[][] small = new boolean[][]{
            new boolean[]{false, false, true, true, true, false, false},
            new boolean[]{false, true , true, true, true, true , false},
            new boolean[]{false, false, true, true, true, true , false},
            new boolean[]{false, false, true, true, true, false, false},
            new boolean[]{false, false, true, true, true, true , false},
            new boolean[]{false, false, true, true, true, false, false},
            new boolean[]{false, false, false, true, false, false, false},
    };

    public void printBooleans(boolean[][] data)
    {
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data[x].length; y++) {
                System.out.print(data[x][y] ? '1' : '0');
            }
            System.out.println();
        }
    }
    @Test
    public void testBasics()
    {
        long[] encoded = Grease.encode(small);
        printBooleans(Grease.decode(encoded));
        System.out.println();
        printBooleans(Grease.decode(Grease.translate(encoded, 1, 1)));
        System.out.println();
        printBooleans(Grease.decode(Grease.expand(encoded)));
        System.out.println();
        printBooleans(Grease.decode(Grease.retract(encoded)));


    }
}
