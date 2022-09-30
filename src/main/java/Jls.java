import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Jls {

    private static List<String> listFilesForFolder(final File pathFolder) throws IOException {
        var listEntriesCsvFile = new ArrayList<String>();
        if(pathFolder.exists()){
            var listPathFiles = Lcsec.getPathFiles(pathFolder);
            for (final Path fileEntry : Objects.requireNonNull(listPathFiles)) {
                listEntriesCsvFile.add(fileEntry.toString());
                    var packagePath = fileEntry.toFile().getParentFile();
                    listEntriesCsvFile.add(packagePath.toString());
                    var fileName = FilenameUtils.removeExtension(fileEntry.toFile().getName());
                    listEntriesCsvFile.add(fileName);
            }
            System.out.println(listEntriesCsvFile);

        } else {
            System.out.println("Votre chemin est invalide");
        }
        return listEntriesCsvFile;
    }

    public static File filePartie0 (File pathFolder) throws IOException {
            var currentPath = Paths.get("").toAbsolutePath().toString();
            var csvFile = new File(currentPath,"file.csv");
            var fileWriter = new FileWriter(csvFile);
            var data = listFilesForFolder(pathFolder);
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                line.append(data.get(i));
                    line.append(';');
                if (((i+1) % 3) == 0) {
                    line.append("\n");
                }
            }

            line.append("\n");
            fileWriter.write(line.toString());
            fileWriter.close();
        return csvFile;
    }



}


