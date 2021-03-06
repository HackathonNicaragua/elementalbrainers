// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or any plugin's vendor/assets/javascripts directory can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// compiled file. JavaScript code in this file should be added after the last require_* statement.
//
// Read Sprockets README (https://github.com/rails/sprockets#sprockets-directives) for details
// about supported directives.
//
//= require jquery
//= require jquery_ujs
//= require turbolinks
//= require template/bootstrap.min
//= require template/bootstrap-checkbox-radio-switch
//= require template/bootstrap-notify
//= require template/bootstrap-select
//= require template/chartist.min
//= require template/light-bootstrap-dashboard
//= require template/demo

// image preview for carrierwave
function readURL(input, container_name) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $(container_name).html('<img id="img_prev" src="#" alt="your image" />');
            $('#img_prev').attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
};

document.addEventListener('turbolinks:load', function () {
    $('.row-clickable').on('click', function() {
        window.location.href = $(this).data('url');
    });
});
