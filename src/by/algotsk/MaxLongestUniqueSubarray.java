package by.algotsk;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class MaxLongestUniqueSubarray
{
    public static void main(String[] args) {
        System.out.println(longestSubstringN2(new char[]{'a', 'b', 'c', 'b', 'a', 'd', 'a'}));
        System.out.println(longestSubstringN2(new char[]{'a', 'x', 'b', 'x', 'c', 'x', 'd'}));
        System.out.println(longestSubstringN2(new char[]{'a', 'a', 'a', 'a', 'a', 'a', 'a'}));
        System.out.println(longestSubstringN2(new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'}));

    }

    // n^2
    static int longestSubstringN2(char[] array)
    {
        int maxLong = 0;
        int currentLong = 0;
        Set<Character> buffer = new HashSet<>();

        for (int left = 0; left < array.length ; left++ )
        {
            for(int right = left; right  < array.length; right++)
            {
                if ( buffer.contains(new Character(array[right])))
                {
                    buffer.clear();
                    currentLong = 0;
                    break;
                }

                buffer.add(new Character(array[right]));
                currentLong++;
                maxLong = Math.max(currentLong, maxLong);
            }

            buffer.clear();
            currentLong = 0;
        }

        return maxLong;
    }
}
