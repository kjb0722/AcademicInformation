--회원
DROP TABLE MEMBER;
CREATE TABLE MEMBER
(
	NUM NUMBER(5),
	ID VARCHAR2(20),
	NAME VARCHAR2(30),
	EMAIL VARCHAR2(30),
	PHONE VARCHAR2(20),
	ADDR VARCHAR2(50),
	HAGNYENO NUMBER(2),
	DEL_YN VARCHAR(5) CHECK(DEL_YN IN('Y','N')),
	AUTH NUMBER(3) CHECK(AUTH IN(10,50,99)),
	MEDATE DATE,
	CONSTRAINT MEMBER_NUM_PK PRIMARY KEY(NUM),
	CONSTRAINT MEMBER_ID_UK UNIQUE(ID)
);

DROP SEQUENCE MEMBER_NUM_SEQ;
CREATE SEQUENCE MEMBER_NUM_SEQ;

--회원 비밀번호
DROP TABLE PASS;
CREATE TABLE PASS
(
	NUM NUMBER(5),
	ID VARCHAR2(20),
	PASS VARCHAR2(20),
	DEL_YN VARCHAR(5) CHECK(DEL_YN IN('Y','N')),
	CONSTRAINT PASS_NUM_PK PRIMARY KEY(NUM),
	CONSTRAINT PASS_NUM_FK FOREIGN KEY(NUM) REFERENCES MEMBER(NUM)
);

--학과
DROP TABLE DEPARTMENT;
CREATE TABLE DEPARTMENT
(
	DENUM NUMBER(5),
	NAME VARCHAR2(30),
	DEL_YN VARCHAR(5) CHECK(DEL_YN IN('Y','N')),
	CONSTRAINT DEPARTMENT_DENUM_PK PRIMARY KEY(DENUM)
);

DROP SEQUENCE DEPARTMENT_DENUM_SEQ;
CREATE SEQUENCE DEPARTMENT_DENUM_SEQ;

--학과별 회원
DROP TABLE DPM_MEMBER;
CREATE TABLE DPM_MEMBER
(
	DENUM NUMBER(5),
	NUM NUMBER(5),
	CONSTRAINT DPM_MEMBER_PK PRIMARY KEY(DENUM, NUM),
	CONSTRAINT DPM_MEMBER_NUM_FK FOREIGN KEY(NUM) REFERENCES MEMBER(NUM),
    CONSTRAINT DPM_MEMBER_DENUM_FK FOREIGN KEY(DENUM) REFERENCES DEPARTMENT(DENUM)
);

--강의
DROP TABLE LECTURE;
CREATE TABLE LECTURE
(
	LENUM NUMBER(5),
	NUM NUMBER(5),
	NAME VARCHAR2(30),
	DEL_YN VARCHAR(5) CHECK(DEL_YN IN('Y','N')),
	CONSTRAINT LECTURE_LENUM_PK PRIMARY KEY(LENUM),
	CONSTRAINT LECTURE_NUM_FK FOREIGN KEY(NUM) REFERENCES MEMBER(NUM)
);

DROP SEQUENCE LEC_LENUM_SEQ;
CREATE SEQUENCE LEC_LENUM_SEQ;

--강의별 회원
DROP TABLE LEC_MEMBER;
CREATE TABLE LEC_MEMBER
(
	LMNUM NUMBER(5),
	LENUM NUMBER(5),
	NUM NUMBER(5),
	LEDATE DATE,
	DEL_YN VARCHAR(5) CHECK(DEL_YN IN('Y','N')),
	CONSTRAINT LEC_MEMBER_PK PRIMARY KEY(LENUM, NUM),
	CONSTRAINT LEC_MEMBER_LENUM_FK FOREIGN KEY(LENUM) REFERENCES LECTURE(LENUM),
	CONSTRAINT LEC_MEMBER_NUM_FK FOREIGN KEY(NUM) REFERENCES MEMBER(NUM)
);

DROP SEQUENCE LM_LMNUM_SEQ;
CREATE SEQUENCE LM_LMNUM_SEQ;

--성적
DROP TABLE GRADE;
CREATE TABLE GRADE
(
	GRNUM NUMBER(5),
	LENUM NUMBER(5),
	NUM NUMBER(5),
	SCORE NUMBER(4),
	RANK VARCHAR2(20),
	DEL_YN VARCHAR(5) CHECK(DEL_YN IN('Y','N')),
	GRDATE DATE,
	CONSTRAINT GRADE_PK PRIMARY KEY(GRNUM),
	CONSTRAINT GRADE_LENUM_FK FOREIGN KEY(LENUM) REFERENCES LECTURE(LENUM),
	CONSTRAINT GRADE_NUM_FK FOREIGN KEY(NUM) REFERENCES MEMBER(NUM)
);

DROP SEQUENCE GR_GRNUM_SEQ;
CREATE SEQUENCE GR_GRNUM_SEQ;