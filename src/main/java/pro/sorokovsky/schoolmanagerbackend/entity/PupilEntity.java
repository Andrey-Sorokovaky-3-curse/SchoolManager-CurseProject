package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "dbo", name = "Pupils")
@SuperBuilder
@PrimaryKeyJoinColumn(name = "UserId")
public class PupilEntity extends UserEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FatherId")
    private ParentEntity father;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MotherId")
    private ParentEntity mother;

    @Column(name = "ExtraInformation", nullable = false, length = 1000)
    private String extraInformation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClassId")
    private ClassEntity clazz;
}
