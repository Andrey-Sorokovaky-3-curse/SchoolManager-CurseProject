package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Employee extends User {
    private String phoneNumber;
    private List<Position> positions;
}
