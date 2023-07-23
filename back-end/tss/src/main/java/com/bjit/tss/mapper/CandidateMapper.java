package com.bjit.tss.mapper;

import com.bjit.tss.entity.CandidateMarks;
import com.bjit.tss.entity.CourseInfo;
import com.bjit.tss.model.CourseModel;
import com.bjit.tss.model.response.CandidateResponse;

public class CandidateMapper {

    public static CandidateResponse mapToCandidateResponse(CandidateMarks candidateMarks ) {
        CandidateResponse candidateResponse = CandidateResponse.builder()
                .candidateId(candidateMarks.getCandidateId())
                .firstName(candidateMarks.getExamineeInfo().getUserInfo().getFirstName())
                .lastName(candidateMarks.getExamineeInfo().getUserInfo().getLastName())
                .email(candidateMarks.getExamineeInfo().getUserInfo().getEmail())
                .cgpa(candidateMarks.getExamineeInfo().getUserInfo().getCgpa())
                .presentAddress(candidateMarks.getExamineeInfo().getUserInfo().getPresentAddress())
                .degreeName(candidateMarks.getExamineeInfo().getUserInfo().getDegreeName())
                .passingYear(candidateMarks.getExamineeInfo().getUserInfo().getPassingYear())
                .courseName(candidateMarks.getExamineeInfo().getCourseInfo().getCourseName())
                .build();
        return candidateResponse;
    }
}