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

            let key_assign = Object.keys(assigned)

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

            }
            document.getElementById("activity_title_placeholder").innerHTML = display_activity_boxs;
            document.getElementById("activity_assigned_placeholder").innerHTML = display_assign_box;
        }).catch(_ => console.error(_));
}

