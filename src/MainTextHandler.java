import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTextHandler {
    public static void main(String[] args) throws IOException {

        File file = new File("someText.txt");
        System.out.println(file.length() / Math.pow(1024,2)  + " Mb");

        List<String> lines;

        try(Stream<String> linesStream = Files.lines(Paths.get(String.valueOf(file)))){
            lines = linesStream
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.toList());
        }

        lines = wordsSorter(symbolsRemover(lines));

        lines.forEach(System.out::println);

    }

    public static List<String> symbolsRemover(List<String> list){
        List<String> clearedLines = new ArrayList<>();
        int counter = 0;

        for (String line : list){
            String[] words = line.split(" ");
            StringBuilder lineBuilder = new StringBuilder();
            for(String word : words){

                if(word.isEmpty()){
                    continue;
                }
                String clearedWord = wordCleaner(word);
                lineBuilder.append(clearedWord).append(" ");
                counter++;
            }
            clearedLines.add(lineBuilder.toString().trim());
        }
        System.out.println("Total words: " + counter);

        return clearedLines;
    }

    public static String wordCleaner(String word) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char symbol = word.charAt(i);

            if (Character.isLetter(symbol)){
                stringBuilder.append(symbol);
            }
        }
        return stringBuilder.toString();
    }

    public static List<String> wordsSorter(List<String> list){
        List<String> sortedLines = new ArrayList<>();

        for (String line : list){
            String[] words = line.toLowerCase().split(" ");
            StringBuilder lineBuilder = new StringBuilder();
            Set<String> sorted = new TreeSet<>();

            for(String word : words){

                if(word.isEmpty()){
                    continue;
                }
                if(word.length()>5){
                    sorted.add(word);
                }
            }
            for(String word : sorted){
                lineBuilder.append(word).append(" ");
            }
            sortedLines.add(lineBuilder.toString().trim());
       }
        return sortedLines;
    }


}
