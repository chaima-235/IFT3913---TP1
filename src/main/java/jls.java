import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.*;


public class jls {
    public static List<String> listFilesForFolder(final File folder) throws IOException {
        var list = new ArrayList<String>();
        if(folder.exists()){
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isFile()) {
                    var filePath = fileEntry.getAbsolutePath();
                    list.add(filePath);
                    var packagePath = fileEntry.getParentFile();
                    list.add(packagePath.toString());
                    var fileName = FilenameUtils.removeExtension(fileEntry.getName());
                    list.add(fileName);

                } else if (fileEntry.isDirectory()) {
                    File[] childFile = fileEntry.listFiles();
                    for (int i = 0; i < childFile.length; i++){
                        var filePath = childFile[i].getAbsolutePath();
                        list.add(filePath);
                        var packagePath = childFile[i].getParentFile();
                        list.add(packagePath.toString());
                        var fileName = FilenameUtils.removeExtension(childFile[i].getName());
                        list.add(fileName);
                    }
                }

            }

        } else {
            System.out.println("Votre chemin est invalide");
        }

        return list;
    }




//

//    public static List<String[]> createDataCsv(File folder) {
//        List<String[]> listData = new ArrayList<>();
//        var line  = separate(listFilesForFolder(folder),3);
//        for (List<Object> objects : line) {
//            String[] strings = objects.stream().toArray(String[]::new);
//            listData.add(strings);
//        }
//        return listData;
//    }
//
//
//    private static  <T> List<List<T>> separate(List<String> liste, final int size) {
//        List<List<T>> separated = new ArrayList<>();
//        List<T> temp;
//        for (int i = 0; i < liste.size(); i += size - 1) {
//            temp = (List<T>) liste.subList(i, Math.min(liste.size(), i + size));
//            if (temp.size() != 1) {
//                separated.add(new ArrayList<T>(temp));
//            }
//        }
//        return separated;
//    }
//
//    public  void csvFormat (File folder) {
//        try {
//            File csvFile = new File(folder, "file2.csv");
//            FileWriter fileWriter = new FileWriter(csvFile);
//            var data = createDataCsv(folder);
//            StringBuilder line = new StringBuilder();
//            for (int i = 0; i < data.size(); i++) {
//                line.append(Arrays.toString(data.get(i)));
//                if (i != data.size() - 1) {
//                    line.append(',');
//                }
//            }
//            line.append("\n");
//            fileWriter.write(line.toString());
//            fileWriter.close();
//        }catch (Exception e){
//            System.out.println("une erreur lors du chargement du fichier ");
//
//        }
//    }


    public static void main(String[] args) throws Exception {
        final File folder = new File("/Users/biancabica/Downloads/ckjm-master/src/gr/spinellis/ckjm");
        System.out.println(listFilesForFolder(folder));
        //createDataCsv(folder);
        //    csvFormat(folder);
       // System.out.println(listeFinale);

    }
}
