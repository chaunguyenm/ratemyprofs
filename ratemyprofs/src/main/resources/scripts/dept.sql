INSERT INTO DEPT(DEPT_NAME, ID_INST, DEPT_STATUS) VALUES 
	('Computer and Mathematical Sciences',      (SELECT ID_INST FROM INST WHERE INST_NAME='University of Toronto Scarborough'), 'A'),
	('Philosophy',                              (SELECT ID_INST FROM INST WHERE INST_NAME='University of Toronto Scarborough'), 'A'),
	('Mathematical and Computational Sciences', (SELECT ID_INST FROM INST WHERE INST_NAME='University of Toronto Mississauga'), 'A'),
	('Communication and Design',                (SELECT ID_INST FROM INST WHERE INST_NAME='Ryerson University'),                'I');