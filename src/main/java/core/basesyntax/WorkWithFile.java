package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    private static final String SPLITERATOR = ",";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_POSITION = 0;
    private static final int OPERATION_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyResult = 0;
        int buyResult = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] split = line.split(SPLITERATOR);
                if (SUPPLY.equals(split[OPERATION_POSITION])) {
                    supplyResult += Integer.parseInt(split[OPERATION_VALUE]);
                } else if (BUY.equals(split[OPERATION_POSITION])) {
                    buyResult += Integer.parseInt(split[OPERATION_VALUE]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int generalResult = supplyResult - buyResult;
        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(SPLITERATOR).append(supplyResult).append(System.lineSeparator());
        sb.append(BUY).append(SPLITERATOR).append(buyResult).append(System.lineSeparator());
        sb.append(RESULT).append(SPLITERATOR).append(generalResult);
        try {
            Files.write(Path.of(toFileName), sb.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
