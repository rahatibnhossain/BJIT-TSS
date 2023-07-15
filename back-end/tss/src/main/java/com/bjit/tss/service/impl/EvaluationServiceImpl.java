package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.EvaluatorInfo;
import com.bjit.tss.entity.HiddenCodeInfo;
import com.bjit.tss.entity.WrittenMarks;
import com.bjit.tss.exception.EmailException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;

import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.repository.EvaluatorRepository;
import com.bjit.tss.repository.HiddenCodeRepository;
import com.bjit.tss.service.EvaluationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluatorRepository evaluatorRepository;
    private final CandidateRepository candidateRepository;
    private final HiddenCodeRepository hiddenCodeRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> assignAnswerSheet(AssignAnswerSheetRequest request) {
        Optional<EvaluatorInfo> evaluatorInfo = evaluatorRepository.findById(request.getEvaluatorId());
         if (evaluatorInfo.isEmpty()){
             throw new UserException("Evaluator does not exist");
         }
        List<CandidateMarks> candidateMarks = candidateRepository.findAllById(request.getCandidateIds());

        if (candidateMarks.size()==0){
            throw new UserException("Candidates does not exist");
        }

        List<HiddenCodeInfo> result = candidateMarks.stream().map(candidate->{
            HiddenCodeInfo hiddenCode=null;

            if (candidate.getWrittenVivaMarks() == null || candidate.getWrittenVivaMarks().getWrittenMarkId() == null)
            {
                WrittenMarks writtenMarks = WrittenMarks.builder()
                        .evaluatorInfo(evaluatorInfo.get())
                        .build();
                candidate.setWrittenVivaMarks(writtenMarks);



            }
            else {
                WrittenMarks writtenMarks = candidate.getWrittenVivaMarks();
                candidate.setWrittenVivaMarks(writtenMarks);

            }

            Optional<HiddenCodeInfo> codeInfo = hiddenCodeRepository.findByCandidateMarksCandidateId(candidate.getCandidateId());
            if (codeInfo.isPresent()){
                hiddenCode = HiddenCodeInfo.builder()
                        .hiddenCode(codeInfo.get().getHiddenCode())
                        .candidateMarks(candidate)
                        .build();
            }
            else {
                hiddenCode = HiddenCodeInfo.builder()
                        .candidateMarks(candidate)
                        .build();
            }




            return hiddenCode;

        }).toList();







        List<HiddenCodeInfo> savedCandidatesWithHiddenCodes = hiddenCodeRepository.saveAll(result);
        return ApiResponseMapper.mapToResponseEntityOK(savedCandidatesWithHiddenCodes);
    }
}
