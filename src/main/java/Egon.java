import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Egon {
    //Returns a hashmap with value of nvloc associated to the correspondent file path (key)
    private static HashMap<String, Double> getNvloc(List<Path> listPaths) throws IOException {
        var pathAndNvloc = new HashMap<String, Double>();
        for (Path listPath : listPaths) {
            pathAndNvloc.put(listPath.toString(), (double) Nvloc.nboflines(new File(String.valueOf(listPath))));
        }
        return pathAndNvloc;
    }
    //Total number of non-empty lines from all files in directory
    private static Double getTotalNvloc(File pathFolder) throws IOException {
        var listPaths = Lcsec.getPathFiles(pathFolder);
        var total = 0.0;
        for (Path listPath : listPaths) {
            total += Nvloc.nboflines(new File(String.valueOf(listPath)));
        }
        return total;
    }
    //Sorting the values by descending order
    private static void triHash(HashMap<String, Double> list) {
        LinkedHashMap<String, Double> mapOrdered =
                list.entrySet().stream().
                        sorted((Map.Entry.comparingByValue(Comparator.reverseOrder()))).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> null, () -> new LinkedHashMap<String, Double>()));
    }
    //Calculating the metric Nvloc compared to all files
    private static HashMap<String, Double> getNvlocPourcentage(File pathFolder) throws IOException {
        var listPath = Lcsec.getPathFiles(pathFolder);
        var pathAndNvloc = getNvloc(listPath);
        var hashPourcentage = new HashMap<String, Double>();
        var total = getTotalNvloc(pathFolder);
        for (Map.Entry<String, Double> entry : pathAndNvloc.entrySet()) {
            hashPourcentage.put(entry.getKey(), entry.getValue() / total);
        }
        triHash(hashPourcentage);
        return hashPourcentage;
    }

    //Returns a hashmap with Csec value associated to correspondent file (key)
    private static List<Double> getCsec(File pathFolder, File fileCsec) throws IOException {
        var pathAndCsec = Lcsec.creationContentFileLcsec(pathFolder, fileCsec);
        var listCsec = new ArrayList<Double>();
        for (Map.Entry<String, Double> entry : pathAndCsec.entrySet()) {
            listCsec.add(entry.getValue());
        }
        return listCsec;
    }

    //Total Csec value of all files
    private static Double getTotalCsec(File pathFolder, File fileCsec) throws IOException {
        var listCsec = getCsec(pathFolder, fileCsec);
        Double total = 0.0;
        for (Double i : listCsec) {
            total += i;
        }
        return total;
    }
    //Calculating the metric Csec compared to all files
    private static HashMap<String, Double> getCsecPourcentage(File pathFolder) throws IOException {
        var fileCsec = retourFileCsv();
        var hashCsec = Lcsec.creationContentFileLcsec(pathFolder, fileCsec);
        var hashPourcentage = new HashMap<String, Double>();
        var total = getTotalCsec(pathFolder, fileCsec);
        for (Map.Entry<String, Double> entry : hashCsec.entrySet()) {
            hashPourcentage.put(entry.getKey(), entry.getValue() / total);
        }
        triHash(hashPourcentage);

        return hashPourcentage;
    }

    //Return the file.csv
    private static File retourFileCsv() {
        var filesCsv = Service.createPath(Objects.requireNonNull(Paths.get("").toAbsolutePath().toFile().listFiles()));
        return new File(filesCsv.get(0));
    }
    //Return the fileCsec.csv
    private static File retourFileCsec() {
        var filesCsv = Service.createPath(Objects.requireNonNull(Paths.get("").toAbsolutePath().toFile().listFiles()));
        return new File(filesCsv.get(1));
    }
    //Calculate the top x percent of Csec
    private static List<String> filtrerSelonCsec(File pathFolder, Double seuil) throws IOException {
        var pourcentageCsec = getCsecPourcentage(pathFolder);
        var topPourcentageCsec = new ArrayList<String>();
        var topPourcent = Math.floor(getCsec(pathFolder, retourFileCsv()).size() * seuil);
        var count = 0;
        for (Map.Entry<String, Double> mapTopPourcent : pourcentageCsec.entrySet()) {
            topPourcentageCsec.add(mapTopPourcent.getKey());
            count++;

            if (count == topPourcent) {
                break;
            }
        }
        return topPourcentageCsec;
    }
    //Calculate the top x percent of Nvloc
    private static ArrayList<String> filtrerSelonNvloc(File pathFolder, Double seuil) throws IOException {
        var pourcentageNvloc = getNvlocPourcentage(pathFolder);
        var topPourcentageCsec = new ArrayList<String>();
        var topPourcent = Math.floor(getCsec(pathFolder, retourFileCsv()).size() * seuil);
        var count = 0;
        for (Map.Entry<String, Double> mapTopPourcent : pourcentageNvloc.entrySet()) {
            topPourcentageCsec.add(mapTopPourcent.getKey());
            count++;
            if (count == topPourcent) {
                break;
            }
        }
        return topPourcentageCsec;
    }
    //Remove the unnecessary components of line (keep only the path of the file to compare list of metrics)
    private static List<String> filtrerListTriCsec(List<String> liste) {
        var listeTrier = new ArrayList<String>();
        for (String s : liste) {
            for (int j = 0; j < s.length(); j++) {
                listeTrier.add(s.substring(0, s.indexOf(";")));
            }
        }
        return listeTrier;

    }
    //Return the common files based on top % metrics
    private static List<String> compareMetrique(File folder, Double seuil) throws IOException {
        var listTriNvloc = filtrerSelonNvloc(folder, seuil);
        var listTriCsec = filtrerListTriCsec(filtrerSelonCsec(folder, seuil));
        listTriNvloc.retainAll(listTriCsec);
        return listTriNvloc;
    }
    //Create the data of the fileEgon.csv
    private static HashMap<String, Double> contentFileEgon(File folderPath, File fileCsec) throws IOException {
        var listOfFiles = Lcsec.getPathFiles(folderPath);
        var listNvloc = getNvloc(listOfFiles);
        var hashAndNvloc = new HashMap<String, Double>();
        var contenuFileCsv = Lcsec.readFileCsv(fileCsec);
        for (Map.Entry<String, Double> entry : listNvloc.entrySet()) {
            for (String s : contenuFileCsv) {
                if (Lcsec.findWord(s, entry.getKey())) {
                    hashAndNvloc.put(s, entry.getValue());
                }
            }
        }
        return hashAndNvloc;
    }
    //Principal method of egon that returns the fileEgon.csv
    // which contains the top % for both metrics
    public static File egon(File pathFolder, Double seuil) throws IOException {
        var listTopMetrique = compareMetrique(pathFolder, seuil);
        var data = contentFileEgon(pathFolder, retourFileCsec());
        var csvFile = new File(Paths.get("").toAbsolutePath().toString(), "fileEgon.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        List<String> dataCsv = new ArrayList<>();
        StringBuilder dataFinal = new StringBuilder();
        String line;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            for (String s : listTopMetrique) {
                if (entry.getKey().contains(s)) {
                    line = entry.getKey() + entry.getValue();
                    dataCsv.add(line);
                }
            }
        }
        //Format for CSV files
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

