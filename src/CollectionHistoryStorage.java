import java.util.LinkedList;
import java.util.Queue;

public class CollectionHistoryStorage implements HistoryStorage{
    private final Writer writer;
    private Queue<String> history = new LinkedList<>();

    public CollectionHistoryStorage(){
        this.writer = new ConsoleWriter();
    }
    public CollectionHistoryStorage(Writer writer){
        this.writer = writer;
    }
    public void save(String str){
        history.add(str);
    }

    public void show(){
        for (String string: history) {
            writer.write(string);
        }
    }
}
