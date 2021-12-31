(()=>{
	document.querySelector('#tmCodeCheck').addEventListener('click',e=>{
		let tmCode = document.querySelector('#tmCode').value;
		console.log(tmCode);
		fetch("/team/tm-code-check?tmCode="+tmCode)
		.then(response =>{
			if(response.ok){
					return response.text();
			}else{
				throw new Error(response.status);
			}
		})
		.then(text => {
			if(text == 'exists'){
				location.href='/team/join-team?tmCode='+tmCode;
			} else{
				drawMsg('<i class="fas fa-times-circle"></i><br>팀이 존재하지 않거나 이미 삭제된 팀입니다.');
			}
		})
		.catch(error=>{
			drawMsg('<i class="fas fa-times-circle"></i><br>응답에 실패했습니다.<br>상태코드 : '+error); ;
		});
	});
})();