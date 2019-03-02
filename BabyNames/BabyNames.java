import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
 public void printNames()
 { FileResource fr = new FileResource();
   
     for(CSVRecord record : fr.getCSVParser(false))
     {
         int numBorn = Integer.parseInt(record.get(2));
         if(numBorn<=100)
         {
         System.out.println("Name "+ record.get(0) + 
                            "Gender "+record.get(1)+ 
                            "Number "+record.get(2));
     }
    }
 }
 public void totalBirths(FileResource fr)
 {
      int numBorn = 0;
      int totalBoys=0;
      int totalGirls=0;
      int girlsNames=0;
      int boysNames=0;
      int totalNames=0;
     for (CSVRecord rec : fr.getCSVParser(false))
     {
         int births=Integer.parseInt(rec.get(2));
         
         if(rec.get(1).equals("M"))
         {
            totalBoys+=births;
            boysNames=boysNames+1;
         }
         else
         {
             totalGirls+=births;
             girlsNames=girlsNames+1;
         }
         numBorn+=births;
         totalNames=totalNames+1;
         
     }
     System.out.println("Total births "+numBorn);
     System.out.println("Total boys "+totalBoys);
     System.out.println("Total girls "+totalGirls);
     System.out.println("Total boy's names "+boysNames);
     System.out.println("Total girl's names "+girlsNames);
     System.out.println("Total names "+totalNames);
    }
 public void testtotalBirths()
 {
 FileResource fr = new FileResource("FakeData/testing/yob2014short.csv");
 totalBirths(fr);
   }
   public int getRank(String year, String name, String gender)
   {
       return 0;
   }
}
