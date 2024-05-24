package live.aiotone.notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import live.aiotone.notification.entity.enumfield.AccountRole;
import live.aiotone.notification.entity.enumfield.AccountStatus;
import live.aiotone.notification.entity.enumfield.AuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "account")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String phone;

  @Column
  private String email;

  @Column(name = "auth_type")
  @Enumerated(EnumType.STRING)
  private AuthType authType;

  @Column
  @ColumnDefault("'ACTIVE'")
  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @Column
  @ColumnDefault("'NONE'")
  @Enumerated(EnumType.STRING)
  private AccountRole role;

}
