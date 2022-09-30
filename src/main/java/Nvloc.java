import java.io.*;

public class Nvloc {

    public static Integer nboflines(File pathFile) throws IOException {
        BufferedReader buffread = new BufferedReader(new FileReader(pathFile));
        int nbloc = 0;
        String line;
        while ((line = buffread.readLine()) != null) {
            if (!line.isBlank())
                nbloc++;
        }
        return nbloc;
    }
}
