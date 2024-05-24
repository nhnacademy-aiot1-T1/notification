package live.aiotone.notification.repository;

import live.aiotone.notification.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}
