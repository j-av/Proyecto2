(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
    
    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      $("body").addClass("sidebar-toggled");
      $(".sidebar").addClass("toggled");
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });
  
  //Evento del botón que me devuelve el listado de actores
  $("#busquedapais").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld2/SeriesByPais?pais_name=' + $('#pais').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.series, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-success').html("");
				$('#div-success').append(htmlMovieList);
			}
		} );

	});
	
	//Evento del botón que me devuelve el listado de películas de un determinado actor
	$("#busquedagenero").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld2/SeriesByGenre?genre_name=' + $('#genero').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.series, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});
	
	//Evento del botón que me devuelve el listado de películas de un determinado actor
	$("#anios").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld2/SeriesByYear?year_1=' + $('#anio1').val() + '&year_2=' + $('#anio2').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.series, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});
	
	$("#serielarga").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld2/SeriesBySeasonHigh',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.series, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});
	
	$("#seriecorta").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld2/SeriesBySeasonLow',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.series, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});
	
	$("#agregarabase").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld2/NewSeries?series_name=' + $('#agregarnombre').val()  +  '&actor_name=' + $('#agregaractor').val() +'&genre_name=' + $('#agregargenero').val() + '&year_name=' + $('#agregaranio').val() + '&season_name=' + $('#agregartemp').val() + '&country_name=' + $('#agregarpais').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
			    htmlMovieList += 'Datos ingresados';
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});

})(jQuery); // End of use strict
