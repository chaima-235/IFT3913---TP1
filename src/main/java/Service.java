import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Service {

    public static List<String> createPath(File[] arrayFiles) {
        var listPathFile = new ArrayList<String>();
        for (File arrayFile : arrayFiles) {
            if (arrayFile.toString().contains("file.csv") || arrayFile.toString().contains("fileCsec.csv") || arrayFile.toString().contains("fileEgon.csv"))
                listPathFile.add(arrayFile.toString());
        }
        Collections.sort(listPathFile);
        return listPathFile;
    }


    public static void main(String[] args) throws IOException {
        final File folder = new File("C:\\Users\\Chaima\\Desktop\\jfreechart-master\\jfreechart-master\\src\\main\\java");
        var pathCsv = Paths.get("").toAbsolutePath();
        var listePathsCsv = createPath(Objects.requireNonNull(new File(pathCsv.toString()).listFiles()));
        Jls.filePartie0(folder);
        Lcsec.lcsec(folder, new File(listePathsCsv.get(0)));
        Egon.egon(folder, 0.05);
    }
}
