package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.enums.Role;
import com.bjit.tss.exception.TraineeSelectionException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.FinalTraineeSelectionRequest;
import com.bjit.tss.model.ListResponse;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.service.FinalTraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinalTraineeServiceImpl implements FinalTraineeService {

    private final CandidateRepository candidateRepository;
    @Override
    public ResponseEntity<ApiResponse<?>> allPassedFinalTrainee(String batchCode) {
        List<CandidateMarks> candidateMarks = candidateRepository.findAllByHrVivaPassedAndTechnicalVivaPassedAndAptitudeTestPassedAndWrittenMarksPassedAndExamineeInfoCourseInfoBatchCode(true,true,true,true,batchCode);
        ListResponse listResponse = ListResponse.builder()
                .dataLength(candidateMarks.size())
                .listResponse(candidateMarks)
                .build();
        return ApiResponseMapper.mapToResponseEntityOK(listResponse,"List of candidates who passed all the exam for this batch : "+batchCode);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> selectFinalTrainee(FinalTraineeSelectionRequest request) {
        List<CandidateMarks> candidateMarks = candidateRepository.findAllById(request.getCandidateIds());

        List<CandidateMarks> candidates = candidateMarks.stream().filter(candidate1 ->{
            return candidate1.getExamineeInfo().getRole() != Role.TRAINEE;
        } ).toList();
        if (candidates.size()==0){
            throw new UserException("No candidate is selected");
        }

        List<CandidateMarks> traineeLists = candidateRepository.findAllByExamineeInfoRoleAndExamineeInfoCourseInfoBatchCode(Role.TRAINEE,candidateMarks.get(0).getExamineeInfo().getCourseInfo().getBatchCode());

        Integer availableVacancy=  Integer.parseInt(String.valueOf(candidates.get(0).getExamineeInfo().getCourseInfo().getVacancy())) -Integer.parseInt(String.valueOf(traineeLists.size()));
        if (candidates.size()+traineeLists.size()>candidates.get(0).getExamineeInfo().getCourseInfo().getVacancy()){
            throw new TraineeSelectionException("Available vacancy is "+ availableVacancy+" number of trainees. You have selected "+ candidateMarks.size()+" trainees");
        }

        List<CandidateMarks> candidateMarksList = candidateMarks.stream().map(candidate -> {
            candidate.getExamineeInfo().setRole(Role.TRAINEE);
            return candidate;
        }).toList();

        List<CandidateMarks> savedCandidates= candidateRepository.saveAll(candidateMarksList);
        Integer newAvailableVacancy=  availableVacancy-savedCandidates.size();
        ListResponse<?> listResponse = ListResponse.builder()
                .listResponse(candidateMarksList)
                .dataLength(candidateMarksList.size())
                .build();
        return ApiResponseMapper.mapToResponseEntityOK(listResponse,"Trainee selection successful. Available vacancy is "+newAvailableVacancy);
    }
}