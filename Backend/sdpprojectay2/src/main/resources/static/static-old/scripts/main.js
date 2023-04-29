// $(document).ready(function () {
//   var tableRows = $("#table-id tbody tr");
//   var totalPages = Math.ceil(tableRows.length / 30); // 30 rows per page

//   for (var i = 1; i <= totalPages; i++) {
//     if (i < totalPages) {
//       $(".pagination-list").append("<li><a href='#'>" + i + "</a></li>");
//     } else if (i = totalPages) {
//       $(".pagination-list").append("<li><a href='#'>" + "Next" + "</a></li>");

//     }
//   }

//   var page = 1;
//   showPage(page); // show the first page by default

//   $(".pagination-list li a").click(function () {
//     var clickPage = $(this).text();

//     if (clickPage == "Next") {
//       page += 1;

//       showPage(page)
//     } else {
//       page = clickPage;
//       showPage(page);
//     }
//     page = 1;
//   });

//   function showPage(page) {
//     var startIndex = (page - 1) * 31;
//     var endIndex = startIndex + 30;
//     tableRows.hide();
//     tableRows.slice(startIndex, endIndex + 1).show();
//   }

// });


/*
  Version 2 
  Where previous button was added
*/


// $(document).ready(function () {
//   var tableRows = $("#table-id tbody tr");
//   var totalPages = Math.ceil(tableRows.length / 30); // 30 rows per page

//   for (var i = 0; i <= totalPages; i++) {
//     if (i == 0) {
//       $(".pagination-list").append("<li><a href='#'>" + "Previous" + "</a></li>");
//     } else if (i < totalPages) {
//       $(".pagination-list").append("<li><a href='#'>" + i + "</a></li>");
//     } else if (i == totalPages) {
//       $(".pagination-list").append("<li><a href='#'>" + "Next" + "</a></li>");
//     }
//   }

//   var page = 1;
//   showPage(page); // show the first page by default

//   $(".pagination-list li a").click(function () {
//     var clickPage = $(this).text();

//     if (clickPage == "Next") {
//       page += 1;
//     } else if (clickPage == "Previous") {
//       page -= 1;
//     } else {
//       page = parseInt(clickPage);
//     }

//     // Handle page boundary conditions
//     if (page < 1) {
//       page = 1;
//     } else if (page > totalPages) {
//       page = totalPages;
//     }

//     showPage(page);
//   });

//   function showPage(page) {
//     var startIndex = (page - 1) * 30;
//     var endIndex = startIndex + 29;
//     tableRows.hide();
//     tableRows.slice(startIndex, endIndex + 1).show();
//   }
// });



/*version 3 
  Disabled prev button
*/

// $(document).ready(function () {
//   var tableRows = $("#table-id tbody tr");
//   var totalPages = Math.ceil(tableRows.length / 30); // 30 rows per page

//   for (var i = 0; i <= totalPages; i++) {
//     if (i == 0) {
//       $(".pagination-list").append("<li><a href='#' class='prev-button'>" + "Previous" + "</a></li>");
//     } else if (i < totalPages) {
//       $(".pagination-list").append("<li><a href='#'>" + i + "</a></li>");
//     } else if (i == totalPages) {
//       $(".pagination-list").append("<li><a href='#'>" + i + "</a></li>");

//       $(".pagination-list").append("<li><a href='#' class='next-button'>" + "Next" + "</a></li>");
//     }
//   }

//   var page = 1;
//   showPage(page); // show the first page by default
//   updatePrevButtonState();

//   $(".pagination-list li a").click(function () {
//     var clickPage = $(this).text();

//     if (clickPage == "Next") {
//       page += 1;
//     } else if (clickPage == "Previous") {
//       page -= 1;
//     } else {
//       page = parseInt(clickPage);
//     }

//     // Handle page boundary conditions
//     if (page < 1) {
//       page = 1;
//     } else if (page > totalPages) {
//       page = totalPages;
//     }

//     showPage(page);
//     updatePrevButtonState();
//   });

//   function showPage(page) {
//     var startIndex = (page - 1) * 30;
//     var endIndex = startIndex + 29;
//     tableRows.hide();
//     tableRows.slice(startIndex, endIndex + 1).show();
//   }

//   function updatePrevButtonState() {
//     if (page == 1) {
//       $(".prev-button").addClass("disabled");
//     } else {
//       $(".prev-button").removeClass("disabled");
//     }

//     if (page == totalPages) {
//       $(".next-button").addClass("disabled");
//     } else {
//       $(".next-button").removeClass("disabled");
//     }
//   }
// });




/*Version 4
  Where added next button disable
*/
// $(document).ready(function () {
//   var tableRows = $("#table-id tbody tr");
//   var itemsPerPage = 30;
//   var totalPages = Math.ceil(tableRows.length / itemsPerPage); // 30 rows per page
//   var visiblePages = 3;


//   var page = 1;
//   updatePagination();
//   showPage(page); // show the first page by default
//   updateButtonState();

//   $(".pagination-list li a").click(function () {
//     var clickPage = $(this).text();

//     if (clickPage == "Next") {
//       page += 1;

//     } else if (clickPage == "Previous") {
//       page -= 1;

//     } else {
//       page = parseInt(clickPage);

//     }

//     // Handle page boundary conditions
//     if (page < 1) {
//       page = 1;
//     } else if (page > totalPages) {
//       page = totalPages;
//     }

//     updatePagination();

//     showPage(page);
//     updateButtonState();

//   });

//   function showPage(page) {
//     var startIndex = (page - 1) * itemsPerPage;
//     var endIndex = startIndex + itemsPerPage - 1;
//     tableRows.hide();
//     tableRows.slice(startIndex, endIndex + 1).show();

//   }

//   function updateButtonState() {
//     if (page == 1) {
//       $(".prev-button").addClass("disabled");
//     } else {
//       $(".prev-button").removeClass("disabled");
//     }

//     if (page == totalPages) {
//       $(".next-button").addClass("disabled");
//     } else {
//       $(".next-button").removeClass("disabled");
//     }
//   }



//   function updatePagination() {
//     $(".pagination-list").empty();


//     if (totalPages > visiblePages) {


//       for (var i = 0; i <= totalPages; i++) {
//         if (i == 0) {
//           $(".pagination-list").append("<li><a href='#' class='prev-button'>" + "Previous" + "</a></li>");

//         } else if (i < totalPages) {
//           $(".pagination-list").append("<li><a href='#'>" + i + "</a></li>");
//         } else if (i == totalPages) {
//           $(".pagination-list").append("<li><a href='#'>" + i + "</a></li>");

//           $(".pagination-list").append("<li><a href='#' class='next-button'>" + "Next" + "</a></li>");
//         }
//       }

//     }



//   }
// });



/*Version 5 
  added fadeout unnecessary buttons
*/

$(document).ready(function () {
  var tableRows = $("#table-id tbody tr");
  var rowsPerPage = 30;
  var totalPages = Math.ceil(tableRows.length / rowsPerPage); // 30 rows per page
  var currentPage = 1;

  // Function to generate pagination links
  function generatePaginationLinks() {
    // Clear existing pagination links
    $(".pagination-list").empty();

    // Add previous button
    if (currentPage > 1) {
      $(".pagination-list").append("<li><a href='#' class='first-button'>First</a></li>");

      $(".pagination-list").append("<li><a href='#' class='prev-button'>Previous</a></li>");
    }

    // Add 3 links before and after current page
    var startPage = Math.max(1, currentPage - 3);
    var endPage = Math.min(totalPages, currentPage + 3);

    for (var i = startPage; i <= endPage; i++) {
      var activeClass = (i == currentPage) ? "active" : "";
      $(".pagination-list").append("<li><a href='#' class='" + activeClass + "'>" + i + "</a></li>");
    }

    // Add next button
    if (currentPage < totalPages) {
      $(".pagination-list").append("<li><a href='#' class='next-button'>Next</a></li>");
      $(".pagination-list").append("<li><a href='#' class='last-button'>Last</a></li>");

    }
  }

  // Show initial page
  showPage(currentPage);

  // Generate pagination links
  generatePaginationLinks();

  // Handle pagination link clicks
  $(".pagination-list").on("click", "a", function () {
    var linkText = $(this).text();
    if (linkText == "Previous") {
      currentPage--;
    } else if (linkText == "Next") {
      currentPage++;
    }
    else if (linkText == "First") {
      currentPage=1;
    } 
    else if (linkText == "Last") {
      currentPage=totalPages;
    }else {
      currentPage = parseInt(linkText);
    }

    // Update pagination links and show current page
    generatePaginationLinks();
    showPage(currentPage);
  });

  // Function to show selected page
  function showPage(pageNumber) {
    var startIndex = (pageNumber - 1) * rowsPerPage;
    var endIndex = Math.min(startIndex + rowsPerPage, tableRows.length);
    tableRows.hide();
    tableRows.slice(startIndex, endIndex).show();
  }
});
