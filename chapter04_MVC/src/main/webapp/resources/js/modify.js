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

// 버튼 클릭 이벤트 부여
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
	
	// pageNum을 스토리지에서 꺼내서 폼에 담아 같이 전송
	// 이미 있으면 재사용, 없으면 생성
	let pageNumInput = f.querySelector("input[name='pageNum']");

	if (!pageNumInput) {
		pageNumInput = document.createElement("input");
		pageNumInput.type = "hidden";
		pageNumInput.name = "pageNum";
		f.appendChild(pageNumInput);
	}
	
	const storageData = getStorageData();
	
	// 값 세팅
	pageNumInput.value = storageData.pageNum;
	
	// amount 스토리지에서 꺼내서 폼에 담아 같이 전송
	// 이미 있으면 재사용, 없으면 생성
	let amountInput = f.querySelector("input[name='amount']");

	if (!amountInput) {
		amountInput = document.createElement("input");
		amountInput.type = "hidden";
		amountInput.name = "amount";
		f.appendChild(amountInput);
	}
	
	// 값 세팅
	amountInput.value = storageData.amount;
	
	// 업로드된 파일의 정보를 가져와서 담기
	let str = ``;
	document.querySelectorAll(`.uploadResult ul li`).forEach((li, index) => {
		let path = li.getAttribute('path');
		let uuid = li.getAttribute('uuid');
		let fileName = li.getAttribute('fileName');
		
		str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${path}"/>`;
		str += `<input type="hidden" name="attachList[${index}].uuid" value="${uuid}"/>`;
		str += `<input type="hidden" name="attachList[${index}].fileName" value="${fileName}"/>`;
	});
	
	// 기존에 form에 작성한 value가 날아갈 수 있음
// f.innerHTML += str;
	f.insertAdjacentHTML('beforeend', str);
	
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

// 파일 리스트 콘솔에 출력
(function(){
	fetch(`/board/getAttachList/${f.bno.value}`)
		.then(response => response.json())
		.then(data => {
			showUploadFile(data);
		})
		.catch(err => console.log(err));
})();

// 파일 업로드 전처리 과정 내용들 (검증)
const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
const MAX_SIZE = 5242880; // 5MB

function checkExtension(fileName, fileSize){
	if(fileSize >= MAX_SIZE){
		alert("파일 사이즈 초과");
		return;
	}
	if(regex.test(fileName)){
		alert("해당 종류의 파일은 업로드할 수 없습니다.");
		return;
	}
	return true;
}

// 파일을 비동기로 업로드 후 선택한 파일이 없는 상태로 초기화 하기 위한 과정
// 비어있는 요소 복사해두기
let uploadDiv = document.querySelector(".uploadDiv");
// 하위 노드까지 복사
let cloneObj = uploadDiv.firstElementChild.cloneNode(true);

// 실제 파일 업로드
document.querySelector('input[type="file"]').addEventListener('change', ()=>{
	const inputFile = document.querySelector('input[type="file"]');
	const files = inputFile.files;
	const formData = new FormData();
	
	for(let i=0; i<files.length; i++){
		
		if(!checkExtension(files[i].name, files[i].size)){
			return false;
		}
		
		formData.append('uploadFile', files[i]);
	}
	
	fetch(`/uploadAsyncAction`, {method : 'post', body : formData})
		.then(response => response.json())
		.then(data => {
			console.log(data);
			
			inputFile.value = '';
			showUploadFile(data);
		})
		.catch(err => console.log(err));
});

// 업로드 완료된 목록 보여주는 함수
let uploadResult = document.querySelector('.uploadResult ul');
function showUploadFile(uploadResultArr){
	let str = ``;
	uploadResultArr.forEach(file =>{
		let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName);
		
		str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`;
// str += `<a href="/download?fileName=${fileCallPath}">`;
		str += `${file.fileName}`;
// str += `</a>`;
		str += `<span data-file="${fileCallPath}", data-uuid="${file.uuid}"> X </span>`;
		str += `</li>`;
	});
	uploadResult.innerHTML = str;
	spanAddEvent();
}

// 위에서 생성된 X에 클릭이벤트 부여
function spanAddEvent(){
	document.querySelectorAll('.uploadResult ul li span').forEach(spanEle=>{
		spanEle.addEventListener('click', e=>{
			// 삭제할 파일의 경로를 저장
			let removeStr = ``;
			removeStr += `<input type="hidden" name="removeFile" value="${e.target.dataset.file}"/>`;
			f.insertAdjacentHTML('beforeend', removeStr);
			// 삭제할 파일의 uuid를 저장
			let removeUuid = ``;
			removeUuid += `<input type="hidden" name="removeUuid" value="${e.target.dataset.uuid}"/>`;
			f.insertAdjacentHTML('beforeend', removeUuid);
			// 삭제할 파일의 li(유저에게 보여주는 정보)를 삭제
			e.target.closest('li').remove();
		});
	})
};