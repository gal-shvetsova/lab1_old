package lab1;

public class Main {

    public static void main(String[] args) {
	    CSVParser csvParser = new CSVParser();
	    csvParser.readFile("/home/galya/ru/nsu/ccfit/shvetsova/lab1/src/lab1/in.txt");
	    csvParser.sort();
	    csvParser.writeFile();
    }
}
