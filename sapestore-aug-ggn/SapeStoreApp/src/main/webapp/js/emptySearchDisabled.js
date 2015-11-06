   function checkForm()
    {
        var searchText = document.forms["searchForm"].elements;
        var cansubmit = true;

          if ((searchText[0].value.length == 0)||(searchText[0].value.trim()=="")){
        	  cansubmit = false;
          }
          if (cansubmit) {
            document.getElementById('searchBtn').disabled =false;
        }
    }
    