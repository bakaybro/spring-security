package kg.itacademy.securitylesson.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserAuthModel {
    private String name;
    private String password;
}
