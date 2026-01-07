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

// --- CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH2 = '/resources/css/modal.css';
// 2. line 태그 생성
let linkEle2 = document.createElement('link');
linkEle2.rel = 'stylesheet';
linkEle2.type = 'text/css';
linkEle2.href = CSS_FILE_PATH2;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle2);

// form 받아오기
const f = document.forms[0];

// 버튼 클릭 이벤트 부여
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
    
    // 댓글 달기 버튼
    else if(type === 'replyBtn'){
    	registerModalPage();
    }
    
    // 댓글 달기 창에서 등록 버튼
    else if(type === 'addReplyBtn'){
    	registerReply();
    }
    
    // 댓글 달기 및 수정 창 닫기 버튼
    else if(type === 'closeModalBtn'){
    	closeModal();
    }
    
    // 댓글 수정 창 수정 버튼
    else if(type === 'modifyReplyBtn'){
    	modifyReply();
    }
    
    // 댓글 수정 창 삭제 버튼
    else if(type === 'removeReplyBtn'){
    	removeReply()
    }
    
  })
});

// 수정 페이지로 이동
function modify() {
	let bno = f.bno.value;
	location.href = `/board/modify?bno=${bno}`;
};

// --------댓글 관련 스크립트------------
const rs = replyService;	// reply.js에서 CRUD 담당 객체

function showList(){
	let bno = f.bno.value;
	let replyUL = document.querySelector('.chat');
	
	rs.getList(bno, jsonArray=>{
		
		let msg = '';
		
		jsonArray.forEach(reply =>{
			if(!reply){
				replyUL.innerHTML = '';
				return;
			}
			
			// 댓글을 클릭할때 던져주는 this는 해당 li 태그 자체를 던져줌
			msg += `<li data-rno=${reply.rno} onclick="modifyModalPage(this)">`;
			msg += `<div>`;
			msg += `<div class="chat-header">`;
			msg += `<strong>${reply.replyer}</strong>`;
            msg += `<small class="pull-right">${displayTime(reply.replydate)}</small>`;
            msg += `</div>`;
            msg += `<p>${reply.reply}</p>`;
            msg += `</div>`;
            msg += `</li>`;
            
		});
		
		replyUL.innerHTML = msg;
	});
};
showList();

function displayTime(unixTimeStamp){
	let myDate = new Date(unixTimeStamp);
	
	let y = myDate.getFullYear();
	let m = String(myDate.getMonth()+1).padStart(2,'0');
	let d = String(myDate.getDate()).padStart(2,'0');
	
	let date = y + '-' + m + '-' + d;
	return date;
};

// -------------modal 관련 스크립트-----------------
const modal = document.querySelector('#modal');
const inputReply = document.querySelector('input[name="reply"]');
const inputReplyer = document.querySelector('input[name="replyer"]');
const inputReplydate = document.querySelector('input[name="replydate"]');
const addReplyBtn = document.querySelector('#addReplyBtn');
const modifyReplyBtn = document.querySelector('#modifyReplyBtn');
const removeReplyBtn = document.querySelector('#removeReplyBtn');
const closeModalBtn = document.querySelector('#closeModalBtn');

function openModal(){
	modal.style.display = "block";
	// modal 창이 떠있을 때 페이지의 스크롤이 가능하게 하려면 overflow를 auto로 설정, 불가능 하게 하려면 hidden으로
	// 설정. default는 auto
// document.body.style.overflow = "hidden";
};

function closeModal(){
	modal.style.display = "none";
};

// 댓글 달기 버튼 클릭시 나오는 댓글 등록 창 함수
function registerModalPage(){
	// 모여질 목록 수정
	regReplyModalStyle();
	// 입력 내용 초기화 & 불러오기
	inputReply.value = '';
	inputReplyer.value = '';
	
	openModal();
};

// 댓글 달기 창 스타일 변경 함수
function regReplyModalStyle(){
	// modifyReplyBtn에 hide 클래스 추가
// modifyReplyBtn.class = 'hide';
	
	// classList는 class를 여러개 관리 가능. add를 하면 class가 추가, remove하면 해당 class가 삭제
	modifyReplyBtn.classList.add('hide');
	removeReplyBtn.classList.add('hide');
	
	
	// closest는 해당 요소의 부모 태그에서 넣어준 태그가 나올때까지 올라감. 해당 div 태그에 class를 부여했음
	inputReplydate.closest('div').classList.add('hide');
	
	// 댓글 수정 페이지에서 hide 한 등록 버튼의 hide 제거
	addReplyBtn.classList.remove('hide');
	
	// 댓글 수정 페이지에서 작성자 input 창에 부여한 readonly 속성 제거 
	inputReplyer.readOnly = false;
};

// 댓글 등록
function registerReply(){
	if(!inputReply.value || !inputReplyer.value){
		alert("모든 내용을 입력하세요");
		return;
	}
	
	const sendData = {
			bno : f.bno.value,
			reply : inputReply.value,
			replyer : inputReplyer.value,
	};
	
	rs.add(sendData, result =>{
		console.log(result);
		closeModal();
		showList();
	});
	
};

// 댓글 클릭 함수
let rno;
function modifyModalPage(t){
	// 보여질 목록 수정
	modReplyModalStyle();
	
	// 입력 내용 초기화 & 불러오기
	rno = t.dataset.rno;
	
	rs.get(rno, function(result){
		inputReply.value = result.reply;
		inputReplyer.value = result.replyer;
		inputReplydate.value = displayTime(result.replydate);
	});
	
	openModal();
	
}

function modReplyModalStyle(){
	// 댓글 달기에서 hide 된 수정, 삭제 버튼의 hide 제거
	modifyReplyBtn.classList.remove('hide');
	removeReplyBtn.classList.remove('hide');
	addReplyBtn.classList.add('hide');
	
	// 댓글 달기에서 hide 된 댓글 등록 날짜의 hide 제거
	inputReplydate.closest('div').classList.remove('hide');
	
	inputReplyer.readOnly = true;
	inputReplydate.readOnly = true;;
}

// 댓글 수정 창에서 댓글 수정
function modifyReply(){
	if(!inputReply.value){
		alert("수정할 댓글 내용을 입력해주세요.");
		return;
	}
	
	rs.update(rno, {reply : inputReply.value}, function(result) {
		console.log(result);
		closeModal();
		showList();
	});
}

// 댓글 수정 창에서 댓글 삭제
function removeReply(){
	if(confirm("댓글을 삭제하시겠습니까?")){
		rs.remove(rno,f.bno.value, function(result){
			console.log(result);
			closeModal();
			showList();
		})
	}
}

// rs.add(
// {
// bno : f.bno.value,
// reply : 'JS TEST',
// replyer : 'tester'
// },
// function(result){
// console.log(result);
// }
// );

// rs.getList(f.bno.value, function(result){console.log(result)});

// rs.remove(21, function(result){console.log(result)});

// rs.update(21, {reply : 'MODIFY TEST'}, function(result)
// {console.log(result)})

// rs.get(21, function(result){console.log(result)});
