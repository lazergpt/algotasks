package by.algotsk;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Задача: Вернуть первый символ строки, который встречается ровно один раз. Для "swiss" это 'w'; для "aabb" такого символа нет.
 *
 * Решение 1: проходим по строке, и каждый символ суём в хэштаблицу где ключ это символ а значение это счётчик это значение.
 * Важно: Для мапы использовать отсортированную мапу - LinkedHashMap
 * Сложность: O(n) по времени и O(k) по памяти где k-во уникальных символов
 *
 * Решение 2: проходим по строке и проверяем - индех текущего элемента равен индексу по testString.indexOf(iChar) и что не существует ли элемент в остальной части пассива лежащей правее текущего индекса testString.indexOf(iChar, i + 1) == -1
 * Полное условие: testString.indexOf(iChar) == i && testString.indexOf(iChar, i + 1) == -1
 * если оба условия ок, тогда печатаем сивол и выходим из цикла.
 * Сложность: O(n^2) по времени и O(1) по памяти
 */
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

// вариант 2 O(n^2) по времени и O(1)
        for (int i = 0; i < testString.length(); i++) {
            Character iChar = testString.charAt(i);

            if (testString.indexOf(iChar) == i && testString.indexOf(iChar, i + 1) == -1) {
                System.out.println(iChar);
                break;
            }
        }

    }


}
