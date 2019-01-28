package lab1;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVParser {
    private Map<String, Integer> dictionary;
    private double count = 0;

    public void readFile(String filePath) {
        int c;
        int temp = 0;
        String buffer = "";
        try {
            FileReader input = new FileReader(filePath);
            dictionary = new HashMap<>();
            while ((c = input.read()) != -1) {
                if (Character.isLetterOrDigit(c))
                    buffer = buffer + (char) c;
                else {
                    if (dictionary.containsKey(buffer)) {
                        temp = dictionary.get(buffer);
                    }
                    dictionary.put(buffer, temp + 1);
                    buffer = "";
                    temp = 0;
                    count++;
                }
            }
            input.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeFile() {
        try {
            FileWriter output = new FileWriter("/home/galya/ru/nsu/ccfit/shvetsova/lab1/src/lab1/out.txt");
            List<Map.Entry> v = sort();
            double percentage;
            for (Map.Entry entry : v) {
                percentage = Double.parseDouble(entry.getValue().toString());
                percentage = percentage / count * 100.0;
                output.write(entry.getKey() + "," + entry.getValue() + "," + percentage + '\n');
            }
            output.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Map.Entry> sort() {
        return dictionary.entrySet().stream().
                sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
    }

}
