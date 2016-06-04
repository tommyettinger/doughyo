package doughyo;

import lombok.Value;
import lombok.experimental.Wither;

/**
 * A region or group of regions in 2D space, encoded in bands or layers.
 * Created by Tommy Ettinger on 6/4/2016.
 */
@Value
public class Region2D {
    @Wither
    int[] data;

    private Region2D()
    {
        data = new int[]{0};
    }
    public Region2D(boolean[][] full)
    {
        if(full == null || full.length <= 0)
        {
            data = new int[]{0};
            return;
        }
        int width = Math.min(4096, full.length), height = Math.min(4096, full[0].length);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // add to Set of some kind
            }
        }
    }
}
