package live.aiotone.notification.repository;

import java.util.List;
import live.aiotone.notification.entity.Account;
import live.aiotone.notification.entity.enumfield.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

  List<Account> findAllByRole(AccountRole role);
}
