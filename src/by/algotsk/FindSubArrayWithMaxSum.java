package by.algotsk;

import java.util.Arrays;

public class FindSubArrayWithMaxSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(subArrayWithMaxSum(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})));
        System.out.println(cadane(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

    }

    // Если нужно узнать именно индексы
    static int[] subArrayWithMaxSum(int[] array)
    {
        // Важно инициализировать current_sum и best_sum первым элементом массива, а не нулём.
        // Иначе решение сломается на массивах, где все числа отрицательные.
        int currentSum = array[0];
        int maxSum = array[0];
        int currentStartIndex = 0;
        int currentEndIndex = 0;
        int maxStartIndex = 0;
        int maxEndIndex = 0;

        // если сумма предыдущего подмассива стала отрицательной, то тащить её дальше бессмысленно
        // Она только ухудшит любой будущий ответ.
        for (int i = 0; i < array.length ; i++ )
        {
            // array[i] положительное, тогда прибавляем его
            if ((currentSum + array[i]) > array[i])
            {
                currentSum = currentSum + array[i];
                currentEndIndex = i;
            }
            else
            {
                currentSum = array[i];
                currentStartIndex = i;
            }

            if(maxSum < currentSum)
            {
                maxSum = currentSum;
                maxStartIndex = currentStartIndex;
                maxEndIndex = currentEndIndex;
            }
        }
        return Arrays.copyOfRange(array, maxStartIndex, maxEndIndex + 1);
    }

    // Если нужно узнать только сумму, без индексов
    static int cadane(int[] array)
    {
        // Важно инициализировать current_sum и best_sum первым элементом массива, а не нулём.
        // Иначе решение сломается на массивах, где все числа отрицательные.
        int currentSum = array[0];
        int maxSum = array[0];

        // если сумма предыдущего подмассива стала отрицательной, то тащить её дальше бессмысленно
        // Она только ухудшит любой будущий ответ.
        for (int i = 1; i < array.length ; i++ )
        {
            currentSum = Math.max(currentSum + array[i], array[i]);
            maxSum = Math.max(maxSum, currentSum);

        }

        return maxSum;
    }
}
