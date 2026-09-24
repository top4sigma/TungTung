public class DialogueSystem{
  public static void main(String[] args){
    outputText("Those who know. I am tung tung tung, I am the sigma of all the ligmas. Those who know about mango mustard.");
  }

  public static void outputText(String input){
    System.out.println("╔════ Triple T chan ════════════════════════════════════════╗");
    int length = input.length();
    int repeats = (int)Math.ceil(length / 59.0);
    for(int i = 0; i < repeats; i++){
      System.out.print("║");
      if((length - i * 59) >= 59)
      {
        System.out.print(input.substring(i  * 59, (i  * 59) + 59));
      }
      else{
        System.out.print(input.substring(i  * 59, (i  * 59) + (length - i * 59)));
        for(int j = 0; j < 59- (length - i * 59); j++)
        {
          System.out.print(" ");
        }
      }  
      System.out.println("║");
    }
    System.out.println("╚═══════════════════════════════════════════════════════════╝");
  }
}
