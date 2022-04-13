import java.io.*;

public class Main {

    public static void main(String ... args) throws IOException {
        File f = new File("C:\\Program Files (x86)\\Steam\\steamapps\\workshop\\content\\108600");
        File dest = new File("C:\\Users\\alyce\\Zomboid\\mods");

        for (File file : f.listFiles()) {
            System.out.println("Looking at ["+file.getName()+"]");
            File mods = new File(file, "mods");
            if(mods.isDirectory()){
                System.out.println("In the mods directory");
                for (File listFile : mods.listFiles()) {
                    if(listFile.isDirectory()){
                        System.out.println("Copying file ["+listFile.getName()+"]");
                        copyDirectory(listFile, new File(dest, listFile.getName()));
                    }
                }
            }
        }

    }

    private static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
        }
        for (String f : sourceDirectory.list()) {
            copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
        }
    }
    private static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }
    private static void copyFile(File sourceFile, File destinationFile)
            throws IOException {
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }
}
