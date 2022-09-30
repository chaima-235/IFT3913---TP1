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
        final File folderComplique = new File("C:\\Users\\Chaima\\Desktop\\fuck\\src\\main\\java");
        final File folder = new File("C:\\Users\\Chaima\\Desktop\\jfreechart-master\\jfreechart-master\\src\\main\\java");
        final File folderCkjm = new File("C:\\Users\\Chaima\\Desktop\\ckjm-master\\src\\gr\\spinellis\\ckjm");
        var pathCsv = Paths.get("").toAbsolutePath();
        var listePathsCsv = createPath(Objects.requireNonNull(new File(pathCsv.toString()).listFiles()));
        Jls.filePartie0(folderComplique);
        Lcsec.lcsec(folderComplique, new File(listePathsCsv.get(0)));
        Egon.egon(folderComplique, 0.05);
    }
}
