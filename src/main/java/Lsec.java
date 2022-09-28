import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lsec {
    private static  Jls jls;
    private static Pattern csvRE;

    public static String readFile (String filePath)
    {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {
            String str;
            while ((str = buffer.readLine()) != null) {

                builder.append(str).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static boolean findWord (String paragraphe , String mot){
        if(mot!= null && mot.length()!=0){
            return paragraphe.contains(mot);
        } else {
            System.out.println("The Keyword :example: is not found in the string");
            return false;
        }
    }

    public static List<Path> getPathFiles(File folder) throws IOException {
        List<Path> pathList;
        try (Stream<Path> stream = Files.walk(Paths.get(String.valueOf(folder)))) {
            pathList = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return pathList;
    }
    public static List<String> getNames (File folder , List<Path> listPaths ) {
        var lists = new ArrayList<String>();
        for (Path listPath : listPaths) {
            lists.add(FilenameUtils.removeExtension(listPath.getFileName().toString()));
        }
        return lists;
    }

    public static HashMap<String,Double> principale(File folder) throws IOException {
        var listPath = getPathFiles(folder);
        var nameFile = getNames(folder,listPath);
        var result = new HashMap<String,Double>();
        Double csec = 0.0;
        for (Path path : listPath) {
            for (String s : nameFile) {
                var fileActual = FilenameUtils.removeExtension(path.getFileName().toString());
                if (!s.equals(fileActual)) {
                    if (findWord(readFile(path.toString()), s)) {
                        csec+=1;
                        result.put(fileActual,csec);
                    }
                }
            }
            csec=0.0;
        }
        return result;
    }


    public static List<String> readFileCsv(File pathCsvInitial) {
        try {
            var data = new ArrayList<String>();//list of lists to store data
            FileReader fr = new FileReader(pathCsvInitial);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                data.add(line);
            }
            data.add(";");
            return data;
        }
        catch(Exception e) {
            System.out.print(e);
            return new ArrayList<>() ;
        }
    }

    public static HashMap<String,Double> csecFinal(File folder,File fileCsvInitial) throws IOException {
        var hash = principale(folder);
        var hashfinal = new HashMap<String,Double>();
        var contentFileCsvPrincipal = readFileCsv(fileCsvInitial);
        for (Map.Entry<String, Double> entry : hash.entrySet()) {
            for (String s : contentFileCsvPrincipal) {
                if (findWord(s, entry.getKey())) {
                    hashfinal.put(s, entry.getValue());
                }
            }
        }
        return hashfinal;
    }

    public static File creationFileCsec (File pathFolder , File fileCsvInitial) throws IOException {
        final String currentPath = Paths.get("").toAbsolutePath().toString();
        File csvFile = new File(currentPath,"fileCsec.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        var data  = csecFinal(pathFolder,fileCsvInitial);
        List<String> dataCsv = new ArrayList<>();
        StringBuilder dataFinal = new StringBuilder();
        String line;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            line = entry.getKey() + entry.getValue();
            dataCsv.add(line);
        }
        System.out.println("liste string "+ dataCsv);
        for (int i = 0; i < dataCsv.size(); i++) {
            dataFinal.append(dataCsv.get(i));
            if (i != dataCsv.size() - 1) {
                dataFinal.append(';');
                dataFinal.append("\n");
            }

        }
        dataFinal.append(';');
        dataFinal.append("\n");

        fileWriter.write(dataFinal.toString());
        fileWriter.close();
        return csvFile;
    }

}