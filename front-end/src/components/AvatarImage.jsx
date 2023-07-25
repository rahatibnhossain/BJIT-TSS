import React from 'react';
import {  Avatar  } from '@mui/material';

const AvatarImage = ({id}) => {

  const imageSrc = "http://localhost:8085/api/file-download/image/"+id.toString();

  return (
    <Avatar sx={{ width: 30, height: 30 }} alt="User" src={imageSrc} />

  );
};

export default AvatarImage;
