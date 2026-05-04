package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "dbo", name = "Employees")
@PrimaryKeyJoinColumn(name = "UserId")
public class EmployeeEntity extends UserEntity {

    @Column(name = "PhoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "EmployeesPositions",
            joinColumns = @JoinColumn(name = "EmployeeId"),
            inverseJoinColumns = @JoinColumn(name = "PositionId")
    )
    @Builder.Default
    private List<PositionEntity> positions = new ArrayList<>();
}
