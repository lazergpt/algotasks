import com.sun.source.tree.IfTree;

import java.util.HashMap;
import java.util.Map;

public class RobotAndExit {
    static Map<String, Integer> cache = new HashMap<>();

    static void main() {

        cache.put("00", 0);
        cache.put("11", 1);
        System.out.println(pathsOne(6, 6));
        System.out.println(pathsTwo(6, 6));
        System.out.println(pathsThreeWithCache(6, 6));
        System.out.println(pathsThreeWithArray(6, 6, new int[6][6]));

        System.out.println(cache);
    }

// Вариант 1
    static int pathsOne(int x, int y) {
        if (x == 1 && y == 1) {
            return 1;
        }

        if (x == 1) {
            return pathsOne(x, y - 1);
        }

        if (y == 1) {
            return pathsOne(x - 1, y);
        }

        return pathsOne(x - 1, y) + pathsOne(x, y - 1);
    }

    static int pathsTwo(int x, int y) {
        if (x == 0 || y == 0)
        {
            return 0;
        }

        if (x == 1 && y == 1) {
            return 1;
        }

        return pathsTwo(x - 1, y) + pathsTwo(x, y - 1);
    }

    static int pathsThreeWithCache(Integer x, Integer y) {
        Integer valueFromCache = cache.get(x.toString() + y.toString());

        if (valueFromCache != null)
        {
            return valueFromCache;
        }

        if (y == 1) {
            int firstDimension =  pathsThreeWithCache(x - 1, y);
            cache.put(Integer.valueOf(x - 1).toString() + y.toString(), firstDimension );
            return firstDimension;
        }

        if (x == 1) {
            int secondDimension = pathsThreeWithCache(x, y - 1);
            cache.put(x.toString() + Integer.valueOf(y - 1).toString(), secondDimension );
            return secondDimension;
        }

        int firstDimension =  pathsThreeWithCache(x - 1, y);
        cache.put(Integer.valueOf(x - 1).toString() + y.toString(), firstDimension );
        int secondDimension = pathsThreeWithCache(x, y - 1);
        cache.put(x.toString() + Integer.valueOf(y - 1).toString(), secondDimension );

        return firstDimension + secondDimension;
    }

    static int pathsThreeWithArray(int x, int y, int array[][]) {
        if (x == 0 || y == 0)
        {
            return 0;
        }

        if (x == 1 && y == 1) {
            return 1;
        }

        array[x - 1][y - 1] =  pathsThreeWithArray(x - 1, y, array) + pathsThreeWithArray(x, y - 1, array);
        return array[x - 1][y - 1];
    }
}

