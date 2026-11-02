package by.algotsk;

/*
Вы — профессиональный грабитель, который планирует обчистить дома на одной улице.
 В каждом доме спрятана определённая сумма денег.
 Единственное, что мешает вам ограбить все дома, — это то, что в соседних домах установлены системы безопасности, которые автоматически сообщат в полицию, если будут ограблены два соседних дома.

 Дан целочисленный массив money, в котором указано количество денег в каждом доме. Верните максимальное количество денег, которое вы сможете украсть сегодня ночью, не привлекая внимания полиции.
 */

public class HouseRobber {

    static void main() {
        //System.out.println(robHouses(new int[]{4, 11, 10, 1, 2, 8, 5}));
        //System.out.println(robHouses(new int[]{4}));
        //System.out.println(robHouses(new int[]{4, 11}));
        //System.out.println(robHouses(new int[]{11, 4}));
        System.out.println(robHouses(new int[]{2, 7, 9, 3, 1}));
    }

    private static int robHouses(int[] money) {
        if (money.length == 1) {
            return money[0];
        }

        int[] sumArray = new int[money.length];
        sumArray[0] = money[0];
        sumArray[1] = Math.max(money[0], money[1]);

        for (int i = 2; i < money.length; i++) // -1 ?
        {
            sumArray[i] = Math.max(sumArray[i - 2] + money[i], sumArray[i - 1]);
        }

        //return Arrays.stream(sumArray).max().orElseGet(() -> 0);
        return sumArray[money.length - 1];
    }
}
