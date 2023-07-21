import React, { useContext } from 'react';
import { Typography, Box } from '@mui/material';
import { LoginContext } from '../context/LoginContex';
import { useNavigate } from '../../node_modules/react-router-dom/dist/index';

const ProfileComponent = () => {
  const navigate = useNavigate();
  const { userData, loggedIn } = useContext(LoginContext);
  if(!loggedIn){
    navigate("/login")
  }

  if (!userData) {
    return <Typography variant="h6">Profile data not available</Typography>;
  }

  const {
    cgpa,
    contactNumber,
    dateOfBirth,
    degreeName,
    educationalInstitute,
    email,
    firstName,
    gender,
    lastName,
    passingYear,
    presentAddress,
    resumeUrl,
    userId,
  } = userData;

  return (
    <Box mt={2}>
      <Typography variant="h4">Profile Information</Typography>
      <Box>
        <Typography variant="subtitle1">Full Name: {firstName || 'Not provided'} {lastName || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Email: {email || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Contact Number: {contactNumber || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Date of Birth: {dateOfBirth || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Gender: {gender || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Degree Name: {degreeName || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Educational Institute: {educationalInstitute || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Passing Year: {passingYear || 'Not provided'}</Typography>
        <Typography variant="subtitle1">CGPA: {cgpa || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Present Address: {presentAddress || 'Not provided'}</Typography>
        <Typography variant="subtitle1">Resume URL: {resumeUrl || 'Not provided'}</Typography>
        <Typography variant="subtitle1">User ID: {userId || 'Not provided'}</Typography>
      </Box>
    </Box>
  );
};

export default ProfileComponent;
