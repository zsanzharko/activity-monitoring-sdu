function notify() {
    fetch('/notify/')
        .then(result => result.json())
        .then((data) => {
            let display = '';
            for (let i = 0; i < data.length; i++) {
                display +=
                    `<div style="display: flex">\n` +
                    `    <p style="padding: 0 5px">${i}. </p>\n` +
                    `    <h5 style="padding: 0 5px">${data[i].title}</h5>\n` +
                    `    <a style="padding: 0 5px" href=\"${data[i].link}\">Enter</a>\n` +
                    `</div>`
            }
            document.getElementById("log").innerHTML = display;


        }).catch(_ => console.error(_));
}

function main(){
    notify()
    setInterval(notify, 5000)
}

main()
