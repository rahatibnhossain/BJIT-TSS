import React, { useState } from 'react';
import {Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, TableSortLabel, Button, Box } from '@mui/material';
import { styled } from '@mui/material/styles';
import axios from '../api/axios';

const HeaderTypography = styled(Typography)(({ theme }) => ({
    fontSize: '1.6rem',
    marginBottom: theme.spacing(2),
}));

const Container = styled(TableContainer)(({ theme }) => ({
    maxHeight: 440,
}));

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    fontWeight: 'bold',
}));

const ResponsiveTable = styled(Table)(({ theme }) => ({
    minWidth: 700,
}));

const ScrollableWrapper = styled('div')(({ theme }) => ({
    overflowX: 'auto',
}));

const ApplicantTableList = ({ evaluator, applicants, setApplicants, action, actionText }) => {
    const [sortField, setSortField] = useState('');
    const [sortOrder, setSortOrder] = useState('asc');

    const handleSort = (field) => {
        if (field === sortField) {
            setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
        } else {
            setSortField(field);
            setSortOrder('asc');
        }
    };


    const sortedApplicants = sortField
        ? [...applicants].sort((a, b) => {
            const aValue = a[sortField];
            const bValue = b[sortField];
            if (sortOrder === 'asc') {
                return aValue < bValue ? -1 : 1;
            } else {
                return bValue < aValue ? -1 : 1;
            }
        })
        : applicants;

    return (
        <Container component={Paper}>
            <Box p={2}>
                <HeaderTypography>
                    {evaluator.name} has been assigned to these candidates.
                </HeaderTypography>
            </Box>
            <ScrollableWrapper>
                <ResponsiveTable stickyHeader>
                    <TableHead>
                        <TableRow>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'firstName'}
                                    direction={sortField === 'firstName' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('firstName')}
                                >
                                    First Name
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'lastName'}
                                    direction={sortField === 'lastName' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('lastName')}
                                >
                                    Last Name
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'email'}
                                    direction={sortField === 'email' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('email')}
                                >
                                    Email
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'cgpa'}
                                    direction={sortField === 'cgpa' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('cgpa')}
                                >
                                    CGPA
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'courseName'}
                                    direction={sortField === 'courseName' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('courseName')}
                                >
                                    Course Name
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'presentAddress'}
                                    direction={sortField === 'presentAddress' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('presentAddress')}
                                >
                                    Present Address
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'degreeName'}
                                    direction={sortField === 'degreeName' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('degreeName')}
                                >
                                    Degree Name
                                </TableSortLabel>
                            </StyledTableCell>
                            <StyledTableCell>
                                <TableSortLabel
                                    active={sortField === 'passingYear'}
                                    direction={sortField === 'passingYear' ? sortOrder : 'asc'}
                                    onClick={() => handleSort('passingYear')}
                                >
                                    Passing Year
                                </TableSortLabel>
                            </StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sortedApplicants.map((applicant, index) => (
                            <TableRow key={index}>
                                <TableCell>{applicant.firstName}</TableCell>
                                <TableCell>{applicant.lastName}</TableCell>
                                <TableCell>{applicant.email}</TableCell>
                                <TableCell>{applicant.cgpa}</TableCell>
                                <TableCell>{applicant.courseName}</TableCell>
                                <TableCell>{applicant.presentAddress}</TableCell>
                                <TableCell>{applicant.degreeName}</TableCell>
                                <TableCell>{applicant.passingYear}</TableCell>
                                {/* <Button variant="contained" color="secondary" onClick={() => action(applicant.examineeId)}>
                                    {actionText}
                                </Button> */}
                            </TableRow>
                        ))}
                    </TableBody>
                </ResponsiveTable>
            </ScrollableWrapper>
        </Container>
    );
};

export default ApplicantTableList;
