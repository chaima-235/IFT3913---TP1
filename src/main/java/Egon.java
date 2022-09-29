import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Egon {

        public static HashMap<String, Double > getNvloc (List<Path> listPaths) throws IOException {
            var hash = new HashMap<String, Double >();
            for (Path listPath : listPaths) {
                hash.put(listPath.toString() , (double) Nvloc.nboflines(new File(String.valueOf(listPath))));
            }

            return hash;
        }

//        private static  Double getTotalNvloc (File pathFolder) throws IOException {
//            var listPath = Lsec.getPathFiles(pathFolder);
//            var  hash = getNvloc(listPath);
//            Double acc = 0.0;
//            for (Map.Entry<String, Double> entry : hash.entrySet()) {
//                acc += entry.getValue();
//            }
//            return acc;
//        }

        public static Double getNvlocAcc (File pathFolder) throws IOException {
        var listPaths = Lsec.getPathFiles(pathFolder);
        var acc = 0.0;
        for (Path listPath : listPaths) {
            acc += Nvloc.nboflines(new File(String.valueOf(listPath)));
        }
        return acc;
    }


    public static HashMap<String, Double> getNvlocPourcentage (File pathFolder) throws IOException {
            var listPath = Lsec.getPathFiles(pathFolder);
            var hash = getNvloc(listPath);
            var listPourcentage = new HashMap<String, Double>();
            var acc = getNvlocAcc(pathFolder);
            for (Map.Entry<String,Double > entry : hash.entrySet()) {
                listPourcentage.put(entry.getKey(),entry.getValue() / acc);
            }
            triHash(listPourcentage);
           // System.out.println(listPourcentage);
            return listPourcentage;
        }


        public static  List<Double> getCsec (File pathFolder, File fileCsvCsec) throws IOException {
            var hash = Lsec.csecFinal(pathFolder,fileCsvCsec);
            var listCsec = new ArrayList<Double>();
            for (Map.Entry<String, Double> entry : hash.entrySet()) {
                listCsec.add(entry.getValue());
            }
            listCsec.sort(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return Double.compare(o1, o2);
                }
            });
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

        public static HashMap<String, Double>  getCsecPourcentage (File pathFolder) throws IOException {
            var pathCsv = Paths.get("").toAbsolutePath();
            var fileCsvCsec = retourFileCsv(pathFolder);
            var hashCsec =Lsec.csecFinal(pathFolder,fileCsvCsec);
            var listPourcentage = new HashMap<String, Double>();
            var acc = getTotalCsec(pathFolder,fileCsvCsec);
                for (Map.Entry<String, Double> entry : hashCsec.entrySet()) {
                        listPourcentage.put(entry.getKey(), entry.getValue() / acc);
            }

            triHash(listPourcentage);
           // System.out.println(listPourcentage);

                return listPourcentage;
        }

        private static void triHash ( HashMap<String, Double> list ){
            LinkedHashMap<String, Double> mapOrdered =
                    list.entrySet().stream().
                            sorted((Map.Entry.comparingByValue(Comparator.reverseOrder()))).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> null, () -> new LinkedHashMap<String, Double>()));
        }

        public static HashMap<String, Double> filesNVLOC(File folderPath, File fileCsvCsec) throws IOException {
            var listOfFiles = Lsec.getPathFiles(folderPath);
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

        private static File retourFileCsv(File folder){
            var pathCsv = Paths.get("").toAbsolutePath();
            var filesCsv = Service.createPath(Objects.requireNonNull(pathCsv.toFile().listFiles()));

            return new File(filesCsv.get(0));
        }
    private static File retourFileCsec(File folder){
        var pathCsv = Paths.get("").toAbsolutePath();
        var filesCsv = Service.createPath(Objects.requireNonNull(pathCsv.toFile().listFiles()));

        return new File(filesCsv.get(1));
    }

        public static List<String> filtrerSelonCsec (File folder , Double seuil) throws IOException {
            var pourcentageCsec = getCsecPourcentage(folder);
            var topPourcentageCsec = new ArrayList<String>();
            var topPourcent = Math.floor(getCsec(folder,retourFileCsv(folder)).size() * seuil);
            var count = 0;
            Iterator<Map.Entry<String, Double>> iterator = pourcentageCsec.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> mapTopPourcent = (Map.Entry<String, Double>) iterator.next();
                topPourcentageCsec.add(mapTopPourcent.getKey());
                count++;

                if (count == topPourcent) {
                    break;
                }

            }
            return  topPourcentageCsec;

            }

    public static ArrayList<String> filtrerSelonNvloc (File folder , Double seuil) throws IOException {
        var pourcentageNvloc = getNvlocPourcentage(folder);
        var topPourcentageCsec = new ArrayList<String>();
        var topPourcent = Math.floor(getCsec(folder,retourFileCsv(folder)).size() * seuil);
        var count = 0;
        Iterator<Map.Entry<String, Double>> iterator = pourcentageNvloc.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Double> mapTopPourcent = (Map.Entry<String, Double>) iterator.next();
            topPourcentageCsec.add(mapTopPourcent.getKey());
            count++;
            if (count == topPourcent) {
                break;
            }
        }
        return topPourcentageCsec;
    }


    public  static List<String> compareMetrique(File folder , Double seuil) throws IOException {
            var listTriNvloc = filtrerSelonNvloc(folder,seuil);
            var listTriCsec=  filtrerListTriCsec(filtrerSelonCsec(folder,seuil));
            listTriNvloc.retainAll(listTriCsec);

            return listTriNvloc;
    }

    private static List<String> filtrerListTriCsec(List<String> liste){
            var listeTrier = new ArrayList<String>();
        for (String s : liste) {
            for (int j = 0; j < s.length(); j++) {
                listeTrier.add(s.substring(0, s.indexOf(";")));
            }
        }
       return listeTrier;

    }

    public static File egon (File pathFolder , Double seuil) throws IOException {
        final String currentPath = Paths.get("").toAbsolutePath().toString();
        var fileMetrique = compareMetrique(pathFolder, seuil);
        var data = filesNVLOC(pathFolder, retourFileCsec(pathFolder));
        File csvFile = new File(currentPath,"fileEgon.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        List<String> dataCsv = new ArrayList<>();
        StringBuilder dataFinal = new StringBuilder();
        String line;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            for (String s : fileMetrique) {
                if (entry.getKey().contains(s)) {
                    line = entry.getKey() + entry.getValue();
                    dataCsv.add(line);
                }

            }
        }
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


//
//        public static File creationFileEgon (File pathFolder , File fileCsvCsec) throws IOException {
//            final String currentPath = Paths.get("").toAbsolutePath().toString();
//            File csvFile = new File(currentPath,"fileEgon.csv");
//            FileWriter fileWriter = new FileWriter(csvFile);
//            var data  = filesNVLOC(pathFolder,fileCsvCsec);
//            List<String> dataCsv = new ArrayList<>();
//            StringBuilder dataFinal = new StringBuilder();
//            String line;
//            for (Map.Entry<String, Double> entry : data.entrySet()) {
//                line = entry.getKey() + entry.getValue();
//                dataCsv.add(line);
//            }
//            for (int i = 0; i < dataCsv.size(); i++) {
//                dataFinal.append(dataCsv.get(i));
//                if (i != dataCsv.size() - 1) {
//                    dataFinal.append(';');
//                    dataFinal.append("\n");
//                }
//
//            }
//            dataFinal.append(';');
//            dataFinal.append("\n");
//
//            fileWriter.write(dataFinal.toString());
//            fileWriter.close();
//            return csvFile;
//        }



    }

