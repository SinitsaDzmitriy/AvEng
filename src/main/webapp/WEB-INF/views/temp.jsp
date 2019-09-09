<!DOCTYPE html>
<html lang="ru">
<head>
  <title>Temp</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,800,900&amp;display=swap&amp;subset=cyrillic">

  <meta charset="utf-8">
  <style>
    .w-10 {
        width: 10% !important;
    }
	.w-20 {
        width: 20% !important;
    }
    .w-80 {
        width: 80% !important;
    }
	.w-100{
	    width: 100% !important;
	}
	.h-100{
	    height: 100vh !important;
	}
	#selectBlock{
		list-style: none;
		margin: 0;
		padding: 0;
	}
	#selectBlock li{
		padding: 10px 0;
		border-bottom: solid 1px #9e9e9e;
		position: relative;
	}
	.selectImgEdit, .selectImgRemove, .selectImgAdd{
		width: 14px;
		height: 14px;
		cursor: pointer;
	}
	#imgBlock{
		float: right;
	}
	#imgBlock *{
		margin: 6px 3px;
		float: right;
	}
	.selectImgAdd{
		margin: 10px auto;
		display: block;
	}
	.selectAdd{
		padding: 10px 0;
		cursor: pointer;
		display: none;
	}
	.newSelect, .selectBtnAdd{
		width: 100%;
	}
	.selectBtnAdd{
		margin: 0;
		width: auto;
	}
	.selectBtnCancel{
		margin: 10px 0 0 5px;
	}
	.editSelect{
		width: 100%;
	}
	.selectBtnEdit{
		margin: 10px 10px 0 0;
	}
	.selectAdd, .editValue, .submitBlock{
		text-align: center;
	}
	.editBtnCancel, .selectBtnEdit, .selectBtnCancel, .selectBtnAdd, .messageOkay, .messageCancel, .btnSubmit{
		border-radius: .25rem;
		cursor: pointer;
		padding: 2px 5px;
		display: inline-block;
		color: #f8f9fa;
		background: #6c747d;
		margin-top: 10px;
		min-width: 65px;
		text-align: center;
	}
	.editBtnCancel:hover, .selectBtnEdit:hover, .selectBtnCancel:hover, .selectBtnAdd:hover, .messageOkay:hover, .messageCancel:hover, .btnSubmit:hover{
		opacity: .9;
	}
	.carousel-control-next, .carousel-control-prev{
		background: #6c747d;
		opacity: 1;
	}
	.mesageBackground{
		position: absolute;
		top: 0;
		left: 0;
		background: rgb(0,0,0,0.5);
		-webkit-box-align: center;
		-ms-flex-align: center;
		align-items: center;
		width: 100%;
		transition: -webkit-transform .6s ease;
		transition: transform .6s ease;
		transition: transform .6s ease,-webkit-transform .6s ease;
		-webkit-backface-visibility: hidden;
		backface-visibility: hidden;
		display: none;
	}
	.messageText{
		color: #6c747d;
		font-weight: 600;
		font-size: 17px;
	}
	.message{
		background: #f8f9fa;
		padding: 25px;
		text-align: center;
	}
	.messageOkay{
		margin-right: 10px;
	}
	.mesageBackgroundFlex{
		display: flex;
	}
  </style>
</head>
<body>
    <div class="bg-secondary">
	
        <div id="carouselExampleControls" class="carousel slide" data-interval="false">

            <div class="carousel-inner w-100 m-auto">

                <form method="POST">

                    <a class="carousel-control-next w-10" href="#carouselExampleControls" role="button"
                       data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>

                    <a class="carousel-control-prev w-10">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>


                    <div class="carousel-item active">



                        <div class="h-100 w-100 bg-light d-flex align-items-center justify-content-center">
                            <div>

                                <div class="form-group">

                                    <label for="enabledContentInput">
                                        <spring:message code="card.attribute.content"/>
                                    </label><br>

                                    <input id="enabledContentInput"
                                                       type="text"
                                                       class="first form-control"
                                                       path="content"
                                                       placeholder="Enter word or phrase"
                                                       cssErrorClass="error"/><br>

                                </div>

                            </div>
                        </div>
						
                    </div>



                    <div class="carousel-item">
						
                        <div class="h-100 w-100 bg-light d-flex align-items-center justify-content-center">
                            <div>

                                <label for="enabledContentInput"
                                                   path="content"
                                                   cssErrorClass="error">
                                </label><br>

                                <input id="disabledContentInput"
                                                   type="text"
                                                   class="second form-control"
                                                   placeholder="Enter word or phrase"
                                                   readonly/>

                                <br>
												   
								<ul id="selectBlock"></ul>

								<div class="selectAdd">
									<input class="form-control newSelect" type="text">
									<div class="selectBtnAdd">Add</div>
									<div class="selectBtnCancel" onclick="addValue(500)">Cancel</div>
								</div>

								<img class="selectImgAdd" onclick="addValue(500)" src="https://image.flaticon.com/icons/svg/61/61733.svg">

								<br>
								
                                <%-- samples[0].content --%>

								<div class="submitBlock">
									<div class="btnSubmit">Submit</div>
								</div>


                            </div>
                        </div>
						
                    </div>

                </form>

            </div>
			
        </div>
		<div class="mesageBackground">
			<div class=" h-100 w-100 mesageBackgroundFlex">
				<div class="message w-20 align-items-center justify-content-center m-auto">
					<div class="messageText">If you go back - you lost information that you entered.</div>
					<div class="messageOkay carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev" style="position: relative;">Okay</div>
					<div class="messageCancel" onclick="cancelMesage(500)">Cancel</div>
				</div>
			</div>
		</div>
<script>

	$('.first').change(function(){
		var value = $('.first').val();
		$('.second').val(value);
	});
	
	function addValue(value){
        $('.selectAdd').slideToggle(value);
		$('.selectImgAdd').slideToggle(value);
    };
		
	function cancelMesage(value){
		$('.mesageBackground').fadeToggle(value);
	}
		
	$('#selectBlock').on('click', '.editBtnCancel', function(){
		$(this).closest('li').children('.selectValue').slideToggle(500);
		var removeTarget = $(this).closest('li').children('.editValue');
		setTimeout(function(){
			removeTarget.remove();
        },500);
		removeTarget.slideToggle(500);
    });
		
	$('.selectBtnAdd').click(function(){
		var newValue = $('.newSelect').val();
		var strElem  = $('.second').val();
		if(newValue.indexOf(strElem) != -1){
			var $new = $('<li><div class=\"selectValue\"><span>'+newValue+'</span><div id=\"imgBlock\"><img class=\"selectImgRemove\" src=\"https://image.flaticon.com/icons/svg/61/61848.svg\"><img class=\"selectImgEdit\" src=\"https://image.flaticon.com/icons/svg/61/61776.svg\"></div></div></li>').hide();
			$new.appendTo('#selectBlock').slideToggle(500);
			addValue(500);
		}
		$('.newSelect').val('');
	});
	
	$('#selectBlock').on('click', '.selectImgRemove', function(){
		var removeTarget = $(this).closest('li');
		setTimeout(function(){
			removeTarget.remove();
        },500);
		$(this).closest('li').slideToggle(500);
	});
	
	$('#selectBlock').on('click', '.selectImgEdit', function(){
		$(this).closest('li').children('.selectValue').slideToggle(500);
		var elemEdit = $(this).closest('li').children('div').children('span');
		var $new = $('<div class=\"editValue\"><input class=\"form-control editSelect\" type=\"text\" value=\"'+elemEdit.text()+'\"><div class=\"selectBtnEdit\">Edit</div><div class=\"editBtnCancel\">Cancel</div></div>').hide();
		$new.appendTo($(this).closest('li')).slideToggle(500);
	});
	
	$('#selectBlock').on('click', '.selectBtnEdit', function(){
		var editValue = $('.editSelect').val();
		var strElem  = $('.second').val();
		if(editValue.indexOf(strElem) != -1){
			$(this).closest('li').children('.selectValue').slideToggle(500);
			var elemEdit = $(this).closest('li').children('div').children('span');
			elemEdit.text($('.editSelect').val());
			var removeTarget = $(this).closest('li').children('.editValue');
			setTimeout(function(){
				removeTarget.remove();
			},500);
			removeTarget.slideToggle(500);
		}
	})
	$('body').on('click', '.messageOkay', function(){
		setTimeout(function(){
			$('#selectBlock').empty()
		 },1500);
	});
	
	// $('.carousel-control-prev').click(function(){
	// 	$('.mesageBackground').fadeToggle(500);
	// });
	
	$('.btnSubmit').click(function(){
		var arr = [],selectLength = $('#selectBlock li').length,i=0;
		for(i;i<selectLength;i++)
			arr[i]=$('#selectBlock li .selectValue span')[i].textContent;
			
	});
	
</script>
</body>
<html>