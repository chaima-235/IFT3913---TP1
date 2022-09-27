import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lsec {

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

}
