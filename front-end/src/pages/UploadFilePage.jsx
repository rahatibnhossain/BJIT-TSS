import React, { useContext, useEffect, useState } from 'react';
import { Container, Row, Col, Form, Button, Alert } from 'react-bootstrap';
import FileService from '../services/FileService';
import { LoginContext } from '../context/LoginContex';

const UploadFile = () => {

    const [up1, setUp1] = useState(false)
    const [up2, setUp2] = useState(false)

    useEffect(() => {
        if (up1 && up2) {
            setUploaded(true);
            window.localStorage.setItem("uploadedfortss", "true")  
        }

    

    }, [up1, up2])

    const { setUploaded } = useContext(LoginContext);
    const [selectedImage, setSelectedImage] = useState(null);
    const [selectedCV, setSelectedCV] = useState(null);
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        setSelectedImage(file);
    };

    const handleCVChange = (e) => {
        const file = e.target.files[0];
        setSelectedCV(file);
    };

    // const form = document.querySelector("form");

    const handleUpload = () => {

        const formData = new FormData();

        const formDatacv = new FormData();


        formData.append('profile-picture', selectedImage);


        formDatacv.append('resume', selectedCV);

        const token = window.localStorage.getItem("tss-token");

        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };

        // axios.post('/api/upload/file-upload/image', formData, config)

        FileService.uploadImage(formData, config)
            .then((response) => {
                console.log(response.data);
                setShowSuccessMessage(true);
                setSelectedImage(null);
                if (response.status === 201) {
                    console.log("Setup 1 is true");

                    setUp2(true)
                }
            })
            .catch((error) => {
                console.error('Error uploading files:', error.response.data);
                setErrors(error.response.data)
                setErrorMessage(JSON.stringify(error.response.data.errorMessage))
                setShowErrorMessage(true)
                setTimeout(() => {
                    setErrorMessage("")
                    setShowErrorMessage(false)

                }, 3000);

            });

        FileService.uploadCV(formDatacv, config)
            .then((response) => {
                console.log(response.data);
                setShowSuccessMessage(true);
                setSelectedCV(null);
                if (response.status === 201) {
                    console.log("Setup 1 is true");
                    setUp1(true)
                }


            })
            .catch((error) => {
                console.error('Error uploading files:', error.response.data);
                setErrors(error.response.data)
                setErrorMessage(JSON.stringify(error.response.data.errorMessage))
                setShowErrorMessage(true)
                setTimeout(() => {
                    setErrorMessage("")
                    setShowErrorMessage(false)

                }, 3000);

            });





    };





    const [errors, setErrors] = useState('');
    const [showErrorMessage, setShowErrorMessage] = useState(false)


    return (
        <Container>
            <Row className="justify-content-center mt-3">
                <Col md={6}>
                    <Form>
                        <Form.Group className="mb-3">
                            <Form.Label>Upload Image</Form.Label>
                            <Form.Control type="file" onChange={handleImageChange} accept="image/*" />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Upload CV</Form.Label>
                            <Form.Control type="file" onChange={handleCVChange} accept=".pdf,.doc,.docx" />
                        </Form.Group>

                        {/* Success message */}
                        {showSuccessMessage && (
                            <Alert variant="success">Files uploaded successfully!</Alert>
                        )}
                        {showErrorMessage && (
                            <Alert variant="danger">{errorMessage}</Alert>
                        )}

                        {(errorMessage && errorMessage === "") ? (
                            <Alert variant="danger">{errorMessage}</Alert>
                        ) : null}





                        <div className="d-flex justify-content-end mt-3">
                            <Button variant="primary" onClick={handleUpload}>
                                Upload
                            </Button>
                        </div>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default UploadFile;
