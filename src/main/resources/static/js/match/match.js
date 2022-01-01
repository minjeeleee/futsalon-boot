let teamMatchRequset = (mmIdx) => {
    $.ajax({
        type: 'POST',
        url: '/match/apply-team-match',
        data: { mmIdx: mmIdx },
        dataType: 'text',
        success: (text) => {
            if(text == 'available') {
                drawMsg('<i class="fas fa-times-circle"></i><br>매치가 성사되었습니다.');
            } else {
                drawMsg('<i class="fas fa-times-circle"></i><br>회원님이 속한 팀은 신청하실 수 없습니다.');
            }
        },
        error: function (e) {
            if (e.status == 403) {
                drawMsg('<i class="fas fa-times-circle"></i><br>신청 권한이 없습니다.');
            } else {
                drawMsg('<i class="fas fa-times-circle"></i><br>에러가 발생하여 요청이 실패했습니다.');
            }
        }
    });
}