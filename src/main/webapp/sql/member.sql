CREATE TABLE member (
    id       VARCHAR(10)  NOT NULL PRIMARY KEY, -- 아이디, 중복 안됨, 레코드를 구분하는 컬럼 
    pw       VARCHAR(10)  NOT NULL, -- 패스워드
    name     VARCHAR(10)  NOT NULL, -- 성명
    birth    VARCHAR(12)  NOT NULL, -- 생년월일
    tel      VARCHAR(13)  NULL,     -- 전화번호
    mail     VARCHAR(25)  NOT NULL, -- 전자우편 주소
    zipcode  VARCHAR(7)   NULL,     -- 우편번호, 101-101
    address1 VARCHAR(50) NULL,      -- 주소 1
    address2 VARCHAR(50) NULL,      -- 주소 2(나머지주소)
    ccode VARCHAR(12)  NOT NULL, -- 클럽코드
    mlevel   CHAR(1)      NOT NULL, -- 회원 등급, A(관리자), B(구장주), C(클럽장), D(일반), E(탈퇴)
    constraint member_ccode FOREIGN KEY (ccode) REFERENCES club(ccode)
);

drop table member;

alter table member drop foreign key member_ccode;

alter table member modify address2 varchar(50)

--외래키로 참조되는 테이블에 동일한 값이 들어가 있어야 insert가 된다
--club에 clubcode가 'fcsoldesk'가 있으면  member에 clubcode에 'fcsoldesk'를 사용
INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk', 'soldesk','가', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308001','A');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk2', 'soldesk2','나', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308002','B');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk3', 'soldesk3','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308003','C');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk4', 'soldesk4','가', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk5', 'soldesk5','나', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk6', 'soldesk6','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk7', 'soldesk7','가', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk8', 'soldesk8','나', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk9', 'soldesk9','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk10', 'soldesk10','가', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk11', 'soldesk11','나', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk12', 'soldesk12','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk13', 'soldesk13','가', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk14', 'soldesk14','나', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk15', 'soldesk15','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk16', 'soldesk16','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk17', 'soldesk18','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','c00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz3', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz4', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz5', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz6', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz7', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz8', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz9', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz10', '1234','다', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '서울', '종로구','C00000000000','D');


delete from member where id='soso2'

--로그인
SELECT COUNT(id) as cnt FROM member WHERE id='soldesk' AND pw='soldesk' AND mlevel IN ('A','B')


SELECT * FROM member where id='clevel1'

--club명
SELECT A.id, B.cname 
FROM member A join club B 
on A.clubcode=B.clubcode

--페이징
SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, clubcode, mlevel 
FROM(SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, clubcode, mlevel, @ROWNUM := @ROWNUM+1 as ROW 
	 FROM (SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, clubcode, mlevel 
		   FROM member 
           ORDER BY id DESC)A, (SELECT @ROWNUM := 0) 
	 B)C 
WHERE C.ROW>=startRow AND C.ROW<=endRow

update member set mlevel='A' where id='soldesk'
