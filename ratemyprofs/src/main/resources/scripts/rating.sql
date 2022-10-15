INSERT INTO RATING(OVERALL_SCORE, DIFFICULTY_LEVEL, WILL_RETAKE, FOR_CREDIT, REQUIRE_TEXTBOOK, REQUIRE_ATTENDANCE, RECEIVED_GRADE, REVIEW, PROF_ID_PROF, COURSE_ID_COURSE, DEPT_ID_DEPT, COURSE_CODE, CREATED, MODIFIED, USER_ID_USER, RATING_STATUS) VALUES
	              (5, 3, 1, 1, 0, 0, 'A+', 'Professor Ilir Dema taught a course at York University this past winter semester. He was a clear lecturer who provided amazing feedback in regards to the material being taught. Furthermore, he was VERY accessible outside of class if any student ever needed clarification on a lab or the material being taught. Considerate professor, highly recommend!', 
	               (SELECT ID_PROF FROM PROF WHERE PROF_FIRST_NAME='Ilir' AND PROF_LAST_NAME='Dema'),
	               (SELECT ID_COURSE FROM COURSE WHERE COURSE_CODE='CSC301'),
	               (SELECT ID_DEPT FROM DEPT WHERE DEPT_NAME='Mathematical and Computational Sciences'),
	               'CSC301', '2022-05-09', '2022-05-09', 
	               (SELECT ID_USER FROM USER WHERE USERNAME='jsmith'), 'A');
INSERT INTO RATING(OVERALL_SCORE, DIFFICULTY_LEVEL, WILL_RETAKE, REVIEW, PROF_ID_PROF, COURSE_ID_COURSE, DEPT_ID_DEPT, COURSE_CODE, CREATED, MODIFIED, USER_ID_USER, RATING_STATUS) VALUES
	              (2, 4, 0, 'We had to teach ourselves lots of things for the project. In a way it was good since we had to learn how to self teach, but for how much we are paying I do not think we should be left to follow online tutorials for so much of the course materials.', 
	               (SELECT ID_PROF FROM PROF WHERE PROF_FIRST_NAME='Ilir' AND PROF_LAST_NAME='Dema'),
	               (SELECT ID_COURSE FROM COURSE WHERE COURSE_CODE='CSCC01'),
	               (SELECT ID_DEPT FROM DEPT WHERE DEPT_NAME='Computer and Mathematical Sciences'),
	               'CSCC01', '2020-11-04', '2020-11-04', 
	               (SELECT ID_USER FROM USER WHERE USERNAME='mgarcia'), 'A');
INSERT INTO RATING(OVERALL_SCORE, DIFFICULTY_LEVEL, WILL_RETAKE, RECEIVED_GRADE, REVIEW, PROF_ID_PROF, COURSE_ID_COURSE, DEPT_ID_DEPT, COURSE_CODE, CREATED, MODIFIED, USER_ID_USER, RATING_STATUS) VALUES
	              (4, 4, 1, 'A', 'Best prof ever, super caring. Definitely recommend!', 
	               (SELECT ID_PROF FROM PROF WHERE PROF_FIRST_NAME='Ilir' AND PROF_LAST_NAME='Dema'),
	               (SELECT ID_COURSE FROM COURSE WHERE COURSE_CODE='CSCC01'),
	               (SELECT ID_DEPT FROM DEPT WHERE DEPT_NAME='Computer and Mathematical Sciences'),
	               'CSCC01', '2020-12-07', '2020-12-07', 
	               (SELECT ID_USER FROM USER WHERE USERNAME='jsmith'), 'A');              