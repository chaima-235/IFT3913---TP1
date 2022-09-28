import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

    public class Egon {

        public static HashMap<String, Double > getNvloc (List<Path> listPaths) throws IOException {
            var hash = new HashMap<String, Double >();
            for (Path listPath : listPaths) {
                hash.put(listPath.toString() , (double) Nvloc.nboflines(new File(String.valueOf(listPath))));
            }
            return hash;
        }

        private static  Double getTotalNvloc (File pathFolder) throws IOException {
            var listPath = Lsec.getPathFiles(pathFolder);
            var  hash = getNvloc(listPath);
            Double acc = 0.0;
            for (Map.Entry<String, Double> entry : hash.entrySet()) {
                acc += entry.getValue();
            }
            return acc;
        }

        public static HashMap<String, Double> getNvlocPourcentage (File pathFolder) throws IOException {
            var listPath = Lsec.getPathFiles(pathFolder);
            var hash = getNvloc(listPath);
            var listPourcentage = new HashMap<String, Double>();
            var acc = getTotalNvloc(pathFolder);
            for (Map.Entry<String,Double > entry : hash.entrySet()) {
                listPourcentage.put(entry.getKey(),entry.getValue() / acc);
            }
            return listPourcentage;
        }

        public static  List<Double> getCsec (File pathFolder, File fileCsvCsec) throws IOException {
            var hash = Lsec.csecFinal(pathFolder,fileCsvCsec);
            var listCsec = new ArrayList<Double>();
            for (Map.Entry<String, Double> entry : hash.entrySet()) {
                listCsec.add(entry.getValue());
            }
            return  listCsec;
        }

        private static  Double getTotalCsec (File pathFolder, File fileCsvCsec) throws IOException {
            var  listCsec = getCsec(pathFolder,fileCsvCsec);
            Double acc = 0.0;
            for (Double i : listCsec) {
                acc += i;
            }
            return acc;
        }

        public static HashMap<String, Double>  getCsecPourcentage (File pathFolder , File fileCsvCsec) throws IOException {
            var listPath = Lsec.getPathFiles(pathFolder);
            var listeCsec = getCsec(pathFolder,fileCsvCsec);
            var hashCsec =Lsec.csecFinal(pathFolder,fileCsvCsec);
            var listPourcentage = new HashMap<String, Double>();
            var acc = getTotalCsec(pathFolder,fileCsvCsec);
            for (Double i : listeCsec) {
                for (Map.Entry<String, Double> entry : hashCsec.entrySet()) {

                    listPourcentage.put(entry.getKey(), i / acc);
                }
            }

            return listPourcentage;
        }

        public static HashMap<String, Double> filesNVLOC(File folderPath, File fileCsvCsec) throws IOException {
            var listOfFiles = Lsec.getPathFiles(folderPath);
            System.out.println(listOfFiles);
            var listNvloc = getNvloc(listOfFiles);
            var hashNvloc = new HashMap<String, Double>();
            var contenuFileCsv =Lsec.readFileCsv(fileCsvCsec);
            for (Map.Entry<String, Double> entry : listNvloc.entrySet()) {
                for (String s : contenuFileCsv) {
                    if (Lsec.findWord(s , entry.getKey())) {
                        hashNvloc.put(s, entry.getValue());
                    }
                }
            }

            return hashNvloc;
        }
        public static File creationFileEgon (File pathFolder , File fileCsvCsec) throws IOException {
            File csvFile = new File("src/main/resourcesCsv","fileEgon.csv");
            FileWriter fileWriter = new FileWriter(csvFile);
            var data  = filesNVLOC(pathFolder,fileCsvCsec);
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
            dataFinal.append("\n");

            fileWriter.write(dataFinal.toString());
            fileWriter.close();
            return csvFile;
        }
    }

