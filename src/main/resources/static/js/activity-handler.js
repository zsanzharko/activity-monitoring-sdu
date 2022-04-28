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
                            <a href="/${projectId}/${activities[i].id}" class="activity_name">View</a>
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

            const current_date = new Date()
            const end_activity_dates = getEndDate(activities)
            let i = 0
            for (const [key, value] of Object.entries(activities)) {
                progress_display += `
                <div class="activity_box">
                                <div class="progress-placeholder">
                `

                const activity_date = new Date(end_activity_dates[i])
                let color_progress = ''
                //если текущий день будет превышать от конца дня назначения задачи


                for (let j = 0; j < dayCount.length; j++) {
                    // const year = activity_date.getFullYear() >
                    const month = dayCount[j] >=  new Date(current_date.getFullYear(), current_date.getMonth() + 1, 0).getDate() ? current_date.getMonth() + 1  : current_date.getMonth()
                    const day = dayCount[j] >=  new Date(current_date.getFullYear(), current_date.getMonth() + 1, 0).getDate() ? 0  : current_date.getDate()
                    let day_count_date = new Date(current_date.getFullYear(), month, day)
                    console.log(day_count_date.getDate() + " - " +  activity_date.getDate() + " = " + (day_count_date.getDate() - activity_date.getDate()) + " > -5 = " +  (day_count_date.getDate() - activity_date.getDate()  > -5))
                    console.log(day_count_date.getDate() + " - " +  activity_date.getDate() + " = " + (day_count_date.getDate() - activity_date.getDate()) + " < -5 = " +  (day_count_date.getDate() - activity_date.getDate()  < -5))
                    if (day_count_date > activity_date)
                        color_progress = "#000000"
                    else if (day_count_date.getDate() <= activity_date.getDate())
                        color_progress = "#93FF96"

                    console.log(value["id"] + " " + dayCount[j] + " " + color_progress)

                    progress_display += `
                    <div class="progress-bar" style="height: 100%; width: 6.67%; background-color: ${color_progress}"
                                         id="${value["id"]} + '-progress-day-' + ${dayCount[j]}"></div>
                              
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
    let today_date = new Date();

    let current_date = today_date.getDate()
    let dayCount = [];
    let next_month = new Date(today_date.getFullYear(), today_date.getMonth() + 1, 0);
    for (let i = 0; i < DAY_COUNT; i++) {
        if (current_date > next_month.getDate()) {
            current_date = 1
            dayCount.push(current_date)
        } else dayCount.push(current_date)
        current_date++
    }
    return dayCount
}

function getEndDate(activities) {
    let end_dates = []
    for (const [key, value] of Object.entries(activities)) {
        end_dates.push(value["endDate"])
    }
    return end_dates
}