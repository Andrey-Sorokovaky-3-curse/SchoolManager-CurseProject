package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Class {
    private Long id;
    private Employee curator;
    private ClassType classType;
    private char letter;
    private int studyYear;
    private int createdAtYear;
}
