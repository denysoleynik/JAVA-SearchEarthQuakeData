import java.util.*;
/**
 * Write a description of class LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        int indexOfLargest = indexOfLargest(list);
        ArrayList<QuakeEntry> largest = getLargest(list,50);
        System.out.println("read data for "+list.size());
        for (int i=0; i<list.size(); i++) {
        System.out.println(list.get(i));
        
      }
      System.out.println("");
      System.out.println(" The Largest ARE : ");
      for (int i=0; i<largest.size(); i++) {
        System.out.println(largest.get(i));        
      }
        System.out.println("");
        System.out.println(" The biggest magnitude was : " + list.get(indexOfLargest));
}

public int indexOfLargest (ArrayList<QuakeEntry> data) {
    int indexOfLargest = 0;
    double maxmag = 0;
    for (int i=0; i<data.size(); i++) {
        QuakeEntry qe = data.get(i);
        double curmag = qe.getMagnitude();
        if (curmag>maxmag) {
            maxmag = curmag;
            indexOfLargest = i;
        
        }
    }
return indexOfLargest;
}

public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
    ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
    ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    for (int i=0; i<howMany; i++) {
        answer.add(copy.get(indexOfLargest(copy)));
        copy.remove(copy.get(indexOfLargest(copy)));
        }
        return answer;
    }
}
