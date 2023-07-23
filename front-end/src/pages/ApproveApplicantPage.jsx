import React, { useContext, useEffect, useState } from 'react'
import { Tab, Tabs, Box } from '@mui/material';
import { LoginContext } from '../context/LoginContex';
import CourseCards from '../components/CourseCards';
import axios from '../api/axios';
import ApplicantTable from '../components/ApplicantTable';

const approveApplicant = (examineeId) => {

    const token = window.localStorage.getItem("tss-token");
    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    axios
        .post('/api/approval/applicant', { examineeId } , config)
        .then((response) => {

            console.log(response);

            setApplicants((prevApplicants) =>
                prevApplicants.filter((applicant) => applicant.examineeId !== examineeId)
            );
        })
        .catch((error) => {
            console.error('Error approving applicant:', error);
        });
};

const ApproveApplicantPage = () => {

 
    


    const [allApplicants, setAllApplicants] = useState([]);

    const [allCandidated, setAllCandidated] = useState([]);

const [value2, setValue2] = useState('')

    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("")

    const [batchCode, setBatchCode] = useState("");
    // const [role, setRole] = useState("APPLICANT");

    const setSingleCourse = (c) => {
        setBatchCode(c.batchCode);
    }

    useEffect(() => {
        if (batchCode !== "") {
            console.log(batchCode);
            let role = "APPLICANT"

            const formData = {
                role,
                batchCode
            };
            console.log(formData);

            const token = window.localStorage.getItem("tss-token");
            const config = {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            };

            axios.post(`/api/application/course`, formData, config)
                .then((response) => {
                    console.log(response);
                    console.log(response?.data?.data);


                    if (response.status === 200) {
                        setShowSuccessMessage(true)
                        setSuccessMessage(response.data.successMessage)
                        console.log(response?.data?.data?.listResponse);
                        setAllApplicants(response?.data?.data?.listResponse)


                        setTimeout(() => {
                            setShowSuccessMessage(false)
                            setSuccessMessage("")



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

            role = "CANDIDATE"

            const formData2 = {
                role,
                batchCode
            };
            console.log(formData2);

            axios.post(`/api/application/course`, formData2, config)
                .then((response) => {
                    console.log(response);
                    console.log(response?.data?.data);


                    if (response.status === 200) {
                        setShowSuccessMessage(true)
                        setSuccessMessage(response.data.successMessage)
                        console.log(response?.data?.data?.listResponse);
                        setAllCandidated(response?.data?.data?.listResponse)


                        setTimeout(() => {
                            setShowSuccessMessage(false)
                            setSuccessMessage("")



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

    }, [batchCode])






    useEffect(() => {

        if (showSuccessMessage) {
            console.log(successMessage);
        }
        if (showErrorMessage) {
            console.log(errorMessage);
        }
    }, [showErrorMessage, showSuccessMessage])



    const handleChange = (event, newValue) => {
        setValue(newValue);
        setValue2("")
    };


    const { allEvaluators, setAllEvaluators, loggedIn, setCourses, courses, unavailableCourses, setUnavailableCourses } = useContext(LoginContext);

    const [value, setValue] = useState('all-applicants');
    // const [value2, setValue2] = useState('')

    useEffect(() => {
        console.log(value);
        console.log(value2);
        }, [value, value2]);


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
                        <Tab value="all-applicants" label="All Applicants" />

                        <Tab value="approved-applicants" label="Approved Applicants" />
                    </Tabs>
                </Box>

            </Box>

            {value == "all-applicants"   && value2==""&&

                <CourseCards courses={courses} pathValue={"single-course-applicant"} setValue={setValue2} setSingleCourse={setSingleCourse} />


            }

            {value == "all-applicants" && value2 == "single-course-applicant" &&
                <Box pt={7}>

                    <ApplicantTable applicants={allApplicants} setApplicants={setAllApplicants} action={approveApplicant} actionText={"Approve Applicant"} />
                </Box>
            }


            {value == "approved-applicants" && value2 == "" &&

                <CourseCards courses={courses} pathValue={"single-course-candidate"} setValue={setValue2} setSingleCourse={setSingleCourse} />


            }

            {value == "approved-applicants" && value2 == "single-course-candidate" &&
                <Box pt={7}>

                    <ApplicantTable applicants={allCandidated} setApplicants={setAllApplicants} action={approveApplicant} actionText={"Approve Candidate"} />
                </Box>
            }



        </Box>
    )
}

export default ApproveApplicantPage