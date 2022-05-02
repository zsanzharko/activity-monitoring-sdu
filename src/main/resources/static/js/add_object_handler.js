window.addEventListener("load", function () {
    function sendNewProjectData() {
        const formData = new FormData(form)

        const object = {};
        formData.forEach(function (value, key) {
            object[key] = value;
        });

        let response = postData('/api/manager/project/create', object)
            .then(data => {
                $(function () {
                    $('#create_project_modal').modal('toggle');
                });
                let display =
                    `<div class="box">
                        <div class="box_head">
                            <div class="box_head_left">
                                <p class="projects_id" style="font-size: 20px;">${data.projectId}</p>
                                <p class="project_version" style="font-size: 13px;">version <span style="font-size: 13px;">${data.projectVersion}</span></p>
                            </div>
                            <p class="project_name" style="font-size: 20px; margin-right: 10px;">${data.title}</p>
                            <div class="dropdown">
                                    <button class="btn  dropdown-toggle tree_dots" type="button" data-bs-toggle="dropdown">
                                        <svg class="bi me-1" width="20" height="20" style="color: white">
                                            <use xlink:href="#three_dots"></use>
                                        </svg>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-dark">
                                        <li class="item_drop">
                                            <a class="dropdown-item" id="delete-${data.projectId}" href="project/delete/${data.projectId}">Delete</a>
                                        </li>
                                        <li class="item_drop"><button type="button" data-bs-toggle="modal" data-bs-target=".edit_project_model" class="btn dropdown-item edit_project">Edit</button></li>
                                    </ul>

                                    <div id="${data.projectId}-delete" class="modal">

                                        <!-- Modal content -->
                                        <div class="modal-content">
                                            <span class="close" onclick="closeModal('delete-' + )">×</span>
                                            <p>Some text in the Modal..</p>
                                        </div>

                                    </div>
                                </div>
                        </div>
                        <div class="box_body">
                            <p class="project_activities">Activities</p>
<!--                            todo get activities-->
                            <!-- <p class="project_deep_id">Data mining</p>
                        <p class="project_deep_id">Data cleaning</p> -->
                        </div>
                        <div class="footer">
                            <div class="footer_images">
                                <img src="images/test photo profile.png" alt="" class="box_image">
                                <!--                           todo in this place will insert users image, check in database-->
                            </div>
                            <a href="/project/panel?id=${data.projectId}" class="see_button" role="button">See
                                more</a>
                        </div>
                    </div>`
                // document.getElementsByClassName("box").insertAdjacentHTML("afterend", display)
                $("#project-dashboard-placeholder").append(display);
            });
    }

    const form = document.getElementById('new-project')

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        sendNewProjectData();
    });
});

// Example POST method implementation:
async function postData(url = '', data = {}) {
    const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    return response.json(); // parses JSON response into native JavaScript objects
}

