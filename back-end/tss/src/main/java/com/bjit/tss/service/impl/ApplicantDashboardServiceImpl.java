package com.bjit.tss.service.impl;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.ExamineeInfo;
import com.bjit.tss.entity.LoginInfo;
import com.bjit.tss.exception.UserException;
import com.bjit.tss.mapper.ApiResponseMapper;
import com.bjit.tss.model.ApiResponse;
import com.bjit.tss.model.ApplicantDashboardMessage;
import com.bjit.tss.model.ListResponse;
import com.bjit.tss.repository.CandidateRepository;
import com.bjit.tss.repository.ExamineeRepository;
import com.bjit.tss.role.Role;
import com.bjit.tss.service.ApplicantDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantDashboardServiceImpl implements ApplicantDashboardService {

    private final ExamineeRepository examineeRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public ResponseEntity<ApiResponse<?>> getApplicantDashboardData() {

        LoginInfo loginInfo = (LoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ExamineeInfo> examineeInfo = examineeRepository.findAllByUserInfoUserIdAndCourseInfoIsAvailable(loginInfo.getUserInfo().getUserId(), true);

        List<CandidateMarks> candidateMarks = candidateRepository.findAllByExamineeInfoUserInfoUserIdAndExamineeInfoCourseInfoIsAvailable(loginInfo.getUserInfo().getUserId(), true);

        List<ApplicantDashboardMessage> dashboardMessage;

        if (candidateMarks.size() == 0) {
           dashboardMessage = examineeInfo.stream().map(examinee -> {
                ApplicantDashboardMessage applicantDashboardMessage = new ApplicantDashboardMessage();
                applicantDashboardMessage.setDashboardMessage(examinee.getCourseInfo().getApplicantDashboardMessage());

                return applicantDashboardMessage;
            }).toList();

        } else {
          dashboardMessage = candidateMarks.stream().map(candidate -> {
                ApplicantDashboardMessage applicantDashboardMessage = new ApplicantDashboardMessage();
                if (candidate.getExamineeInfo().getRole() == Role.CANDIDATE) {

                    if (candidate.getAptitudeTest().getPassed() != null && candidate.getAptitudeTest().getPassed()) {
                        applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getAptitudeTestPassedDashboardMessage());

                    } else if (candidate.getTechnicalViva().getPassed() != null && candidate.getTechnicalViva().getPassed()) {
                        applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getTechnicalVivaPassedDashboardMessage());

                    } else if (candidate.getWrittenMarks().getPassed() != null && candidate.getWrittenMarks().getPassed()) {
                        applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getWrittenPassedDashboardMessage());

                    } else if(candidate.getWrittenMarks().getPassed() == null ) {
                        applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getWrittenShortlistedDashboardMessage());

                    } else {
                        applicantDashboardMessage.setDashboardMessage("Sorry you did not qualify the process. Best of luck for you.");
                    }
                } else if (candidate.getExamineeInfo().getRole() == Role.TRAINEE) {
                    applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getTraineeDashboardMessage());
                } else {
                    throw new UserException("Invalid Request");
                }
                return applicantDashboardMessage;
            }).toList();

        }

      ListResponse<?>  listResponse = ListResponse.builder()
                .dataLength(dashboardMessage.size())
                .listResponse(dashboardMessage)
                .build();
        return ApiResponseMapper.mapToResponseEntityOK(listResponse, "Applicant Dashboard");
    }
}