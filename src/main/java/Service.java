import java.io.File;

public class Service {
    private static Jls jls;
    private static Nvloc nvloc;
    private static Lsec lsec;



    public static void main(String[] args) {
        final File folder1 = new File("C:\\Users\\Chaima\\Downloads\\ckjm-master\\ckjm-master\\src\\gr\\spinellis\\ckjm");
        System.out.println(Lsec.findWord("je suis tres contente , on va reussir","tres"));

    }
}
