public class DialogueSystem{
  public static void main(String[] args){
    outputText("* yo what is up あなたは太っています hows it hanging in the crib", "Triple T chan");
  }

  public static void outputText(String input, String character){
    System.out.print("╔════ ");
    System.out.print(character);
    System.out.println(" ════════════════════════════════════════╗");
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
