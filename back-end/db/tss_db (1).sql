-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 28, 2023 at 11:52 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tss_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidate_marks`
--

CREATE TABLE `candidate_marks` (
  `candidate_id` bigint(20) NOT NULL,
  `full_mark` float DEFAULT NULL,
  `aptitude_test_id` bigint(20) DEFAULT NULL,
  `examinee_id` bigint(20) DEFAULT NULL,
  `hr_viva_id` bigint(20) DEFAULT NULL,
  `technical_viva_id` bigint(20) DEFAULT NULL,
  `written_mark_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `candidate_marks`
--

INSERT INTO `candidate_marks` (`candidate_id`, `full_mark`, `aptitude_test_id`, `examinee_id`, `hr_viva_id`, `technical_viva_id`, `written_mark_id`) VALUES
(1, NULL, 1, 30, 2, 3, 1),
(2, NULL, 4, 30, 5, 6, 2),
(3, NULL, 7, 6, 8, 9, 3),
(4, NULL, 10, 12, 11, 12, 4),
(5, NULL, 13, 18, 14, 15, 5),
(6, NULL, 16, 24, 17, 18, 6),
(7, NULL, 19, 2, 20, 21, 7),
(8, NULL, 22, 8, 23, 24, 8),
(9, NULL, 25, 14, 26, 27, 9),
(10, NULL, 28, 20, 29, 30, 10),
(11, NULL, 31, 26, 32, 33, 11),
(12, 92, 34, 3, 35, 36, 12),
(13, NULL, 37, 9, 38, 39, 13),
(14, NULL, 40, 15, 41, 42, 14),
(15, NULL, 43, 21, 44, 45, 15),
(16, NULL, 46, 27, 47, 48, 16),
(17, NULL, 49, 29, 50, 51, 17),
(18, NULL, 52, 29, 53, 54, 18),
(19, NULL, 55, 23, 56, 57, 19),
(20, NULL, 58, 17, 59, 60, 20),
(21, NULL, 61, 11, 62, 63, 21),
(22, NULL, 64, 4, 65, 66, 22),
(23, NULL, 67, 5, 68, 69, 23),
(24, NULL, 70, 10, 71, 72, 24),
(25, NULL, 73, 16, 74, 75, 25),
(26, NULL, 76, 22, 77, 78, 26),
(27, NULL, 79, 28, 80, 81, 27),
(28, NULL, 82, 13, 83, 84, 28),
(29, 76, 85, 31, 86, 87, 29),
(30, 88, 88, 32, 89, 90, 30),
(31, NULL, 91, 19, 92, 93, 31);

-- --------------------------------------------------------

--
-- Table structure for table `course_info`
--

CREATE TABLE `course_info` (
  `course_id` bigint(20) NOT NULL,
  `applicant_dashboard_message` varchar(1000) DEFAULT NULL,
  `application_deadline` datetime DEFAULT NULL,
  `aptitude_test_passed_dashboard_message` varchar(1000) DEFAULT NULL,
  `batch_code` varchar(255) DEFAULT NULL,
  `course_description` varchar(1000) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `hr_viva_passed_dashboard_message` varchar(1000) DEFAULT NULL,
  `is_available` bit(1) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `technical_viva_passed_dashboard_message` varchar(1000) DEFAULT NULL,
  `trainee_dashboard_message` varchar(1000) DEFAULT NULL,
  `vacancy` bigint(20) DEFAULT NULL,
  `written_exam_instruction` varchar(1000) DEFAULT NULL,
  `written_exam_time` datetime DEFAULT NULL,
  `written_passed_dashboard_message` varchar(1000) DEFAULT NULL,
  `written_shortlisted_dashboard_message` varchar(1000) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `course_info`
--

INSERT INTO `course_info` (`course_id`, `applicant_dashboard_message`, `application_deadline`, `aptitude_test_passed_dashboard_message`, `batch_code`, `course_description`, `course_name`, `end_date`, `hr_viva_passed_dashboard_message`, `is_available`, `start_date`, `technical_viva_passed_dashboard_message`, `trainee_dashboard_message`, `vacancy`, `written_exam_instruction`, `written_exam_time`, `written_passed_dashboard_message`, `written_shortlisted_dashboard_message`) VALUES
(1, 'You have successfully applied for this course', '2023-08-30 06:00:00', 'Congratulations. You have passed the aptitude test. Your technical viva will be held soon.', 'YSD_02_J2EE', 'During this course almost every J2EE topics will be covered. Basics of Java, OOP concepts, Advanced Java, Design Pattern, JSP and Servlet, Spring/Hibernate framework, Front End, React JS, Unit Texting are the main topics of these robust training course.', 'J2EE', '2023-12-27 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'1', '2023-10-01 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 20, '1. Please arrive at the exam venue 30 minutes before the scheduled start time.\n2. Bring your photo ID and this admit card for verification.\n3. The written exam will consist of multiple-choice questions and coding exercises.\n4. You will have 2 hours to complete the exam.\n5. Use only the provided answer sheet for marking your answers.\n6. Read the instructions carefully before attempting each section.\n7. Write your answers clearly and legibly.\n8. Use a black or blue pen for writing.\n9. Electronic devices, calculators, and study materials are not allowed during the exam.\n10. Once the exam starts, you cannot leave the exam hall until it\'s completed.\n11. Make sure to submit your answer sheet before leaving.\n12. Violation of exam rules may lead to disqualification.\n13. All the best for your exam!', '2023-09-15 21:12:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon'),
(2, 'You have successfully applied for this course', '2023-09-30 06:00:00', 'Congratulations. You have passed the aptitude test. Your technical viva will be held soon.', 'YSD_02_DEvOps', 'In this course we will cover all the necessary topics of DevOps. Specially you will be able to learn Modern JavaScript, Introduction about DevOps, Linux Basics, Version Control, Continuous integrity, Cloud Computing, Configuration Management, Containers. Also, you will know more on Monitoring, Data Analysis and visualization as well as configuration management. After successful completion of the course, you will be able to work in international client project.', 'DevOps', '2023-10-31 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'1', '2023-10-31 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 20, '1. Please arrive at the exam venue 30 minutes before the scheduled start time.\n2. Bring your photo ID and this admit card for verification.\n3. The written exam will consist of multiple-choice questions and coding exercises.\n4. You will have 2 hours to complete the exam.\n5. Use only the provided answer sheet for marking your answers.\n6. Read the instructions carefully before attempting each section.\n7. Write your answers clearly and legibly.\n8. Use a black or blue pen for writing.\n9. Electronic devices, calculators, and study materials are not allowed during the exam.\n10. Once the exam starts, you cannot leave the exam hall until it\'s completed.\n11. Make sure to submit your answer sheet before leaving.\n12. Violation of exam rules may lead to disqualification.\n13. All the best for your exam!', '2023-09-30 20:14:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon'),
(3, 'You have successfully applied for this course', '2023-08-10 06:00:00', 'Congratulations. You have passed the aptitude test. Your technical viva will be held soon.', 'YSD_02_Android', 'This Specialization enables learners to successfully apply core Kotlin programming languages features & software patterns needed to develop maintainable mobile apps comprised of core Android components, as well as fundamental Kotlin and advanced features of Kotlin. This course stands as a good guide for strengthening your basics in Android app development and is a good mixture of theory and practical knowledge. You will start this course by getting an overview of Android Studio.', 'Android', '2023-09-01 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'1', '2023-08-17 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 20, '1. Please arrive at the exam venue 30 minutes before the scheduled start time.\n2. Bring your photo ID and this admit card for verification.\n3. The written exam will consist of multiple-choice questions and coding exercises.\n4. You will have 2 hours to complete the exam.\n5. Use only the provided answer sheet for marking your answers.\n6. Read the instructions carefully before attempting each section.\n7. Write your answers clearly and legibly.\n8. Use a black or blue pen for writing.\n9. Electronic devices, calculators, and study materials are not allowed during the exam.\n10. Once the exam starts, you cannot leave the exam hall until it\'s completed.\n11. Make sure to submit your answer sheet before leaving.\n12. Violation of exam rules may lead to disqualification.\n13. All the best for your exam!', '2023-08-24 20:16:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon'),
(4, 'You have successfully applied for this course', '2023-08-31 06:00:00', 'Congratulations. You have passed the aptitude test. Your technical viva will be held soon.', 'YSD_02_MERN', 'During this course, almost every JavaScript topic will be covered. Basics of JavaScript, OOP concepts, Advanced JavaScript, Design Pattern, Express and Node, Mongo DB, Front End, React JS, and TypeScript is the main topics of these robust training course.', 'MERN', '2023-10-01 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'1', '2023-09-01 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 19, '1. Please arrive at the exam venue 30 minutes before the scheduled start time.\n2. Bring your photo ID and this admit card for verification.\n3. The written exam will consist of multiple-choice questions and coding exercises.\n4. You will have 2 hours to complete the exam.\n5. Use only the provided answer sheet for marking your answers.\n6. Read the instructions carefully before attempting each section.\n7. Write your answers clearly and legibly.\n8. Use a black or blue pen for writing.\n9. Electronic devices, calculators, and study materials are not allowed during the exam.\n10. Once the exam starts, you cannot leave the exam hall until it\'s completed.\n11. Make sure to submit your answer sheet before leaving.\n12. Violation of exam rules may lead to disqualification.\n13. All the best for your exam!', '2023-08-10 20:19:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon'),
(5, 'You have successfully applied for this course', '2023-09-20 06:00:00', 'Congratulations. You have passed the aptitude test. Your technical viva will be held soon.', 'YSD_02_SQA', 'During this course, almost every topic of Manual testing will be covered such as Basics of Testing, Test Plan Preparation Test Design Techniques Test Case Writing Test Management & Control Bug Reporting and Issue Tracking, are the main topics of this robust training course.', 'SQA', '2023-12-02 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'1', '2023-10-01 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 19, '1. Please arrive at the exam venue 30 minutes before the scheduled start time.\n2. Bring your photo ID and this admit card for verification.\n3. The written exam will consist of multiple-choice questions and coding exercises.\n4. You will have 2 hours to complete the exam.\n5. Use only the provided answer sheet for marking your answers.\n6. Read the instructions carefully before attempting each section.\n7. Write your answers clearly and legibly.\n8. Use a black or blue pen for writing.\n9. Electronic devices, calculators, and study materials are not allowed during the exam.\n10. Once the exam starts, you cannot leave the exam hall until it\'s completed.\n11. Make sure to submit your answer sheet before leaving.\n12. Violation of exam rules may lead to disqualification.\n13. All the best for your exam!', '2023-09-21 20:21:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon'),
(6, 'You have successfully applied for this course', '2023-12-20 06:00:00', 'Congratulations. You have been selected as a Trainee.', 'YSD_03_SQA', 'During this course, almost every topic of Manual testing will be covered such as Basics of Testing, Test Plan Preparation Test Design Techniques Test Case Writing Test Management & Control Bug Reporting and Issue Tracking, are the main topics of this robust training course.', 'SQA 3', '2024-04-02 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'0', '2024-01-02 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 20, 'Instructions', '2024-01-02 14:24:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon'),
(7, 'You have successfully applied for this course', '2023-08-05 06:00:00', 'Congratulations. You have been selected as a Trainee.', 'YSD_02_IOS', 'In this robust iOS App Development Training, if you’re interested in developing mobile applications, you’re best starting off with an iOS app training program. All courses, topics, and materials are updated regularly to ensure they are moving along with the latest trends in technology. Whether you’re a large, corporate business or a small, privately owned company, you can expect the same great results from our iOS application development training programs. ', 'IOS', '2023-12-01 06:00:00', 'Congratulations. You have passed the HR viva. Please wait for the final selection', b'1', '2023-09-01 06:00:00', 'Congratulations. You have passed the technical viva. Your HR viva will be held soon', 'Congratulations. You have been selected as a Trainee.', 20, '1. Please arrive at the exam venue 30 minutes before the scheduled start time.\n2. Bring your photo ID and this admit card for verification.\n3. The written exam will consist of multiple-choice questions and coding exercises.\n4. You will have 2 hours to complete the exam.\n5. Use only the provided answer sheet for marking your answers.\n6. Read the instructions carefully before attempting each section.\n7. Write your answers clearly and legibly.\n8. Use a black or blue pen for writing.\n9. Electronic devices, calculators, and study materials are not allowed during the exam.\n10. Once the exam starts, you cannot leave the exam hall until it\'s completed.\n11. Make sure to submit your answer sheet before leaving.\n12. Violation of exam rules may lead to disqualification.\n13. All the best for your exam!', '2023-08-21 08:26:00', 'Congratulations. You have passed the written exam. Your aptitude test will be held soon', 'Congratulations. You have shortlisted for the written exam. Your written exam will be held soon');

-- --------------------------------------------------------

--
-- Table structure for table `data_storage`
--

CREATE TABLE `data_storage` (
  `data_id` bigint(20) NOT NULL,
  `data_key` varchar(255) DEFAULT NULL,
  `data_value` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_storage`
--

INSERT INTO `data_storage` (`data_id`, `data_key`, `data_value`) VALUES
(1, 'TotalMarkWritten', '30'),
(2, 'PassingMarkWritten', '15'),
(3, 'WrittenQuestionNumber', '5'),
(4, 'TotalMarkAptitude', '30'),
(5, 'AptitudeQuestionNumber', '5'),
(6, 'PassingMarkAptitude', '15'),
(7, 'TotalMarkHrViva', '30'),
(8, 'HrVivaQuestionNumber', '3'),
(9, 'PassingMarkHrViva', '15'),
(10, 'TotalMarkTechnical', '30'),
(11, 'TechnicalQuestionNumber', '3'),
(12, 'PassingMarkTechnical', '15');

-- --------------------------------------------------------

--
-- Table structure for table `email_info`
--

CREATE TABLE `email_info` (
  `email_id` bigint(20) NOT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `email_body` varchar(1000) DEFAULT NULL,
  `email_time` datetime DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evaluator_info`
--

CREATE TABLE `evaluator_info` (
  `evaluator_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `evaluator_info`
--

INSERT INTO `evaluator_info` (`evaluator_id`, `email`, `name`) VALUES
(1, 'evaluator1@gmail.com', 'Ariful'),
(2, 'evaluator2@gmail.com', 'Akmal'),
(3, 'evaluator3@gmail.com', 'Akash');

-- --------------------------------------------------------

--
-- Table structure for table `examinee_info`
--

CREATE TABLE `examinee_info` (
  `examinee_id` bigint(20) NOT NULL,
  `application_time` datetime DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `examinee_info`
--

INSERT INTO `examinee_info` (`examinee_id`, `application_time`, `role`, `course_id`, `user_id`) VALUES
(1, '2023-07-27 14:42:57', 'APPLICANT', 1, 1),
(2, '2023-07-27 14:43:05', 'CANDIDATE', 2, 1),
(3, '2023-07-27 14:43:14', 'TRAINEE', 3, 1),
(4, '2023-07-27 14:43:22', 'CANDIDATE', 5, 1),
(5, '2023-07-27 14:43:30', 'CANDIDATE', 4, 1),
(6, '2023-07-27 14:43:35', 'CANDIDATE', 7, 1),
(7, '2023-07-27 14:53:01', 'APPLICANT', 1, 2),
(8, '2023-07-27 14:53:08', 'CANDIDATE', 2, 2),
(9, '2023-07-27 14:53:14', 'CANDIDATE', 3, 2),
(10, '2023-07-27 14:53:19', 'CANDIDATE', 4, 2),
(11, '2023-07-27 14:53:24', 'CANDIDATE', 5, 2),
(12, '2023-07-27 14:53:30', 'CANDIDATE', 7, 2),
(13, '2023-07-27 14:58:04', 'CANDIDATE', 1, 3),
(14, '2023-07-27 14:58:10', 'CANDIDATE', 2, 3),
(15, '2023-07-27 14:58:15', 'CANDIDATE', 3, 3),
(16, '2023-07-27 14:58:21', 'CANDIDATE', 4, 3),
(17, '2023-07-27 14:58:26', 'CANDIDATE', 5, 3),
(18, '2023-07-27 14:58:32', 'CANDIDATE', 7, 3),
(19, '2023-07-27 15:25:10', 'CANDIDATE', 1, 4),
(20, '2023-07-27 15:25:15', 'CANDIDATE', 2, 4),
(21, '2023-07-27 15:25:21', 'CANDIDATE', 3, 4),
(22, '2023-07-27 15:25:27', 'CANDIDATE', 4, 4),
(23, '2023-07-27 15:25:33', 'CANDIDATE', 5, 4),
(24, '2023-07-27 15:25:40', 'CANDIDATE', 7, 4),
(25, '2023-07-27 15:32:16', 'APPLICANT', 1, 5),
(26, '2023-07-27 15:32:23', 'CANDIDATE', 2, 5),
(27, '2023-07-27 15:32:29', 'CANDIDATE', 3, 5),
(28, '2023-07-27 15:32:33', 'CANDIDATE', 4, 5),
(29, '2023-07-27 15:32:39', 'CANDIDATE', 5, 5),
(30, '2023-07-27 15:32:45', 'CANDIDATE', 7, 5),
(31, '2023-07-28 11:14:53', 'TRAINEE', 6, 6),
(32, '2023-07-28 14:44:15', 'TRAINEE', 1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `hidden_code_info`
--

CREATE TABLE `hidden_code_info` (
  `hidden_code` bigint(20) NOT NULL,
  `candidate_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hidden_code_info`
--

INSERT INTO `hidden_code_info` (`hidden_code`, `candidate_id`) VALUES
(17332, 12),
(17333, 13),
(17334, 14),
(17335, 15),
(17336, 16),
(17337, 17),
(17338, 18),
(17339, 19),
(17340, 20),
(17341, 21),
(17342, 22),
(17343, 26),
(17344, 23),
(17345, 24),
(17346, 25),
(17347, 27),
(17348, 1),
(17349, 2),
(17350, 3),
(17351, 4),
(17352, 5),
(17353, 6),
(17383, 29),
(17433, 30);

-- --------------------------------------------------------

--
-- Table structure for table `login_info`
--

CREATE TABLE `login_info` (
  `login_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `evaluator_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login_info`
--

INSERT INTO `login_info` (`login_id`, `email`, `password`, `role`, `evaluator_id`, `user_id`) VALUES
(1, 'rahatibnhossain@gmail.com', '$2a$10$i92z/Q6sWXh9sc97.Vugd.JHo2q0TzVIwWD/Q.JlMhr.4SB98OsJK', 'ADMIN', NULL, NULL),
(2, 'evaluator1@gmail.com', '$2a$10$7OgeLd6qo1rsOfpCqCI5O.lAvdUUw7aALHI8BvC0e3dgVl6ysVKo6', 'EVALUATOR', 1, NULL),
(3, 'evaluator2@gmail.com', '$2a$10$cu6iOhqKiqAFoiS6a9bBfuF6WJwhupx77OlAFqB6.QiYZNhH1ViQ2', 'EVALUATOR', 2, NULL),
(4, 'evaluator3@gmail.com', '$2a$10$c516uFz0HR8WO0PSgrmJkez2Q/Sv.uYt/xIERSeZNYbiARKyy4Tg2', 'EVALUATOR', 3, NULL),
(5, 'applicant1@gmail.com', '$2a$10$Tf4ZyhLp3effK5J0tZqUB.6UvqknAbDAITsIRbeSBiX/.WzyHXXvq', 'APPLICANT', NULL, 1),
(6, 'applicant3@gmail.com', '$2a$10$ToN9SQxjMNTNY9oDTMZvrOrU4c8.FcJ4RTdEVWW1cjbfhDX4M0T7e', 'APPLICANT', NULL, 2),
(7, 'applicant4@gmail.com', '$2a$10$NAyx551MjuA6edb4meMJj..FofzOgGoO.BxtsvajES9LrvJi/csj.', 'APPLICANT', NULL, 3),
(8, 'applicant5@gmail.com', '$2a$10$GLrDVTa1wuP8u7soKBVd3.0oy5snCAP8SB9QpiawZP0fm98viPX4i', 'APPLICANT', NULL, 4),
(9, 'applicant7@gmail.com', '$2a$10$Bkjy5X/XS1NuQeNhqTvSReQTV8192sWP9anwWQpH25tICSINxUAVu', 'APPLICANT', NULL, 5),
(11, 'ribnahossain@gmail.com', '$2a$10$0fXA5YV.CADQcd8dk3xRzeuyVUtPELlg48ZtgcGIGLVReNNXfo7Q6', 'APPLICANT', NULL, 7);

-- --------------------------------------------------------

--
-- Table structure for table `my_sequence`
--

CREATE TABLE `my_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `my_sequence`
--

INSERT INTO `my_sequence` (`next_val`) VALUES
(17532);

-- --------------------------------------------------------

--
-- Table structure for table `question_marks`
--

CREATE TABLE `question_marks` (
  `question_id` bigint(20) NOT NULL,
  `question_mark` float DEFAULT NULL,
  `question_no` int(11) DEFAULT NULL,
  `round_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `question_marks`
--

INSERT INTO `question_marks` (`question_id`, `question_mark`, `question_no`, `round_id`) VALUES
(1, 4, 1, 34),
(2, 4, 2, 34),
(3, 5, 3, 34),
(4, 5, 4, 34),
(5, 5, 5, 34),
(6, 6, 1, 36),
(7, 7, 2, 36),
(8, 7, 3, 36),
(9, 7, 1, 35),
(10, 7, 2, 35),
(11, 7, 3, 35),
(12, 4, 1, 85),
(13, 4, 2, 85),
(14, 4, 3, 85),
(15, 4, 4, 85),
(16, 4, 5, 85),
(17, 5, 1, 87),
(18, 5, 2, 87),
(19, 7, 3, 87),
(20, 7, 1, 86),
(21, 7, 2, 86),
(22, 7, 3, 86),
(23, 7, 1, 88),
(24, 7, 2, 88),
(25, 3, 3, 88),
(26, 5, 4, 88),
(27, 5, 5, 88),
(28, 4, 1, 90),
(29, 5, 2, 90),
(30, 8, 3, 90),
(31, 12, 1, 89),
(32, 2, 2, 89),
(33, 3, 3, 89);

-- --------------------------------------------------------

--
-- Table structure for table `round_marks`
--

CREATE TABLE `round_marks` (
  `round_id` bigint(20) NOT NULL,
  `passed` bit(1) DEFAULT NULL,
  `round_mark` float DEFAULT NULL,
  `round_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `round_marks`
--

INSERT INTO `round_marks` (`round_id`, `passed`, `round_mark`, `round_name`) VALUES
(1, NULL, NULL, NULL),
(2, NULL, NULL, NULL),
(3, NULL, NULL, NULL),
(4, NULL, NULL, NULL),
(5, NULL, NULL, NULL),
(6, NULL, NULL, NULL),
(7, NULL, NULL, NULL),
(8, NULL, NULL, NULL),
(9, NULL, NULL, NULL),
(10, NULL, NULL, NULL),
(11, NULL, NULL, NULL),
(12, NULL, NULL, NULL),
(13, NULL, NULL, NULL),
(14, NULL, NULL, NULL),
(15, NULL, NULL, NULL),
(16, NULL, NULL, NULL),
(17, NULL, NULL, NULL),
(18, NULL, NULL, NULL),
(19, NULL, NULL, NULL),
(20, NULL, NULL, NULL),
(21, NULL, NULL, NULL),
(22, NULL, NULL, NULL),
(23, NULL, NULL, NULL),
(24, NULL, NULL, NULL),
(25, NULL, NULL, NULL),
(26, NULL, NULL, NULL),
(27, NULL, NULL, NULL),
(28, NULL, NULL, NULL),
(29, NULL, NULL, NULL),
(30, NULL, NULL, NULL),
(31, NULL, NULL, NULL),
(32, NULL, NULL, NULL),
(33, NULL, NULL, NULL),
(34, b'1', 23, 'APTITUDE'),
(35, b'1', 21, 'HR'),
(36, b'1', 20, 'TECHNICAL'),
(37, NULL, NULL, NULL),
(38, NULL, NULL, NULL),
(39, NULL, NULL, NULL),
(40, NULL, NULL, NULL),
(41, NULL, NULL, NULL),
(42, NULL, NULL, NULL),
(43, NULL, NULL, NULL),
(44, NULL, NULL, NULL),
(45, NULL, NULL, NULL),
(46, NULL, NULL, NULL),
(47, NULL, NULL, NULL),
(48, NULL, NULL, NULL),
(49, NULL, NULL, NULL),
(50, NULL, NULL, NULL),
(51, NULL, NULL, NULL),
(52, NULL, NULL, NULL),
(53, NULL, NULL, NULL),
(54, NULL, NULL, NULL),
(55, NULL, NULL, NULL),
(56, NULL, NULL, NULL),
(57, NULL, NULL, NULL),
(58, NULL, NULL, NULL),
(59, NULL, NULL, NULL),
(60, NULL, NULL, NULL),
(61, NULL, NULL, NULL),
(62, NULL, NULL, NULL),
(63, NULL, NULL, NULL),
(64, NULL, NULL, NULL),
(65, NULL, NULL, NULL),
(66, NULL, NULL, NULL),
(67, NULL, NULL, NULL),
(68, NULL, NULL, NULL),
(69, NULL, NULL, NULL),
(70, NULL, NULL, NULL),
(71, NULL, NULL, NULL),
(72, NULL, NULL, NULL),
(73, NULL, NULL, NULL),
(74, NULL, NULL, NULL),
(75, NULL, NULL, NULL),
(76, NULL, NULL, NULL),
(77, NULL, NULL, NULL),
(78, NULL, NULL, NULL),
(79, NULL, NULL, NULL),
(80, NULL, NULL, NULL),
(81, NULL, NULL, NULL),
(82, NULL, NULL, NULL),
(83, NULL, NULL, NULL),
(84, NULL, NULL, NULL),
(85, b'1', 20, 'APTITUDE'),
(86, b'1', 21, 'HR'),
(87, b'1', 17, 'TECHNICAL'),
(88, b'1', 27, 'APTITUDE'),
(89, b'1', 17, 'HR'),
(90, b'1', 17, 'TECHNICAL'),
(91, NULL, NULL, NULL),
(92, NULL, NULL, NULL),
(93, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL,
  `cgpa` float DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `degree_name` varchar(255) DEFAULT NULL,
  `educational_institute` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `passing_year` int(11) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `present_address` varchar(255) DEFAULT NULL,
  `resume_url` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`user_id`, `cgpa`, `contact_number`, `date_of_birth`, `degree_name`, `educational_institute`, `email`, `first_name`, `gender`, `last_name`, `passing_year`, `photo_url`, `present_address`, `resume_url`) VALUES
(1, 3.61, '01580661685', '2000-01-27 06:00:00', 'BSc', 'JASHORE UNIVERSTY OF SCIENCE AND TECHNOLOGY', 'applicant1@gmail.com', 'Rahat', 'male', 'Ibna Hossain', 2023, 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\image\\1.jpeg', 'Dhaka', 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\resume\\1.pdf'),
(2, 3.61, '01592042274', '2000-01-05 06:00:00', 'BSc', 'DHAKA UNIVERSITY', 'applicant3@gmail.com', 'Taslim', 'male', 'Hosen', 2023, 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\image\\2.jpeg', 'Gulshan', 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\resume\\2.pdf'),
(3, 3.33, '01547556294', '2000-01-19 06:00:00', 'MSc', 'KHULNA UNIVERSITY', 'applicant4@gmail.com', 'Yasir', 'male', 'Arafat', 2023, 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\image\\3.jpeg', 'Khulna', 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\resume\\3.pdf'),
(4, 3.43, '01580661685', '2023-07-21 06:00:00', 'BSc', 'DHAKA UNIVERSITY', 'applicant5@gmail.com', 'Sajal', 'male', 'Haldar', 2022, 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\image\\4.jpeg', 'Dhaka', 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\resume\\4.pdf'),
(5, 3.54, '01442758263', '2023-07-01 06:00:00', 'BA', 'JASHORE UNIVERSTY OF SCIENCE AND TECHNOLOGY', 'applicant7@gmail.com', 'Alamin', 'male', 'Hossen', 2018, 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\image\\5.jpeg', 'Dhaka', 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\resume\\5.pdf'),
(7, 3.61, '01580661685', '2023-07-07 06:00:00', 'BSc', 'KHULNA UNIVERSITY', 'ribnahossain@gmail.com', 'Abc', 'male', 'Khan', 2023, 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\image\\7.jpeg', 'Dhaka', 'C:\\Users\\Test\\Documents\\finalproject\\BJIT-TSS\\back-end\\tss\\src\\main\\resources\\static\\resume\\7.pdf');

-- --------------------------------------------------------

--
-- Table structure for table `validation_codes`
--

CREATE TABLE `validation_codes` (
  `validation_id` bigint(20) NOT NULL,
  `validation_code` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `validation_codes`
--

INSERT INTO `validation_codes` (`validation_id`, `validation_code`, `user_id`) VALUES
(1, '30202', 1),
(2, '68838', 2),
(3, '39796', 3),
(4, '76632', 4),
(5, '83792', 5),
(6, '00496', 6),
(7, '24365', 7);

-- --------------------------------------------------------

--
-- Table structure for table `written_marks`
--

CREATE TABLE `written_marks` (
  `written_mark_id` bigint(20) NOT NULL,
  `passed` bit(1) DEFAULT NULL,
  `written_mark` float DEFAULT NULL,
  `evaluator_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `written_marks`
--

INSERT INTO `written_marks` (`written_mark_id`, `passed`, `written_mark`, `evaluator_id`) VALUES
(1, NULL, NULL, 1),
(2, NULL, NULL, 1),
(3, NULL, NULL, 1),
(4, NULL, NULL, 1),
(5, NULL, NULL, 1),
(6, NULL, NULL, 1),
(7, NULL, NULL, NULL),
(8, NULL, NULL, NULL),
(9, NULL, NULL, NULL),
(10, NULL, NULL, NULL),
(11, NULL, NULL, NULL),
(12, b'1', 28, 1),
(13, b'1', 28, 1),
(14, b'1', 25, 1),
(15, NULL, NULL, 1),
(16, NULL, NULL, 1),
(17, NULL, NULL, 1),
(18, NULL, NULL, 1),
(19, NULL, NULL, 1),
(20, NULL, NULL, 1),
(21, NULL, NULL, 1),
(22, NULL, NULL, 1),
(23, NULL, NULL, 1),
(24, NULL, NULL, 1),
(25, NULL, NULL, 1),
(26, NULL, NULL, 1),
(27, NULL, NULL, 1),
(28, NULL, NULL, NULL),
(29, b'1', 18, 1),
(30, b'1', 27, 1),
(31, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `written_question_marks`
--

CREATE TABLE `written_question_marks` (
  `written_question_id` bigint(20) NOT NULL,
  `question_no` int(11) DEFAULT NULL,
  `written_question_mark` float DEFAULT NULL,
  `written_mark_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `written_question_marks`
--

INSERT INTO `written_question_marks` (`written_question_id`, `question_no`, `written_question_mark`, `written_mark_id`) VALUES
(1, 1, 10, 12),
(2, 2, 12, 12),
(3, 3, 2, 12),
(4, 4, 2, 12),
(5, 5, 2, 12),
(6, 1, 9, 13),
(7, 2, 8, 13),
(8, 3, 7, 13),
(9, 4, 2, 13),
(10, 5, 2, 13),
(11, 1, 9, 14),
(12, 2, 3, 14),
(13, 3, 7, 14),
(14, 4, 2, 14),
(15, 5, 4, 14),
(16, 1, 2, 29),
(17, 2, 4, 29),
(18, 3, 2, 29),
(19, 4, 7, 29),
(20, 5, 3, 29),
(21, 1, 3, 30),
(22, 2, 3, 30),
(23, 3, 4, 30),
(24, 4, 10, 30),
(25, 5, 7, 30);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidate_marks`
--
ALTER TABLE `candidate_marks`
  ADD PRIMARY KEY (`candidate_id`),
  ADD KEY `FKi9y2ydv46etp31n1bpjvjg9xm` (`aptitude_test_id`),
  ADD KEY `FKrymp7387qxo4njcyakcsywe1b` (`examinee_id`),
  ADD KEY `FKao0vvsp4u27b5fco6hft1tu2e` (`hr_viva_id`),
  ADD KEY `FKhqvpgsqv8979hgjkwkw2onk3g` (`technical_viva_id`),
  ADD KEY `FKau3j9h9nrncdnkpoaoa3i90rg` (`written_mark_id`);

--
-- Indexes for table `course_info`
--
ALTER TABLE `course_info`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `data_storage`
--
ALTER TABLE `data_storage`
  ADD PRIMARY KEY (`data_id`);

--
-- Indexes for table `email_info`
--
ALTER TABLE `email_info`
  ADD PRIMARY KEY (`email_id`);

--
-- Indexes for table `evaluator_info`
--
ALTER TABLE `evaluator_info`
  ADD PRIMARY KEY (`evaluator_id`);

--
-- Indexes for table `examinee_info`
--
ALTER TABLE `examinee_info`
  ADD PRIMARY KEY (`examinee_id`),
  ADD KEY `FK5tjioowuk22nqpc48i9er25ej` (`course_id`),
  ADD KEY `FKmgcnn67w9rsnyx7rglb60vrrd` (`user_id`);

--
-- Indexes for table `hidden_code_info`
--
ALTER TABLE `hidden_code_info`
  ADD PRIMARY KEY (`hidden_code`),
  ADD KEY `FKj5vo58mmin376tjpks8wa16i4` (`candidate_id`);

--
-- Indexes for table `login_info`
--
ALTER TABLE `login_info`
  ADD PRIMARY KEY (`login_id`),
  ADD KEY `FKqhrk58711eh4hkh8thqpjxmsh` (`evaluator_id`),
  ADD KEY `FK4g50iu9l6qjo5irsq5wgf7kue` (`user_id`);

--
-- Indexes for table `question_marks`
--
ALTER TABLE `question_marks`
  ADD PRIMARY KEY (`question_id`),
  ADD KEY `FK849smvkg7wmhev5rfic7ukgmq` (`round_id`);

--
-- Indexes for table `round_marks`
--
ALTER TABLE `round_marks`
  ADD PRIMARY KEY (`round_id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `validation_codes`
--
ALTER TABLE `validation_codes`
  ADD PRIMARY KEY (`validation_id`),
  ADD KEY `FKg04aua1364wag4jp77dj41u9m` (`user_id`);

--
-- Indexes for table `written_marks`
--
ALTER TABLE `written_marks`
  ADD PRIMARY KEY (`written_mark_id`),
  ADD KEY `FKtfnjysiei6vtgqeptrwrog3w` (`evaluator_id`);

--
-- Indexes for table `written_question_marks`
--
ALTER TABLE `written_question_marks`
  ADD PRIMARY KEY (`written_question_id`),
  ADD KEY `FKb1qhe6onqsx8k29phesh93gkb` (`written_mark_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `candidate_marks`
--
ALTER TABLE `candidate_marks`
  MODIFY `candidate_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `course_info`
--
ALTER TABLE `course_info`
  MODIFY `course_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `data_storage`
--
ALTER TABLE `data_storage`
  MODIFY `data_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `email_info`
--
ALTER TABLE `email_info`
  MODIFY `email_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evaluator_info`
--
ALTER TABLE `evaluator_info`
  MODIFY `evaluator_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `examinee_info`
--
ALTER TABLE `examinee_info`
  MODIFY `examinee_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `login_info`
--
ALTER TABLE `login_info`
  MODIFY `login_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `question_marks`
--
ALTER TABLE `question_marks`
  MODIFY `question_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `round_marks`
--
ALTER TABLE `round_marks`
  MODIFY `round_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `validation_codes`
--
ALTER TABLE `validation_codes`
  MODIFY `validation_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `written_marks`
--
ALTER TABLE `written_marks`
  MODIFY `written_mark_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `written_question_marks`
--
ALTER TABLE `written_question_marks`
  MODIFY `written_question_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
