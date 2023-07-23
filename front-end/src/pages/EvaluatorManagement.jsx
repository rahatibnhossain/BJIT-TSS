import React, { useContext, useEffect, useState } from 'react';
import { Tab, Tabs, Box, Card, CardContent, CardMedia, Typography, Button, Grid } from '@mui/material';

import { LoginContext } from '../context/LoginContex';
import axios from '../api/axios';

import JSON2Message from '../services/JSON2Message';
import AddEvaluatorForm from '../components/AddEvaluatorForm';
import EvaluatorTable from '../components/EvaluatorTable';
import ApplicantTable from '../components/ApplicantTable'
import ApplicantTableList from '../components/ApplicantTableList';









const EvaluatorManagement = () => {

    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("")

    useEffect(() => {
        if (showErrorMessage) {
            console.log(errorMessage);
            setTimeout(() => {
                setErrorMessage("")
                setShowErrorMessage(false)
            }, 2000);
        }
        if (showSuccessMessage) {
            console.log(successMessage);
            setTimeout(() => {
                setSuccessMessage("")
                setShowSuccessMessage(false)
            }, 2000);
        }

    }, [showErrorMessage, showSuccessMessage])





    const [requestedEvaluatorId, setRequestedEvaluatorId] = useState(0);
    const [assignedApplicants, setAssignedApplicants] = useState([])

    const [searchedEvaluator, setSearchedEvaluator] = useState({});

    const showAssignedEvaluator = (evaluator) => {
        setRequestedEvaluatorId(evaluator.evaluatorId);
        console.log(evaluator.evaluatorId);
        setValue2("single-evaluator-applicant")
        setSearchedEvaluator(evaluator);


        const token = window.localStorage.getItem("tss-token");
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        axios.get(`/api/evaluator/assigned-candidates/${evaluator.evaluatorId}`, config)
            .then((response) => {
                console.log(response);
                if (response.status === 200) {
                    setShowSuccessMessage(true)
                    setSuccessMessage(response.data.successMessage)
                    console.log(response?.data?.data?.listResponse);
                    setAssignedApplicants(response?.data?.data?.listResponse)
                }
            }).catch((error) => {
                console.error('Error uploading written marks:', error);
                setShowErrorMessage(true)
                setErrorMessage(JSON2Message(JSON.stringify(error.response.data.errorMessage)))
            });
    }

    const [value2, setValue2] = useState('')









    const { allEvaluators, setAllEvaluators, role, loggedIn, setCourses, courses, unavailableCourses, setUnavailableCourses } = useContext(LoginContext);

    const [value, setValue] = useState('all-evaluators');
    const [value3, setValue3] = useState("")

    const handleChange = (event, newValue) => {
        setValue(newValue);
        setValue2("")
        setValue3("")
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


    }






    useEffect(() => {

        if (value === "single-course") {

        } else {
            // resetFormFields();
        }

        console.log(value);

    }, [value]);


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



            {value === "all-evaluators" && value2 == "" &&
                <Box pt={7}>
                    <EvaluatorTable data={allEvaluators} onRowClick={showAssignedEvaluator} />
                </Box>
            }


            {value == "all-evaluators" && value2 == "single-evaluator-applicant" &&
                <Box pt={7}>

                    <ApplicantTableList evaluator={searchedEvaluator} applicants={assignedApplicants} setApplicants={setAssignedApplicants} actionText={"Aassigned Applicant"} />
                </Box>
            }




            {value === "add-evaluator" &&
                <AddEvaluatorForm setAllEvaluators={setAllEvaluators} setValue={setValue} />

            }



            {value === "assign-evaluator" && value2 == "" &&
                <Box pt={7}>
                    <EvaluatorTable data={allEvaluators} onRowClick={showAssignedEvaluator} />
                </Box>
            }


            {value == "assign-evaluator" && value2 == "courses-for-assigining" &&
                <Box pt={7}>

                    {/* <ApplicantTable applicants={allApplicants} setApplicants={setAllApplicants} action={approveApplicant} actionText={"Approve Applicant"} /> */}
                </Box>
            }


            {value == "assign-evaluator" && value2 == "courses-for-assigining" && value3 == "courses-for-assigining" &&
                <h1>table</h1>
            }








        </Box >
    );
};

export default EvaluatorManagement