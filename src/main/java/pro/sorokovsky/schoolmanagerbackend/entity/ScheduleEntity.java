package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Schedules")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @JoinColumn(name = "SubjectId", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private SubjectEntity subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClassId", nullable = false)
    private ClassEntity clazz;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "DateOfWeek", nullable = false)
    private Integer dateOfWeek;

    @Column(name = "StartTime", nullable = false)
    private Time startTime;

    @Column(name = "EndTime", nullable = false)
    private Time endTime;
}
