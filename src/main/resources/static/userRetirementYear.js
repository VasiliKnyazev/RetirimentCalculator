let count = 0;

$(function(){
    $('#userCanRetirementYearDiv').append('<p><h5>Calculate Retirement Year</h5></p>' +
        '<div class="form-group">' +
        '<label for="userAgeId"></label>' +
        '<input th:type="text" name="userAge" class="form-control" id="userAgeId" placeholder="Your Age">' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="userRetirementAgeId"></label>' +
        '<input th:type="text" name="userRetirementAge" class="form-control" id="userRetirementAgeId" placeholder="Your Retirement Age">' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="userMailAddressId"></label>' +
        '<input th:type="text" name="userMailAddress" class="form-control" id="userMailAddressId" placeholder="Your Email (Optional)">' +
        '</div>' +
        '<p><button type="button" class="btn btn-info" onclick="calculate()">Calculate</button>' +
        '<div class="form-group">' +
        '</div>');
});

function calculate(){
    let userAge = document.getElementById("userAgeId").value;
    let userRetirementAge = document.getElementById("userRetirementAgeId").value;
    let userMailAddress = document.getElementById("userMailAddressId").value;
    //const randomColor = Math.floor(Math.random()*16777215).toString(16);

    if (userAge != null && userRetirementAge != null && userAge !== "" && userRetirementAge !== "") {
        count = count + 1;
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            url: '/calculate',
            data: JSON.stringify({
                userAge: userAge,
                presentYear: null,
                userRetirementAge: userRetirementAge,
                userCanRetireYear: null,
                canRetireMessage: null,
                userMailAddress: userMailAddress !== null ? userMailAddress : null,
            }),
            contentType: "application/json",
            dataType: 'json',
            success: function(data) {
                //$('#userCanRetirementYearDivInside').append('<wbr><h5>' + '<font color="' + randomColor + '">Result №' + count + '</font>: ' + data.canRetireMessage + '</h5>');
                $('#userCanRetirementYearDivInside').append('<wbr><h5>Result №' + count + ': ' + data.canRetireMessage + '</h5>');
            }
        });
    } else {
        //$('#userCanRetirementYearDivInside').append('<wbr><h5>' + '<font color="' + randomColor + '">Not all data has been entered</font></h5>');
        $('#userCanRetirementYearDivInside').append('<wbr><h5>Not all required fields were filled</h5>');
    }

}

//REGISTER
function showRegisterModal() {
    $("#registerModal").modal('show');
    $(".registerSubmit").click(function () {
        let registerLogin = document.getElementById("registerLogin").value;
        let registerName = document.getElementById("registerName").value;
        let registerPass = document.getElementById("registerPass").value;
        let registerEmail = document.getElementById("registerEmail").value;
        if (registerLogin !== null && registerName !== null && registerPass !== null && registerEmail !== null &&
            registerLogin !== "" && registerName !== "" && registerPass !== "" && registerEmail !== "") {
            let registerRole = 'ROLE_USER';
            let registerRoleId = 2;
            $.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                type: 'POST',
                url: '/register',
                data: JSON.stringify({
                    name: registerName,
                    login: registerLogin,
                    password: registerPass,
                    email: registerEmail,
                    roles: [
                        {
                            id: registerRoleId,
                            role: registerRole
                        }
                    ],
                    enabled: true,
                    username: registerLogin,
                    accountNonExpired: true,
                    accountNonLocked: true,
                    credentialsNonExpired: true
                }),
                contentType: "application/json",
                dataType: 'json',
                success: function() {
                    window.location.href = "/login";
                }
            });
        } else {
            $('#userRegisterModalDivInside').empty().append('<wbr><h5>Not all required fields were filled</h5>');
        }

    });
}