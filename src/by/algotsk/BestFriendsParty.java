package by.algotsk;

import java.util.Arrays;
import java.util.Comparator;

/*
Петя хочет устроить вечеринку. У него есть список друзей, заданный в виде двумерного массива [ [w₁,h₁], [w₂,h₂], …]. Все числа натуральные.
 Где wi — уровень достатка i-го друга, а hi — уровень счастья Пети, если этот друг будет присутствовать на встрече.
  Итоговое счастье Пети будет равняться сумме уровней счастья пришедших друзей.
  Также Петя знает, что если у кого-то из друзей уровень достатка в два или более раза меньше уровня достатка кого-либо на вечеринке, то он будет чувствовать себя некомфортно.
   Петя не хочет, чтобы кому-то из его гостей было некомфортно. Найдите максимальный уровень счастья вечеринки, которую может устроить Петя.
   maxHappiness([ [1,3], [5, 2], [3, 2]] => 4 // приглашаем двух
 */

public class BestFriendsParty {
    public static void main(String[] args) {
        System.out.println(bestFriends(new int[][]{{7, 1}, {5, 4}, {5, 2}, {3, 2}, {1, 10}, {1, 3}}));
        System.out.println(bestFriends(new int[][]{{2, 1}, {3, 10}, {4, 10}, {100, 10}}));
        System.out.println(bestFriends(new int[][]{{5, 2}, {3, 2}, {1, 3}}));
    }


    public static int bestFriends(int[][] bestFriends) {

        Arrays.sort(bestFriends, Comparator.comparingInt(a -> a[0]));

        int maxHappiness = 0;
        int currentHappiness = 0;
        int leftPointer = 0;

        for (int right = 0; right < bestFriends.length; right++) {
            currentHappiness += bestFriends[right][1];

            while (bestFriends[right][0] >= 2L * bestFriends[leftPointer][0]) {  // двигаем левый указатель, пока не наше условие не перестанет нарушаться.
                currentHappiness -= bestFriends[leftPointer][1];
                leftPointer++;
            }

            maxHappiness = Math.max(maxHappiness, currentHappiness);
        }
        return maxHappiness;
    }
}
