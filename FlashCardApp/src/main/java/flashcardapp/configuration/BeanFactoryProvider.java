package flashcardapp.configuration;

import flashcardapp.controller.LoginController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactoryProvider {
    @Bean
    public LoginController loginController() {
        return new LoginController();
    }
}
