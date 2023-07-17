package com.bjit.tss.repository;

import com.bjit.tss.entity.CandidateMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateMarks, Long> {

    Optional<CandidateMarks> findByExamineeInfoExamineeId(Long examineeId);

    List<CandidateMarks> findAllByExamineeInfoUserInfoUserIdAndExamineeInfoCourseInfoIsAvailable(Long userId, boolean b);

    List<CandidateMarks> findAllByHrVivaPassed(boolean b);

    List<CandidateMarks> findAllByHrVivaPassedAndTechnicalVivaPassedAndAptitudeTestPassedAndWrittenMarksPassed(boolean b, boolean b1, boolean b2, boolean b3);

    List<CandidateMarks> findAllByHrVivaPassedAndTechnicalVivaPassedAndAptitudeTestPassedAndWrittenMarksPassedAndExamineeInfoCourseInfoBatchCode(boolean b, boolean b1, boolean b2, boolean b3, String batchCode);
}