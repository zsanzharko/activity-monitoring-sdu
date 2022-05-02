function notify() {
    fetch('/notify/')
        .then(result => result.json())
        .then((data) => {
            let display = '';
            for (let i = 0; i < data.length; i++) {
                display +=
                    `<div class="not_box">
                        <h5 class="title_not">${data[i].title}</h5>
                        <p class="description_not">
                            ${data[i].description}
                            <a href="${data[i].link}"> see more...</a>
                        </p>
                        <hr>
                    </div>`
            }
            if (display.length === 0) {
                display = `
                <div class="is_empty_box">
                You don't have notification
                </div>
                `
            }
            document.getElementById("notification-place").innerHTML = display;

        }).catch(_ => console.error(_));
}

function main() {
    notify()
    setInterval(notify, 5000)
}

main()

