$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	{
	$("#alertError").text(status);
	$("#alertError").show();
	return;
	}
	
	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "UserAPI",
	type : type,
	data : $("#formUser").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onItemSaveComplete(response.responseText, status);
	}
	});
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).data("hidItemIDUpdate"));
	$("#username").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#institue").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#phone").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#password").val($(this).closest("tr").find('td:eq(4)').text());
});

//UPDATE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "UserAPI",
		type : "DELETE",
		data : "hidItemIDSave=" + $(this).data("hidItemIDUpdate"),
		dataType : "text",
		complete : function(response, status)
		{
			onItemDeleteComplete(response.responseText, status);
		}
	});
});


//CLIENT-MODEL================================================================
function validateItemForm()
{
	// username
	if ($("#username").val().trim() == "") 
	 { 
	 return "Insert Item username."; 
	 } 

	// insititue-----
	if ($("#institue").val().trim() == "") 
	 { 
	 return "Insert Item institue."; 
	 }

	// phone-------------------------------
	if ($("#phone").val().trim() == "") 
	{ 
	return "Insert Item phone."; 
	}

	// email.........
	if ($("#email").val().trim() == "") 
	{ 
	return "Insert Item email."; 
	}
	// password.........
	if ($("#password").val().trim() == "") 
	{ 
	return "Insert Item password."; 
	}
		return true;
}



function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
	}
		$("#hidItemIDSave").val("");
		$("#formUser")[0].reset();
}



function onItemDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		} else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} else
		{
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
	}
}



