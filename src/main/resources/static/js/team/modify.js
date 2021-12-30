let fileCheck = (e) => {
	let file = e[0].name.split('.');
	let fileSize = e[0].size;
	let filetype = file[file.length-1].toLowerCase()
	let files = document.getElementsByName('teamFile');
	if(filetype != 'jpg' && filetype != 'gif' && filetype != 'jpeg' && filetype != 'png') {
		drawMsg('<i class="fas fa-times-circle"></i><br>JPG/GIF/PNG 파일만 업로드 가능합니다.');
		files[0].value='';
		return false;
	}
	if(fileSize > (20 * 1024 * 1024)) {
		drawMsg('<i class="fas fa-times-circle"></i><br>파일 사이즈는 20MB 이하만 가능합니다.');
		files[0].value='';
		return false;
	}
}
let drawMsg = (msg) => {
	document.querySelector('.pop-msg-wrap.question').style.display='none';
	document.querySelector('.pop-msg-wrap.answer').style.display='none';
	document.querySelector('.pop-msg-wrap.msg').style.display='flex';
	document.querySelector('.pop-msg-wrap.msg p').innerHTML=msg;
}
let btnClose = () => {
	let msgWrap = document.querySelectorAll('.pop-msg-wrap');
	msgWrap.forEach(e=>{
		e.style.display='none';		
	});
}