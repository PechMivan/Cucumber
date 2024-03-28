package epam.integrationtests.cucumber.dtos.response;

import epam.integrationtests.cucumber.dtos.TrainerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeUpdateResponse {
    public String username;
    public String firstname;
    public String lastname;
    public String dateOfBirth;
    public String address;
    public boolean active;
    public List<TrainerDTO> trainers;
}
