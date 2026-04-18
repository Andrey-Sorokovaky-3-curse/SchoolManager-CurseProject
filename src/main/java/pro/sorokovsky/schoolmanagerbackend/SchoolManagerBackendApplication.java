package pro.sorokovsky.schoolmanagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SchoolManagerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagerBackendApplication.class, args);
    }

}
