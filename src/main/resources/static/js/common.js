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

let showTeamInfo = (tmIdx) => {
    $.ajax({
        type: 'POST',
        url: '/team/team-info',
        data: { 'tmIdx': tmIdx },
        dataType:'json',
        success: (result) => {
            // popup-team-img
            if (result.file != null) {
                let filePath = result.file.savePath + result.file.renameFileName;
                $('.popup-team-img').css('background', 'url("/file/'+filePath+'")');
            } else {
                $('.popup-team-img').css('background', 'url("/img/team/no-img.jpg")');
            }
            $('.popup-team-img').css('background-size', 'cover');
            $('.popup-teaminfo h2').text(result.tmName);
            let teamLevel = result.teamLevel == 'HIGH' ? '상' : result.teamLevel == 'MIDDLE' ? '중' : '하';
            $('.popup-teaminfo .popup-tm-grade').text("실력 : " + teamLevel);
            $('.popup-teaminfo .popup-tm-lc').text("주 활동지역 : " + result.location.localCity);
            $('.popup-teaminfo .popup-tm-info').text(result.location.tmInfo);
            document.querySelector('.popup-teaminfo-wrap').style.display = "block";
        },
        error: function (e) {
            drawMsg('<i class="fas fa-times-circle"></i><br>에러가 발생하여 요청이 실패했습니다.');
        }
    })
}

$('.popup-close i').click(()=>{
    document.querySelector('.popup-teaminfo-wrap').style.display = "none";
});