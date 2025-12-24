// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/boardList.css';
// 2. line 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

document.querySelector('#registerBtn').addEventListener('click', (e)=>{
	location.href = '/board/register';
});

// 게시글 제목을 누르면 해당 글로 이동
document.querySelectorAll('tbody tr td a').forEach(aEle =>{
	aEle.addEventListener('click', (e)=>{
		e.preventDefault();

		let bno = e.target.getAttribute('href');

		location.href = '/board/get?bno=' + bno;
	})
});