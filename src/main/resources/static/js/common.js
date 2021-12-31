let msgWrap = document.querySelectorAll('.pop-msg-wrap');
let drawMsg = (msg) => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
    document.querySelector('.pop-msg-wrap.msg').style.display='flex';
    document.querySelector('.pop-msg-wrap.msg p').innerHTML=msg;
}
let drawAnswer = (msg,url) => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
    document.querySelector('.pop-msg-wrap.answer').style.display='flex';
    document.querySelector('.pop-msg-wrap.answer p').innerHTML=msg;
    document.querySelector('.close-btn').setAttribute('onClick','location.href="'+url+'"');
}
let btnClose = () => {
    msgWrap.forEach(e=>{
        e.style.display='none';
    });
}