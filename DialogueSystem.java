import java.io.*;

public class DialogueSystem {

    public static void main(String[] args) throws Exception {
        String path = "./dump.txt";

        BufferedReader bfro = new BufferedReader(
                new FileReader(path));

        Inputs.open();

        String character = null;
        String st;

        while ((st = bfro.readLine()) != null) {

            if (st.trim().isEmpty()) {
                character = null;
                continue;
            }

            if (character == null) {
                character = st;
                continue;
            }

            outputText(st, character);
            waitForEnter();
        }

        bfro.close();
        Inputs.close();
    }


    public static void outputText(String input, String character) {

        System.out.print("╔════ ");
        System.out.print(character);

        for (int a = 0; a < 58 - (4 + character.length()); a++) {
            System.out.print("═");
        }

        System.out.println("╗");

        String[] lines = input.split("\n");

        int lineCount = 0;

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

                lineCount++;
            }
        }

        // Make every textbox at least 2 lines tall
        while (lineCount < 2) {

            System.out.print("║");

            for (int j = 0; j < 59; j++) {
                System.out.print(" ");
            }

            System.out.println("║");

            lineCount++;
        }

        System.out.println(
                "╚═══════════════════════════════════════════════════════════╝"
        );
    }


    public static void waitForEnter() throws Exception {
        while (true) {
            int key = Inputs.readKey();
            if (key == 13) break;
        }
    }
}
