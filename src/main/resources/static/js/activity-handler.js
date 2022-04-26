// import postData from "./add_object_handler"

function getActivityDayInformation(projectId) {
    const url = '/project/activity/panel?projectId=' + projectId

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

            let assign_json = {}

            let display_activity_boxs = ``;
            let display_assign_box = ``;

            let recursiveFunction = function (arr, x, start, end) {

                // Base Condition
                if (start > end) return false;

                // Find the middle index
                let mid = Math.floor((start + end) / 2);

                // Compare mid with given key x
                if (arr[mid] === x) return true;

                // If element at mid is greater than x,
                // search in the left half of mid
                if (arr[mid] > x)
                    return recursiveFunction(arr, x, start, mid - 1);
                else

                    // If element at mid is smaller than x,
                    // search in the right half of mid
                    return recursiveFunction(arr, x, mid + 1, end);
            }

            Object.keys(assigned).forEach(function (entry) {
                assign_json +=
                    {
                        first_name: assigned[`${entry}`].firstName
                    }
                let assign_name = assigned[`${entry}`].firstName
                const assignIsNull = assign_name != null && assign_name.length !== 0

                display_assign_box += `
                <div class="activity_placeholder">
                                <div class="activity_box">
                                    <div class="assign_box" style="background-color:  ${assignIsNull ? `white` : "#D68300"}; border-color: ${assignIsNull ? "#D68300" : "red"}; color: ${assignIsNull ? "black" : "white"};">
                                        ${assignIsNull ? 'assigned' : 'assign'}
                                    </div>
                                </div>
                            </div>
                `
            });



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

