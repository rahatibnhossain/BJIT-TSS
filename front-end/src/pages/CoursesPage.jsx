import React, { useContext, useState } from 'react';
import { TextField, Tab, Tabs, Box, Card, CardContent, CardMedia, Typography, Button, Grid } from '@mui/material';
import { styled } from '@mui/material/styles'; // Import the styled function from Emotion
import { Link } from 'react-router-dom';
import { LoginContext } from '../context/LoginContex';

const FormContainer = styled(Box)(({ theme }) => ({
  // maxWidth: 90 %,
  margin: '0 auto',
  padding: theme.spacing(4),
  borderRadius: theme.spacing(2),
  boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
}));

const FormTitle = styled(Typography)(({ theme }) => ({
  fontWeight: 600,
  fontSize: '1.8rem',
  marginBottom: theme.spacing(2),
}));

const FormField = styled(TextField)(({ theme }) => ({
  marginBottom: theme.spacing(2),
}));

const SubmitButton = styled(Button)(({ theme }) => ({
  marginTop: theme.spacing(2),
  borderRadius: theme.spacing(0),
  fontWeight: 600,
}));


const CourseCard = styled(Card)(({ theme, role }) => ({
  display: 'flex',
  flexDirection: 'column',
  height: '100%',
  borderRadius: theme.spacing(1),
  boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
  transition: 'transform 0.2s ease-in-out',
  cursor: role === 'ADMIN' ? 'pointer' : 'default',
  '&:hover': {
    transform: role === 'ADMIN' ? 'scale(1.03)' : 'none',
  },
}));

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

const CourseTitle = styled(Typography)(({ theme }) => ({
  fontWeight: 600,
  fontSize: '1.2rem',
}));

const CourseDescription = styled(Typography)(({ theme }) => ({
  marginTop: theme.spacing(1),
  color: theme.palette.text.secondary,
}));

const EnrollButton = styled(Button)(({ theme }) => ({
  marginTop: 'auto',
  borderRadius: theme.spacing(0),
  fontWeight: 600,
}));




const AllCourses = ({ courses }) => {

  const { role } = useContext(LoginContext);

  const [value, setValue] = useState('available-courses');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };



  const [courseName, setCourseName] = useState('');
  const [courseDescription, setCourseDescription] = useState('');
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [batchCode, setBatchCode] = useState('');
  const [vacancy, setVacancy] = useState('');
  const [writtenExamTime, setWrittenExamTime] = useState('');
  const [applicantDashboardMessage, setApplicantDashboardMessage] = useState('You have successfully applied for this course');
  const [writtenShortlistedDashboardMessage, setWrittenShortlistedDashboardMessage] = useState('Congratulations. You have shortlisted for the written exam. Your written exam will held soon');
  const [writtenPassedDashboardMessage, setWrittenPassedDashboardMessage] = useState('Congratulations. You have passed the written exam. Your aptitude test will held soon');
  const [technicalVivaPassedDashboardMessage, setTechnicalVivaPassedDashboardMessage] = useState('Congratulations. You have passed the technical viva. Your HR viva will held soon');
  const [aptitudeTestPassedDashboardMessage, setAptitudeTestPassedDashboardMessage] = useState('Congratulations. You have passed the aptitude test. Your technical viva will held soon.');
  const [hrVivaPassedDashboardMessage, setHrVivaPassedDashboardMessage] = useState('Congratulations. You have passed the HR viva. Please wait for the final selection');
  const [traineeDashboardMessage, setTraineeDashboardMessage] = useState('Congratulations. You have been selected as a Trainee.');

  const handleSubmit = (event) => {
    event.preventDefault();

    // Perform form submission or API call here with the form data
    // For example:
    const formData = {
      courseName,
      courseDescription,
      startDate,
      endDate,
      batchCode,
      vacancy,
    };
    console.log(formData);

    // Reset form fields after submission
    setCourseName('');
    setCourseDescription('');
    setStartDate('');
    setEndDate('');
    setBatchCode('');
    setVacancy('');
  };



  return (
    <Box mt={role === "ADMIN" ? 1 : 4}>
      {role === "ADMIN" &&


        <Box sx={{
          width: '100%',
          backgroundColor: 'white',

        }} >
          <Box position="fixed">

            <Tabs
              value={value}
              onChange={handleChange}
              textColor="secondary"
              indicatorColor="secondary"
              aria-label="secondary tabs example"
            >
              <Tab value="available-courses" label="Available Courses" />
              <Tab value="add-course" label="Add Course" />
              <Tab value="three" label="Unavailable Courses" />
            </Tabs>
          </Box>

        </Box>
      }

      {role !== "ADMIN" &&
        <Box pt={role === "ADMIN" ? 7 : 0}>

          <Grid container spacing={2} justifyContent="center">
            {courses?.data.data.listResponse.map((course) => (
              <Grid item key={course.courseId} xs={12} sm={6} md={4}>
                <Link to={`/course/${course.courseId}`}>

                  <CourseCard>
                    <CourseMedia component="img" image={course.imageUrl} alt={course.title} />
                    <CourseContent>
                      <CourseTitle variant="h6">{course.courseName}</CourseTitle>
                      <CourseDescription variant="body2">{course.courseDescription}</CourseDescription>
                    </CourseContent>
                    <EnrollButton variant="contained" color="primary" size="small">
                      Enroll Now
                    </EnrollButton>
                  </CourseCard>
                </Link>

              </Grid>
            ))}
          </Grid>

        </Box>

      }



      {


        value === "available-courses" && role === "ADMIN" &&

        <Box pt={role === "ADMIN" ? 7 : 0}>

          <Grid container spacing={2} justifyContent="center">
            {courses?.data.data.listResponse.map((course) => (
              <Grid item key={course.courseId} xs={12} sm={6} md={4}>
                <Link to={`/course/${course.courseId}`}>

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
                </Link>

              </Grid>
            ))}
          </Grid>

        </Box>
      }

      {value === "add-course" && role === "ADMIN" &&
        <Box pt={role === "ADMIN" ? 7 : 0} pb={2}>
          <Grid container spacing={2} justifyContent="center">

            <FormContainer>
              <FormTitle variant="h2">Add New Course</FormTitle>
              <form onSubmit={handleSubmit}>
                <Grid container spacing={2}>
                  <Grid item xs={12} sm={6}>
                    <FormField
                      label="Course Name"
                      variant="outlined"
                      fullWidth
                      value={courseName}
                      onChange={(e) => setCourseName(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <FormField
                      label="Batch Code"
                      variant="outlined"
                      fullWidth
                      value={batchCode}
                      onChange={(e) => setBatchCode(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="Course Description"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={courseDescription}
                      onChange={(e) => setCourseDescription(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={6} sm={6} >
                    <Typography variant="h5" color="initial">Vacancy</Typography>
                  </Grid>

                  <Grid item xs={6} sm={6}>
                    <FormField
                      label="Vacancy"
                      variant="outlined"
                      type="number"
                      fullWidth
                      value={vacancy}
                      onChange={(e) => setVacancy(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={6} sm={6} >
                    <Typography variant="h5" color="initial">Application Deadline</Typography>
                  </Grid>
                  <Grid item xs={6} sm={6}>
                    <FormField
                      type="date"
                      variant="outlined"
                      fullWidth
                      value={endDate}
                      onChange={(e) => setEndDate(e.target.value)}
                    />
                  </Grid>

                  <Grid item xs={6} sm={6}>
                    <Typography variant="h5" color="initial">Written Exam Time</Typography>
                  </Grid>
                  <Grid item xs={6} sm={6}>
                    <FormField
                      type="datetime-local"
                      variant="outlined"
                      fullWidth
                      value={writtenExamTime}
                      onChange={(e) => setWrittenExamTime(e.target.value)}
                    />
                  </Grid>

                  <Grid item xs={6} sm={6}>

                    <Typography variant="h5" color="initial">Start Date</Typography>
                  </Grid>
                  <Grid item xs={6} sm={6}>

                    <FormField
                      type="date"
                      variant="outlined"
                      fullWidth
                      value={startDate}
                      onChange={(e) => setStartDate(e.target.value)}
                    />
                  </Grid>

                  <Grid item xs={6} sm={6} >

                    <Typography variant="h5" color="initial">End Date</Typography>
                  </Grid>

                  <Grid item xs={6} sm={6}>
                    <FormField
                      type="date"
                      variant="outlined"
                      fullWidth
                      value={endDate}
                      onChange={(e) => setEndDate(e.target.value)}
                    />
                  </Grid>




                  <Grid item xs={12}>
                    <FormField
                      label="Applicant Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={applicantDashboardMessage}
                      onChange={(e) => setApplicantDashboardMessage(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="Written Shortlisted Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={writtenShortlistedDashboardMessage}
                      onChange={(e) => setWrittenShortlistedDashboardMessage(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="Written Passed Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={writtenPassedDashboardMessage}
                      onChange={(e) => setWrittenPassedDashboardMessage(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="Technical Viva Passed Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={technicalVivaPassedDashboardMessage}
                      onChange={(e) => setTechnicalVivaPassedDashboardMessage(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="Aptitude Test Passed Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={aptitudeTestPassedDashboardMessage}
                      onChange={(e) => setAptitudeTestPassedDashboardMessage(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="HR Viva Passed Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={hrVivaPassedDashboardMessage}
                      onChange={(e) => setHrVivaPassedDashboardMessage(e.target.value)}
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <FormField
                      label="Trainee Dashboard Message"
                      variant="outlined"
                      fullWidth
                      multiline
                      rows={4}
                      value={traineeDashboardMessage}
                      onChange={(e) => setTraineeDashboardMessage(e.target.value)}
                    />
                  </Grid>

                </Grid>
                <Grid container justifyContent="center">

                  <Grid item xs={6} sm={6} justifyContent="center">
                    <SubmitButton variant="contained" color="primary" size="large" type="submit" fullWidth>
                      Add Course
                    </SubmitButton>
                  </Grid>
                </Grid>

              </form>
            </FormContainer>
          </Grid>

        </Box >

      }



    </Box >
  );
};

export default AllCourses;