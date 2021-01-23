$(function() {
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

    $('#videoContentId').empty().append(
        '<div class="container-fluid">' +
        '  <div class="row no-gutters">' +
        '    <div class="p-4">' +
        '        <div>Movie 1</div>' +
        '        <button type="image" onclick="showVideoModal(' + 1 + ')">' +
        '           <img src="https://www.joblo.com/assets/images/oldsite/top_ten_gallery_img/cc45980a-dd13-1e41.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '    <div class="p-4">' +
        '        <div>Movie 2</div>' +
        '        <button type="image" onclick="showVideoModal(' + 2 + ')">' +
        '           <img src="https://static.toiimg.com/thumb/msid-56312948,imgsize-43945,width-1000,height-562,resizemode-8/56312948.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '    <div class="p-4">' +
        '        <div>Movie 3</div>' +
        '        <button type="image" onclick="showVideoModal(' + 3 + ')">' +
        '           <img src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Movie-Titles-Free-Premiere-Pro-Template.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '  </div>' +
        '  <div class="row no-gutters">' +
        '    <div class="p-4">' +
        '        <div>Movie 4</div>' +
        '        <button type="image" onclick="showVideoModal(' + 4 + ')">' +
        '           <img src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Film-Noir-Detective-Movie-Title.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '    <div class="p-4">' +
        '        <div>Movie 5</div>' +
        '        <button type="image" onclick="showVideoModal(' + 5 + ')">' +
        '           <img src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Horror-Movie-Title-Final.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '    <div class="p-4">' +
        '        <div>Movie 6</div>' +
        '        <button type="image" onclick="showVideoModal(' + 6 + ')">' +
        '           <img src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Wild-West-Movie-Title.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '  </div>' +
        '  <div class="row no-gutters">' +
        '    <div class="p-4">' +
        '        <div>Movie 7</div>' +
        '        <button type="image" onclick="showVideoModal(' + 7 + ')">' +
        '           <img src="https://d39l2hkdp2esp1.cloudfront.net/img/eps/E4876_2x/c/E4876_07.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '    <div class="p-4">' +
        '        <div>Movie 8</div>' +
        '        <button type="image" onclick="showVideoModal(' + 8 + ')">' +
        '           <img src="https://d39l2hkdp2esp1.cloudfront.net/img/eps/E4876_2x/c/E4876_00.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '    <div class="p-4">' +
        '        <div>Movie 9</div>' +
        '        <button type="image" onclick="showVideoModal(' + 9 + ')">' +
        '           <img src="https://d39l2hkdp2esp1.cloudfront.net/img/eps/E4876_2x/c/E4876_01.jpg" width="360" height="240"/>' +
        '        </button>' +
        '    </div>' +
        '  </div>' +
        '</div>');
});

function findContent(videoTitle) {
    if (videoTitle === undefined) {
        videoTitle = document.getElementById("tags").value;
    }
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

function showVideoModal(id) {
    $.ajax({
        url: '/rest/admin/videos/' + id,
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            $('#videoModalContentId').empty().append(
                '<center><wbr><h3>Title: ' + data.title + '. Genre: ' + data.genre + '. Format: ' + data.format + '.</h3>' + '<p><wbr>' +
                '<video width="1280" height="720" controls>' +
                '<source src="' + data.url + '" type="video/' + data.format + '">' +
                '</video></center>');
            $("#videoModal").modal('show');
        }
    });
}
