import React, { useContext, useEffect } from 'react';
import { Typography, Box } from '@mui/material';
import { LoginContext } from '../context/LoginContex';
import { useNavigate } from 'react-router-dom/dist/index';

const ProfileComponent = () => {

  const navigate = useNavigate();
  const { userData, loggedIn, role } = useContext(LoginContext);
  if (!loggedIn) {
    navigate("/login")
  }

  if (!userData) {
    return <Typography variant="h6">Profile data not available</Typography>;
  }

  console.log(userData);



  return (
    <Box mt={2}>
      <Typography variant="h4">Profile Information</Typography>

      {role == "EVALUATOR" &&
        <Box>
          <Typography variant="subtitle1">Name: {userData.name || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Email: {userData.email || 'Not provided'}</Typography>
        </Box>

      }

      {role == "APPLICANT" &&
        <Box>
          <Typography variant="subtitle1">Full Name: {userData.data?.data?.data?.firstName || 'Not provided'} {userData.data?.data?.data?.lastName || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Email: {userData.data?.data?.data?.email || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Contact Number: {userData.data?.data?.data?.contactNumber || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Date of Birth: {userData.data?.data?.data?.dateOfBirth || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Gender: {userData.data?.data?.data?.gender || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Degree Name: {userData.data?.data?.data?.degreeName || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Educational Institute: {userData.data?.data?.data?.educationalInstitute || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Passing Year: {userData.data?.data?.data?.passingYear || 'Not provided'}</Typography>
          <Typography variant="subtitle1">CGPA: {userData.data?.data?.data?.cgpa || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Present Address: {userData.data?.data?.data?.presentAddress || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Resume URL: {userData.data?.data?.data?.resumeUrl || 'Not provided'}</Typography>
          <Typography variant="subtitle1">Resume URL: {userData.data?.data?.data?.photoUrl || 'Not provided'}</Typography>
          <Typography variant="subtitle1">User ID: {userData.data?.data?.data?.userId || 'Not provided'}</Typography>
        </Box>
      }
    </Box>
  );
};

export default ProfileComponent;
