package com.bjit.tss.service.impl;

import com.bjit.tss.entity.*;
import com.bjit.tss.exception.EvaluationException;
import com.bjit.tss.exception.HiddenCodeException;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.AssignAnswerSheetRequest;
import com.bjit.tss.model.UploadWrittenMarkRequest;
import com.bjit.tss.repository.*;
import com.bjit.tss.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluatorRepository evaluatorRepository;
    private final CandidateRepository candidateRepository;
    private final HiddenCodeRepository hiddenCodeRepository;
    private final DataStorageRepository dataStorageRepository;
    private final WrittenMarksRepository writtenMarksRepository;
    private final WrittenQuestionMarksRepository writtenQuestionMarksRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> assignAnswerSheet(AssignAnswerSheetRequest request) {
        Optional<EvaluatorInfo> evaluatorInfo = evaluatorRepository.findById(request.getEvaluatorId());
        if (evaluatorInfo.isEmpty()) {
            throw new UserException("Evaluator does not exist");
        }
        List<CandidateMarks> candidateMarks = candidateRepository.findAllById(request.getCandidateIds());
        if (candidateMarks.size() == 0) {
            throw new UserException("Candidates does not exist");
        }
        List<HiddenCodeInfo> result = candidateMarks.stream().map(candidate -> {
            HiddenCodeInfo hiddenCode = null;
            if (candidate.getWrittenMarks() == null || candidate.getWrittenMarks().getWrittenMarkId() == null) {
                WrittenMarks writtenMarks = WrittenMarks.builder()
                        .evaluatorInfo(evaluatorInfo.get())
                        .build();
                candidate.setWrittenMarks(writtenMarks);
            } else {
                WrittenMarks writtenMarks = candidate.getWrittenMarks();
                writtenMarks.setEvaluatorInfo(evaluatorInfo.get());
                candidate.setWrittenMarks(writtenMarks);
            }

            Optional<HiddenCodeInfo> codeInfo = hiddenCodeRepository.findByCandidateMarksCandidateId(candidate.getCandidateId());
            if (codeInfo.isPresent()) {
                hiddenCode = HiddenCodeInfo.builder()
                        .hiddenCode(codeInfo.get().getHiddenCode())
                        .candidateMarks(candidate)
                        .build();
            } else {
                hiddenCode = HiddenCodeInfo.builder()
                        .candidateMarks(candidate)
                        .build();
            }

            return hiddenCode;
        }).toList();

        List<HiddenCodeInfo> savedCandidatesWithHiddenCodes = hiddenCodeRepository.saveAll(result);
        return ApiResponseMapper.mapToResponseEntityOK(savedCandidatesWithHiddenCodes);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> uploadWrittenMark(UploadWrittenMarkRequest request) {
        Optional<HiddenCodeInfo> hiddenCodeInfo = hiddenCodeRepository.findById(request.getHiddenCode());
        if (hiddenCodeInfo.isEmpty() || hiddenCodeInfo.get().getCandidateMarks().getWrittenMarks().getEvaluatorInfo() == null) {
            throw new HiddenCodeException("Invalid hidden code");
        }
        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(hiddenCodeInfo.get().getCandidateMarks().getWrittenMarks().getEvaluatorInfo().getEvaluatorId(), loginInfo.getEvaluatorInfo().getEvaluatorId())) {
            throw new HiddenCodeException("Invalid hidden code");
        }

        Optional<DataStorage> dataStorage = dataStorageRepository.findByDataKey("WrittenQuestionNumber");
        if (dataStorage.isEmpty()) {
            throw new EvaluationException("Ask admin to set the number of written question");
        }

        if (Integer.parseInt(dataStorage.get().getDataValue()) < (Integer) request.getMarks().size()) {
            throw new EvaluationException("Maximum number of written questions are : " + dataStorage.get().getDataValue());
        }

        WrittenMarks writtenMarks = hiddenCodeInfo.get().getCandidateMarks().getWrittenMarks();
        List<Long> ids = request.getMarks().stream().map(iterate -> writtenMarks.getWrittenMarkId()).toList();

        List<Long> writtenQuestionIds = null;
        try {
            writtenQuestionIds = IntStream.range(0, ids.size())
                    .mapToObj(i -> writtenMarks.getWrittenQuestionMarks().get(i).getWrittenQuestionId()).toList();
        } catch (Exception ignored) {
        }

        AtomicReference<Integer> questionNo = new AtomicReference<>(0);
        AtomicReference<Float> totalMark = new AtomicReference<>(0.0f);

        List<WrittenQuestionMarks> writtenQuestionMarks = null;
        writtenQuestionMarks = request.getMarks().stream().map(question -> {
            questionNo.set(questionNo.get() + 1);
            if (question < 0) {
                throw new EvaluationException("Mark cannot be negative");
            }
            totalMark.set(totalMark.get() + question);
            return WrittenQuestionMarks.builder()
                    .writtenQuestionMark(question)
                    .questionNo(Integer.parseInt(String.valueOf(questionNo)))
                    .build();
        }).toList();
        questionNo.set(0);

        List<WrittenQuestionMarks> writtenQuestionMarksList = null;
        if (writtenQuestionIds != null) {
            if (writtenMarks.getWrittenQuestionMarks().size() == writtenQuestionIds.size()) {
                List<Long> finalWrittenQuestionIds = writtenQuestionIds;
                List<WrittenQuestionMarks> finalWrittenQuestionMarks = writtenQuestionMarks;
                writtenQuestionMarksList = IntStream.range(0, writtenQuestionMarks.size())
                        .mapToObj(i -> {
                            finalWrittenQuestionMarks.get(i).setWrittenQuestionId(finalWrittenQuestionIds.get(i));
                            return finalWrittenQuestionMarks.get(i);
                        }).toList();
            }
        }

        Optional<DataStorage> maximumMark = dataStorageRepository.findByDataKey("TotalMarkWritten");
        Optional<DataStorage> passingMark = dataStorageRepository.findByDataKey("PassingMarkWritten");
        if (maximumMark.isEmpty()) {
            throw new EvaluationException("Ask admin to set total mark of written exam.");
        }

        if (passingMark.isEmpty()) {
            throw new EvaluationException("Ask admin to set passing mark of written exam");
        }

        if (totalMark.get() > Float.parseFloat(maximumMark.get().getDataValue())) {
            throw new EvaluationException("Total written mark cannot be greater than " + maximumMark);
        }

        boolean isPassed = totalMark.get() >= Float.parseFloat(passingMark.get().getDataValue());

        List<WrittenQuestionMarks> savedQuestionMarks = null;
        if (writtenQuestionIds != null) {
            if (writtenMarks.getWrittenQuestionMarks().size() == writtenQuestionIds.size()) {
                assert writtenQuestionMarksList != null;
                savedQuestionMarks = writtenQuestionMarksRepository.saveAll(writtenQuestionMarksList);
            } else {
                savedQuestionMarks = writtenQuestionMarksRepository.saveAll(writtenQuestionMarks);
            }
        } else {
            savedQuestionMarks = writtenQuestionMarksRepository.saveAll(writtenQuestionMarks);
        }

        writtenMarks.setWrittenMark(totalMark.get());
        writtenMarks.setWrittenQuestionMarks(savedQuestionMarks);
        writtenMarks.setPassed(isPassed);

        WrittenMarks savedMarks = writtenMarksRepository.save(writtenMarks);
        return ApiResponseMapper.mapToResponseEntityOK(savedMarks, "Mark updated successfully");
    }
}