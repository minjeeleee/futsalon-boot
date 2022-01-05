let tabBtns = document.querySelector('.team-tab-wrap').children;
for (var i = 0; i < tabBtns.length; i++) {
    let url = document.location.href.split('?')[0].split('/');
    let urlTarget = url[url.length-1];
    let tabBtn = tabBtns[i].children[0];
    let tabUrl = tabBtn.href.split('/');
    let tabTarget = tabUrl[tabUrl.length-1];

    tabBtn.classList.remove('selected');
    if(urlTarget == tabTarget) tabBtn.classList.add('selected');
    if(tabTarget == 'board-team') {
        if(urlTarget == 'board-mercenary' || urlTarget == 'board-team-away') {
            tabBtn.classList.add('selected');
        }
    }
}