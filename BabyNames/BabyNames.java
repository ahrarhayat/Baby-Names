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
 FileResource fr = new FileResource();
 totalBirths(fr);
   }
   public int getRank(int year, String name, String gender)
   {
       int rank =1;
       FileResource fr = new FileResource();
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
       String name="Frank";
       String gender="M";
       int year = 2014;
       rank = getRank(year,name,gender);
       System.out.println("Rank of "+name+" is "+rank);
   }
   public String getName(int year,int rank,String gender )
   {
      FileResource fr = new FileResource("us_babynames_by_year/"+"yob"+year+".csv");
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
      int year=1982;
      int rank=450;
      String gender="M";
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
      String name="Owen";
      int year = 1974;
      String gender ="M";
      int newYear=2014;
      whatIsNameInYear(name,gender,year,newYear);
  }
  public int yearOfHighestRank(String name, String gender)
  {
      DirectoryResource dr = new DirectoryResource();
      String year = "";
      String year2="";
      int yearI=0;
      int yearI2=0;
      int rankTemp=-1;
      int indicator=0;
      for(File f : dr.selectedFiles())
      {
       FileResource fr = new FileResource(f);
       int count=0;
       int rank=1;
       for (CSVRecord rec : fr.getCSVParser(false))
       {     
           if(rec.get(1).equals(gender) && count==0 )
           {
               if(rec.get(0).equals(name)){
               count=1;
               indicator=1;
           }
           else{
               rank = rank +1;
            }
        }
}
      if(rankTemp==-1)
      {
          rankTemp=rank+1;
      }
      
      if(rank<rankTemp )
       {
           rankTemp=rank;
           year= f.getName();
       }
       if(rank==rankTemp)
       {
           year2=f.getName();
           
       }
       

}

 yearI=Integer.parseInt(year.substring(3,7));
 yearI2=Integer.parseInt(year2.substring(3,7));
      if(yearI>0 && indicator == 1 )
      {
          return yearI ;
      }
      else{
    return -1;
}

}



  public void testYearOfHighestRank()
  {
      String name="Mich";
      String gender="M";
      int x =yearOfHighestRank( name, gender);
      System.out.println(name+ " is ranked the highest in the year "+x);
      
    }
   public double getAverageRank(String name,String gender)
   {
       DirectoryResource dr = new DirectoryResource();
       double total=0;
       double totalRank=0;
       int indicator=0;
       double rank =1;
       for(File f : dr.selectedFiles())
      {
       int count=0;
       rank =1;
       FileResource fr = new FileResource(f);
       
       for(CSVRecord rec : fr.getCSVParser(false))
       {
            if(rec.get(1).equals(gender) && count == 0)
            {
               if(rec.get(0).equals(name))
               {
                   total=total+1;
                   count=1;
                   indicator=1;
               }
               else{
                   rank=rank+1;
               }
              
        }
      
        }
       totalRank=totalRank+rank;
         
      
    }

   if(indicator==1){
   double averageRank = totalRank/total;
   return averageRank;
}
else 
{
    return -1;
}
}
public void testGetAverageRank()
{
    String name="Robert";
    String gender="M";
    double averageRank = getAverageRank(name,gender);
    System.out.println("Average rank for the name "+ name+ " is "+averageRank);
}
public int getTotalBirthsRankedHigher(int year , String name, String gender)
{
    FileResource fr = new FileResource("us_babynames_by_year/"+"yob"+year+".csv");
    int rank = getRank(year,name,gender);
    int rankOther=rank-1;
    int totalBirths=0;
    int births=0;
    int indicator=0;
     for(CSVRecord rec : fr.getCSVParser(false))
     {   
         if(rec.get(1).equals(gender))
         {
              
              if(rec.get(0).equals(name))
               {  indicator=1;
                  break;
               }
               else{
                   births=Integer.parseInt(rec.get(2));
               }
               if(totalBirths==0)
               {
                   totalBirths=births;
               }
               else{
                   totalBirths=totalBirths+births;
                }
         }
         
         
     }
     if(indicator==0)
     {
    return -1;}
    else
{
    return totalBirths;
}
}
public void testGetTotalBirthsRankedHigher()
{
    int year = 1990;
    String name="Drew";
    String gender="M";
    int totalBirths= getTotalBirthsRankedHigher(year,name,gender);
    System.out.println("There were "+totalBirths+" babies that were ranked higher than babies names with " 
                         +name);
}

}
//Some names may appear in some years but not in others. Make sure your code correctly handles the case where a name appears in some years but not in others.
