package live.aiotone.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountInfo {
    private final Long id;
    private final String name;
    private final String phone;
    private final String email;
    private final String role;
    private final String authType;
}
