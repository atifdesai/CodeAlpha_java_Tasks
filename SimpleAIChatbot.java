import java.util.HashMap;
import java.util.Scanner;

public class SimpleAIChatbot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined responses
        HashMap<String, String> responses = new HashMap<>();
        responses.put("hello", "Hi there! How can I help you today?");
        responses.put("how are you", "I'm just a program, but I'm doing fine! How about you?");
        responses.put("what is your name", "I'm ChatGPT, your virtual assistant.");
        responses.put("bye", "Goodbye! Have a great day!");
        responses.put("help", "You can ask me about my name, how I am, or just say hello!");

        System.out.println("AI Chatbot: Hello! Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase().trim();

            if (userInput.equals("bye")) {
                System.out.println("AI Chatbot: " + responses.get("bye"));
                break;
            }

            // Respond to the user
            if (responses.containsKey(userInput)) {
                System.out.println("AI Chatbot: " + responses.get(userInput));
            } else {
                System.out.println("AI Chatbot: I'm not sure how to respond to that. Try asking for 'help'.");
            }
        }

        scanner.close();
    }
}
