import java.util.Collections;
import java.util.List;

/**
 * Есть компания из N человек. Каждый человек в компании может знать некоторое к-во других людей в компании, от 0 до N.
 * Среди них может быть знаменитость, человек который не знает никого в компании, но его знают все.
 * Изначально мы не знаем, кто кого знает в компании, но мы можем подходить к любому и спрашивать, знает ли он другого конкретного человека.
 * Нужно определить кто знаменитость, используя минимальное к-во вопросов.
 * <p>
 * Неоптимальное Решение: Если человек в компании никого не знает, а его все знают, то такой человек может быть только один.
 * Нужно опросить первого человека, знает ли он остальных, и опросить остальных знают ли они первого. Если первый никого не знает и его знают все, тогда он знаменитость, иначе повторяем для каждого человека.
 * Сложность O(N^2)
 * <p>
 * <p>
 * Оптимальное решение: Берём два указателя, на левый и правый конец.
 * Если левый знает правого, инкрементируем левый.
 * Иначе инкрементируем правый, т.к. если кто-то не знает правого, тогда он тоже не знаментитость.
 * Другими словами
 * Если А знает В, тогда А не знаменитость.
 * Если В не знает А, тогда А не знаменитость.
 *
 * После этого шага знаменитость не гарантированна, т.к. могли просто сойтись указатели.
 * Нужно ещё и проверить, что человек на указателе действительно знаменитость.
 * Проверяем циклом, что конкретный человек на который указывает указатель никого не знает, и его знают все.
 */
public static void main() {
    Person one = new Person(1, List.of(2));
    Person two = new Person(2, Collections.EMPTY_LIST);
    Person zero = new Person(0, List.of(1, 2));
    Person three = new Person(3, List.of(2));
    Person four = new Person(4, List.of(0, 1, 2, 3));

    Person[] people = new Person[]{zero, one, two, three, four};

    System.out.println(findCelebrity(people));
}

private static Person findCelebrity(Person[] people) {
    // todo null check for people
    Person potentialCelebrity = null;
    int left = 0;
    int right = people.length - 1;
/**
 * c помощью двух указателей проходим и опрашиваем людей, если левый знает правого, сдвигаем левый указатель,
 * если правый знает левого, тогда сдвигаем левый указатель.
 */
    while (left != right) {
        if (people[left].knows(people[right])) { // задавая только один вопрос мы можем исбавиться от одного человека в паре,
            left++;
        } else {
            right--;
        }
    }

/**
 * Теперь нужно проверить результат. Если наш кандидат никого не знает, и его все знают, тогда вернём его, если хоть раз условие нарушилось, тгда вернём null.
 */

    for (int i = 0; i < people.length; i++)
    {
        if (i == left)
        {
            continue;
        }

        if (people[i].doesNotKnow(people[left]) || people[left].knows(people[i]))
        {
            return null;
        }
    }

    return people[left];
}

record Person(int id, List<Integer> connections) {
    public boolean knows(Person person) {
        return connections.contains(person.id);
    }

    public boolean doesNotKnow(Person person) {
        return !knows(person);
    }
}