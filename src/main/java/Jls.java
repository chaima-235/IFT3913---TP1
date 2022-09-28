import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Jls {

    public static List<String> listFilesForFolder(final File folder) throws IOException {
        var list = new ArrayList<String>();
        if(folder.exists()){
            var listsPathFile = Lsec.getPathFiles(folder);
            for (final Path fileEntry : Objects.requireNonNull(listsPathFile)) {
                list.add(fileEntry.toString());
                    var packagePath = fileEntry.toFile().getParentFile();
                    list.add(packagePath.toString());
                    var fileName = FilenameUtils.removeExtension(fileEntry.toFile().getName());
                    list.add(fileName);
            }

        } else {
            System.out.println("Votre chemin est invalide");
        }

        return list;
    }

    public static File csvFormat(File folder) throws IOException {
            final String currentPath = Paths.get("").toAbsolutePath().toString();
            File csvFile = new File(currentPath,"file.csv");
            FileWriter fileWriter = new FileWriter(csvFile);
            var data = listFilesForFolder(folder);
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


