let formCheck = () =>{
    let localCode = document.getElementsByName('localCode');
    let checkLocalCode = null;
    localCode.forEach((e) => {
        if (e.checked) {
            checkLocalCode = e.value;
        }
    })

    let level = document.getElementsByName('level');
    let checkLevel = null;
    level.forEach((e) => {
        if (e.checked) {
            checkLevel = e.value;
        }
    })

    if (checkLocalCode == null && checkLevel == null) {
        drawMsg('<i class="fas fa-times-circle"></i><br>하나 이상의 검색 옵션을 선택해주세요.');
        return false;
    }

    return true;
}