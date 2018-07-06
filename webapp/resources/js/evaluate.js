$(function() {

	// var rating = $('.rating-stars .caption').text();

	$('.filled-stars').click(
			function() {

				var rating = $(this).attr('style');
				rating = rating.substring(6, rating.length - 2);
				//var movieName = $(this).parent.().parent().prev().text();
				var movieNum = $(this).parent().parent().prev().find('input').val(); 
				// alert(rating.substring(6, rating.length - 2));
				//alert(rating);
				//alert(movieNum);
					//alert('선택된 영화번호 : ' + movieNum );
				// alert(movieName);

				$.ajax({
					url : "http://localhost:8081/MovieR/movieData/evaluate.jh?rating=" + rating
							+ "&movieNum=" + movieNum,
					success : function() {
						alert('저장되었습니다.');
					}, error: function(){
						alert('Ajax Error!')
					}

				});

			});
});