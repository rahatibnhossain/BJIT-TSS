import React, { useContext, useEffect, useState } from 'react';
import { Tab, Tabs, Box, Card, CardContent, CardMedia, Typography, Button, Grid } from '@mui/material';

import { LoginContext } from '../context/LoginContex';
import axios from '../api/axios';

import JSON2Message from '../services/JSON2Message';
import AddEvaluatorForm from '../components/AddEvaluatorForm';
import EvaluatorTable from '../components/EvaluatorTable';









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




    const { allEvaluators, setAllEvaluators, role, loggedIn, setCourses, courses, unavailableCourses, setUnavailableCourses } = useContext(LoginContext);

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


            {value === "all-evaluators" &&
                <Box pt={7}>
                    <EvaluatorTable data={allEvaluators} />
                </Box>
            }

            {value === "add-evaluator" &&
                <AddEvaluatorForm  setAllEvaluators={setAllEvaluators} setValue={setValue} />

            }

            {/* {value === "assign-evaluator" &&
                <AddEvaluatorForm />

            } */}






        </Box >
    );
};

export default EvaluatorManagement