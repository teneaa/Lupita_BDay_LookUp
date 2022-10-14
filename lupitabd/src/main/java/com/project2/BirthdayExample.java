package com.project2;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BirthdayExample {

  //
  // Func: ReadJSONFile
  // Desc: Reads a json file storing an array and returns an object
  // that can be iterated over
  //
  public static JSONArray readJSONArrayFile(String fileName) {
    //
    // read the birthday.json file and iterate over it
    //

    // JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

    JSONArray birthdayList = null;

    try (FileReader reader = new FileReader(fileName)) {
      // Read JSON file
      Object obj = jsonParser.parse(reader);

      birthdayList = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return birthdayList;
  }

  public static void main(final String[] args) {

    //Bool to assess whether a birthday match can be extracted from data file
    Boolean isValid;

    // how to read user input from keyboard
    System.out.println("Reading user input into a string");
      
    // get user input
    Scanner input = new Scanner(System.in);

    do {  // Repeat until HashMap returns a birthday  
      System.out.print("Enter a name:");
      String lupitaFriend = input.nextLine();

      // print user input
      System.out.println("Searching for: " + lupitaFriend + "\n\n");

      // reads a json data file
      String pathToFile = "/Users/teneaallen/Desktop/Fall 2022 /Java Programming/Assignments/Week_7/Lupita_BDay_LookUp/lupitabd/src/main/java/com/project2/birthdayOnlyForTesting.json";

      JSONArray jsonData = readJSONArrayFile(pathToFile);

      // Create HashMap to retrieve specific info from JSON object
      HashMap<String, String> lupitaHashMap = new HashMap<>();

      // loop over list
      JSONObject obj;
      for (Integer i = 0; i < jsonData.size(); i++) {
        // parse the object and pull out the name and birthday

        obj = (JSONObject) jsonData.get(i);
        String birthday;

        String name = (String) obj.get("name");
        birthday = (String) obj.get("birthday");

        // Put object data into HashMap
        lupitaHashMap.put((name), (birthday)); 
      }

      // Conditions for printing and repeating user input
      if (lupitaHashMap.get(lupitaFriend) == null) {
        System.out.println("That name was not found, try again");
        isValid = false;
      } else {
        System.out.println(lupitaFriend + "'s birthday is: " + lupitaHashMap.get(lupitaFriend) + "\n\n" + lupitaHashMap);
        isValid = true;
      }
    } while( (isValid == false) );
    
    // close the scanner
    input.close();
  }
}
