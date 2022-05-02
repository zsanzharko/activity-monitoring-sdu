const DAY_COUNT = 15

function getActivityDayInformation(projectId) {
    const url = '/api/activity/panel?projectId=' + projectId

    fetch(url, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    })
        .then(result => result.json())
        .then((data) => {
            let developers = []
            console.log(data)
            const activities = data["activities"];
            const assigned = data["devConnectionActivities"];

            let display_activity_boxs = ``;
            let display_assign_box = ``;
            let display_activity_link_boxs = ``;


            let cortege_display_assign_box = ``

            for (const [key, value] of Object.entries(assigned)) {
                let assign_name = null;
                if (value != null) {
                    assign_name = value["firstName"]
                }
                const assignIsNull = assign_name != null
                cortege_display_assign_box += `
                <div class="activity_placeholder">
                                <div class="activity_box active_box_centered">
                                    <div class="assign_box" style="background-color:  ${assignIsNull ? `white` : "#D68300"}; border-color: ${assignIsNull ? "#D68300" : "red"}; color: ${assignIsNull ? "black" : "white"};">
                                        ${assignIsNull ? 'assigned' : 'assign'}
                                    </div>
                                </div>
                            </div>
                `
            }

            fetch('/manager/get-developers', {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(result => result.json())
                .then((developers) => {
                    console.log(developers)
                    for (let j = 0; j < developers.length; j++) {
                        display_activity_link_boxs += `
                                <div class="person_box">
                                            <h6 class="person_name">Serikkhan Ayan
                                            </h6>

                                            <div class="box_proger">
                                                <div class="zogolovok_box">
                                                    <p class="zogolovok">Contacts</p>
                                                </div>
                                                <p class="person_PH_number info">Phone number: <span>${developers[i]['phoneNumber']}</span></p>
                                                <p class="person_email info">Email: <span>${developers[i]['email']}</span></p>
                                            </div>
                                            <div class="box_proger">
                                                <div class="zogolovok_box">
                                                    <p class="zogolovok">Projects</p>
                                                </div>
                                                <p class="person_pr_num info">Active projects: <span>4</span>
                                                </p>
                                            </div>
                                            <div class="box_proger">
                                                <div class="zogolovok_box">
                                                    <p class="zogolovok">Choose</p>
                                                </div>
                                                <a type="button" href="/project/activity/assign/${activities[i].id}/${projectId}}" data-bs-toggle="modal"
                                                    class="btn choose">select</a>
                                            </div>
                                        </div>
                    `
                    }
                })
                .catch(_ => console.error(_));

            for (let i = 0; i < activities.length; i++) {
                display_activity_boxs += `
                    <div class="activity_placeholder">
                        <div class="activity_box">
                                <div style="height: 100%;">
                                    <p class="activity_name names">${i + 1}. ${activities[i].title}</p>
                                </div>
                                </div>
                            </div>`

                display_activity_link_boxs +=
                    `
                    <div class="activity_box">
                        <div style="height: 100%;">
                            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target=".view_modal">
                               View
                            </button>
                        </div>
                    </div>
                    
                    <!-- Modal -->
            <div class="modal fade view_modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Activity details</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="header">
                                <div class="title_head">
                                    <p class="title_activity_modal title">Title:<span>${activities[i].title}</span></p>
                                    <!--<p class="title_activity_modal title">Activity ID:<span>${activities[i]}</span></p> -->
                                    <p class="start_day_activity_modal title">Stat day:<span>${activities[i].startDate.split('T')[0]}</span></p>
                                    <p class="start_day_activity_modal title">End day:<span>${activities[i].endDate.split('T')[0]}</span></p>
                                </div>

                                <div class="head_description">
                                    <h5>Description</h5>
                                    <hr>
                                    <div class="description_activity">${activities[i].description}</div>
                                </div>
                            </div>

                            <div>
                                <div class="topic">
                                    <h5>Developers</h5>
                                    <hr>
                                </div>

                                <div class="main_box">
                                    <div class="container">`

                display_activity_link_boxs += cortege_display_assign_box
                display_activity_link_boxs += `
                                    </div>
                                </div>
                            </div>
                            
                            <div>
                                <div class="spent_topic">
                                    <h5>Spent time</h5>
                                    <hr>
                                </div>
                                <div class="spent_time_container">
                                    
                                    <div class="spent_time_box">
                                        <div class="spent_left_box">
                                            <p class="zogolovok">Entered day</p>
                                            <p>31.01.2002</p>
                                        </div>
                                        <div class="spent_space_line"></div>
                                        <div class="spent_right_box">
                                            <p class="zogolovok">Spent time</p>
                                            <p>31.01.2002</p>
                                        </div>
                                    </div>

                                    <div class="spent_time_box">
                                        <div class="spent_left_box">
                                            <p class="zogolovok">Entered day</p>
                                            <p>31.01.2002</p>
                                        </div>
                                        <div class="spent_space_line"></div>
                                        <div class="spent_right_box">
                                            <p class="zogolovok">Spent time</p>
                                            <p>31.01.2002</p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Delete</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ------------------------------------------ -->
                    
                    `

            }
            document.getElementById("activity_title_placeholder").innerHTML = display_activity_boxs;
            document.getElementById("activity_assigned_placeholder").innerHTML = display_assign_box;
            document.getElementById("activity_details_placeholder").innerHTML = display_activity_link_boxs;


            // PROGRESS BAR

            const dayCount = getDateForPanel()
            let display = ``
            let progress_display = ``
            for (let i = 0; i < dayCount.length; i++) {
                display += `
                <div class="day-counter">
                                    <p style="margin: auto; text-align: center">${dayCount[i].getDate()}</p>
                                </div>
                `
            }
            document.getElementById('activity-day').innerHTML = display

            const end_activity_dates = getEndDate(activities)
            let i = 0
            for (const [key, value] of Object.entries(activities)) {

                const start_date = getStartDate(activities)[i]
                const end_date = new Date(end_activity_dates[i])

                getColorRequest(start_date, end_date, DAY_COUNT).then(r => console.log(r));

                progress_display += `
                <div class="activity_box">
                                <div class="progress-placeholder">
                `
                if (start_date == null || end_date == null) {
                    progress_display += `
                <div class="empty_progress">
                    <h3>Date is not set</h3>
                </div>
                `
                } else {
                    const start_time = start_date.getTime()
                    const end_time = end_date.getTime()
                    let color_progress = ""
                    let color_progress_main = ""
                    for (let j = 0; j < dayCount.length; j++) {

                        let date = dayCount[j]
                        const date_time = date.getTime()

                        if (color_progress_main === "" && date_time >= start_time && date_time <= end_time) {
                            color_progress = ""
                            if (end_time - date_time > 864_000_000) {
                                color_progress_main = "#33A853"
                            } else if (end_time - date_time < 864_000_000 && end_time - date_time > 259200000) {
                                color_progress_main = "#FBBC05"
                            } else if (end_time - date_time < 259200000) {
                                color_progress_main = "#EA4336"
                            }
                        }

                        if (date_time < start_time || date_time > end_time) {
                            color_progress = "#DADADA"
                        }


                        progress_display += `
                                <div class="custom-progress-bar" style="height: 100%; width: 6.67%; background-color: ${color_progress.length === 0 ? color_progress_main : color_progress}"
                                         id="${value["id"]}-progress-day-${date.getDate()}"></div>`
                    }
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

}


function getDateForPanel() {
    let dayCount = [];
    let today_date = new Date()
    for (let i = 0; i < DAY_COUNT; i++) {
        dayCount.push(new Date(today_date))
        today_date.setDate(today_date.getDate() + 1);
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
        if (value["startDate"] === null) {
            start_date.push(null);
            continue;
        }
        let json_date = value["startDate"].substring(0, 10)
        let date = new Date(json_date.replaceAll('-', '/'))
        start_date.push(date)
    }
    return start_date
}

async function getColorRequest(startDate, endDate, duration) {
    const url = '/api/get-color-activity-calendar'

    let data = {
        startDate: startDate,
        endDate: endDate,
        duration: duration
    }
    return await fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(result => result.json());
}