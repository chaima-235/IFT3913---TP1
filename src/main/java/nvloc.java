import org.apache.commons.io.FilenameUtils;
import java.io.*;
import java.util.Scanner;

public class nvloc {

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


    public static void main(String[] args) throws IOException {
        File file = new File("/Users/biancabica/Downloads/ckjm-master/src/gr/spinellis/ckjm/bilel.java");
        System.out.println(nboflines(file));
    }
}
