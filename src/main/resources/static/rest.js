//TABLE
$(function(){
    $.ajax({
        url: 'rest/users',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            for(let i = 0; i < data.length; i++){
                let userId = data[i].id;
                $('#userList').append('<tr><td>'
                    + data[i].id + '</td><td>'
                    + data[i].login + '</td><td>'
                    + data[i].name + '</td><td>'
                    + data[i].password + '</td><td>'
                    + data[i].roles[0].role + '</td><td>'
                    + '<button type="button" class="btn btn-primary" onclick="showUpdateModal(' + data[i].id + ')">Edit</button>' + '</td><td>'
                    + '<button type="button" class="btn btn-danger" onclick="deleteUser(' + userId + ')">Delete</button>' + '</td><tr>');
            }
        }
    });
});

//CREATE
function createUser() {
    let createLogin = document.getElementById("createLogin").value;
    let createName = document.getElementById("createName").value;
    let createPass = document.getElementById("createPass").value;
    let createRole = document.getElementById("createRole").value;
    let createRoleId;
    if(createRole === 'ROLE_ADMIN') {
        createRoleId = 1;
    } else if (createRole === 'ROLE_USER') {
        createRoleId = 2;
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        url: '/rest/users',
        data: JSON.stringify({
            name: createName,
            login: createLogin,
            password: createPass,
            roles: [
                {
                    id: createRoleId,
                    role: createRole
                }
            ],
            enabled: true,
            username: createLogin,
            accountNonExpired: true,
            accountNonLocked: true,
            credentialsNonExpired: true
        }),
        contentType: "application/json",
        dataType: 'json',
        success: function() {
            window.location.href = "/rest";
        }
    });
}

//UPDATE
function showUpdateModal(id) {
    $.ajax({
        url: 'rest/users/' + id,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let updateId = data.id;
            let updateLogin = data.login;
            let updateName = data.name;
            let updatePass = data.password;
            let updateRole = data.roles[0].role;
            $("#updateID").val(updateId);
            $("#updateLogin").val(updateLogin);
            $("#updateName").val(updateName);
            $("#updatePass").val(updatePass);
            $("#updateRole").val(updateRole);
            $("#updateModal").modal('show');
            $(".updateSubmit").click(function () {
                let updateId = document.getElementById("updateID").value;
                let updateLogin = document.getElementById("updateLogin").value;
                let updateName = document.getElementById("updateName").value;
                let updatePass = document.getElementById("updatePass").value;
                let updateRole = document.getElementById("updateRole").value;
                let updateRoleId;
                if(updateRole === 'ROLE_ADMIN') {
                    updateRoleId = 1;
                } else if (updateRole === 'ROLE_USER') {
                    updateRoleId = 2;
                }
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: 'PUT',
                    url: '/rest/users',
                    data: JSON.stringify({
                        id: updateId,
                        name: updateName,
                        login: updateLogin,
                        password: updatePass,
                        roles: [
                            {
                                id: updateRoleId,
                                role: updateRole
                            }
                        ],
                        enabled: true,
                        username: updateLogin,
                        accountNonExpired: true,
                        accountNonLocked: true,
                        credentialsNonExpired: true
                    }),
                    contentType: "application/json",
                    dataType: 'json',
                    success: function() {
                        window.location.href = "/rest";
                    }
                });
            });
        }
    });
}

//DELETE
function deleteUser(userId) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'DELETE',
        url: '/rest/users/' + userId,
        contentType: "application/json",
        dataType: 'json',
        success: function() {
            window.location.href = "/rest";
        }
    });
}
