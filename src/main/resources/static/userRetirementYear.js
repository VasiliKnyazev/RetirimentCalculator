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
        '<input th:type="text" name="userMailAddress" class="form-control" id="userMailAddressId" placeholder="Your Mail Address">' +
        '</div>' +
        '<p><button type="button" class="btn btn-info" onclick="calculate()">Calculate</button>' +
        '<div class="form-group">' +
        '<div id="userCanRetirementYearDivInside">' +
        '</div>');
});

function calculate(){
    let userAge = document.getElementById("userAgeId").value;
    let userRetirementAge = document.getElementById("userRetirementAgeId").value;
    let userMailAddress = document.getElementById("userMailAddressId").value;

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
            userMailAddress: userMailAddress != null ? userMailAddress : null,
        }),
        contentType: "application/json",
        dataType: 'json',
        success: function(data) {
            $('#userCanRetirementYearDivInside').append(
                '<textarea th:type="text" class="form-control" placeholder="' + data.canRetireMessage + '" disabled>');
        }
    });
}
