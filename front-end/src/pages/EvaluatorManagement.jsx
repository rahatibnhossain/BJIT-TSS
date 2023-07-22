import React, { useContext, useEffect, useState } from 'react';
import { Tab, Tabs, Box, Card, CardContent, CardMedia, Typography, Button, Grid } from '@mui/material';

import { LoginContext } from '../context/LoginContex';
import axios from '../api/axios';
import CourseComponent from '../components/CourseComponent';
import CourseCards from '../components/CourseCards';
import JSON2Message from '../services/JSON2Message';
import AddEvaluatorForm from '../components/AddEvaluatorForm';








const EvaluatorManagement = () => {





    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("")

    useEffect(() => {

        if (showSuccessMessage) {
            console.log(successMessage);
        }
        if (showErrorMessage) {
            console.log(errorMessage);
        }
    }, [showErrorMessage, showSuccessMessage])


    const resetFormFields = () => {
    };

    const { role, loggedIn, setCourses, courses, unavailableCourses, setUnavailableCourses } = useContext(LoginContext);

    const [value, setValue] = useState('all-evaluators');

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };








    const handleSubmit = (event) => {
        event.preventDefault();

        const formData = {
            courseName,
            courseDescription,
            startDate,
            endDate,
            batchCode,
            applicationDeadline,
            vacancy,
            writtenExamTime,
            applicantDashboardMessage,
            writtenShortlistedDashboardMessage,
            writtenPassedDashboardMessage,
            technicalVivaPassedDashboardMessage,
            aptitudeTestPassedDashboardMessage,
            hrVivaPassedDashboardMessage,
            traineeDashboardMessage,
            isAvailable
        };
        console.log(formData);

        const token = window.localStorage.getItem("tss-token");
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };




        if (value === "single-course" && role === "ADMIN") {

            axios.post(`/api/course/update/batch_code/${formData.batchCode}`, formData, config)
                .then((response) => {
                    console.log(response);
                    console.log(response?.data?.data);

                    setSingleCourse(response?.data?.data)
                    const courseId = response?.data?.data?.courseId

                    if (response.status === 200) {
                        setShowSuccessMessage(true)
                        setSuccessMessage(response.data.successMessage)

                        const updatedCourse = { ...formData, courseId }


                        const updatedCourseIndex = courses.findIndex((course) => course.courseId === response?.data?.data?.courseId);

                        // If the course is found in the array, replace it with the updatedCourse
                        if (updatedCourseIndex !== -1) {
                            setCourses((prevCourses) => [
                                ...prevCourses.slice(0, updatedCourseIndex),
                                updatedCourse,
                                ...prevCourses.slice(updatedCourseIndex + 1),
                            ]);
                        }



                        setTimeout(() => {
                            setShowSuccessMessage(false)
                            setSuccessMessage("")

                            if (isAvailable == true) {
                                setValue("available-courses")
                            } else {
                                setValue("unavailable-courses")
                            }
                            resetFormFields();
                        }, 2000);
                    }
                })
                .catch((error) => {
                    console.error('Error uploading files:', error);
                    setShowErrorMessage(true)
                    setErrorMessage(JSON2Message(JSON.stringify(error.response.data.errorMessage)))
                    setTimeout(() => {
                        setShowErrorMessage(false)
                        setErrorMessage("")

                    }, 5000);


                });


        } else {

            axios.post('/api/course/create', formData, config)
                .then((response) => {
                    console.log(response);

                    if (response.status === 201) {
                        setShowSuccessMessage(true)
                        setSuccessMessage(response.data.successMessage)
                        setCourses((prevCourses) => [...prevCourses, response.data.data]);

                        setTimeout(() => {
                            setShowSuccessMessage(false)
                            setSuccessMessage("")

                            if (isAvailable == true) {
                                setValue("available-courses")
                            } else {
                                setValue("unavailable-courses")
                            }
                            resetFormFields();
                        }, 2000);
                    }
                })
                .catch((error) => {
                    console.error('Error uploading files:', error);
                    setShowErrorMessage(true)
                    setErrorMessage(JSON2Message(JSON.stringify(error.response.data.errorMessage)))
                    setTimeout(() => {
                        setShowErrorMessage(false)
                        setErrorMessage("")

                    }, 5000);


                });
        }




    };

    console.log(role);

    useEffect(() => {

        if (value === "single-course" && role === "ADMIN") {


        } else {
            resetFormFields();
        }

        console.log(value);

    }, [value])
        ;


    return (
        <Box mt={1}>
            <Box sx={{
                width: '100%',
            }} >
                <Box position="fixed" >

                    <Tabs
                        value={value}
                        onChange={handleChange}
                        textColor="secondary"
                        indicatorColor="secondary"
                        aria-label="secondary tabs example"
                    >
                        <Tab value="all-evaluators" label="All Evaluators" />

                        <Tab value="add-evaluator" label="Add Evaluator" />
                        <Tab value="assign-evaluator" label="Assign Evaluator" />
                    </Tabs>
                </Box>

            </Box>


            {value === "all-evaluators" &&
                <AddEvaluatorForm />

            }



            {value === "add-evaluator" &&
                <AddEvaluatorForm />

            }

            {value === "assign-evaluator" &&
                <AddEvaluatorForm />

            }

            {
                value === "available-courses" && role === "ADMIN" &&

                <Box pt={role === "ADMIN" ? 7 : 0}>

                    <Grid container spacing={2} justifyContent="center">
                        {/* {courses?.data.data.listResponse.map((course) => ( */}

                        {courses?.map((course) => (
                            <Grid item key={course.courseId} xs={12} sm={6} md={4}>
                                <Box onClick={() => {
                                    setValue("single-course");
                                    setSingleCourse(course)
                                }}>

                                    <CourseCard>
                                        <CourseMedia component="img" image={course.imageUrl} alt={course.title} />
                                        <CourseContent>
                                            <CourseTitle variant="h6">{course.courseName}</CourseTitle>
                                            <CourseDescription variant="body2">{course.courseDescription}</CourseDescription>
                                        </CourseContent>
                                        <EnrollButton variant="contained" color="primary" size="small">
                                            Edit Course Details
                                        </EnrollButton>
                                    </CourseCard>
                                </Box>

                            </Grid>
                        ))}
                    </Grid>

                </Box>
            }

            {value === "add-course" && role === "ADMIN" &&
                <CourseComponent formHeader={"Add New Course"} buttonText={"Add Course"} handleSubmit={handleSubmit} courseName={courseName} setCourseName={setCourseName} batchCode={batchCode} setBatchCode={setBatchCode} isAvailable={isAvailable} setIsAvailable={setIsAvailable} showSuccessMessage={showSuccessMessage} successMessage={successMessage} showErrorMessage={showErrorMessage} errorMessage={errorMessage} courseDescription={courseDescription} setCourseDescription={setCourseDescription} vacancy={vacancy} setVacancy={setVacancy} applicationDeadline={applicationDeadline} setApplicationDeadline={setApplicationDeadline} writtenExamTime={writtenExamTime} setWrittenExamTime={setWrittenExamTime} startDate={startDate} setStartDate={setStartDate} endDate={endDate} setEndDate={setEndDate} applicantDashboardMessage={applicantDashboardMessage} setApplicantDashboardMessage={setApplicantDashboardMessage} writtenShortlistedDashboardMessage={writtenShortlistedDashboardMessage} setWrittenShortlistedDashboardMessage={setWrittenShortlistedDashboardMessage} writtenPassedDashboardMessage={writtenPassedDashboardMessage} setWrittenPassedDashboardMessage={setWrittenPassedDashboardMessage} technicalVivaPassedDashboardMessage={technicalVivaPassedDashboardMessage} setTechnicalVivaPassedDashboardMessage={setTechnicalVivaPassedDashboardMessage} aptitudeTestPassedDashboardMessage={aptitudeTestPassedDashboardMessage} setAptitudeTestPassedDashboardMessage={setAptitudeTestPassedDashboardMessage} hrVivaPassedDashboardMessage={hrVivaPassedDashboardMessage} setHrVivaPassedDashboardMessage={setHrVivaPassedDashboardMessage} traineeDashboardMessage={traineeDashboardMessage} setTraineeDashboardMessage={setTraineeDashboardMessage} />
            }
            {value === "unavailable-courses" && role === "ADMIN" &&

                <CourseCards courses={unavailableCourses} setValue={setValue} setSingleCourse={setSingleCourse} />

            }



        </Box >
    );
};

export default EvaluatorManagement