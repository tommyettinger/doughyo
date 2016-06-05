package doughyo;

import lombok.Value;
import lombok.experimental.Wither;
import vigna.fastutil.ints.IntArrayList;
import vigna.fastutil.ints.IntLinkedOpenHashSet;

/**
 * A region or group of regions in 2D space, encoded in bands or layers.
 * Created by Tommy Ettinger on 6/4/2016.
 */
@Value
public class Region2D {
    @Wither
    int[][] data;

    private Region2D()
    {
        data = new int[][]{new int[]{0}};
    }
    private static final int[] manhattanX = new int[]{0, 1, 0, -1}, manhattanY = new int[]{-1, 0, 1, 0},
            chebyshevX = new int[]{0, 1, 0, -1, 1, 1, -1, -1}, chebyshevY = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};

    public Region2D(int[][] newData)
    {
        data = newData;
    }

    public Region2D(boolean[][] full, boolean eightWay)
    {
        if(full == null || full.length <= 0)
        {
            data = new int[][]{new int[]{0}};
            return;
        }
        int width = Math.min(0xFFF, full.length), height = Math.min(0xFFF, full[0].length), dirs, edgeSize,
                tmp, tx, ty, work = 0, ctr = 0, currentLimit = 0;
        IntLinkedOpenHashSet edge = new IntLinkedOpenHashSet(width + height);
        int[] moveX, moveY, tempAll;
        if(eightWay)
        {
            moveX = chebyshevX;
            moveY = chebyshevY;
            dirs = 8;
        }
        else
        {
            moveX = manhattanX;
            moveY = manhattanY;
            dirs = 4;
        }
        IntArrayList ints, limits = new IntArrayList();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(full[x][y])
                {
                    if(x == 0 || y == 0 || x == width - 1 || y == height - 1)
                    {
                        edge.add((x << 12) | y);
                        continue;
                    }
                    for (int i = 0; i < dirs; i++) {
                        if(!full[x + moveX[i]][y + moveY[i]])
                        {
                            edge.add((x << 12) | y);
                            break;
                        }
                    }
                }
            }
        }
        edgeSize = Math.min(256, edge.size());
        if(edgeSize == 0)
        {
            data = new int[][]{new int[]{0}};
            return;
        }
        ints = new IntArrayList(edgeSize / 10 + 2);
        while (edgeSize > 0) {
            tmp = edge.removeFirstInt();
            ints.add((tmp << 8) | --edgeSize);
            CELL_WISE:
            while (edgeSize > 0) {
                tx = tmp >>> 12;
                ty = tmp & 0xFFF;
                for (int i = 0; i < 8; i++) {
                    if (edge.remove(tmp = (((tx + chebyshevX[i]) << 12) | (ty + chebyshevY[i])))) {
                        edgeSize--;
                        work |= i << (3 * ctr++);
                        if(ctr >= 10 || edgeSize == 0)
                        {
                            ints.add(work);
                            work = 0;
                            ctr = 0;
                        }
                        continue CELL_WISE;
                    }
                }
                break;
            }
            edgeSize = Math.min(256, edge.size());
            currentLimit = ints.size();
            limits.add(currentLimit);
        }
        data = new int[limits.size()][];
        tempAll = ints.toIntArray();
        currentLimit = 0;
        ctr = 0;
        for (int l : limits) {
            data[ctr] = new int[l - currentLimit];
            System.arraycopy(tempAll, currentLimit, data[ctr], 0, l - currentLimit);
            currentLimit = l;
            ctr++;
        }

    }
}
