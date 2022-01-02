let msgWrap = document.querySelectorAll('.pop-msg-wrap');
let drawMsg = (msg) => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
    document.querySelector('.pop-msg-wrap.msg').style.display='flex';
    document.querySelector('.pop-msg-wrap.msg p').innerHTML=msg;
}
let drawAnswer = (msg, url) => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
    document.querySelector('.pop-msg-wrap.answer').style.display='flex';
    document.querySelector('.pop-msg-wrap.answer p').innerHTML=msg;
    document.querySelector('.close-btn').setAttribute('onClick','location.href="'+url+'"');
}
let drawQuestion = (txt, func) => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
    document.querySelector('.pop-msg-wrap.question').style.display='flex';
    document.querySelector('.pop-msg-wrap.question p').innerHTML='<i class="fas fa-question-circle"></i><br>'+txt;
    document.querySelector('.submit-btn').setAttribute('onClick',func);
    document.querySelector('.submit-btn').innerHTML='확인';
}
let btnClose = () => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
}