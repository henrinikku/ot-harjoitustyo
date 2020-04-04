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

@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
@Component
public class FlashCardUi extends Application {

    private static ConfigurableApplicationContext applicationContext;
    private static Stage stage;

    @Override
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext(
            new String[]{"spring-config.xml"}
        );
    }

    @Override
    public void start(Stage stage) {
        FlashCardUi.stage = stage;
        stage.setTitle("FlashCardApp");
        stage.setResizable(false);
        displayLoginView();
    }

    public static void displayLoginView() {
        displayView(Views.loginView());
    }

    public static void displayIndexView() {
        displayView(Views.indexView());
    }

    public static void displayNewDeckView() {
        displayView(Views.newDeckView());
    }

    @SneakyThrows(IOException.class)
    private static void displayView(URL url) {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
