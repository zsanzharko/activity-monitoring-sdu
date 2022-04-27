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
        }).catch(_ => console.error(_));
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
