package Controller;

import Model.Record;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.HallOfFame;
import View.NameWindow;

public class HOFController {

    private HallOfFame hof;

    public HOFController()
    {
        hof = new HallOfFame();
    }


    public ArrayList<Record> getScores()
    {
        ArrayList<Record> hofscores = hof.showscores();
        return hofscores;

    }


    public void CreateHOFfile()
    {
        hof.createfile();
    }


    public void checkifhighscore(String score,int won)
    {
        String name = GetPlayerNameController(won);
        hof.CheckLocation(score,name);
    }

    public String GetPlayerNameController(int won)
    {
        NameWindow nw = new NameWindow(600,200,"Enter Name",won);

        while(nw.getPlayerName() == null)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return nw.getPlayerName();
    }


}
