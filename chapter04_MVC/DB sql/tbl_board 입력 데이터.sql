insert into tbl_board(bno, title, content, writer)
values(seq_board.nextval, '테스트제목', '테스트내용', 'user00');

insert into tbl_board(bno, title, content, writer)
values(seq_board.nextval, '테스트제목1', '테스트내용1', 'user01');

insert into tbl_board(bno, title, content, writer)
values(seq_board.nextval, '테스트제목2', '테스트내용2', 'user02');

insert into tbl_board(bno, title, content, writer)
values(seq_board.nextval, '테스트제목3', '테스트내용3', 'user03');

insert into tbl_board(bno, title, content, writer)
values(seq_board.nextval, '테스트제목4', '테스트내용4', 'user04');

commit;