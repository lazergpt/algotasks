package by.algotsk;

public class MoveSpecCharToTheEnd {
    public static final String SPECIAL_CHARS = "!@#$%^&*";
    public static final String REGEXP = "[a-zA-Z0-9\\s+]";

    static void main() {
        String testString = "12345!@#$%^&*qwerty1234677";
        System.out.println(moveSpecCharactersToTheEnd(testString));
        System.out.println(moveSpecCharactersToTheEnd1(testString));
        System.out.println(moveSpecCharactersToTheEnd2(testString));
    }

    // Первый вариант с contains и некрасивой конкатенацией
    private static String moveSpecCharactersToTheEnd(String testString) {
        StringBuilder resultString = new StringBuilder();
        StringBuilder specialCharsString = new StringBuilder();

        for (int i = 0; i < testString.length(); i++) {
            if (SPECIAL_CHARS.contains("" + testString.charAt(i))) {
                specialCharsString.append(testString.charAt(i));
            } else {
                resultString.append(testString.charAt(i));
            }
        }

        resultString.append(specialCharsString);

        return resultString.toString();
    }

    // Через Stream
    private static String moveSpecCharactersToTheEnd1(String testString) {
        StringBuilder resultString = new StringBuilder();
        StringBuilder specialCharsString = new StringBuilder();

        testString.chars()
                .mapToObj(i -> (char) i)
                .forEachOrdered(c -> {
                    if (SPECIAL_CHARS.indexOf(c) != -1) {
                        specialCharsString.append(c);
                    } else {
                        resultString.append(c);
                    }
                });

        resultString.append(specialCharsString);

        return resultString.toString();
    }

    //с регуляркой
    private static String moveSpecCharactersToTheEnd2(String testString) {
        StringBuilder resultString = new StringBuilder();
        StringBuilder specialCharsString = new StringBuilder();

        testString.chars()
                .mapToObj(i -> (char) i)
                .forEachOrdered(c -> {
                    if (String.valueOf(c).matches(REGEXP)) {
                        resultString.append(c);
                    } else {
                        specialCharsString.append(c);
                    }
                });

        resultString.append(specialCharsString);

        return resultString.toString();
    }


}
