package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ParentModel {
    private String phoneNumber;
    private String job;
}
