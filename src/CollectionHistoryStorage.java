import java.util.Collection;

public class CollectionHistoryStorage implements HistoryStorage{
    private final Writer writer;
    private final Collection<String> history;

    public CollectionHistoryStorage(Collection<String> collection){
        history = collection;
        this.writer = new ConsoleWriter();
    }
    public CollectionHistoryStorage(Collection<String> collection, Writer writer){
        history = collection;
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
