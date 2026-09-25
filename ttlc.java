import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

public class ttlc {

    public static int choose(String[] options) throws Exception {

        int selected = 0;

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        // Enter alternate screen
        terminal.puts(InfoCmp.Capability.enter_ca_mode);
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.puts(InfoCmp.Capability.cursor_home);
        terminal.flush();

        terminal.enterRawMode();

        try {
            while (true) {

                // Clear the alternate screen
                terminal.puts(InfoCmp.Capability.clear_screen);
                terminal.puts(InfoCmp.Capability.cursor_home);

                // Find the longest option
                int maxLength = 0;

                for (String option : options) {
                    if (option.length() > maxLength) {
                        maxLength = option.length();
                    }
                }

                // Print options
                for (int i = 0; i < options.length; i++) {

                    // Selection highlight (reverse video)
                    if (i == selected) {
                        terminal.writer().print("\033[7m");
                    }

                    terminal.writer().print(options[i]);

                    // Space so every option gets the same width
                    for (int j = options[i].length(); j < maxLength + 2; j++) {
                        terminal.writer().print(" ");
                    }

                    // Selection marker
                    if (i == selected) {
                        terminal.writer().print("<");
                        terminal.writer().print("\033[27m");
                    } else {
                        terminal.writer().print(" ");
                    }

                    // Space between options
                    terminal.writer().print("    ");
                }

                terminal.writer().flush();

                int key = terminal.reader().read();

                // Arrow key
                if (key == 27) {

                    int second = terminal.reader().read();
                    int third = terminal.reader().read();

                    // LEFT: ESC O D (SS3) or ESC [ D (CSI)
                    if (second == 'O' && third == 'D') {
                        if (selected > 0) selected--;
                    } else if (second == '[' && third == 'D') {
                        if (selected > 0) selected--;
                    }

                    // RIGHT: ESC O C (SS3) or ESC [ C (CSI)
                    else if (second == 'O' && third == 'C') {
                        if (selected < options.length - 1) selected++;
                    } else if (second == '[' && third == 'C') {
                        if (selected < options.length - 1) selected++;
                    }
                }

                // ENTER
                else if (key == 13) {
                    return selected;
                }
            }

        } finally {

            // Return to normal terminal
            terminal.puts(InfoCmp.Capability.exit_ca_mode);
            terminal.flush();

            terminal.close();
        }
    }


    public static void main(String[] args) throws Exception {

        String[] options = {
            "Yes",
            "No"
        };

        int choice = choose(options);

        System.out.println();
        System.out.println("You chose: " + options[choice]);
    }
}
