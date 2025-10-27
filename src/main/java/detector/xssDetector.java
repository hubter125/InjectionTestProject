package src.main.java.detector;
public class xssDetector implements Detector {
    protected int hits;
    private String name;
    public xssDetector(String name){
        this.name = name;
    }
    @Override
    public int evaluateLine(String line){
        if (line == null){
            return 0;
        }
        int score = 0;

        if (line.contains("<script>") || line.contains("<img src")){
            score +=1;
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
}