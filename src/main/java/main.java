import java.io.File;

public class main {
    private static Jls jls;
    private static Nvloc nvloc;



    public static void main(String[] args) throws Exception {
        //final File folder = new File("C:\\Users\\Chaima\\Downloads\\ckjm-master\\ckjm-master\\src\\gr\\spinellis\\ckjm");
        final File folder1 = new File("C:\\Users\\Chaima\\Downloads\\hola");


        //System.out.println(Jls.getListAsCsvString(Jls.listFilesForFolder(folder)));
        jls.csvFormat(folder1);

//        File file = new File("/Users/biancabica/Downloads/ckjm-master/src/gr/spinellis/ckjm/bilel.java");
//        System.out.println(Nvloc.nboflines(file));
    }
}
