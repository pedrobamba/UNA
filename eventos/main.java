import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Event> events = new ArrayList<>();
    private static EventRepository repo = new EventRepository();
    private static User currentUser;

    public static void main(String[] args) {
        events = repo.loadEvents();
        System.out.println("=== Sistema de Eventos ===");
        menu();
    }

    private static void menu() {
        int option = 0;
        while (option != 6) {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Criar evento");
            System.out.println("3 - Listar eventos");
            System.out.println("4 - Participar de evento");
            System.out.println("5 - Cancelar participação");
            System.out.println("6 - Sair");
            System.out.print("Opção: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1: registerUser(); break;
                case 2: createEvent(); break;
                case 3: listEvents(); break;
                case 4: joinEvent(); break;
                case 5: cancelParticipation(); break;
                case 6: exit(); break;
                default: System.out.println("Opção inválida");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Nome completo: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        currentUser = new User(username, fullName, email);
        System.out.println("Usuário cadastrado: " + currentUser);
    }

    private static void createEvent() {
        if (currentUser == null) {
            System.out.println("Cadastre um usuário primeiro!");
            return;
        }
        System.out.print("Nome do evento: ");
        String name = scanner.nextLine();
        System.out.print("Endereço: ");
        String address = scanner.nextLine();
        System.out.print("Categoria (FESTA, SHOW, ESPORTIVO, CONFERENCIA, OUTRO): ");
        EventCategory category = EventCategory.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Descrição: ");
        String description = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1); // simples: amanhã

        Event event = new Event(UUID.randomUUID().toString(), name, address, category, dateTime, description);
        events.add(event);
        repo.saveEvents(events);
        System.out.println("Evento criado!");
    }

    private static void listEvents() {
        if (events.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (Event e : events) {
            System.out.println(e.getId() + " -> " + e);
        }
    }

    private static void joinEvent() {
        if (currentUser == null) {
            System.out.println("Cadastre um usuário primeiro!");
            return;
        }
        listEvents();
        System.out.print("Digite o ID do evento: ");
        String id = scanner.nextLine();
        for (Event e : events) {
            if (e.getId().equals(id)) {
                e.addParticipant(currentUser.getUsername());
                repo.saveEvents(events);
                System.out.println("Você entrou no evento!");
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    private static void cancelParticipation() {
        if (currentUser == null) {
            System.out.println("Cadastre um usuário primeiro!");
            return;
        }
        listEvents();
        System.out.print("Digite o ID do evento para sair: ");
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

    private static void exit() {
        System.out.println("Saindo do sistema...");
    }
}
