import java.util.List;
public interface OperationStorage {
    void save(Operation o);
    void remove(long id);
    Operation findById(long id);
    List<Operation> findAll();
}
