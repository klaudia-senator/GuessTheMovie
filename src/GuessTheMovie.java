import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class GuessTheMovie {

    private String randomMovie = "";
    private String encryptedMovie = "";
    private String wrongLetters;
    private String rightLetters;
    private int wrongAttempts;

    String[] getMovieList(String Movies) throws FileNotFoundException {
        File movieDatabase = new File(Movies); //tworzy obiekt File na podstawie pliku txt Movies
        Scanner movieDatabaseScanner = new Scanner(movieDatabase); //Scanner - odczytywanie zawartości pliku
        StringBuilder movieListReader = new StringBuilder(); //tworzy łańcuch zawięrający listę filmów
        while (movieDatabaseScanner.hasNextLine()) { //sprawdza czy istnieje kolejna linia w pliku
            movieListReader.append(movieDatabaseScanner.nextLine()); //dodaje odczytaną linię do movieListReader za pomocą append
            movieListReader.append("\n"); //dodaje znak aby oddzielić linie w łańcuchu
        }
        movieDatabaseScanner.close(); //zamyka plik
        return movieListReader.toString().trim().split("\n"); //zawartość jest przekształcana w łańcuch znaków, rozdzielana i zwaracana tablica z listą filmów
    }
    String generateRandomMovie(String[] movies) {
        int movieIndex = (int) (Math.random() * movies.length); //losuje indeks 0-1 i mnoży przez długość listy filmów
        return movies[movieIndex].replaceAll("[^a-zA-Z\\s]", "").toLowerCase(); //pobiera wybrany na podstawie indeksu film, usuwa niepotrzebne znaki, zamienia litery na małe
    }
    void setRandomMovie(String randomMovie) { //przypisuje wygenerowany tytuł do zmiennej w obiekcie gry aby później mieć do niego dostęp
        this.randomMovie = randomMovie;
    }
    public String getRandomMovie() { //wyświetla wygenerowany tytuł
        return randomMovie;
    }
    String encryptMovie(String randomMovie) {
        return randomMovie.replaceAll("[a-zA-Z]", "-"); //szyfruje tytuł, zastępując znaki -
    }
    void setEncryptedMovie(String encryptedMovie) {
        this.encryptedMovie = encryptedMovie; //przypisuje zaszyfrowany tytuł do obiektu w klasie
    }
    String getEncryptedMovie() {
        return encryptedMovie; //zwraca zaszyfrowany tytuł
    }
    void setWrongAttempts(int wrongAttempts) {
        this.wrongAttempts = wrongAttempts; //ustawia liczbę błędnych prób
    }
    int getWrongAttempts() {
        return wrongAttempts; //zwraca liczbę błędnych prób przechowywaną w obiekcie gry
    }
    int getAttemptsLeft() {
        return 10 - wrongAttempts; //zwraca liczbę pozostałych prób
    }
    private void setWrongLetters(String wrongLetters) {
        this.wrongLetters = wrongLetters; //ustawia łańcuch znaków, zawierający błędne litery
    }
    String getWrongLetters() {
        return wrongLetters; //zwraca błędny łańcuch znaków
    }
    private void setRightLetters(String rightLetters) {
        this.rightLetters = rightLetters; //ustawia łańcuch zawięrający poprawne litery
    }
    private String getRightLetters() {
        return rightLetters; //zwraca łańcuch
    }
    boolean isValidGuess(char guess) {
        return guess >= 'a' && guess <= 'z'; //sprawdza czy podana litera mieści się w zakresie a-z
    }
    boolean hasAlreadyGuessed(char guess) {
        if (getWrongLetters() == null) {
            setWrongLetters(""); // Inicjalizacja, jeśli jest null
        }
        if (getRightLetters() == null) {
            setRightLetters(""); // Inicjalizacja, jeśli jest null
        }

        return getWrongLetters().indexOf(guess) >= 0 || getRightLetters().indexOf(guess) >= 0;
    }

    void checkGuess(char guess) {
        if (getRandomMovie().indexOf(guess) >= 0) {
            StringBuilder encryptedMovieTracker = new StringBuilder(getEncryptedMovie());
            for (int i = 0; i < encryptedMovie.length(); i++) {
                if (guess == randomMovie.charAt(i)) {
                    encryptedMovieTracker.setCharAt(i, guess);
                }
            }
            setEncryptedMovie(encryptedMovieTracker.toString());
            rightLetters += guess + " ";
            setRightLetters(rightLetters);
        } else {
            wrongLetters += guess + " ";
            setWrongLetters(wrongLetters);
            wrongAttempts++;
            setWrongAttempts(wrongAttempts);
        }
    }
    boolean gameWon() {
        return getEncryptedMovie().indexOf('-') < 0;
    }
    private boolean gameLost() {
        return getWrongAttempts() == 10;
    }
    boolean gameOver() {
        return gameWon() || gameLost();
    }
}

