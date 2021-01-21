$( function() {
    $.ajax({
        url: '/rest/user/videos/titles',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            let result = [];
            let keys = Object.keys(data);
            keys.forEach(function(key){
                result.push(data[key]);
            });
            $("#tags").autocomplete({
                source: result
            });
        }
    });
});

function findVideoByTitle() {
    let videoTitle = document.getElementById("videoTitleId").value;
    $.ajax({
        url: '/rest/user/videos/' + videoTitle,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#videoContentId').empty().append(
                '<center><wbr><h3>Title: ' + data.title + '. Genre: ' + data.genre + '. Format: ' + data.format + '.</h3>' + '<p><wbr>' +
                '<video width="1280" height="720" controls>' +
                '<source src="' + data.url + '" type="video/' + data.format + '">' +
                '</video></center>');
        }
    });
}

function findContent() {
    let videoTitle = document.getElementById("tags").value;
    $.ajax({
        url: '/rest/user/videos/' + videoTitle,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#videoContentId').empty().append(
                '<center><wbr><h3>Title: ' + data.title + '. Genre: ' + data.genre + '. Format: ' + data.format + '.</h3>' + '<p><wbr>' +
                '<video width="1280" height="720" controls>' +
                '<source src="' + data.url + '" type="video/' + data.format + '">' +
                '</video></center>');
        }
    });
}