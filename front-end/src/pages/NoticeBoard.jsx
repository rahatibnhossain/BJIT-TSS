import React, { useContext, useEffect, useState } from 'react'
import { Box, Card, CardContent, CardMedia, Typography, Button, Grid } from '@mui/material';

import { styled } from '@mui/material/styles'; // Import the styled function from Emotion

import axios from '../api/axios';
import { LoginContext } from '../context/LoginContex';


const CourseMedia = styled(CardMedia)(({ theme }) => ({
    height: 180,
    objectFit: 'cover',
    borderTopLeftRadius: theme.spacing(1),
    borderTopRightRadius: theme.spacing(1),
}));

const CourseContent = styled(CardContent)(({ theme }) => ({
    flexGrow: 1,
    padding: theme.spacing(2),
}));

const CourseCard = styled(Card)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    height: '100%',
    borderRadius: theme.spacing(1),
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
    transition: 'transform 0.2s ease-in-out',
    backgroundColor: "#0437f136",
    alignItem:"center",
    justifyContent: "center",

    '&:hover': {
        transform: 'scale(1.03)',
    },
}));
const CourseTitle = styled(Typography)(({ theme }) => ({
    fontWeight: 600,
    fontSize: '1.2rem',
}));

const CourseDescription = styled(Typography)(({ theme }) => ({
    marginTop: theme.spacing(1),
    color: theme.palette.text.secondary,
}));


const NoticeBoardPage = () => {

    const [appliedCourse, setAppliedCourse] = useState(0);


    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("")

    const { appliedCoursesGlobal, setappliedCoursesGlobal } = useContext(LoginContext);

    const [dashboardData, setdashboardData] = useState(null);

    useEffect(() => {

        const token = window.localStorage.getItem("tss-token");
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        axios.get('/api/candidate/dashboard', config)
            .then((response) => {
                console.log(response.data.data.listResponse);



                if (response.status === 200) {
                    setShowSuccessMessage(true)
                    setAppliedCourse(response.data.data.dataLength);
                    setappliedCoursesGlobal(response.data.data.dataLength)
                    setSuccessMessage(response.data.successMessage)
                    setdashboardData(response.data.data.listResponse)

                    setTimeout(() => {
                        setShowSuccessMessage(false)
                        setSuccessMessage("")


                    }, 2000);

                }



            })
            .catch((error) => {
                console.error('Error uploading files:', error.response.data);
                setShowErrorMessage(true)
                setErrorMessage(error.data.errorMessage)

                setTimeout(() => {
                    setShowErrorMessage(false)
                    setErrorMessage("")

                }, 2000);


            });

    }, [])





    return (
        <>

            <Card sx={{ minWidth: 275, marginTop: 4 }}>
                <CardContent>
                    <Typography align="center" sx={{ fontSize: 34 }} color="text.secondary" gutterBottom>
                        Notice Board
                    </Typography>
                    <Typography variant="h5" component="div">
                        {appliedCourse > 0 ?
                            `You have applied ${appliedCourse} ${appliedCourse > 1 ? "courses" : "course"} .` :
                            "You haven't applied for any course."
                        }
                    </Typography>
                </CardContent>
            </Card>

            <Box mt={4}>
                <Grid container spacing={2}   justifyContent="center"
 >
                    {dashboardData?.map((dashboard, index) =>

                        <Grid item key={index} xs={12} sm={6} md={4}>

                            <CourseCard>

                                <CourseContent>
                                    <CourseTitle variant="h6">{dashboard.courseName}</CourseTitle>
                                    <CourseDescription variant="body2">{dashboard.dashboardMessage}</CourseDescription>
                                </CourseContent>

                            </CourseCard>

                        </Grid>
                    )}

                </Grid>
            </Box>






        </>


    )
}

export default NoticeBoardPage