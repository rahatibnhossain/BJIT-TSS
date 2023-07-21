import React, { useContext, useEffect, useState } from 'react'

import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Link } from '../../node_modules/react-router-dom/dist/index';
import axios from '../api/axios';
import { LoginContext } from '../context/LoginContex';

const NoticeBoardPage = () => {

    const [appliedCourse, setAppliedCourse] = useState(0);


    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("")

const {appliedCoursesGlobal  , setappliedCoursesGlobal } = useContext(LoginContext);

    useEffect(() => {

        const token = window.localStorage.getItem("tss-token");
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        axios.get('/api/candidate/dashboard', config)
            .then((response) => {
                console.log(response.data.data.dataLength);



                if (response.status === 200) {
                    setShowSuccessMessage(true)
                    setAppliedCourse(response.data.data.dataLength);
                    setappliedCoursesGlobal(response.data.data.dataLength)
                    setSuccessMessage(response.data.successMessage)


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

        <Card sx={{ minWidth: 275, marginTop: 4 }}>
            <CardContent>
                <Typography align="center" sx={{ fontSize: 34 }} color="text.secondary" gutterBottom>
                    Notice Board
                </Typography>
                <Typography variant="h5" component="div">
                    You have applied {appliedCourse} {appliedCourse>1? "courses": "course"} .
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    Be Limitless
                </Typography>
                <Typography variant="body2">
                    {/* {(data ? data?.data.data.dataLength + " Courses are available" : null)} */}

                </Typography>
            </CardContent>
            <CardActions>
                <Link to="/course" >
                    <Typography color="royalblue" variant="Button" size="small" >See Avaialable Courses</Typography>
                </Link>
            </CardActions>
        </Card>



    )
}

export default NoticeBoardPage