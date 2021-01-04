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
        '<p><button type="button" class="btn btn-info" onclick="calculate()">Calculate</button>' +
        '<div class="form-group">' +
        '<div id="userCanRetirementYearDivInside">' +
        '</div>');
});

function calculate(){
    let userAge = document.getElementById("userAgeId").value;
    let userRetirementAge = document.getElementById("userRetirementAgeId").value;
    $.ajax({
        url: 'rest/users/calculate/' + userAge + "+" + userRetirementAge,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#userCanRetirementYearDivInside').append(
                '<input th:type="text" class="form-control" placeholder="' + 'It\'s ' + data.presentYear + '. So you can retire in ' + data.canRetireYear + '" disabled></div>')
        }
    });
}
