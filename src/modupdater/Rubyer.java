package modupdater;

import java.io.IOException;

public class Rubyer {

    public static void exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
