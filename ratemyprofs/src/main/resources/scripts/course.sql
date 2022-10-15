INSERT INTO COURSE(ID_DEPT, COURSE_STATUS, COURSE_NAME, COURSE_CODE) VALUES
	((SELECT ID_DEPT FROM DEPT WHERE DEPT_NAME='Computer and Mathematical Sciences'),      'A', 'Introduction to Software Engineering', 'CSCC01'),
	((SELECT ID_DEPT FROM DEPT WHERE DEPT_NAME='Mathematical and Computational Sciences'), 'A', 'Introduction to Software Engineering', 'CSC301'),
	((SELECT ID_DEPT FROM DEPT WHERE DEPT_NAME='Philosophy'),                              'A', 'Reason and Truth',                     'PHLA10');