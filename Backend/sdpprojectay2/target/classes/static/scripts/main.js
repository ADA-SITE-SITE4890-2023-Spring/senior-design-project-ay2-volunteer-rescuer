


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
      $(".pagination-list").append("<li><a href='#' class='first-button'>İlk</a></li>");

      $(".pagination-list").append("<li><a href='#' class='prev-button'>Sonuncu</a></li>");
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
      $(".pagination-list").append("<li><a href='#' class='next-button'>Növbəti</a></li>");
      $(".pagination-list").append("<li><a href='#' class='last-button'>Sonuncu</a></li>");

    }
  }

  // Show initial page
  showPage(currentPage);

  // Generate pagination links
  generatePaginationLinks();

  // Handle pagination link clicks
  $(".pagination-list").on("click", "a", function () {
    var linkText = $(this).text();
    if (linkText == "Əvvəlki") {
      currentPage--;
    } else if (linkText == "Növbəti") {
      currentPage++;
    }
    else if (linkText == "İlk") {
      currentPage=1;
    } 
    else if (linkText == "Sonuncu") {
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
