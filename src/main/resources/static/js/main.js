$(function() {

  // append input control at start of form
  $("<input type='text' value='' />")
     .attr("id", "myfieldid")
     .attr("name", "myfieldid")
     .prependTo("#form-0");

  // OR

  // append input control at end of form
  $("<input type='text' value='' />")
     .attr("id", "myfieldid")
     .attr("name", "myfieldid")
     .appendTo("#form-0");

  // OR

  // see .after() or .before() in the api.jquery.com library

});
