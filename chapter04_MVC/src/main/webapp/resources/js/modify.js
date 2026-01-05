// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/modify.css';
// 2. line 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// form 받아오기
const f = document.forms[0];

//버튼 클릭 이벤트 부여
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    let type = btn.id;

    // 게시글 수정
    if(type === 'modifyBtn'){
      modify();
    }
    
    // 게시글 삭제
    if(type === 'removeBtn'){
    	remove();
    }
    
 // 목록으로 이동 버튼
    else if(type === 'indexBtn'){
    	moveIndex();
    }
    
  })
});

// 게시글 수정
function modify() {
	if(f.title.value == ''){
		alert("제목을 입력해주세요.");
		return;
	}
	
	if(f.content.value == ''){
		alert("내용을 입력해주세요.");
		return;
	}
	
	// 이미 있으면 재사용, 없으면 생성
	let pageInput = f.querySelector("input[name='pageNum']");

	if (!pageInput) {
	  pageInput = document.createElement("input");
	  pageInput.type = "hidden";
	  pageInput.name = "pageNum";
	  f.appendChild(pageInput);
	}
	
	const storageData = getStorageData();
	
	// 값 세팅
	pageInput.value = storageData.pageNum;
	
	f.action = '/board/modify';
	f.submit();
}

// 게시글 삭제
function remove(){
	if(confirm("글을 삭제하시겠습니까?")){
		// 게시글을 삭제할 때는 form에서 bno만 필요하기에 나머지를 지우고 bno를 추가하는 과정
		const bnoEle = f.bno;	// bno를 담고 있는 input 태그
		f.innerHTML = '';		// form 태그 내부 비우기
		f.appendChild(bnoEle);	// form 태그 내부에 bno 추가
		
		// 이미 있으면 재사용, 없으면 생성
		let pageInput = f.querySelector("input[name='pageNum']");

		if (!pageInput) {
		  pageInput = document.createElement("input");
		  pageInput.type = "hidden";
		  pageInput.name = "pageNum";
		  f.appendChild(pageInput);
		}
		
		const storageData = getStorageData();
		
		// 값 세팅
		pageInput.value = storageData.pageNum;
		
		f.action = '/board/remove';
		f.submit();
	}
}