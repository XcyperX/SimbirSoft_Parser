submitNewSite = () => {
    url_site.url = document.getElementById("url_site").value;
    createNewSite(url_site);

}

const url_site = {
    url: null
}

sendRequest = (method, url, body) => {
    const headers = {
        'Content-Type': 'application/json'
    }
    if (body !== null) {
        return fetch(url, {
            method: method,
            body: JSON.stringify(body),
            headers: headers
        });
    } else {
        return fetch(url, {
            method: method,
            headers: headers
        });
    }
}

createNewSite = (url) => {
    sendRequest('POST', '/api/parser/', url).then(response => {
        if (response.ok) {
            console.log(response);
            alert("Готово! )");
            document.location.reload(true);
        } else {
            alert("Что-то пошло не так! )");
            console.log(response);
        }
    });

}