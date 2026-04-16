package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    private Long id;
    private String name;
    private String description;
    private Employee teacher;
}
