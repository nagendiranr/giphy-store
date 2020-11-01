// Initial Search Buttons
var topics = [];
function addSearchBtns() {
  $("#buttons").html("");
  for (i = 0; i < topics.length; i++) {
    var $button = $("<input type='button' class='btn btn-sm search-btn' />");
    $button.val(topics[i]);
    $("#buttons").append($button);
  }
}
//addSearchBtns();
$(".btn").on("click", function() {
  $("#results").html("");
  //CSRF token
 	var csrfHeader = $("meta[name=_csrf_header]").attr("content");
  var csrfToken = $("meta[name=_csrf]").attr("content");
  var headers = {};
  headers[csrfHeader] = csrfToken;
  // Beginning API call
  var queryURL = "https://api.giphy.com/v1/gifs/search?";
  var categoryURL = "http://localhost:8080/giphy/category/get"
  var query;
  var categoryList;
  var params = {
    q: query,
    limit: 10,
    api_key: "gjHQmHIaXmOaTXQfPmCHO1Nnt9SkXPUL",
    rating: "g",
    fmt: "json"
  };
  //Get category list
  $.ajax({
    url: categoryURL,
    contentType: "application/json",
    method: "GET",
    async : false,
    success : function(category){  
      categoryList = category;
    }
  });
  if ($(this).hasClass("search-btn")) {
    query = $(this).val();
  } else if ($("#user-search").val() !== "") {
    query = $("#user-search").val();
    topics.push(query);
    if (topics.length > 6) {
      topics.shift();
    }
    //addSearchBtns();
  }
  params.q = query;
  if ($(this).hasClass("trending")) {
    queryURL = "https://api.giphy.com/v1/gifs/trending?";
    delete params.q;
  }
  $.ajax({
    url: queryURL + $.param(params),
    method: "GET",
    success: function(r) {
      for (i = 0; i < params.limit; i++) {
        var $img = $("<img>");
        var $div = $("<div>");
        var gifObj = r.data[i];
        var gif = gifObj.images;
        var gifURL = gif.fixed_height.url;
        // Image builder object
        $img.attr({
          // "width": "200px",
          src: gifURL,
          class: "gif"
        });
        // $div.attr("id", "gif-" + i);
        $div.addClass("gif-box");
        var $drop_div = $("<div>");
        $drop_div.attr({
          "class" : "dropdown"
          /* "id" : gifObj.id,
          "url" : gifURL, */
        });
        var $button = $("<button>");
        $button.attr({
          "id" : "category",
          "type" : "button",
          "data-toggle" : "dropdown",
          "class" : "btn btn-link dropdown-toggle",
          giphyId : gifObj.id,
        });
        $button.append("Save ");
        var $span = $("<span>");
        $span.attr("class", "caret");
        $button.append($span);
        var $ul = $("<ul>");
        $ul.attr("class", "dropdown-menu");
        //Category list populate
        for (j = 0; j < categoryList.length; j++) {
          var $li = $("<li>")
          var $a = $("<a>");
          var category = categoryList[j].name;
          var categoryId = categoryList[j].id;
          $a.attr({
            href : "#",
            id : categoryId,
            name : category,
            giphyId : gifObj.id,
            url : gifURL
          });
          $a.append(category);
          $li.append($a);
          $ul.append($li);
          $drop_div.append($button, $ul); 
        }
        //Add new drop down for create category
        var $li = $("<li>")
        var $a = $("<a>");
        $a.attr({
            href : "http://localhost:8080/category",
            id : "create"                   
        });
        $a.append("Create New");
        $li.append($a);
        $ul.append($li);
        $drop_div.append($button, $ul); 
        $div.append($img, $drop_div);
        $("#results").append($div);
      }
      $(".dropdown-menu li a").on("click", function() {
        var removeFlag = "false";
        var category = {
          id : $(this).attr("id")
        }
        var giphy = {
          "url" : $(this).attr("url"),
          "giphyId" : $(this).attr("giphyId"),
          "category" : category
        }
        $.ajax({
          url: "http://localhost:8080/giphy/add",
          contentType: "application/json",
          headers : headers,
          async : false,
          method: "POST",
          data : JSON.stringify(giphy),       
          success : function(){
            removeFlag = "true";
          }
        });
        if(removeFlag === "true") {
          $(this).parent().parent().parent().parent().remove();
        }
        //$(".dropbtn").attr("value", "Added");
      });
    }
  });
});