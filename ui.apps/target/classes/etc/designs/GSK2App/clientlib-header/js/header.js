<script>
$(function() {
	window.prettyPrint && prettyPrint()
	$(document).on('click', '.megaMenu .dropdown-menu', function(e) {
	  e.stopPropagation()
	});
  });
</script>
<script>
$(document).ready(function(){
  $('.dropdown-submenu a.test').on("click", function(e){
    $(this).next('ul').toggle();
    e.stopPropagation();
    e.preventDefault();
  });
});