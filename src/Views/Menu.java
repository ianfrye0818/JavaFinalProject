package Views;

/**
 *
 * @author Ian Frye
 *
 * This interface makes sure that all the menus contain a displayMenu and handleChoice method.
 */
public interface Menu {
    void displayMenu();

    void handleChoice(int choice);
}
