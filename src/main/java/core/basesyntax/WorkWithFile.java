package core.basesyntax;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class WorkWithFile {
    private final static String STRING_SPLITTER = ",";
    private final static int NAME_INDEX = 0;
    private final static int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        ArrayList<Pair> allPairs = readFromCSV(Path.of(fromFileName));
        Collections.sort(allPairs);
        StringBuilder resultTable = new StringBuilder();
        int result = 0;
        for (Pair pair : allPairs) {
            resultTable.append(pair.get_name()).append(STRING_SPLITTER).append(pair.get_ammount()).append(System.lineSeparator());
            if (result == 0) {
                result = pair.get_ammount();
            } else {
                result -= pair.get_ammount();
            }
        }
        resultTable.append("result").append(STRING_SPLITTER).append(result);
        writeToCSVfile(toFileName, resultTable.toString());
    }

    public ArrayList<Pair> readFromCSV(Path fromFile) {
        List<String> allLines;
        ArrayList<Pair> pairs = new ArrayList<>();
        try {
            allLines = Files.readAllLines(fromFile);
            for (int i = 0; i < allLines.size(); i++) {
                String[] splitString = allLines.get(i).split(STRING_SPLITTER);//read all lines
                if(pairs.size() == 0) { //if list clear
                    pairs.add(new Pair(splitString[NAME_INDEX], Integer.parseInt(splitString[AMOUNT_INDEX]))); //add pair
                } else { //if list have pairs
                    for(int j = 0; j<pairs.size(); j++) {
                        if(pairs.get(j).equalByName(splitString[NAME_INDEX])) { //if this name already in list
                            pairs.get(j).addAmmount(Integer.parseInt(splitString[AMOUNT_INDEX])); //just add amount
                            break; //no longer need to check the array
                        } else { //if this name not in list
                            if(j == pairs.size()-1) { //and we check all list
                                pairs.add(new Pair(splitString[NAME_INDEX], Integer.parseInt(splitString[AMOUNT_INDEX]))); //add new
                                break;
                            }
                        }
                    }
                }
            }
            return pairs;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from this file: " + fromFile, e);
        }
    }

    public void writeToCSVfile(String tofile, String resultTable) {
        try {
            Files.write(Path.of(tofile), resultTable.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data in this file: " + tofile, e);
        }
    }
}
