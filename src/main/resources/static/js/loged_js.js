$(document).ready(function () {
  $('.sidebarCollapse_button').on('click', function () {
      $('.sidebar').toggleClass('active');
      $(this).toggleClass('active');
  });
});

var dashboard_text = document.querySelectorAll("dash_text");
const chk = document.getElementById('chk');
chk.addEventListener('change', () => {
        document.body.classList.toggle('light');
        document.getElementsByClassName('sidebar_head')[0].classList.toggle('light');
        document.getElementsByClassName('upper_dev_inf')[0].classList.toggle('light');
        document.getElementsByClassName('project')[0].classList.toggle('light');
        document.getElementsByClassName('sidebar')[0].classList.toggle('light');
        document.getElementsByClassName('content')[0].classList.toggle('light');
        document.getElementsByClassName('label')[0].classList.toggle('light');
        for(var i = 0; i < document.querySelectorAll('.dash_text').length; i++){
            document.getElementsByClassName('dash_text')[i].classList.toggle('light');
            document.getElementsByClassName('sider_dash_box')[i].classList.toggle('light');
        };
        for(var i = 0; i < document.querySelectorAll('.box_head').length; i++){
            document.getElementsByClassName('box_head')[i].classList.toggle('light');
            document.getElementsByClassName('see_button')[i].classList.toggle('light');
            document.getElementsByClassName('box')[i].classList.toggle('light');
        };
        document.getElementsByClassName('main_title')[0].classList.toggle('light');
        document.getElementsByClassName('header')[0].classList.toggle('light');
        document.getElementsByClassName('add_button')[0].classList.toggle('light');

        });
        
