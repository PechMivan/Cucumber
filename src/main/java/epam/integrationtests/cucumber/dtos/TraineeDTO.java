package epam.integrationtests.cucumber.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDTO {
    public String username;
    public String firstname;
    public String lastname;
}
