// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/get.css';
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

    // 수정 페이지로 이동 버튼
    if(type === 'modifyBtn'){
      modify();
    }
    
 // 목록으로 이동 버튼
    else if(type === 'indexBtn'){
    	moveIndex();
    }
    
  })
});

// 수정 페이지로 이동
function modify() {
	let bno = f.bno.value;
	location.href = `/board/modify?bno=${bno}`;
}

//--------댓글 관련 스크립트------------
const rs = replyService;	// reply.js에서 CRUD 담당 객체

//rs.add(
//	{
//		bno : f.bno.value,
//		reply : 'JS TEST',
//		replyer : 'tester'
//	}, 
//	function(result){
//		console.log(result);
//	}
//);

//rs.getList(f.bno.value, function(result){console.log(result)});

//rs.update(21, {reply : 'MODIFY TEST'}, function(result) {console.log(result)})

//rs.get(21, function(result){console.log(result)});

//rs.remove(21, function(result){console.log(result)});