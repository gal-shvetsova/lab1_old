package lab1;


import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CSVParser {
    private Map<String, Integer> dictionary;

    public void readFile(String filePath) {
        int c;
        int temp = 0;
        String buffer = "";
        try {
            FileReader input = new FileReader(filePath);;
            dictionary = new HashMap<String, Integer>();
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
            sort();
            for (Map.Entry entry: dictionary.entrySet()) {
                output.write(entry.getKey() + "," + entry.getValue() + '\n');
            }
            output.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void sort(){
        Integer counter = 0;

        List<Map.Entry> list = dictionary.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());


        for (Map.Entry v : list){
            System.out.println(v.getKey() +" - " + v.getValue());
        }

    }

}
