import java.io.File;
import java.io.IOException;

public class Service {
    private static Jls jls;
    private static Nvloc nvloc;
    private static Lsec lsec;



    public static void main(String[] args) throws IOException {
        final File folder = new File("/Users/biancabica/Downloads/ckjm-master/src/gr/spinellis/ckjm");
        final File folderFileCsv = new File("/Users/biancabica/IFT3913/IFT3913---TP1/src/main/resourcesCsv/file.csv");
        final File folderFileCsvCsec = new File("/Users/biancabica/IFT3913/IFT3913---TP1/src/main/resourcesCsv/fileCsec.csv");
        final File folder1 = new File("/Users/biancabica/IFT3913/IFT3913---TP1/src/main/resourcesCsv");

        Egon.creationFileEgon(folder,folderFileCsvCsec);
        Egon.filesNVLOC(folder,folderFileCsvCsec);
        System.out.println(Egon.getCsec(folder,folderFileCsvCsec));
        System.out.println(Egon.getNvloc(Lsec.getPathFiles(folder)));
        System.out.println(Egon.getNvlocPourcentage(folder));
        System.out.println(Egon.getCsecPourcentage(folder,folderFileCsvCsec));


        System.out.println(Lsec.findWord("je suis tres contente , on va reussir","tres"));
       // System.out.println(Lsec.principale(folder,folderFileCsv));
        Jls.csvFormat(folder);
        System.out.println(Lsec.csecFinal(folder,folderFileCsv));
        System.out.println(Lsec.creationFileCsec(folder,folderFileCsv));

        Lsec.readFileCsv(folderFileCsv);
        System.out.println(Lsec.readFileCsv(new File(folderFileCsv.toURI())));
        //Egon.filesNVLOC(new File("C:\\Users\\Chaima\\Desktop\\ckjm-master\\src\\gr\\spinellis\\ckjm"));
    }
}
