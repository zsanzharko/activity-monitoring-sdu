const DAY_COUNT = 15

function getActivityDayInformation(projectId) {
    const url = '/api/activity/panel?projectId=' + projectId

    fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(result => result.json())
        .then((data) => {
            console.log(data)
            const activities = data["activities"];
            const assigned = data["devConnectionActivities"];

            let display_activity_boxs = ``;
            let display_assign_box = ``;
            let display_activity_link_boxs = ``;

            for (const [key, value] of Object.entries(assigned)) {
                let assign_name = null;
                if (value != null) {
                    assign_name = value["firstName"]
                }
                const assignIsNull = assign_name != null
                display_assign_box += `
                <div class="activity_placeholder">
                                <div class="activity_box">
                                    <div class="assign_box" style="background-color:  ${assignIsNull ? `white` : "#D68300"}; border-color: ${assignIsNull ? "#D68300" : "red"}; color: ${assignIsNull ? "black" : "white"};">
                                        ${assignIsNull ? 'assigned' : 'assign'}
                                    </div>
                                </div>
                            </div>
                `
            }

            for (let i = 0; i < activities.length; i++) {
                display_activity_boxs += `
                    <div class="activity_placeholder">
                        <div class="activity_box">
                                <div style="height: 100%;">
                                    <p class="activity_name">${i + 1}. ${activities[i].title}</p>
                                </div>
                                </div>
                            </div>`

                display_activity_link_boxs +=
                    `
                    <div class="activity_box">
                        <div style="height: 100%;">
                            <a href="activity/${projectId}/${activities[i].id}" class="activity_name">View</a>
                        </div>
                    </div>`

            }
            document.getElementById("activity_title_placeholder").innerHTML = display_activity_boxs;
            document.getElementById("activity_assigned_placeholder").innerHTML = display_assign_box;
            document.getElementById("activity_details_placeholder").innerHTML = display_activity_link_boxs;


            // PROGRESS BAR

            let dayCount = getDateForPanel()
            let display = ``
            let progress_display = ``
            for (let i = 0; i < dayCount.length; i++) {
                display += `
                <div class="day-counter">
                                    <p style="margin: auto; text-align: center">${dayCount[i]}</p>
                                </div>
                `
            }
            document.getElementById('activity-day').innerHTML = display

            const end_activity_dates = getEndDate(activities)
            let i = 0
            for (const [key, value] of Object.entries(activities)) {
                let color_progress = ''
                progress_display += `
                <div class="activity_box">
                                <div class="progress-placeholder">
                `

                const start_date = getStartDate(activities)[i]
                const end_date = new Date(end_activity_dates[i])


                console.log(start_date)
                console.log(end_date)
                for (let j = 1; j <= dayCount.length; j++) {
                    let date = new Date(new Date().getFullYear(), new Date().getMonth(), dayCount[j-1])
                    if (dayCount[j - 1] < dayCount[j]) {
                        date = new Date(new Date().getFullYear(), new Date().getMonth()+1, dayCount[j-1])
                    }
                    


                    console.log(date)
                    if (date => start_date && date <= end_date) {
                        color_progress = "#93FF96"
                    } else
                        color_progress = "#000000"

                    progress_display += `
                    <div class="progress-bar" style="height: 100%; width: 6.67%; background-color: ${color_progress}"
                                         id="${value["id"]} + '-progress-day-' + ${dayCount[j-1]}"></div>

                    `
                }
                progress_display += `
                 </div>
                            </div>`
                i++
            }

            document.getElementById("progress-calendar-placeholder").innerHTML = progress_display;
        })
        .catch(_ => console.error(_));
}

async function newActivity(projectId) {
    if (projectId == null) return null;

    const url = 'api/manager/create/' + projectId

    const form = document.getElementById('new-activity')
    const formData = new FormData(form)

    const object = {};
    formData.forEach(function (value, key) {
        object[key] = value;
    });

    console.log(object)
    // const response = await fetch(url, {
    //     method: 'POST', // *GET, POST, PUT, DELETE, etc.
    //     headers: {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(data) // body data type must match "Content-Type" header
    // });
    // return response.json(); // parses JSON response into native JavaScript objects
}


function getDateForPanel() {
    let dayCount = [];
    let today_date = new Date().getDate()
    let last_month = new Date(new Date().getFullYear(), new Date().getMonth() + 1, 0)
    for (let i = 0; i < DAY_COUNT; i++) {
        if (today_date > last_month.getDate()) {
            today_date = 1
        }
        dayCount.push(today_date)

        today_date++

    }
    return dayCount
}

function getNumberOfDays(start, end) {
    const date1 = new Date(start);
    const date2 = new Date(end);

    // One day in milliseconds
    const oneDay = 1000 * 60 * 60 * 24;

    // Calculating the time difference between two dates
    const diffInTime = date2.getTime() - date1.getTime();

    // Calculating the no. of days between two dates
    return Math.round(diffInTime / oneDay);
}

function getEndDate(activities) {
    let end_dates = []
    for (const [key, value] of Object.entries(activities)) {
        end_dates.push(value["endDate"])
    }
    return end_dates
}

function getStartDate(activities) {
    let start_date = []
    for (const [key, value] of Object.entries(activities)) {
        let json_date = value["startDate"].substring(0, 10)
        let date = new Date(json_date.replaceAll('-', '/'))
        start_date.push(date)
    }
    return start_date
}