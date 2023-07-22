package com.bjit.tss.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinalTraineeSelectionRequest {

    @Valid

    @NotNull(message = "Candidate ID is required")
    private List<Long> candidateIds;
}