package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionModel {
    private Long id;
    private String name;
    private double salary;
    private List<RequirementModel> requirements;
    private List<ResponsibilityModel> responsibilities;
}
