package Model;

import Model.*;

import java.util.Comparator;

public class RatingComparator implements Comparator<Record> {
    @Override
    public int compare(Record obj1, Record obj2) {
        return (Integer.parseInt(obj1.getScore())<Integer.parseInt(obj2.getScore())) ? -1 : (Integer.parseInt(obj1.getScore())> Integer.parseInt(obj2.getScore())) ? 1 : 0;
    }
}