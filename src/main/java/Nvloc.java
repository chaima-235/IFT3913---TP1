import java.io.*;

public class Nvloc {

    public static int nboflines(File file) throws IOException {
        BufferedReader buffread = new BufferedReader(new FileReader(file));
        int nbloc = 0;
        String line;
        while ((line = buffread.readLine()) != null) {
            if (!line.isBlank())
                nbloc++;
        }
        return nbloc;
    }
}
