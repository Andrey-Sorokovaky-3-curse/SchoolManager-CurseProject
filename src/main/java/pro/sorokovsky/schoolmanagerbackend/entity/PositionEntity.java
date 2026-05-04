package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Entity
@Table(schema = "dbo", name = "Positions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Salary", nullable = false)
    private BigDecimal salary;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PositionsResponsibilities",
            joinColumns = @JoinColumn(name = "PositionId"),
            inverseJoinColumns = @JoinColumn(name = "ResponsibilityId")
    )
    @Builder.Default
    private List<ResponsibilityEntity> responsibilities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PositionsRequirements",
            joinColumns = @JoinColumn(name = "PositionId"),
            inverseJoinColumns = @JoinColumn(name = "RequirementId")
    )
    @Builder.Default
    private List<RequirementEntity> requirements = new ArrayList<>();
}
