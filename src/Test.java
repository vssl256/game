import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

class Test {
    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.terminal();
        NonBlockingReader reader = terminal.reader();
        while (true) {
            char ch = (char)reader.read();
            System.out.println(ch);
        }
    }
}