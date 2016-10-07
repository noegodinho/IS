import java.util.ArrayList;

public class Olympics{

    public class Country{
        private String abbreviation;
        private String name;
        private int gold;
        private int silver;
        private int bronze;
        private int position;
        private ArrayList<Athlete> athletes;

        public String getAbbreviation(){
            return this.abbreviation;
        }

        public String getName(){
            return this.name;
        }

        public int getGold(){
            return this.gold;
        }

        public int getSilver(){
            return this.silver;
        }

        public int getBronze(){
            return this.bronze;
        }

        public int getPosition(){
            return this.position;
        }

        public ArrayList<Athlete> getAthletes(){
            return this.athletes;
        }

        public void setAbbreviation(String abbreviation){
            this.abbreviation = abbreviation;
        }

        public void setName(String name){
            this.name = name;
        }

        public void setGold(int gold){
            this.gold = gold;
        }

        public void setSilver(int silver){
            this.silver = silver;
        }

        public void setBronze(int bronze){
            this.bronze = bronze;
        }

        public void setPosition(int position){
            this.position = position;
        }

        public void setAthletes(ArrayList<Athlete> athletes){
            this.athletes = athletes;
        }
    }


    public static class Athlete{
        private String name;
        private String medal;
        private String modality;

        public String getMedal(){
            return this.medal;
        }

        public String getModality(){
            return this.modality;
        }

        public String getName(){
            return this.name;
        }

        public void setMedal(String medal){
            this.medal = medal;
        }

        public void setModality(String modality){
            this.modality = modality;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
