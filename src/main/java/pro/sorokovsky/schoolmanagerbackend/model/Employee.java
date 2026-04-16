package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends User {
    private String phoneNumber;
    private List<Position> positions;
}
