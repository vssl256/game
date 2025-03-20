import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

public class Movement {
    static Terminal terminal;
    static NonBlockingReader reader;
    
    public static void Terminal() throws IOException {
        if (terminal == null) {
            terminal = TerminalBuilder.builder().build();
            reader = terminal.reader();
        }
    }

    public static int Start() throws IOException {
        Terminal();
        return reader.read();
    }
}