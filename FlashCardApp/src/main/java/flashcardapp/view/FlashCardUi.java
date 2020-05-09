package flashcardapp.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Responsible for loading and displaying views.
 */
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@Component
public class FlashCardUi extends Application {

    private static ConfigurableApplicationContext applicationContext;
    private static Stage stage;

    /**
     * Sets up the application, i.e., loads spring app context.
     */
    @Override
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext(
            new String[]{"spring-config.xml"}
        );
    }

    /**
     * Sets up the ui and displays the login view.
     *
     * @param stage The main stage
     */
    @Override
    public void start(Stage stage) {
        FlashCardUi.stage = stage;
        stage.setTitle("FlashCardApp");
        stage.setResizable(false);
        displayLoginView();
    }

    /**
     * Displays the login view
     */
    public static void displayLoginView() {
        displayView(Views.loginView());
    }

    /**
     * Displays the default view for logged in user
     */
    public static void displayIndexView() {
        displayView(Views.indexView());
    }

    /**
     * Displays the form for creating new decks
     */
    public static void displayNewDeckView() {
        displayView(Views.newDeckView());
    }

    /**
     * Displays the single deck view
     */
    public static void displaySingleDeckView() {
        displayView(Views.singleDeckView());
    }

    /**
     * Displays the single card view
     */
    public static void displaySingleCardView() {
        displayView(Views.singleCardView());
    }

    /**
     * Displays the view for studying some deck
     */
    public static void displayStudyView() {
        displayView(Views.studyView());
    }

    /**
     * Displays the given view and and initializes its controller class
     *
     * @param url Path to the view to be displayed
     */
    @SneakyThrows(IOException.class)
    private static void displayView(URL url) {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Closes all connections and stops the application.
     */
    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
