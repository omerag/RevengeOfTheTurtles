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


    public void checkifhighscore(String score)
    {
        String name = GetPlayerNameController();
        hof.CheckLocation(score,name);
    }

    public String GetPlayerNameController()
    {
        NameWindow nw = new NameWindow(400,200,"Enter Name");

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
