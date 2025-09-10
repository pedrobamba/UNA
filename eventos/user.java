// Classe que representa um usuário
public class User {
    private String username; // apelido único
    private String fullName;
    private String email;

    public User(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }

        listEvents();
        System.out.print("Digite o ID do evento: ");
        String id = scanner.nextLine();
        for (Event e : events) {
            if (e.getId().equals(id)) {
                e.removeParticipant(currentUser.getUsername());
                repo.saveEvents(events);
                System.out.println("Você saiu do evento!");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
}
