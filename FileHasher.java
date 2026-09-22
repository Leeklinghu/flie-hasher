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
            writer.write("This is my notes");
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
                while (character != -1) {
                    System.out.print((char) character);
                    backupWriter.write(character);
                    character = reader.read();
                }
                reader.close();

            }
            backupWriter.close();
            // TODO (FH-4): print each file's name next to hashFile(path)
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /**
     * Reads the file at filePath and returns its SHA-256 hash
     * as a lowercase 64-character hexadecimal string.
     */
    public static String hashFile(String filePath) throws IOException {
        // TODO (FH-4): read the whole file, digest it, convert the bytes to hex
        return "";
    }
}
