import com.sun.source.tree.IfTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Есть двумерное поле на котором робот в координатах 1:1 и дверь в прпотивоположном углу.
 * Робот двигается только вверх и вправо;
 *
 * Решение 1: Рекурсивное. Если координаты выхода 1:1, то к-во способов добраться до выхода клетки равно 1.
 * Если х = 1, то нужно двигаться только меняя у
 * Если у = 1, то нужно двигаться только меняя х
 * В остальных случаях двигаемся и по х, и по у и суммируем результат.
 * Сложность: 2^(x + y). Увеличивая стороны поля на 1, время работы алгоритма увеличивается в два раза.(экспоненциальный рост)
 *
 * Решение 2: Такоеже как и первое, но с кэшем, в который кладутся вычисленные значения
 *  Сложность: х * у т.к каждая ячейка просчитывается один раз
 *
 *  Решение 3: Как и первое, но в качестве кэша выступает массив. Так красивее
 *  Заводим двумерный массив в виде поля, и при вычислении к-ва путей к ячейке, записываем в аналогичную массива.
 *  В начале каждого массива проверяем соответствующую ячейку на пустоту.
 *  Сложность: х * у т.к каждая ячейка просчитывается один раз
 */

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

        // двигаемся вдоль пола
        if (x == 1) {
            return pathsOne(x, y - 1);
        }

        // двигаемся вдоль левой границы
        if (y == 1) {
            return pathsOne(x - 1, y);
        }

        // в отальных случаях
        return pathsOne(x - 1, y) + pathsOne(x, y - 1);
    }

    // Вариация первого решения
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

    // Решение 2 с кешем
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

    // Решение 3. С кешем в виде массива.
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

