// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/register.css';
// 2. line 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.type = 'text/css';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// form 받아오기
const f = document.forms[0];

// 버튼 클릭 이벤트 부여
document.querySelectorAll("button").forEach(btn =>{
  btn.addEventListener('click', ()=>{
    let type = btn.id;

    // 새 게시글 등록 버튼
    if(type === 'registerBtn'){
      register();
    }
    
    // 다시 작성 버튼
    else if(type === 'resetBtn'){
      f.reset();
    }
    
    // 목록으로 이동 버튼
    else if(type === 'indexBtn'){
    	moveIndex();
    }
  })
});

// 새 게시글 등록
function register() {
	// 각 데이터 검증 후 데이터 전송 -> 삽입
	let title = f.title;
	let writer = f.writer;
	let content = f.content;
		
	if(title.value == ''){
		alert("제목을 입력해주세요.");
		return;
	}
	if(writer.value == ''){
		alert("작성자를 입력해주세요.");
		return;
	}
	if(content.value == ''){
	  alert("내용을 입력해주세요.");
	  return;
	}
	
	f.action = '/board/register';
	f.submit();
}