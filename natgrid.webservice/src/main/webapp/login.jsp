<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
  <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script src="webjars/jquery/3.5.1/jquery.min.js"></script>
  <!--  <script src="webjars/datatables/1.10.21/js/jquery.dataTables.js"></script>
  <link href="webjars/datatables/1.10.21/css/jquery.dataTables.css" rel="stylesheet">-->
  
  <link href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
  <link href="https://cdn.datatables.net/rowgroup/1.1.2/css/rowGroup.bootstrap4.min.css" rel="stylesheet" />
  <link href="https://cdn.datatables.net/buttons/1.6.3/css/buttons.bootstrap4.min.css" rel="stylesheet" />
  
<meta charset="ISO-8859-1">
<title>Natgrid Home</title>
</head>
<body>


<div class="main main-raised">
    <div class="container">
      <div class="section text-center">
        <h2 class="title">Report</h2>
      </div>
    </div>
    
    <div class="row">
	      	<div class="col-md-12 mx-auto text-center">
	      		<input type="month" id="monthyear" />
	      	
             
              <button class="btn btn-primary btn-round" type="button" onclick="showData();" >Show</button>
              <button class="btn btn-primary btn-round" type="button" onclick="mailData();" >Email</button>
             
            </div>
     </div>
     
      <div class="row  text-center" style="max-width: 850px; margin: auto;">
			      	<div class="col-md-12 mx-auto" id="myTableDiv">
				    </div>
		 </div>
      		      

</div>

<script type="text/javascript">
 function mailData()
 {
	 //alert(sel.value);

	 $.ajax({
	        type: "GET",
	        url:"/sendemail",
	       // dataType: 'json',
	       // username: "admin",
	       // password: "admin",
	        success: function (result) {
	            alert("Email Sent!!");
	            //console.log(result);
	        },
	        error: function (result) {
	            alert("Error");
	        }
	    });
 }


 function showData()
 {
   //alert(document.getElementById("monthyear").value);
   if(document.getElementById("monthyear").value=="")
	{alert("Select Month");
	}else
    {		
	 $.ajax({
	        type: "GET",
	        url:"/GENREPORT?monthyr="+document.getElementById("monthyear").value,
	        dataType: 'json',
	        success: function (result) {
	        	//console.log(genReportHtmlTable(result));
				   document.getElementById("myTableDiv").innerHTML=genReportHtmlTable(result);	
				   loadDataTable();
	        },
	        error: function (result) {
	            alert("Error");
	        }
	    }); 
    }      
 }

 function genReportHtmlTable( data)
 {
	 str1 = "<table id='myTable' class='table table-bordered'><thead><tr><th>System</th><th>Usecase</th>";
	 str1+="<th>No. of Requests Received</th><th>No. of Responses Served</th><th>Total No of Requests for which<br> NO RECORD FOUND</th>";
	 str1+="<th>Total No of Records Returned</th></tr></thead><tbody>";

	 if(data.length==0)
	 {
		 str1+="<tr><td colspan='6'><b> No Record Found</b> </td></tr></tbody></table>";

	 }else
	 {	  
	  sumrequest=0;
	  sumnorcd=0;
	  sumrf=0;
	  for (i = 0; i < data.length; i++) 
	  {
		  //console.log(data[i]);
		sumrequest+=data[i].requestcount;
		sumnorcd+=data[i].norecordfoundcount;
		sumrf+=data[i].recordcount;
		
		str1+="<tr>"
		str1+="<td class='text-left'>"+data[i].crisgroup+"</td>";
		str1+="<td class='text-left'>"+data[i].usecaseid+"</td>";
		str1+="<td class='text-right'>"+data[i].requestcount+"</td>";
		str1+="<td class='text-right'>"+data[i].requestcount+"</td>";
		str1+="<td class='text-right'>"+data[i].norecordfoundcount+"</td>";
		str1+="<td class='text-right'>"+data[i].recordcount+"</td></tr>";
						
	  }

	  str1+="</tbody><tfoot><tr>";
	  str1+=	"<th class='text-left'  colspan='2'>TOTAL</th>";
	  str1+=	"<th class='text-right'>"+sumrequest+"</th>";
	  str1+=	"<th class='text-right'>"+sumrequest+"</th>";
	  str1+=	"<th class='text-right'>"+sumnorcd+"</th>";
	  str1+=	"<th class='text-right'>"+sumrf+"</th></tr></tfoot></table>";
	 }

	 return str1;
  }


 function loadDataTable() 
 {
	 var table = $('#myTable').DataTable( {
	    	dom: 'Bfrtip',
	        lengthChange: false,
	        "paging":   false,
	        "ordering": true,
	        "info":     false,
	        "searching": false,
	        "order": [[ 0, "asc" ],[ 1, "asc" ]],
	        "rowGroup": {
	            dataSrc: 0
	        },

	        buttons: [
	            {
	                text: 'Download in PDF',
	                extend: 'pdfHtml5',
	            message: '',
	            footer: true,
	            orientation: 'portrait',
	            exportOptions: {
	                 columns: ':visible'
	            },
	            customize: function (doc) {
	            	doc['header']=(function() {
						return {
							columns: [

								{
									alignment: 'left',
									italics: false,
									text: "Summary of data shared with NATGRID for August 2020\n(Confidential)",
									fontSize: 14,
									margin: [60,40]
								},

								],
								margin: 20
						}
					});
	                doc.pageMargins = [10,10,10,10];
	                doc.defaultStyle.fontSize = 7;
	                doc.styles.tableHeader.fontSize = 7;
	                doc.styles.title.fontSize = 9;
	                // Remove spaces around page title
	                doc.content[0].text = "Summary of Data Shared with NATGRID for August 2020\n(Confidential)";
	                // Create a footer
	                var currentDate = new Date()
					var day = currentDate.getDate()
					var month = currentDate.getMonth() + 1
					var year = currentDate.getFullYear()
					
					var d = day + "/" + month + "/" + year;
	                doc['footer']=(function(page, pages) {
	                    return {
	                        columns: [
	                         
	                            {
	                                // This is the right column
	                                alignment: 'right',
	                                text: ['page ', { text: page.toString() },  ' of ', { text: pages.toString() }]
	                            }
	                        ],
	                        margin: [10, 0]
	                    }
	                });
	                // Styling the table: create style object
	                var objLayout = {};
	                // Horizontal line thickness
	                objLayout['hLineWidth'] = function(i) { return .5; };
	                // Vertikal line thickness
	                objLayout['vLineWidth'] = function(i) { return .5; };
	                // Horizontal line color
	                objLayout['hLineColor'] = function(i) { return '#aaa'; };
	                // Vertical line color
	                objLayout['vLineColor'] = function(i) { return '#aaa'; };
	                // Left padding of the cell
	                objLayout['paddingLeft'] = function(i) { return 4; };
	                // Right padding of the cell
	                objLayout['paddingRight'] = function(i) { return 4; };
	                // Inject the object in the document
	                doc.content[1].layout = objLayout;
	            }
	            }
	        ]
	    } );
	 
	    table.buttons().container()
	        .insertBefore( '#myTable_filter' );
	} 
</script>

<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.21/js/dataTables.jqueryui.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
   <script src="https://cdn.datatables.net/rowgroup/1.1.2/js/dataTables.rowGroup.min.js" crossorigin="anonymous"></script>
   <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.bootstrap4.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.jqueryui.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.colVis.min.js"></script>
  
</body>
</html>