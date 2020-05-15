import java.util.*;

/**
 *
 * @author DanrleiMucelin
 */
public class 2ProjectTask0{
    
    private static class GenreInfo{
        protected int reviews;
        protected int amazingCount;
        protected int nintendoCount;
        protected ArrayList<Double> score;
        protected ArrayList<String> title;
        protected double average;
        protected double StandardDeviation;
        
        
        private GenreInfo(){
            this.score = new ArrayList();
            this.title = new ArrayList();
        }
        
        private double average(){
            double sum =  0;
            for (int i = 0; i < score.size(); i++){
                sum += score.get(i);
            }
            return this.average = (sum / this.score.size());
        }
        
        private double standardDeviation(){
            double var = 0;
            for (int i = 0; i < score.size(); i++){
                var += ((this.score.get(i) - this.average) * (this.score.get(i) - this.average));
            }
            return this.StandardDeviation = Math.sqrt((var / this.reviews));
        }
        
        private double percAmazing(){
            double ac = this.amazingCount;
            double r = this.reviews;
            return (((ac*100)/(r*100))*100);
        }
        
        private String theBest(){
            double better = 0;
            String title = "";
            for (int i = 0; i < this.score.size(); i++){
                if (this.score.get(i) > better){
                    better = this.score.get(i);
                    title = this.title.get(i);
                }
            }
            return title;
        }
        
        private String theWorst(){
            double worst = 0;
            String title = "";
            for (int i = 0; i < (this.score.size()-1); i++){
                if (this.score.get(i) < this.score.get(i+1)){
                    worst = this.score.get(i);
                    title = this.title.get(i);
                }
            }
            return title;
        }
        
    }
    
    public static void main(String[] args) {
        
        Map<String, GenreInfo> map = new TreeMap<String, GenreInfo>();
        String line = "";
        
        SimpleReader file = new SimpleReader("game-reviews.csv");
        line = file.readLine();
        line = file.readLine();
        
        while (line != null){
            
            String[] col = line.split(";");
            
            String title = col[0];
            String plataform = col[1];
            String scorePhrase = col[2];
            double score = Double.parseDouble(col[3]);
            double scoreBest = Double.parseDouble(col[3]);
            double scoreWorst = Double.parseDouble(col[3]);
            String genre = col[4];
            char editorsChoice =   col[5].charAt(0);
            int releaseYear = Integer.parseInt(col[6]);
            
            
            if (!map.containsKey(genre)){
                GenreInfo g = new GenreInfo();
                g.reviews = 1;
                
                if (scorePhrase.contains("Amazing")){
                g.amazingCount = 1;
                }
                
                g.score.add(score);
                
                g.title.add(title);
                
                if (plataform.contains("Nintendo")){
                    g.nintendoCount++;
                }
                map.put(genre, g);
            }
            else{
                GenreInfo g = map.get(genre);
                g.reviews++;
                
                if (scorePhrase.contains("Amazing")){
                g.amazingCount++;
                }
                
                g.score.add(score);
                
                g.title.add(title);
                
                if (plataform.contains("Nintendo")){
                    g.nintendoCount++;
                }
                map.put(genre, g);
             }
            
            line = file.readLine();
        }
        file.close();
        
        int nintendo = 0;
        String genreNintendo = "";
        
        for (String k: map.keySet()){
            
            
            GenreInfo g = map.get(k);
            
            System.out.println("----------------------------------------------");
            System.out.println(" Genre:                "+k);
            System.out.println("");
            System.out.println(" Reviews:              "+g.reviews);
            int a = (int) g.percAmazing();
            System.out.println(" Amazing(%):           "+ a+"%");
            System.out.printf(" Average:              %.2f %n ", g.average());
            System.out.printf("Standard Deviation:   %.2f %n ", g.standardDeviation());
            System.out.println("The best game:        "+g.theBest());
            System.out.println(" The worst game:       "+g.theWorst());
            System.out.println("----------------------------------------------");
            System.out.println("");
            System.out.println("");
            
            
            if (g.nintendoCount > nintendo){
                nintendo = g.nintendoCount;
                genreNintendo = k;
            }
        }
        System.out.println("----------------------------------------------");
        System.out.println("The genre most common in ‘Nintendo’s family is :    "+genreNintendo);
        System.out.println("----------------------------------------------");
    
    }
    
}
