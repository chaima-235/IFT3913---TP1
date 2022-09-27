import java.io.File;
import java.io.IOException;

public class Service {
    private static Jls jls;
    private static Nvloc nvloc;
    private static Lsec lsec;



    public static void main(String[] args) throws IOException {
        final File folder = new File("C:\\Users\\Chaima\\Desktop\\ckjm-master\\src\\gr\\spinellis\\ckjm");
        final File folderFileCsv = new File("C:\\Users\\Chaima\\Desktop\\tp1\\src\\main\\resourcesCsv\\file.csv");
        final File folder1 = new File("src/main/resourcesCsv");

        // System.out.println(Lsec.findWord("je suis tres contente , on va reussir","tres"));
        //System.out.println(Lsec.principale(folder,folderFileCsv));
        Jls.csvFormat(folder);
        //System.out.println(Lsec.csecFinal(folder,folderFileCsv));
        System.out.println(Lsec.creationFileCsec(folder,folderFileCsv));
        //Lsec.readFileCsv(folderFileCsv);
        //System.out.println(Lsec.readFileCsv(new File(folderFileCsv.toURI())));

    }
}
