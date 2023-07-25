package com.bjit.tss.repository;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateMarks, Long> {

    Optional<CandidateMarks> findByExamineeInfoExamineeId(Long examineeId);

    List<CandidateMarks> findAllByExamineeInfoUserInfoUserIdAndExamineeInfoCourseInfoIsAvailable(Long userId, boolean b);

    List<CandidateMarks> findAllByHrVivaPassedAndTechnicalVivaPassedAndAptitudeTestPassedAndWrittenMarksPassedAndExamineeInfoCourseInfoBatchCode(boolean b, boolean b1, boolean b2, boolean b3, String batchCode);

    List<CandidateMarks> findAllByExamineeInfoRoleAndExamineeInfoCourseInfoBatchCode(Role role, String batchCode);

    List<CandidateMarks> findByWrittenMarksEvaluatorInfoEvaluatorIdAndExamineeInfoCourseInfoIsAvailable(Long evaluatorId, boolean b);

    List<CandidateMarks> findAllByExamineeInfoRoleAndExamineeInfoCourseInfoBatchCodeAndWrittenMarksEvaluatorInfoIsNull(Role role, String batchCode);

    List<CandidateMarks> findAllByAptitudeTestPassedAndExamineeInfoCourseInfoBatchCodeAndExamineeInfoCourseInfoIsAvailable(boolean b, String batchCode, boolean b1);

    List<CandidateMarks> findAllByWrittenMarksPassedAndExamineeInfoCourseInfoBatchCodeAndExamineeInfoCourseInfoIsAvailable(boolean b, String batchCode, boolean b1);

    List<CandidateMarks> findAllByTechnicalVivaPassedAndExamineeInfoCourseInfoBatchCodeAndExamineeInfoCourseInfoIsAvailable(boolean b, String batchCode, boolean b1);

    List<CandidateMarks> findAllByHrVivaPassedAndExamineeInfoCourseInfoBatchCodeAndExamineeInfoCourseInfoIsAvailable(boolean b, String batchCode, boolean b1);

    List<CandidateMarks> findAllByExamineeInfoRole(Role role);
}