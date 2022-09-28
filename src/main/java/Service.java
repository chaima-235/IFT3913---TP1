import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Service {
    private static Jls jls;
    private static Nvloc nvloc;
    private static Lsec lsec;



    public static void main(String[] args) throws IOException {
        final File folder = new File("/Users/biancabica/Downloads/ckjm-master/src/gr/spinellis/ckjm");
        final String currentPath = Paths.get("").toAbsolutePath().toString();
        var pathCsv = new File(currentPath);


    //  Egon.creationFileEgon(folder, pathEgon.listFiles()[2]);
       // Egon.filesNVLOC(folder,folderFileCsvCsec);
       // System.out.println(Egon.getCsec(folder,folderFileCsvCsec));
       // System.out.println(Egon.getNvloc(Lsec.getPathFiles(folder)));
       // System.out.println(Egon.getNvlocPourcentage(folder));
       // System.out.println(Egon.getCsecPourcentage(folder,folderFileCsvCsec));


      //  System.out.println(Lsec.findWord("je suis tres contente , on va reussir","tres"));
       // System.out.println(Lsec.principale(folder,folderFileCsv));
       Jls.csvFormat(folder);
        //System.out.println(Lsec.csecFinal(folder,folderFileCsv));
       System.out.println(Lsec.creationFileCsec(folder, Objects.requireNonNull(pathCsv.listFiles())[1]));

        //Lsec.readFileCsv(folderFileCsv);
       // System.out.println(Lsec.readFileCsv(new File(folderFileCsv.toURI())));
        //Egon.filesNVLOC(new File("C:\\Users\\Chaima\\Desktop\\ckjm-master\\src\\gr\\spinellis\\ckjm"));
    }
}
