CREATE TABLE member (
    id       VARCHAR(10)  NOT NULL PRIMARY KEY, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 �����ϴ� �÷� 
    pw       VARCHAR(10)  NOT NULL, -- �н�����
    name     VARCHAR(10)  NOT NULL, -- ����
    birth    VARCHAR(12)  NOT NULL, -- �������
    tel      VARCHAR(13)  NULL,     -- ��ȭ��ȣ
    mail     VARCHAR(25)  NOT NULL, -- ���ڿ��� �ּ�
    zipcode  VARCHAR(7)   NULL,     -- �����ȣ, 101-101
    address1 VARCHAR(50) NULL,      -- �ּ� 1
    address2 VARCHAR(50) NULL,      -- �ּ� 2(�������ּ�)
    ccode VARCHAR(12)  NOT NULL, -- Ŭ���ڵ�
    mlevel   CHAR(1)      NOT NULL, -- ȸ�� ���, A(������), B(������), C(Ŭ����), D(�Ϲ�), E(Ż��)
    constraint member_ccode FOREIGN KEY (ccode) REFERENCES club(ccode)
);

drop table member;

alter table member drop foreign key member_ccode;

alter table member modify address2 varchar(50)

--�ܷ�Ű�� �����Ǵ� ���̺� ������ ���� �� �־�� insert�� �ȴ�
--club�� clubcode�� 'fcsoldesk'�� ������  member�� clubcode�� 'fcsoldesk'�� ���
INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk', 'soldesk','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308001','A');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk2', 'soldesk2','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308002','B');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk3', 'soldesk3','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308003','C');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk4', 'soldesk4','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk5', 'soldesk5','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk6', 'soldesk6','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk7', 'soldesk7','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk8', 'soldesk8','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk9', 'soldesk9','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk10', 'soldesk10','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk11', 'soldesk11','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk12', 'soldesk12','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk13', 'soldesk13','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308001','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk14', 'soldesk14','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308002','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk15', 'soldesk15','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c20180308003','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk16', 'soldesk16','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('soldesk17', 'soldesk18','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','c00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz3', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz4', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz5', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz6', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz7', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz8', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz9', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');

INSERT INTO member(id, pw, name, birth, tel, mail, zipcode, address1, address2, ccode, mlevel)
VALUES('zzzz10', '1234','��', '19840111', '010-9353-9545', 'chhho@daum.net', '248-888', '����', '���α�','C00000000000','D');


delete from member where id='soso2'

--�α���
SELECT COUNT(id) as cnt FROM member WHERE id='soldesk' AND pw='soldesk' AND mlevel IN ('A','B')


SELECT * FROM member where id='clevel1'

--club��
SELECT A.id, B.cname 
FROM member A join club B 
on A.clubcode=B.clubcode

--����¡
SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, clubcode, mlevel 
FROM(SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, clubcode, mlevel, @ROWNUM := @ROWNUM+1 as ROW 
	 FROM (SELECT id, pw, name, birth, tel, mail, zipcode, address1, address2, clubcode, mlevel 
		   FROM member 
           ORDER BY id DESC)A, (SELECT @ROWNUM := 0) 
	 B)C 
WHERE C.ROW>=startRow AND C.ROW<=endRow

update member set mlevel='A' where id='soldesk'
