package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Classes")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CuratorId")
    private EmployeeEntity curator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClassTypeId")
    private ClassTypeEntity classType;

    @Column(name = "Letter", nullable = false)
    private Character letter;

    @Column(name = "StudyYear", nullable = false)
    private Integer studyYear;

    @Column(name = "CreatedAtYear", nullable = false)
    private Integer createdAtYear;

    @Formula("(SELECT COUNT(*) FROM dbo.Pupils p WHERE p.ClassId = Id)")
    private Integer pupilsCount;
}
