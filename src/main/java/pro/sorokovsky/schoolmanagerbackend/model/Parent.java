package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parent extends User {
    private String phoneNumber;
    private String job;
}
