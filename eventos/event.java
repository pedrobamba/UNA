import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// Classe que representa um evento
public class Event {
    private String id;
    private String name;
    private String address;
    private EventCategory category;
    private LocalDateTime dateTime;
    private String description;
    private Set<String> participants = new HashSet<>();

    public Event(String id, String name, String address, EventCategory category,
                 LocalDateTime dateTime, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public EventCategory getCategory() { return category; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getDescription() { return description; }
    public Set<String> getParticipants() { return participants; }

    // Métodos de participação
    public void addParticipant(String username) {
        participants.add(username);
    }

    public void removeParticipant(String username) {
        participants.remove(username);
    }

    @Override
    public String toString() {
        return name + " - " + category + " - " + dateTime + " (" + participants.size() + " participantes)";
    
        System.out.println("Saindo...");
        System.exit(0);
    }
}