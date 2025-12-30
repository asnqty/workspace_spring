// --- CSS 파일 추가
// CSS를 여러개 추가하면 경로를 배열로 만들고 반복문을 돌린다.
// 1. 파일 경로 설정
const CSS_FILE_PATH = [
	'/resources/css/boardList.css',
	'/resources/css/page.css'
	];

cssBinding(CSS_FILE_PATH);
function cssBinding(cssFiles) {
	cssFiles.forEach(css =>{
		// 2. line 태그 생성
		let linkEle = document.createElement('link');
		linkEle.rel = 'stylesheet';
		linkEle.type = 'text/css';
		linkEle.href = css;
		// 3. head 태그에 link 엘리먼트 추가
		document.head.appendChild(linkEle);
	})
}

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

// 페이지 이동 등에서 사용하기 위한 객체 cri를 저장하는 방법
let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');
if(!pageNum || !amount){
	pageNum = 1;
	amount = 10;
}
setStorageData(pageNum, amount);

// 페이지 번호를 누르면 해당 페이지로 이동
document.querySelectorAll('.page-nation li a').forEach(aEle =>{
	aEle.addEventListener('click', (e)=>{
		e.preventDefault();
		
		pageNum = e.target.getAttribute('href');
		setStorageData(pageNum, amount);
		
		let sendData = '?pageNum=' + pageNum + '&amount=' + amount ;
		location.href = '/board/list' + sendData;
	})
});
