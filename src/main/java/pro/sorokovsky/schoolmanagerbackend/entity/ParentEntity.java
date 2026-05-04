package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "dbo", name = "Parents")
@PrimaryKeyJoinColumn(name = "UserId")
public class ParentEntity extends UserEntity {
    @Column(name = "Job", length = 1000)
    private String job;

    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;
}
