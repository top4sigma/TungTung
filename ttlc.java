import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

public class ttlc {

    public static int choose(String[] options) throws Exception {

        if (options.length > 4) {
            throw new IllegalArgumentException(
                "A maximum of 4 options is allowed."
            );
        }

        int selected = 0;

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        terminal.puts(InfoCmp.Capability.enter_ca_mode);
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.puts(InfoCmp.Capability.cursor_home);
        terminal.flush();

        terminal.enterRawMode();

        try {
            while (true) {

                terminal.puts(InfoCmp.Capability.clear_screen);
                terminal.puts(InfoCmp.Capability.cursor_home);

                // Box
                terminal.writer().println(
                    "╔═══════════════════════════════════════════════════════════╗"
                );

                // TOP OPTION
                terminal.writer().print("║");

                if (options.length > 0) {

                    String option = options[0];

                    int spaces = (59 - option.length() - 1) / 2;

                    terminal.writer().print(" ".repeat(spaces));

                    if (selected == 0) {
                        terminal.writer().print("\033[7m");
                    }

                    terminal.writer().print(option);

                    if (selected == 0) {
                        terminal.writer().print("\033[27m");
                        terminal.writer().print("<");
                    }

                    terminal.writer().print(
                        " ".repeat(
                            Math.max(0,
                                59 - spaces - option.length()
                                - (selected == 0 ? 1 : 0)
                            )
                        )
                    );
                } else {
                    terminal.writer().print(" ".repeat(59));
                }

                terminal.writer().println("║");

                // Empty middle line
                terminal.writer().println(
                    "║                                                           ║"
                );

                // LEFT AND RIGHT OPTIONS
                terminal.writer().print("║");

                if (options.length > 2) {

                    String left = options[2];
                    String right = options.length > 3 ? options[3] : "";

                    // LEFT
                    if (selected == 2) {
                        terminal.writer().print("\033[7m");
                    }

                    terminal.writer().print(left);

                    if (selected == 2) {
                        terminal.writer().print("\033[27m");
                        terminal.writer().print("<");
                    }

                    // Space between left and right
                    terminal.writer().print(
                        " ".repeat(
                            Math.max(1,
                                59 - left.length()
                                - right.length()
                                - (selected == 2 ? 1 : 0)
                                - (selected == 3 ? 1 : 0)
                            )
                        )
                    );

                    // RIGHT
                    if (options.length > 3) {

                        if (selected == 3) {
                            terminal.writer().print("\033[7m");
                        }

                        terminal.writer().print(right);

                        if (selected == 3) {
                            terminal.writer().print("\033[27m");
                            terminal.writer().print("<");
                        }
                    }

                } else {
                    terminal.writer().print(" ".repeat(59));
                }

                terminal.writer().println("║");

                // Empty middle line
                terminal.writer().println(
                    "║                                                           ║"
                );

                // BOTTOM OPTION
                terminal.writer().print("║");

                if (options.length > 1) {

                    String option = options[1];

                    int spaces = (59 - option.length() - 1) / 2;

                    terminal.writer().print(" ".repeat(spaces));

                    if (selected == 1) {
                        terminal.writer().print("\033[7m");
                    }

                    terminal.writer().print(option);

                    if (selected == 1) {
                        terminal.writer().print("\033[27m");
                        terminal.writer().print("<");
                    }

                    terminal.writer().print(
                        " ".repeat(
                            Math.max(0,
                                59 - spaces - option.length()
                                - (selected == 1 ? 1 : 0)
                            )
                        )
                    );

                } else {
                    terminal.writer().print(" ".repeat(59));
                }

                terminal.writer().println("║");

                // Bottom
                terminal.writer().println(
                    "╚═══════════════════════════════════════════════════════════╝"
                );

                terminal.writer().flush();

                // Read input
                int key = terminal.reader().read();

                if (key == 27) {

                    int second = terminal.reader().read();
                    int third = terminal.reader().read();

                    // LEFT
                    if ((second == 'O' || second == '[') && third == 'D') {

                        if (selected == 0 || selected == 1) {
                            // Top/Bottom -> Left
                            if (options.length > 2) {
                                selected = 2;
                            }
                        }
                        else if (selected == 3) {
                            // Right -> Left
                            selected = 2;
                        }
                    }

                    // RIGHT
                    else if ((second == 'O' || second == '[') && third == 'C') {

                        if (selected == 0 || selected == 1) {
                            // Top/Bottom -> Right
                            if (options.length > 3) {
                                selected = 3;
                            }
                        }
                        else if (selected == 2) {
                            // Left -> Right
                            if (options.length > 3) {
                                selected = 3;
                            }
                        }
                    }

                    // UP
                    else if ((second == 'O' || second == '[') && third == 'A') {

                        if (selected == 1) {
                            // Bottom -> Top
                            selected = 0;
                        }
                        else if (selected == 2 || selected == 3) {
                            // Left/Right -> Top
                            selected = 0;
                        }
                    }

                    // DOWN
                    else if ((second == 'O' || second == '[') && third == 'B') {

                        if (selected == 0 && options.length > 1) {
                            // Top -> Bottom
                            selected = 1;
                        }
                        else if (selected == 2 || selected == 3) {
                            // Left/Right -> Bottom
                            if (options.length > 1) {
                                selected = 1;
                            }
                        }
                    }
                }

                // ENTER
                else if (key == 13 || key == 10) {
                    return selected;
                }
            }

        } finally {

            terminal.puts(InfoCmp.Capability.exit_ca_mode);
            terminal.flush();
            terminal.close();
        }
    }


    public static void main(String[] args) throws Exception {

        String[] options = {
            "Top",
            "Bottom",
            "Left",
            "Right"
        };

        int choice = choose(options);

        System.out.println("You chose: " + options[choice]);
    }
}