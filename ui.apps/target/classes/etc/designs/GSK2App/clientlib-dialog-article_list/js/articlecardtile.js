(function ($, $document){
       "use strict";

    $(document).on("click", ".cq-dialog-submit", function () {
            $('.articlecard-type1').hide();
   	 		$('.articlecard-type2').hide();

 initialize();

          }); 

    $(document).on("dialog-ready", function(e) {
          $('.articlecard-type1').hide();
   	 		$('.articlecard-type2').hide();

        initialize();

    });

  $(document).on('selected.select', '.view-type', function(e){

                initialize();
     });


function initialize(viewType){
        var viewType = $("[name='./articleCardTileViewType']").val();
        if(viewType=='typeone') {
            $('.articlecard-type2').hide();
            $('.articlecard-type1').show();
        	


    	}else if(viewType=='typetwo'){
            $('.articlecard-type1').hide();
            $('.articlecard-type2').show();
            



        }
    }



})($, $(document));