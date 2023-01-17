import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class FileOperationStorage implements OperationStorage{

    private long ids;
    private final String filePath;
    private final FileWriter fileWriter;
    private BufferedReader bufferedReader;

    FileOperationStorage(String filePath) {
        this.filePath = filePath;
        try {
            fileWriter = new FileWriter(this.filePath, true);
            bufferedReader = new BufferedReader(new FileReader(this.filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        checkFilePath();
    }

    private void defineId(){
        List<Operation> operationList = findAll();
        ids = 1;
        if(!operationList.isEmpty()){
            int size = operationList.size();
            ids = operationList.get(--size).getId() + 1;
        }

    }
    @Override
    public void save(Operation operation) {
        operation.setId(ids++);
        String operationAsFileStorageString =
                operation.getId() + "|"
                + operation.getNum1() + "|"
                + operation.getNum2() + "|"
                + operation.getType() + "|"
                + operation.getResult() + "|"
                + operation.getCreatedAt().toEpochSecond(ZoneOffset.UTC);
        try {
            fileWriter.write(operationAsFileStorageString);
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Operation> findAll() {
        List<Operation> operationsList = new ArrayList<>();
        try {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                operationsList.add(convertStringToOperation(line));
            }
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader(this.filePath));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return operationsList;
    }

    @Override
    public void remove(long id) {
        List<Operation> operationsList = findAll();
        for (Operation operation : operationsList) {
            if (operation.getId() == id) {
                operationsList.remove(operation);
                try {
                    fileWriter.write("");
                    fileWriter.flush();
                    for(Operation o : operationsList){
                        save(o);
                    }
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }
        }

    }

    @Override
    public Operation findById(long id) {
        List<Operation> operationsList = findAll();
        for (Operation operation : operationsList) {
            if (operation.getId() == id) {
                return operation;
            }
        }
        return null;
    }

    private void checkFilePath() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            defineId();
        }
    }

    private Operation convertStringToOperation(String str){
        String[] operationFields = str.split("\\|");
        LocalDateTime createdAt = LocalDateTime.ofEpochSecond(Long.parseLong(operationFields[5]),0,ZoneOffset.UTC);
        return new Operation(
                Integer.parseInt(operationFields[0]),
                Double.parseDouble(operationFields[1]),
                Double.parseDouble(operationFields[2]),
                operationFields[3],
                Double.parseDouble(operationFields[4]),
                createdAt

        );
    }
}
