var send = function(method, url, {headers, body}, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
    for(const key in headers){
        if(Object.hasOwnProperty.call(headers, key)){
            xhr.setRequestHeader(key, headers[key]);
        }
    }
    xhr.send(body);
    xhr.onreadystatechange = function() {
        callback(xhr);
    }
}