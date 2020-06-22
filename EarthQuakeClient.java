import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry le : quakeData) {
            double currmag = le.getMagnitude();
            if (currmag>magMin) {
                answer.add(le);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList <QuakeEntry> quakeData, double minDepth,double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry le : quakeData) {
            double currmag = le.getDepth();
            if (currmag>minDepth && currmag<maxDepth) {
                answer.add(le);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList <QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry le : quakeData) {
            String title  = le.getInfo();
            if (where.equals("start")) {
            if (title.startsWith(phrase)) {
                answer.add(le);
            }
          } 
          if (where.equals("end")) {
            if (title.endsWith(phrase)) {
                answer.add(le);
            }
          } 
          if (where.equals("any")) {
            if (title.contains(phrase)) {
                answer.add(le);
            }
          } 
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        String source = "nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> answer = filterByPhrase(list,"any","Can");
        System.out.println("Filtering by title:");
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }
        
        System.out.println("The quaekes with title are : " + answer.size());
    }
    
    public void quakesOfDepth() {
        String source = "nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> listbig  = filterByDepth(list,-4000.0,-2000.0 );
        System.out.println("Filtering by depth:");
        for(QuakeEntry qe : listbig){
            System.out.println(qe);
        }
        
        System.out.println("Depth quaekes are : " + listbig.size());
        
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry le : quakeData) {
            Location curloc = le.getLocation();
            if (distMax > curloc.distanceTo(from)) {
            answer.add(le);
            }
            
        }
        // TODO
        for (QuakeEntry le : answer) {
            //System.out.println(le);

       }
       return answer;
            }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> listbig  = filterByMagnitude(list,5.0);
        
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("read data for big  "+listbig.size()+" quakes");
        for (QuakeEntry le : listbig) {
            System.out.println(le);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        


        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> listdistance = filterByDistanceFrom(list,1000000,city);
        System.out.println("Filtering by distance:");
        for(QuakeEntry qe : listdistance){
            System.out.println(qe.getLocation().distanceTo(city)+ " " + qe.getInfo());
        }
        
        System.out.println(" Near quaekes are : " + listdistance.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
