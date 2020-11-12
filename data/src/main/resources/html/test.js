const cache = {}
function onload() {
    cache.div = document.getElementById("x");
    cache.input = document.getElementById("x-x");
    let tds = document.getElementsByTagName("tbody")[0].getElementsByTagName("td");
    for (let i = 0; i < tds.length; i++) {
        tds[i].addEventListener("click",  function (event) {
            console.log("xxxxxx")
            let target = event.target;
            if (target != this) {
                return;
            }
            if (cache.cur) {
                if (target === cache.cur) {
                    return;
                }
                cache.cur.style.removeProperty("padding");
                cache.cur.innerText = cache.input.value;
            }
            target.style.padding = "0";
            cache.input.value = target.innerText;
            target.innerText = null;
            target.appendChild(cache.div);
            cache.input.focus();
            cache.cur = target;
        }, true);
    }
}