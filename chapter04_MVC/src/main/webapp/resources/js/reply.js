console.log("reply module...");

// 댓글 기능 관련 객체를 만들고 그 객체의 안에 기능을 넣어둠
// 다른 js에서 댓글 객체를 만들고 객체 이름.메소드 이름()으로 해당 메소드를 사용 가능
const replyService = (function(){
	
	// 댓글 작성
	function add(reply, callback){
		fetch('/reply/new', 
				{
					method : 'post', 
					body : JSON.stringify(reply), 
					headers : {'Content-type' : 'application/json; charset=utf-8'}
				}
			)
		.then(response => {
			if(!response.ok || response.status != 200){
				throw new Error("오류 발생");
			}
			
			return response.text()
		})
		.then(data =>{callback(data)})
		.catch(err => console.log(err));
	}
	
	// 댓글 조회
	function getList(bno, callback){
		fetch('/reply/pages/' + bno + '.json')
		.then(response => response.json())
		.then(data =>{callback(data)})
		.catch(err => console.log(err));
	}
	
	// 댓글 삭제
	function remove(rno, callback){
		fetch('/reply/' + rno, {method : 'delete'})
		.then(response => response.text())
		.then(data =>{callback(data)})
		.catch(err => console.log(err));
	}
	
	// 댓글 수정
	function update(rno, rvo, callback){
		fetch('/reply/' + rno, 
				{
					method : 'put', 
					body : JSON.stringify(rvo), 
					headers : {'Content-type' : 'application/json; charset=utf-8'}
				}
			)
		.then(response => response.text())
		.then(data =>{callback(data)})
		.catch(err => console.log(err));
	}
	
	// 댓글 검색
	function get(rno, callback){
		fetch('/reply/' + rno + '.json')
		.then(response => response.json())
		.then(data =>{callback(data)})
		.catch(err => console.log(err));
	}
	
	return {add : add, getList : getList, remove : remove, update : update, get : get};
})();