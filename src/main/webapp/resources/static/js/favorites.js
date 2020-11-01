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
      $div.attr("class", "btn-group");
      $div.attr("align", "center");
      for (i = 0; i < category.length ; i++) {
        var name = category[i].name; 
        var id = category[i].id;  
        var $button = $("<button>");
        $button.attr({
          "id" : id,
          "name" : name,
          "type" : "button",       
          "class" : "btn btn-primary"
        });
        $button.append(name);
        $div.append($button);    
      }
      $("#categories").append($div);
    }
  });
}
showCategories();
 
//$(".dropdown-content a").on("click", function(e) {
$(document).on("click", ".btn-primary", function() {
  $("#selectedcategory").html("</br>");
  var $span = $("<span>");
  $span.attr("class", "badge");
  $span.attr("style", "font-size:20px");     
  //CSRF token
 	var csrfHeader = $("meta[name=_csrf_header]").attr("content");
  var csrfToken = $("meta[name=_csrf]").attr("content");  
  var headers = {};
  headers[csrfHeader] = csrfToken;
  $("#favorites").html("</br>");
  // Beginning API call
  $span.append($(this).attr("name")); 
  $("#selectedcategory").append($span);
  var queryURL = "http://localhost:8080/giphy/category";
  var selectCategory = $(this).attr("id");
  var removeFlag = "false";
  var moveFlag = "false";
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
        var giphy = giphys[i];
        var id = giphy.id;
        var giphyId = giphy.giphyId; 
        var giphyUrl = giphy.url;    
        $img.attr("src", giphy.url);
        $img.attr("class", "gif")
        $div.attr("id", id);
        $div.addClass("gif-box");
        var $drop_div = $("<div>");
        $drop_div.attr({
          "class" : "dropdown"         
        });
        var $button = $("<button>");
        $button.attr({
          "id" : "category",
          "type" : "button",
          "data-toggle" : "dropdown",
          "class" : "btn btn-link dropdown-toggle",
           "giphyId" : id,
        });
        $button.append("Move ");
        var $span = $("<span>");
        $span.attr("class", "caret");
        $button.append($span);
        var $ul = $("<ul>");
        $ul.attr("class", "dropdown-menu");
        var queryURL = "http://localhost:8080/giphy/category/get"; 
        $.ajax({
          url: queryURL,
          method: "GET",
          async : false,
          contentType: "application/json",
          success: function(categoryList) { 
            for (j = 0; j < categoryList.length; j++) {
              var $li = $("<li>")
              var $a = $("<a>");
              var category = categoryList[j].name;
              var categoryId = categoryList[j].id;
              $a.attr({
                href : "#",
                catId : categoryId,
                name : category,
                url : giphyUrl,
                giphyId : giphyId,
                id : id
              });
              if(selectCategory != categoryId) {
                $a.append(category);
                $li.append($a);
                $ul.append($li);
                $drop_div.append($button, $ul);
              } 
            }
            var $li = $("<li>")
            var $a = $("<a>");
            $a.attr({
                href : "#",
                name : "remove",
                id : id                   
            });
            $a.append("Trash");
            $li.append($a);
            $ul.append($li);
            $drop_div.append($button, $ul); 
          }   
        });
        $div.append($img, $drop_div);
        $("#favorites").append($div);        
      } 
      //$("#category").attr("value", $(this).attr("name"))
      //Remove gif
      $(".dropdown-menu li a").on("click", function() {
      if($(this).attr("name") === "remove")
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
          $(this).parent().parent().parent().parent().remove();
        }       
      }
      else
      {
        var category = {
          id : $(this).attr("catId")
        }
        var giphy = {
          "url" : $(this).attr("url"),
          "giphyId" : $(this).attr("giphyid"),
          "category" : category,
          "id" : $(this).attr("id")
        }
        $.ajax({
          url: "http://localhost:8080/giphy/add",
          contentType: "application/json",
          headers : headers,
          async : false,
          method: "POST",
          data : JSON.stringify(giphy),       
          success : function(){
            moveFlag = "true";            
          }
        });
        if(moveFlag === "true") {
          $(this).parent().parent().parent().parent().remove();
        }
      }
    });   
    }
  });
});
