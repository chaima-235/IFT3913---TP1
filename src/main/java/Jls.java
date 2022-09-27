import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.*;


public class Jls {

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
                    for (int i = 0; i < Objects.requireNonNull(childFile).length; i++){
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

    public static File csvFormat(File folder) throws IOException {

            File csvFile = new File("src/main/resourcesCsv","file.csv");
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


