package Model;

public class Record {

    private String Name;
    private String Score;
    private String Date;

    public Record(String Name,String Score,String Date)
    {
        this.Date = Date;
        this.Name = Name;
        this.Score = Score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
