import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "country"
})

@XmlRootElement(name = "olympics")
public class Olympics{
    @XmlElement(name = "country", required = true)
    private ArrayList<Country> country;

    public ArrayList<Country> getCountry(){
        if(this.country == null){
            this.country = new ArrayList<>();
        }

        return this.country;
    }

    public void setCountry(ArrayList<Country> countries){
        this.country = countries;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "position",
            "name",
            "abbreviation",
            "gold",
            "silver",
            "bronze",
            "total",
            "athlete"
    })
    public static class Country{
        @XmlElement(name = "abbreviation", required = true)
        private String abbreviation;
        @XmlElement(name = "name", required = true)
        private String name;
        @XmlElement(name = "gold", required = true)
        private int gold;
        @XmlElement(name = "silver", required = true)
        private int silver;
        @XmlElement(name = "bronze", required = true)
        private int bronze;
        @XmlElement(name = "total", required = true)
        private int total;
        @XmlElement(name = "position", required = true)
        private int position;
        @XmlElement(name = "athlete", required = true)
        private ArrayList<Athlete> athlete;

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

        public int getTotal(){
            return this.total;
        }

        public int getPosition(){
            return this.position;
        }

        public ArrayList<Athlete> getAthlete(){
            if(this.athlete == null){
                this.athlete = new ArrayList<>();
            }

            return this.athlete;
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

        public void setTotal(){
            this.total = this.gold + this.silver + this.bronze;
        }

        public void setPosition(int position){
            this.position = position;
        }

        public void setAthlete(ArrayList<Athlete> athletes){
            this.athlete = athletes;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "medal",
                "name",
                "modality"
        })
        public static class Athlete{
            @XmlElement(name = "name", required = true)
            private String name;
            @XmlElement(name = "medal", required = true)
            private String medal;
            @XmlElement(name = "modality", required = true)
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
}
