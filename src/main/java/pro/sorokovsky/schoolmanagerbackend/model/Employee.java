package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Employee {
    private String phoneNumber;
    private List<Position> positions;
}
