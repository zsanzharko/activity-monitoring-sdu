function notify() {
    fetch('/notify/')
        .then(result => result.json())
        .then((data) => {
            let display = '';
            for (let i = 0; i < data.length; i++) {
                display += `${data[i].title} `
            }
            document.getElementById("log").innerText = display;

        }).catch(_ => console.error(_));
}

function main(){
    notify()
    setInterval(notify, 2000)
}

main()
