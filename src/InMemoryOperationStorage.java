import java.util.ArrayList;
import java.util.List;

public class InMemoryOperationStorage implements OperationStorage{

    private long ids = 1;

    private final List<Operation> store = new ArrayList<>();

    @Override
    public void save(Operation o) {
        o.setId(ids++);
        store.add(o);
    }

    @Override
    public void remove(long id) {
        for (Operation operation : store) {
            if (operation.getId() == id) {
                store.remove(operation);
                break;
            }
        }
    }

    @Override
    public Operation findById(long id) {
        for (Operation operation : store) {
            if (operation.getId() == id) {
                return operation;
            }
        }
        return null;
    }

    @Override
    public List<Operation> findAll() {
        return new ArrayList<>(store);
    }
}
