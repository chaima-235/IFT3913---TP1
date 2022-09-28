import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Service {
    private static Jls jls;
    private static Nvloc nvloc;
    private static Lsec lsec;

    public static List<String> createPath(File[] arrayFiles){
        var  currentPath = Paths.get("").toAbsolutePath().toString();
        var pathFileCsv = new File(currentPath);
        var listPathFile = new ArrayList<String>();
        for (File arrayFile : arrayFiles) {
            if (arrayFile.toString().contains("file.csv") || arrayFile.toString().contains("fileCsec.csv") || arrayFile.toString().contains("fileEgon.csv"))
                listPathFile.add(arrayFile.toString());
        }
        Collections.sort(listPathFile);
        return listPathFile ;
    }



    public static void main(String[] args) throws IOException {
        final File folder = new File("C:\\Users\\Chaima\\Desktop\\fuck\\src\\main\\java");
        var  pathCsv = Paths.get("").toAbsolutePath();
        var listePathsCsv = createPath(Objects.requireNonNull(new File(pathCsv.toString()).listFiles()));

       // Jls.csvFormat(folder);
        //System.out.println(Lsec.getPathFiles(folder));
        Lsec.creationFileCsec(folder, new File(listePathsCsv.get(0)));
//        Egon.creationFileEgon(folder, new File(listePathsCsv.get(1)));

    }
}
