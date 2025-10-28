package src.main.java.detector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class sqlDetector implements Detector {
    protected int hits;
    private String name;
    private ArrayList<String> signatures;
    public sqlDetector(String name){
        this.name = name;
        this.signatures = new ArrayList<>();
    }
    @Override
    public int evaluateLine(String line){
        if (line == null) {
            return 0;
        }
        int score = 0;

        for (String l:signatures){
            if (line.contains(l)){
                score +=1;
            }
        }
        hits += score;
        return score;
    }

    public int getTotalHits(){
        return hits;
    }
    
    public String getName(){
        return name;
    }
    public ArrayList<String> readList(String filepath) {
        signatures.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    signatures.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading signatures: " + e.getMessage());
        }
        return signatures;

    }
}
