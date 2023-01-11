import java.util.LinkedList;
import java.util.Queue;

public class CollectionHistoryStorage implements HistoryStorage{
    private final Writer writer;
    private Queue<String> history = new LinkedList<>();

    CollectionHistoryStorage(){
        this.writer = new ConsoleWriter();
    }
    public CollectionHistoryStorage(Writer writer){
        this.writer = writer;
    }
    public void save(String str){
        history.add(str);
    }

    public void show(){
        String operationAsString;
        while((operationAsString = history.poll()) != null){
            writer.write(operationAsString);
        }
    }
}
