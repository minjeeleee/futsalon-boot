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