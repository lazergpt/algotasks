package by.algotsk;

import java.util.Arrays;

/**
 * Дан массив целых чисел. Разрешается выполнение следующей операции любое количество раз: два соседних элемента меняются между собой знаками.
 * Выведите максимальную сумму элементов массива, которую можно получить.
 * maxPossibleSum([-2, 1, 3]) => 4 // -2 и 1 меняются знаками. [2,-1, 3]
 */

class SumArrayElements {
    static void main(String[] args) {
        //System.out.println(maxPossibleSumm(new int[]{-2, 1, 3})); // 4
        System.out.println(maxPossibleSumm(new int[]{-10, 1, 2, -5})); // 12

    }

    public static int maxPossibleSumm(int[] array) {
        int sum = 0;
        int minusCounter = 0;
        int[] positiveArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            positiveArray[i] = Math.abs(array[i]);

            if (array[i] < 0) {
                minusCounter++;
            }
        }

        Arrays.sort(positiveArray);

        for (int i = 0; i < minusCounter; i++) {
            positiveArray[i] = -positiveArray[i];
            sum += positiveArray[i];
        }

        for (int i = minusCounter; i < array.length; i++) {
            sum += positiveArray[i];
        }

        return sum;
    }
}

