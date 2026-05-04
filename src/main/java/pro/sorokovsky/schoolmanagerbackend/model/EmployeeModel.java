package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EmployeeModel {
    private String phoneNumber;
    private UserModel user;
    private List<PositionModel> positions;
}
