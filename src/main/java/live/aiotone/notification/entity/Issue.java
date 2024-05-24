package live.aiotone.notification.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issue")
public class Issue {
  @Id
  private String id;

}
