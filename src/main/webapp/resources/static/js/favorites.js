function showCategories() {
  $("#categories").html("");
  // Beginning API call
  var queryURL = "http://localhost:8080/giphy/category/get"; 
  $.ajax({
    url: queryURL,
    method: "GET",
    contentType: "application/json",
    success: function(category) {  
      var $div = $("#categories");
      $div.attr("class", "dropdown");
      var $button = $("<button>");
      $button.attr({
        "id" : "category",
        "type" : "button",
        "data-toggle" : "dropdown",
        "class" : "btn btn-primary dropdown-toggle"
      });
      $button.append("View by Category ");
      var $span = $("<span>");
      $span.attr("class", "caret");
      $button.append($span);
      var $ul = $("<ul>");
      $ul.attr("class", "dropdown-menu");          
      for (i = 0; i < category.length ; i++) { 
        var name = category[i].name; 
        var id = category[i].id;             
        var $li = $("<li>")
        var $a = $("<a>")         
        $a.attr({
            href : "#",
            id : id,
            name : name          
        });        
        $a.append(name);
        $li.append($a); 
        $ul.append($li);      
      } 
      $div.append($button, $ul);        
      //$("#categories").append($div);
    }
  });
}
showCategories(); 
//$(".dropdown-content a").on("click", function(e) {
$(document).on("click", ".dropdown-menu li a", function(e) {
  e.preventDefault();
  //CSRF token
 	var csrfHeader = $("meta[name=_csrf_header]").attr("content");
  var csrfToken = $("meta[name=_csrf]").attr("content");  
  var headers = {};
  headers[csrfHeader] = csrfToken;
  $("#favorites").html("");
  // Beginning API call
  var queryURL = "http://localhost:8080/giphy/category";
  var category = {
    id : $(this).attr("id")
  }
  $.ajax({
    url: queryURL + "?categoryId=" + $(this).attr("id"),
    method: "GET",      
    contentType: "application/json",
    success: function(giphys) {
      if(giphys.length == 0) {
        $("#favorites").append("No giphy found!");
      }
      for (i = 0; i < giphys.length ; i++) {
        var $img = $("<img>");
        var $div = $("<div>");
        var $remove = $("<input>");
        var giphy = giphys[i];
        var id = giphy.id;     
        $img.attr("src", giphy.url);
        $div.attr("id", id);
        $div.addClass("gif-box");
        $remove.attr({
            type: "button",
            value: "Remove",
            id : id,
            class: "btn btn-link"
        });
        $div.append($img, $remove);
        $("#favorites").append($div);        
      } 
      //$("#category").attr("value", $(this).attr("name"))
      //Remove gif
      $(".btn-link").on("click", function() {
      if($(this).attr("value") === "Remove")
      {
        var giphy = {
          "id" : $(this).attr("id")
        } 
        $.ajax({
          url: "http://localhost:8080/giphy/remove",
          contentType: "application/json",
          async : false,
          headers : headers,
          method: "DELETE",
          data : JSON.stringify(giphy),       
          success : function(){
             removeFlag = "true";          
          }
        });
        if(removeFlag === "true") {
          $(this).parent().remove();
        }       
      }
    });   
    }
  });
});