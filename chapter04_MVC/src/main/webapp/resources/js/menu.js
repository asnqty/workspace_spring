// .header 하위에 있는 a 태그들에 버튼 클릭 이벤트 부여
// 기본 이벤트 방지
// 해당 속성에서 값 꺼내서 분기 태우기
document.querySelectorAll(".header a").forEach(aEle =>{
	aEle.addEventListener('click', (e)=>{
		e.preventDefault();
		
		let menu = e.target.getAttribute('href');

		if(menu === 'mainPage'){
			location.href = '/';
		}
		if(menu === 'boardList'){
			location.href = '/board/list';
		}
	})
});

//목록으로 이동 등에서 사용하기 위한 pageNum과 amount를 저장 및 꺼내기
function setStorageData(pageNum, amount){
	let pageData = {
		pageNum : pageNum,
		amount : amount
	};
	localStorage.setItem('page_data', JSON.stringify(pageData));
}

function getStorageData(){
	return JSON.parse(localStorage.getItem('page_data'));
}

function moveIndex(){
	const {pageNum, amount} = getStorageData();
	const sendData = `pageNum=${pageNum}&amount=${amount}`;
	location.href = '/board/list?' + sendData;
}