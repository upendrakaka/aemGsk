$(function() {
	window.prettyPrint && prettyPrint()
	$(document).on('click', '.megaMenu .dropdown-menu', function(e) {
	  e.stopPropagation()
	})
  })

$(document).ready(function(){
  $('.dropdown-submenu a.test').on("click", function(e){
    $(this).next('ul').toggle();
    e.stopPropagation();
    e.preventDefault();
  });
});

$('.panel-collapse').on('show.bs.collapse', function () {
    $(this).siblings('.panel-heading').addClass('active');
  });

  $('.panel-collapse').on('hide.bs.collapse', function () {
    $(this).siblings('.panel-heading').removeClass('active');
  });

function testCall(){
alert("SUBMIT BUTTON FUNCTION CHECKING");
}


document.addEventListener("click", function(){
    console.log("CHECK CHECK");
   document.getElementById("demo").innerHTML = "Hello World!";

});