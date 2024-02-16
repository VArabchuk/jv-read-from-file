package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FileWork {
    private static final int INDEX_OF_BEGIN_ARRAY = 0;
    private static final int SPACE_SYMBOL = 32;
    private static final int CAP_LETTER_A = 65;
    private static final int CAP_LETTER_Z = 90;
    private static final int SMALL_LETTER_A = 97;
    private static final int SMALL_LETTER_Z = 122;
    private static final int NEW_LINE = 10;
    private static final char STARTING_LETTER = 'w';
    private static final String EXCEPTION_MESSAGE = "Can't read data from file";
    private static final String SEPARATOR = " ";

    public String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            int value = reader.read();
            while (value != -1) {
                if (value == SPACE_SYMBOL || (value >= CAP_LETTER_A && value <= CAP_LETTER_Z)
                        || (value >= SMALL_LETTER_A && value <= SMALL_LETTER_Z)) {
                    builder.append((char)value);
                    value = reader.read();
                } else if (value == NEW_LINE) {
                    value = SPACE_SYMBOL;
                } else {
                    value = reader.read();
                }
            }
            fileReader.close();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(EXCEPTION_MESSAGE, e);
        }
        String[] sortedArray = searchNeededWords(builder.toString().split(SEPARATOR));
        Arrays.sort(sortedArray);
        return sortedArray;
    }

    private static String[] searchNeededWords(String[] words) {
        StringBuilder builder = new StringBuilder();
        for (String neededWords : words) {
            if (!neededWords.isEmpty()
                    && neededWords.toLowerCase().toCharArray()
                    [INDEX_OF_BEGIN_ARRAY] == STARTING_LETTER) {
                builder.append(neededWords).append(SEPARATOR);
            }
        }
        if (builder.isEmpty()) {
            return new String[] {};
        }
        return builder.toString().toLowerCase().split(SEPARATOR);
    }
}
