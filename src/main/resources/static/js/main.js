$(document).ready(function () {

    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();
    });

});

function fire_ajax_submit() {
    var data = $("#search-form").serializeArray();
    
    var formArray = {};
    
    $.map(data, function(n, i){
        formArray[n['name']] = n['value']; // key - value
    });

    var json =JSON.stringify(formArray)
    console.log(json);
    $.ajax({
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        url: '/create',
        data: json,
        dataType: 'text',
        success: function (formArray) {
        	console.log("SUCCESS : ", formArray);
        	window.location.href = "/users";
        	
        },
        error: function (e) {
        	console.log("ERROR : ", e);        	
        }
    });

}
