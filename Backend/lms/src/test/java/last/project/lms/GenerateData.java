package last.project.lms;

import last.project.lms.entities.Users;
import last.project.lms.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class GenerateData implements CommandLineRunner {

    private final UserRepo userRepo;

    public GenerateData(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(GenerateData.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepo.deleteAll();
        Users user = new Users("admin", "admin", "admin@lms.edu", "admin", "admin");
        userRepo.save(user);
        System.out.println("User created successfully with ID: " + user.getUserId());
        exit(0);
    }
}
