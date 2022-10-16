function setPage(page, url){
    var root = document.getElementById("app")
    root.replaceChildren()
    root.innerHTML = page
    var style = document.createElement('link')
    style.setAttribute('rel', 'stylesheet')
    style.setAttribute('href', url)
    root.appendChild(style)
}