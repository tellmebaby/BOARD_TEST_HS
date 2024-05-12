-- Active: 1714701530602@@127.0.0.1@3306@joeun
CREATE TABLE `board` (
  `no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `content` text,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `views` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`)
) COMMENT='게시판';

INSERT INTO `board` ( `title`, `writer`, `content`)
VALUES ( '제목01', '작성자01', '내용01'),
        ( '제목02', '작성자02', '내용02'),
         ( '제목03', '작성자03', '내용03')
;

UPDATE `board` SET  `title` = '수정', 
`writer` = '수정',
 `content` = '수정',
  `upd_date` = SYSDATE()
  WHERE no = '1';



SELECT *
FROM board;

TRUNCATE board;


DROP TABLE reply;
DROP TABLE board;