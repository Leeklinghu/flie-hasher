import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHasher {

    public static void main(String[] args) {
        try {
            // TODO (FH-1): create the JavaFileSystem directory
            File folder = new File("JavaFileSystem");
            if (!folder.exists()) {
                folder.mkdir();
            }

            // TODO (FH-2): create notes.txt, data.txt, log.txt and write a sentence into
            // each
            File notes = new File(folder, "notes.txt");
            FileWriter writer = new FileWriter(notes);
            writer.write("This is my notes version 2");
            writer.close();

            File data = new File(folder, "data.txt");
            FileWriter writer2 = new FileWriter(data);
            writer2.write("This is my data");
            writer2.close();

            File log = new File(folder, "log.txt");
            FileWriter writer3 = new FileWriter(log);
            writer3.write("This is my log");
            writer3.close();
            // TODO (FH-3): read each file back, print it, and write all three into
            // Backup/backup.txt
            File Backup = new File(folder, "Backup");
            if (!Backup.exists()) {
                Backup.mkdir();
            }
            File backupFile = new File(Backup, "backup.txt");
            FileWriter backupWriter = new FileWriter(backupFile);
            File[] filearray = { notes, data, log };
            for (File filetemp : filearray) {
                FileReader reader = new FileReader(filetemp);
                int character = reader.read();
                System.out.print(filetemp.getName() + ": ");
                while (character != -1) {
                    System.out.print((char) character);
                    backupWriter.write(character);
                    character = reader.read();
                }
                System.out.println();
                backupWriter.write(System.lineSeparator());
                reader.close();

            }
            backupWriter.close();
            // TODO (FH-4): print each file's name next to hashFile(path)
            System.out.println();
            for(File filetemp: filearray){
                String hash = hashFile(filetemp.getPath());
                System.out.println(filetemp.getName()+": "+ hash);

            }
        File empty = new File(folder, "empty.txt");
        System.out.println("empty.txt: " +hashFile(empty.getPath()));
        
        } 
        catch (IOException| NoSuchAlgorithmException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /**
     * Reads the file at filePath and returns its SHA-256 hash
     * as a lowercase 64-character hexadecimal string.
     */
    public static String hashFile(String filePath) throws IOException, NoSuchAlgorithmException {
        // TODO (FH-4): read the whole file, digest it, convert the bytes to hex
        FileReader reader = new FileReader(filePath);
        StringBuilder toSave = new StringBuilder();
        int character = reader.read();
        while(character!=-1){
            toSave.append((char)character);
             character = reader.read();
        }
        reader.close();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hashBytes = digest.digest(toSave.toString().getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder();

        for (byte bite : hashBytes) {
            hex.append(String.format("%02x", bite & 0xff));
        }  

        return hex.toString();
    
    }
}
