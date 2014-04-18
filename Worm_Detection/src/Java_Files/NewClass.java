package Java_Files;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public class NewClass {

    public static void test() {
        File[] files = File.listRoots();

        for (File f : files) {
            //f.getPath()
            parseAllFiles(f.getAbsolutePath());
        }
    }

    public static void parseAllFiles(String parentDirectory) {
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        for (File f : filesInDirectory) {
            try {
                String s = f.getAbsolutePath();
                if (f.isDirectory()) {

                    Path ps = Paths.get(s);
                    if (Files.isSymbolicLink(ps)) {
                        System.out.println("link.............................................");
                    } else {
                          parseAllFiles(f.getAbsolutePath());
                    }
                } else {
                    File dir = new File(f.getAbsolutePath());
                    if (dir.canExecute()) {
                        System.out.println("files!!!!!!! " + f.getAbsolutePath());
                        File ff = new File("/home/anju/PR/worm_info");
                        FileReader fr = new FileReader(ff);
                        BufferedReader br = new BufferedReader(fr);
                        String str2;
                        String str1 = f.getName();
                        while ((str2 = br.readLine()) != null) {
                            String line = str2.trim();
                            if (str1.toLowerCase().contains(line.toLowerCase())) {
                                System.out.println("File is: " + f.getAbsolutePath());
                                //            f.delete();
                                System.out.println("file deleted********************" + f.getAbsolutePath());
                            }
                        }
                        ///////////////// TESTING OF WORM EXTENSIONS FROM THE FILE: worm_extensions/////////////////////             


                        File fi2 = new File("/home/anju/PR/worm_extensions");
                        FileReader fileReader2 = new FileReader(fi2);
                        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

                        String line3;
                        while ((line3 = bufferedReader2.readLine()) != null) {
                            String name = line3.trim();
                            int mid = str1.lastIndexOf(".");
                            String ext = str1.substring(mid + 1, str1.length());

                            if (ext.toLowerCase().contains(name.toLowerCase())) {
                                 System.out.println("file detected by extension********************" + f.getAbsolutePath());
                            }

                        }
                       }
                }
            } catch (Exception e) {
            }
        }
    }
}
