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
        '      <form>' +
        '        <div>Movie 1</div>' +
        '        <input type="image" src="https://www.joblo.com/assets/images/oldsite/top_ten_gallery_img/cc45980a-dd13-1e41.jpg" alt="Submit" onclick="showVideoModal(' + 1 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 2</div>' +
        '        <input type="image" src="https://static.toiimg.com/thumb/msid-56312948,imgsize-43945,width-1000,height-562,resizemode-8/56312948.jpg" alt="Submit" onclick="showVideoModal(' + 2 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 3</div>' +
        '        <input type="image" src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Movie-Titles-Free-Premiere-Pro-Template.jpg" alt="Submit" onclick="showVideoModal(' + 3 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '  </div>' +
        '  <div class="row no-gutters">' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 4</div>' +
        '        <input type="image" src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Film-Noir-Detective-Movie-Title.jpg" alt="Submit" onclick="showVideoModal(' + 4 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 5</div>' +
        '        <input type="image" src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Horror-Movie-Title-Final.jpg" alt="Submit" onclick="showVideoModal(' + 5 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 6</div>' +
        '        <input type="image" src="https://www.enchanted.media/wp-content/uploads/edd/2020/09/Vintage-Classic-Wild-West-Movie-Title.jpg" alt="Submit" onclick="showVideoModal(' + 6 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '  </div>' +
        '  <div class="row no-gutters">' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 7</div>' +
        '        <input type="image" src="https://d39l2hkdp2esp1.cloudfront.net/img/eps/E4876_2x/c/E4876_07.jpg" alt="Submit" onclick="showVideoModal(' + 7 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 8</div>' +
        '        <input type="image" src="https://d39l2hkdp2esp1.cloudfront.net/img/eps/E4876_2x/c/E4876_00.jpg" alt="Submit" onclick="showVideoModal(' + 8 + ')" width="360" height="240">' +
        '      </form>' +
        '    </div>' +
        '    <div class="p-4">' +
        '      <form>' +
        '        <div>Movie 9</div>' +
        '        <input type="image" src="https://d39l2hkdp2esp1.cloudfront.net/img/eps/E4876_2x/c/E4876_01.jpg" alt="Submit" onclick="showVideoModal(' + 9 + ')" width="360" height="240">' +
        '      </form>' +
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
    alert('inside showVideoModal()');
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
            alert('after show modal. Title: ' + data.title + '. Genre: ' + data.genre + '. Format: ' + data.format);
        }
    });
}
