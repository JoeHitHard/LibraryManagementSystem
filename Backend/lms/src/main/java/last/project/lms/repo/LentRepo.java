package last.project.lms.repo;

import last.project.lms.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LentRepo extends JpaRepository<Users, Long> {
}
