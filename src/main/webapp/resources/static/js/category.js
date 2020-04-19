function showCategories() {
  $("#categories").html("");
  // Beginning API call
  var queryURL = "http://localhost:8080/giphy/category/get"; 
  $.ajax({
    url: queryURL,
    method: "GET",
    contentType: "application/json",
    success: function(category) {
      for (i = 0; i < category.length ; i++) {       
        var $div = $("<div>");
        $div.attr("align", "center");        
        var $span = $("<span>");
        $span.attr("class", "badge");
        $span.attr("style", "font-size:20px");       
        var name = category[i].name;
        $div.attr("id", category[i].id);
        //$div.addClass("gif-box");
        $span.text(name);
        $div.append("<br/>");
        $div.append($span);
        $("#categories").append($div);
      }
    }
  });
}
showCategories();      

$(document).on("click", ".btn", function() {
  //CSRF token
 	var csrfHeader = $("meta[name=_csrf_header]").attr("content");
  var csrfToken = $("meta[name=_csrf]").attr("content");  
  var headers = {};
  headers[csrfHeader] = csrfToken;
  if($(this).attr("value") === "Create")
  {
    var categoryName;
    if ($("#category").val() !== "") {
      categoryName = $("#category").val();
      var category = {
        name : categoryName
      }
      $.ajax({
      url: "http://localhost:8080/giphy/category/add",
      contentType: "application/json",
      headers : headers,
      method: "POST",
      data : JSON.stringify(category),       
      success : function(){
        $("#category").val("");
        showCategories();            
      }
    });
  }
  
}
});