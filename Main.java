import java.util.Arrays;
import java.util.ArrayList;
public class Main {  
  private static String[] holidays = { "Thanksgiving", "Hanukkah", "Christmas", "Kwanzaa", "Fathers Day", "Lunar New Year", "Groundhog", "Valentines", "Presidents", "St Patrick",  "Ramadan", "Passover", "Good Friday", "Easter", "Cinco de Mayo", "Dilwale", "Rosh Hashana", "Eid", "Veterans"};;
  private static ArrayList<String> holidayList = new ArrayList<String>();
  private static int n = (int)((Math.random() * 4) + 5);
  
  public static void main(String[] args) {
    System.out.println("The list of holidays chosen: ");
    copy();
    sortDescending();
    System.out.println(holidayList);
    strip();
    int maxR = max() * 2;
    char[][] crossword = new char[150][maxR];
    tempFill(crossword);
    for (int i = 0; i < holidayList.get(0).length(); i++){
      crossword[maxR/2][(maxR - holidayList.get(0).length()) + i] = holidayList.get(0).charAt(i);
    }
    for (int i = 1; i < holidayList.size(); i++){
      addTo(holidayList.get(i), crossword);
    }
    print(crossword);
  }
  
  public static int generate(){
    return (int)(Math.random() * holidays.length);
  }
  public static void copy(){
    for (int i = 0; i < n; i++){
      String toAdd = (holidays[generate()]);
      if (holidayList.contains(toAdd)){
        i--;
        continue;
      }
      holidayList.add(toAdd);
    }
  }
  public static int max(){
    int sum = 0;
    int hold = 0;
    for (String a: holidayList){
      for(int i = 0; i < a.length(); i++){
        hold++;
      }
      if (hold > sum){
        sum = hold;
        hold = 0;
      }
    }
    return sum;
  }
  public static void strip(){
    for (int i = 0; i < holidayList.size(); i++){
      String hold = holidayList.get(i);
      hold = holidayList.get(i).replaceAll("\\s", "");
      holidayList.set(i, hold.toLowerCase());
    }
  } 
  public static void tempFill(char[][] arr){
    for (int i = 0; i < arr.length; i++){
      for (int j = 0; j < arr[i].length; j++){
        arr[i][j] = ' ';
      }
    }
  }
  public static void addTo(String word, char[][] arr){
    int x = 0;
    int y = 0; 
    int idx = 0;
    for (int j = 0; j < word.length(); j++){
      for(int a = 0; a < arr.length; a++){
        for(int b = 0; b < arr[a].length; b++){
          if (a == arr.length - 1 || b == arr[0].length - 1){
            continue;
          }
          
          if (arr[a][b] == word.charAt(j) && arr[a+1][b + 1] == ' ' && arr[a - 1][b - 1] == ' ' && arr[a - 1][b + 1] == ' ' && arr[a + 1][b - 1] == ' '){
            x = a;
            y = b;
            idx = j;
            break;
          }
        }
      }
    }
    if (arr[x - 1][y] == ' ' && arr[x + 1][y] == ' '){
      for (int i = 0; i < word.length(); i++){
        arr[x - idx + i][y] = word.charAt(i);
      }
    } else if (arr[x][y + 1] == ' ' && arr[x][y - 1] == ' '){
      for (int i = 0; i < word.length(); i++){
        arr[x][y - idx + i] = word.charAt(i);
      }
    } else{
      System.out.println(word + " didn't fit in the crossword.");
    }
  }
  public static void sortDescending() {
    for (int i = 0; i < holidayList.size(); i++) {
      for (int j = i + 1; j < holidayList.size(); j++) {
        if (holidayList.get(i).length() < holidayList.get(j).length()) {
          String temp = holidayList.get(i);
            holidayList.set(i, holidayList.get(j));
              holidayList.set(j, temp);
        }
      }
    }
  }
  public static void print(char[][] arr){
    for (char[] row: arr){
      for (char i: row){
        System.out.print(i);
      }
      System.out.println();
    }
  }
}