import java.io.*;

public class DialogueSystem {
    public static void main(String[] args) throws Exception {
        String path = "./dump.txt";

        BufferedReader bfro = new BufferedReader(
                new FileReader(path));
        String character = null;
        String st;

        StringBuilder dialogue = new StringBuilder();

        while ((st = bfro.readLine()) != null) {
            if (st.trim().isEmpty()) {

                if (character != null && dialogue.length() > 0) {
                    outputText(dialogue.toString(), character);
                }
                character = null;
                dialogue.setLength(0);

            } else if (character == null) {
                character = st;
            } else {
                if (dialogue.length() > 0) {
                    dialogue.append("\n");
                }

                dialogue.append(st);
            }
        }

        if (character != null && dialogue.length() > 0) {
            outputText(dialogue.toString(), character);
        }

        bfro.close();
    }

    public static void outputText(String input, String character) {

        System.out.print("╔════ ");
        System.out.print(character);
        for(int a = 0; a < 58 - (4 + character.length()); a++)
          System.out.print("═");
        System.out.println("╗");

        String[] lines = input.split("\n");

        for (String line : lines) {

            int length = line.length();
            int repeats = Math.max(1, (int) Math.ceil(length / 59.0));

            for (int i = 0; i < repeats; i++) {

                System.out.print("║");

                int start = i * 59;
                int end = Math.min(start + 59, length);

                String part = line.substring(start, end);

                System.out.print(part);

                for (int j = part.length(); j < 59; j++) {
                    System.out.print(" ");
                }

                System.out.println("║");
            }
        }

        System.out.println(
                "╚═══════════════════════════════════════════════════════════╝"
        );
    }
}
