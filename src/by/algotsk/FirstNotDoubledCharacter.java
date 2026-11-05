package by.algotsk;

// Вернуть первый символ строки, который встречается ровно один раз. Для "swiss" это 'w'; для "aabb" такого символа нет.

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNotDoubledCharacter {

    static void main() {
        String testString = "pnumonoultramicroscopicsilicovolcanoconiosis";
// вариант 1 O(n) по времени и O(k) по памяти где k-во уникальных символов
        Map<Character, Integer> counters = new LinkedHashMap<>();

        for (int i = 0; i < testString.length(); i++) {
            Character iChar = testString.charAt(i);

            if (counters.containsKey(iChar)) {
                Integer counter = counters.get(iChar);
                counter += 1;
                counters.put(iChar, counter);
            } else {
                counters.put(iChar, 1);
            }
        }

        counters.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst().map(Map.Entry::getKey).ifPresentOrElse(System.out::println, () -> System.out.println("No unique symbols"));


        //  testString.chars().mapToObj(i -> (char) i).map(character -> counters.computeIfAbsent())

// вариант 2 O(n)^2 по времени и O(1)
        for (int i = 0; i < testString.length(); i++) {
            Character iChar = testString.charAt(i);

            if (testString.indexOf(iChar) == i && testString.indexOf(iChar, i + 1) == -1) {
                System.out.println(iChar);
                break;
            }
        }

    }


}
