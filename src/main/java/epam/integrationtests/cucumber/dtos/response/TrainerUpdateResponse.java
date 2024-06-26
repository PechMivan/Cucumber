package epam.integrationtests.cucumber.dtos.response;

import epam.integrationtests.cucumber.dtos.TraineeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerUpdateResponse {
    public String username;
    public String firstname;
    public String lastname;
    public String specialization;
    public boolean active;
    public List<TraineeDTO> trainees;
}
