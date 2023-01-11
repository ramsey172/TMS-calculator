import java.io.*;

public class FileHistoryStorage {
    private final String filePath;
    private final FileWriter fileWriter;
    private final BufferedReader bufferedReader;
    private final Writer writer;

    FileHistoryStorage(String filePath){
        this.filePath = filePath;
        this.writer = new ConsoleWriter();
        try {
            fileWriter = new FileWriter(this.filePath, true);
            bufferedReader = new BufferedReader(new FileReader(this.filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        checkFilePath();
    }
    public FileHistoryStorage(String filePath, Writer writer){
        this.filePath = filePath;
        this.writer = writer;
        try {
            fileWriter = new FileWriter(this.filePath, true);
            bufferedReader = new BufferedReader(new FileReader(this.filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        checkFilePath();
    }
    public void save(String str){
        try {
            fileWriter.write(str);
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void show(){
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                writer.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkFilePath(){
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
