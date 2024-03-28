package epam.integrationtests.cucumber.dtos.response;

import epam.integrationtests.cucumber.dtos.TraineeDTO;

import java.util.List;

public class TrainerFindResponse {
    public String firstname;
    public String lastname;
    public String specialization;
    public boolean isActive;
    public List<TraineeDTO> trainees;
}
