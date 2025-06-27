package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkWithFile {

    public static void main(String[] args) {
        getStatistic("apple.csv", "test.kek");
    }


    private static String SPLITERATOR = ",";
    private static Map<String, Integer> operations = new LinkedHashMap<>();
    public static void getStatistic(String fromFileName, String toFileName) {
        try (Stream<String> lines = Files.lines(Path.of(fromFileName))) {
           operations = lines.map(line -> line.split(SPLITERATOR))
                    .collect(Collectors.toMap(
                            e -> e[0],
                            e -> Integer.parseInt(e[1]),
                            Integer::sum));
           List<String> outputLines = new java.util.ArrayList<>(operations.entrySet().stream()
                   .map(entry -> String.format("%s,%s", entry.getKey(), entry.getValue()))
                   .toList());
           int result  = operations.values().stream().reduce((a, b) -> a - b).orElse(0);
           outputLines.add(String.format("result,%d", result));
           Files.write(Path.of(toFileName), outputLines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
