package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassModel {
    private Long id;
    private EmployeeModel curator;
    private ClassTypeModel classType;
    private char letter;
    private int studyYear;
    private int createdAtYear;
}
