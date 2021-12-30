let tabBtns = document.querySelector('.team-tab-wrap').children;
for (var i = 0; i < tabBtns.length; i++) {
    let tabBtn = tabBtns[i].children[0];
    tabBtn.classList.remove('selected');
    if(tabBtn.href==document.location.href.split('?')[0]){
        tabBtn.classList.add('selected');
    }
}