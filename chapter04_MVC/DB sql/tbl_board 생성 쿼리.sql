create SEQUENCE seq_board;

create table tbl_board(
    bno number(10,2),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);

alter table tbl_board
add constraint pk_board
primary key(bno);

commit;

select * from tbl_board;

select * from tbl_reply;

-- tbl_board 테이블 수정
alter table tbl_board add (replycnt number default 0);

-- 기존의 댓글이 존재했다면, replycnt에 반영
update tbl_board set replycnt = (select count(rno) from tbl_reply where tbl_reply.bno = tbl_board.bno) where bno = 1;

