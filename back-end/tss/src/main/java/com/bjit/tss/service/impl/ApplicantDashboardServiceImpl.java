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
import com.bjit.tss.enums.Role;
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
                applicantDashboardMessage.setCourseName(examinee.getCourseInfo().getCourseName());

                return applicantDashboardMessage;
            }).toList();

        } else {
            dashboardMessage = candidateMarks.stream().map(candidate -> {
                ApplicantDashboardMessage applicantDashboardMessage = new ApplicantDashboardMessage();
                if (candidate.getExamineeInfo().getRole() == Role.CANDIDATE) {

                    if (candidate.getHrViva().getPassed() != null ) {
                        if (candidate.getHrViva().getPassed()){
                            applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getHrVivaPassedDashboardMessage());

                        } else {
                            applicantDashboardMessage.setDashboardMessage("Sorry you did not qualify HR viva. Best of luck.");

                        }

                    } else if (candidate.getTechnicalViva().getPassed() != null) {

                        if (candidate.getTechnicalViva().getPassed()) {
                            applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getTechnicalVivaPassedDashboardMessage());
                        } else {
                            applicantDashboardMessage.setDashboardMessage("Sorry you did not qualify technical viva. Best of luck.");
                        }

                    } else if (candidate.getAptitudeTest().getPassed() != null) {

                        if (candidate.getAptitudeTest().getPassed()) {
                            applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getAptitudeTestPassedDashboardMessage());
                        } else {
                            applicantDashboardMessage.setDashboardMessage("Sorry you did not qualify aptitude test. Best of luck.");
                        }

                    } else if (candidate.getWrittenMarks().getPassed() != null) {

                        if (candidate.getWrittenMarks().getPassed()) {
                            applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getWrittenPassedDashboardMessage());
                        } else {
                            applicantDashboardMessage.setDashboardMessage("Sorry you did not qualify written test. Best of luck.");
                        }

                    } else {
                        applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getWrittenShortlistedDashboardMessage());
                    }

                } else if (candidate.getExamineeInfo().getRole() == Role.TRAINEE) {
                    applicantDashboardMessage.setDashboardMessage(candidate.getExamineeInfo().getCourseInfo().getTraineeDashboardMessage());
                } else {
                    throw new UserException("Invalid Request");
                }
                applicantDashboardMessage.setCourseName(candidate.getExamineeInfo().getCourseInfo().getCourseName());
                return applicantDashboardMessage;
            }).toList();
        }

        if (dashboardMessage.size() == 0) {
            ApplicantDashboardMessage applicantDashboardMessage = new ApplicantDashboardMessage();
            applicantDashboardMessage.setDashboardMessage("You have not been applied to any course");
            ListResponse<?> listResponse = ListResponse.builder()
                    .dataLength(0)
                    .listResponse(applicantDashboardMessage)
                    .build();
            return ApiResponseMapper.mapToResponseEntityOK(listResponse, "Applicant Dashboard");
        }

        ListResponse<?> listResponse = ListResponse.builder()
                .dataLength(dashboardMessage.size())
                .listResponse(dashboardMessage)
                .build();
        return ApiResponseMapper.mapToResponseEntityOK(listResponse, "Applicant Dashboard");
    }
}