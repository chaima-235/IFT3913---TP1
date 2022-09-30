import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lcsec {
    //Read a .java file and correctly format its content in the appropriate CSV file
    private static String readFile(String pathFile) {
        var builder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(new FileReader(pathFile))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    //Find a particular word in a file
    public static boolean findWord(String contentFile, String word) {
        if (word != null && word.length() != 0) {
            return contentFile.contains(word);
        } else {
            System.out.println("The keyword is not found in the string");
            return false;
        }
    }
    //Get all path files of a directory and sub-directories
    public static List<Path> getPathFiles(File pathFolder) throws IOException {
        List<Path> listFilePaths;
        try (Stream<Path> stream = Files.walk(Paths.get(String.valueOf(pathFolder)))) {
            listFilePaths = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return listFilePaths;
    }
    //Get all file names of directory and sub-directories without extension
    private static List<String> getFileNames(File pathFolder, List<Path> listPaths) {
        var listNameNoExtension = new ArrayList<String>();
        for (Path listPath : listPaths) {
            listNameNoExtension.add(FilenameUtils.removeExtension(listPath.getFileName().toString()));
        }
        return listNameNoExtension;
    }
    //Returns a hashmap with Csec value associated to the correspondent file (key)
    private static HashMap<String, Double> calculCsec(File pathFolder) throws IOException {
        var listOfPathFiles = getPathFiles(pathFolder);
        var listOfFileNames = getFileNames(pathFolder, listOfPathFiles);
        var result = new HashMap<String, Double>();
        double csec = 0.0;
        for (Path path : listOfPathFiles) {
            for (String s : listOfFileNames) {
                var fileActual = FilenameUtils.removeExtension(path.getFileName().toString());
                if (!s.equals(fileActual)) {
                    if (findWord(readFile(path.toString()), s)) {
                        csec += 1;
                        result.put(fileActual, csec);
                    }
                }
            }
            csec = 0.0;
        }
        return result;
    }

    //Reads the CSV file associated to Jls.java
    public static List<String> readFileCsv(File filePathP0) {
        try {
            var data = new ArrayList<String>();
            FileReader fr = new FileReader(filePathP0);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null && line.length() != 0 && !line.isBlank()) {
                data.add(line);
            }
            return data;
        } catch (Exception e) {
            System.out.print(e);
            return new ArrayList<>();
        }
    }
    //Creates a hashmap with Csec value associated to the correspondent line in file.csv (Key)
    public static HashMap<String, Double> creationContentFileLcsec(File pathFolder, File filePathP0) throws IOException {
        var fileNameAndCsec = calculCsec(pathFolder);
        var dataFileAndCsec = new HashMap<String, Double>();
        var contentFileCsvPrincipal = readFileCsv(filePathP0);
        for (Map.Entry<String, Double> entry : fileNameAndCsec.entrySet()) {
            for (String s : contentFileCsvPrincipal) {
                if (findWord(s, entry.getKey())) {
                    //if the name of the file matches the name in the line of file.csv,
                    // associate the Csec value with the line
                    dataFileAndCsec.put(s, entry.getValue());
                }
            }
        }
        return dataFileAndCsec;
    }
    //Create the fileCsec.csv file with the correct data
    public static File lcsec(File pathFolder, File filePathP0) throws IOException {
        var currentPath = Paths.get("").toAbsolutePath().toString();
        var csvFile = new File(currentPath, "fileCsec.csv");
        var fileWriter = new FileWriter(csvFile);
        var data = creationContentFileLcsec(pathFolder, filePathP0);
        var dataCsec = new ArrayList<>();
        StringBuilder dataCsecFinal = new StringBuilder();
        String line;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            line = entry.getKey() + entry.getValue();
            dataCsec.add(line);
        }
        //Format CSV file
        for (int i = 0; i < dataCsec.size(); i++) {
            dataCsecFinal.append(dataCsec.get(i));
            if (i != dataCsec.size() - 1) {
                dataCsecFinal.append(';');
                dataCsecFinal.append("\n");
            }
        }
        dataCsecFinal.append(';');
        dataCsecFinal.append("\n");
        fileWriter.write(dataCsecFinal.toString());
        fileWriter.close();
        return csvFile;
    }
}