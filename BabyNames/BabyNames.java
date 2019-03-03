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
   public int getRank(int year, String name, String gender)
   {
       int rank =1;
       FileResource fr = new FileResource("FakeData/testing/"+"yob"+year+"short.csv");
       for (CSVRecord rec : fr.getCSVParser(false))
       {
           if(rec.get(1).equals(gender))
           {
           if(rec.get(0).equals(name))
           {
               return rank;
           }
           else{
               rank = rank +1;
            }
           
       }
     
    }
       return -1;
       //This method could have another implementation
   }
   public void testGetRank()
   {
       int rank=0;
       String name="Emma";
       String gender="F";
       int year = 2014;
       rank = getRank(year,name,gender);
       System.out.println("Rank of "+name+" is "+rank);
   }
   public String getName(int year,int rank,String gender )
   {
      FileResource fr = new FileResource("FakeData/testing/"+"yob"+year+"short.csv");
      int count = 0;
    for(CSVRecord rec:fr.getCSVParser(false))
    {  
      
      if(rec.get(1).equals(gender))
    { 
        count=count+1;
        if(count==rank)
        {
            return rec.get(0);
        }
        
    }

   }
   return "NO NAME";
}
  public void testGetName()
  {
      int year=2014;
      int rank=2;
      String gender="F";
      String name = getName( year, rank, gender );
      System.out.println("The name at rank "+rank+" is "+name);
  }
  public void whatIsNameInYear(String name, String gender, int year, int newYear)
  {
      int rank= getRank(year,name,gender);
      String nameNewYear= getName(newYear,rank,gender);
      System.out.println(name+ " born in " + year +
                         " would be " + nameNewYear+ " if he/she was born in "
                         + newYear);
  }
  public void testWhatIsNameInYear()
  {
      String name="Isabella";
      int year = 2012;
      String gender ="F";
      int newYear=2014;
      whatIsNameInYear(name,gender,year,newYear);
  }
  public int yearOfHighestRank(String name, String gender)
  {
      DirectoryResource dr = new DirectoryResource();
    
      String year = "";
      int yeari=0;
      int rankTemp=-1;
      for(File f : dr.selectedFiles())
      {
       FileResource fr = new FileResource(f);
       int count=0;
       int rank=1;
       for (CSVRecord rec : fr.getCSVParser(false))
       {     
           if(rec.get(1).equals(gender) && count==0 && rec.get(0).equals(name))
           {
               System.out.println("Rank on file is "+rank); 
               count=1;
           }
           else{
               rank = rank +1;
            }
        
}
      if(rankTemp==-1)
      {
          rankTemp=rank+1;
      }
      if(rank<rankTemp)
       {
           rankTemp=rank;
           year= f.getName();
           System.out.println(year);
       }

}
 System.out.println(year.substring(3,7));
 yeari=Integer.parseInt(year.substring(3,7));
      if(yeari>0)
      {
          return yeari;
      }
      else{
    return -1;
}
//There is a bug with this method, it returns value for both male and female genders
}



  public void testYearOfHighestRank()
  {
      String name="Isabella";
      String gender="F";
      int x =yearOfHighestRank( name, gender);
      System.out.println(name+ " is ranked the highest in the year "+x);
      
    }
    
}

