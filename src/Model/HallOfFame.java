package Model;
import com.sun.xml.internal.bind.v2.runtime.Name;

import java.io.*;
import java.io.FileReader;
import java.io.File;
import java.util.*;

public class HallOfFame
{

    private String FILE_NAME = "HallOfFame.csv";


    public HallOfFame()
    {

    }

    public ArrayList<Record> showscores()
    {

        char [] a = new char[100];
        String[] lines;
        String[] details;
        ArrayList<Record> recordsarraylost = new ArrayList<>();
        try {

            FileReader fr = new FileReader(FILE_NAME);
        StringBuffer stringBuffer = new StringBuffer();
        int numCharsRead;
        char[] charArray = new char[1024];
        while ((numCharsRead = fr.read(charArray)) > 0) {
            stringBuffer.append(charArray, 0, numCharsRead);
        }
        fr.close();
        lines = stringBuffer.toString().split("\n");

            for(String line : lines) {
                details = line.split(",");

                recordsarraylost.add(new Record(details[1],details[2],details[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return recordsarraylost;
    }


    public void CheckLocation(String name,String score,String date)
    {

        char [] a = new char[100];
        String[] lines;
        String[] details;
        try {
            FileReader fr = new FileReader(FILE_NAME);
            StringBuffer stringBuffer = new StringBuffer();
            int numCharsRead;
            char[] charArray = new char[1024];
            while ((numCharsRead = fr.read(charArray)) > 0) {
                stringBuffer.append(charArray, 0, numCharsRead);
            }
            fr.close();
            lines = stringBuffer.toString().split("\n");

            for(String line : lines)
            {
                details = line.split(",");
                if(Integer.parseInt(details[2]) < Integer.parseInt(score) || details[2] == " ")
                {
                    UpdateFile(Integer.parseInt(details[0]),name,score,date,lines);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void UpdateFile(int Location,String name,String score,String date,String[] lines)
    {

        //String templayer;
        //List<String> recordsstringlist = Arrays.asList(lines);
        List<Record> records = new ArrayList<>();
        String[] recordarray;


        for(int i=0;i<lines.length;i++)
        {
            recordarray = lines[i].split(",");
            records.add(new Record(recordarray[1],recordarray[2],recordarray[3]));

        }

        records.add(new Record(name,score,date));

        Comparator<Record> compareByScore = (Record r1, Record r2) -> r1.getScore().compareTo( r2.getScore() );

        Collections.sort(records,compareByScore);


        try {
            FileWriter oFile = new FileWriter(FILE_NAME,false);
            int i=0;


            oFile.append("Rank");
            oFile.append(',');
            oFile.append("name");
            oFile.append(',');
            oFile.append("Score");
            oFile.append(',');
            oFile.append("Date");
            oFile.append(',');
            oFile.append('\n');


            for(Record rec : records)
            {
                oFile.append(i+1+"");
                oFile.append(',');
                oFile.append(rec.getName());
                oFile.append(',');
                oFile.append(rec.getScore());
                oFile.append(',');
                oFile.append(rec.getDate());
                oFile.append(',');
                oFile.append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createfile()
    {
        File scoresfile = new File(FILE_NAME);



        if(!scoresfile.exists())
        {
            try {
                scoresfile.createNewFile();
                FileWriter oFile = new FileWriter(FILE_NAME);

                oFile.append("Rank");
                oFile.append(',');
                oFile.append("name");
                oFile.append(',');
                oFile.append("Score");
                oFile.append(',');
                oFile.append("Date");
                oFile.append(',');
                oFile.append('\n');


                for(int i=0;i<10;i++)
                {
                    oFile.append(i+1+"");
                    oFile.append(',');
                    for(int j=1;j<4;j++)
                    {
                        oFile.append(" ");
                        oFile.append(',');
                    }

                    oFile.append('\n');
                }

                oFile.flush();
                oFile.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }





    //read,write,show

}