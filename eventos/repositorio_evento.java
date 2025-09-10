import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Classe que salva e lê os eventos do arquivo events.data
public class EventRepository {
    private String fileName = "events.data";

    public void saveEvents(List<Event> events) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(events);
        } catch (Exception e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public List<Event> loadEvents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Event>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
