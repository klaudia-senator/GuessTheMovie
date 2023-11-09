import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GuessTheMovie game = new GuessTheMovie();

        try {
            // Pobierz listę filmów z pliku
            String[] movies = game.getMovieList("Movies.txt");

            // Wygeneruj losowy tytuł i ustaw go w grze
            String randomMovie = game.generateRandomMovie(movies);
            game.setRandomMovie(randomMovie);

            // Zaszyfruj tytuł i ustaw go w grze
            game.setEncryptedMovie(game.encryptMovie(randomMovie));

            Scanner scanner = new Scanner(System.in);

            while (!game.gameOver()) {
                // Wyświetl zaszyfrowany tytuł
                System.out.println("Zgadnij film: " + game.getEncryptedMovie());

                // Wyświetl błędne zgadywania
                System.out.println("Błędne zgadywania: " + game.getWrongLetters());

                // Pobierz zgadywaną literę od gracza
                System.out.print("Podaj literę: ");
                char guess = scanner.next().charAt(0);

                // Sprawdź, czy litera jest prawidłowa
                if (game.isValidGuess(guess)) {
                    // Sprawdź, czy litera została już wcześniej zgadywana
                    if (!game.hasAlreadyGuessed(guess)) {
                        // Sprawdź, czy zgadywana litera istnieje w tytule
                        game.checkGuess(guess);
                    } else {
                        System.out.println("Ta litera została już zgadnięta. Spróbuj ponownie.");
                    }
                } else {
                    System.out.println("To nie jest litera. Spróbuj ponownie.");
                }
            }

            // Po zakończeniu gry wyświetl wynik
            if (game.gameWon()) {
                System.out.println("Gratulacje! Wygrałeś! Tytuł filmu to: " + game.getRandomMovie());
            } else {
                System.out.println("Przegrałeś. Tytuł filmu to: " + game.getRandomMovie());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się wczytać listy filmów. Sprawdź ścieżkę do pliku.");
        }
    }

    // ... pozostała część klasy GuessTheMovie0
}
