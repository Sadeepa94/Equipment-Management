/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var H='....';
var H=H.split('');
var M='.....';
var M=M.split('');
var S='......';
var S=S.split('');
var Ypos=0;
var Xpos=0;
var Ybase=8;
var Xbase=8;
var dots=12;

function clock(){
var time=new Date ();
var secs=time.getSeconds();
var sec=-1.57 + Math.PI * secs/30;
var mins=time.getMinutes();
var min=-1.57 + Math.PI * mins/30;
var hr=time.getHours();
var hrs=-1.57 + Math.PI * hr/6 + Math.PI*parseInt(time.getMinutes())/360;
for (i=0; i < dots; ++i){
document.getElementById("dig" + (i+1)).style.top=0-15+40*Math.sin(-0.49+dots+i/1.9).toString() + "px";
document.getElementById("dig" + (i+1)).style.left=0-14+40*Math.cos(-0.49+dots+i/1.9).toString() + "px";
}
for (i=0; i < S.length; i++){
document.getElementById("sec" + (i+1)).style.top =Ypos+i*Ybase*Math.sin(sec).toString() + "px";
document.getElementById("sec" + (i+1)).style.left=Xpos+i*Xbase*Math.cos(sec).toString() + "px";
}
for (i=0; i < M.length; i++){
document.getElementById("min" + (i+1)).style.top =Ypos+i*Ybase*Math.sin(min).toString() + "px";
document.getElementById("min" + (i+1)).style.left=Xpos+i*Xbase*Math.cos(min).toString() + "px";
}
for (i=0; i < H.length; i++){
document.getElementById("hour" + (i+1)).style.top =Ypos+i*Ybase*Math.sin(hrs).toString() + "px";
document.getElementById("hour" + (i+1)).style.left=Xpos+i*Xbase*Math.cos(hrs).toString() + "px";
} 
$(".date").html(time.toDateString());
setTimeout('clock()',50);
}

function toggle_ssl(){
    $("#sslg").toggleClass("glyphicon-chevron-left");
    $("#sslg").toggleClass("glyphicon-chevron-right");
}

function new_transaction_init(){
    $('#myModal').modal('show');
    $('#barcode-input').select();
}
function scan_finish(){
    $('#myModal').modal('hide');
    
    var barcode= $('#barcode-input').val();
    
	$.post("getTransaction", {
		id : barcode
	}, function(data) {
		console.log(data);
		if(!data)
			$('#tr-item-code').val("inavalid meterial");
		else if(data.meterial.availability){
			$('#tr-item-code').val(data.meterial.meterial_id);
			$('#tr-item-name').val(data.meterial.meterial_name);
			$('#tr-mc').val(data.meterial.meterial_condition);
			$('#tr-dob').val(new Date().toISOString().substring(0, 10));
			$('#tr-pdor').hide();
			$('#tr-mc-lbl').html("Borrowing Condition");
		}else{
			$('#tr-id').val(data.transaction_id);
			$('#tr-item-code').val(data.meterial.meterial_id);
			$('#tr-item-name').val(data.meterial.meterial_name);
			$('#tr-user-id').val(data.user.userId);
			$('#tr-dob').val(data.borrowed_date);
			$('#tr-dor').val(new Date().toISOString().substring(0, 10));
			$('#tr-br-selecter').hide();
			$('#tr-mc').val(data.meterial.meterial_condition);
			$('#tr-mc-lbl').html("Returning Condition");
		}
	});
    
}

function home_init(){
    clock();
}

$(document).ready(function(){
    
    $('[data-toggle="tooltip"]').tooltip(); 
    
    $('.user-list-item').click(function(){
    	$.post("getUser", {
    		id : $(this).attr("id")
		}, function(data) {
			$('#uid').val(data.userId);
			$('#ujt').val(data.jobTitle);
			$('#ufname').val(data.firstName);
			$('#ulname').val(data.lastName);
			$('#ucontact').val(data.contactNo);
			$('#uaddno').val(data.address.no);
			$('#uaddstreet').val(data.address.street);
			$('#uaddtown').val(data.address.town);
			$('#uaddcity').val(data.address.city);
			$('#u_photo').html('<img src="/IM_version1/resources/images/'+ data.photo+ '"' + ' width="200px" class="img-rounded" style="padding:5px;border: 1px solid #ddd;border-radius: 4px;display: block;" />');
		});
	});
    $('.meterial-list-item').click(function(){
    	$.post("getMeterial", {
    		id : $(this).attr("id")
		}, function(data) {
			$('#mid').val(data.meterial_id);
			$('#mname').val(data.meterial_name);
			$('#mdescription').val(data.description);
			$('#mcondition').val(data.meterial_condition);
			$('#mavailability').val(data.availability);
		});
	});
    $('#change-apn').click(function(){
    	$.post("changeAdminPhone", {
    		uname : $('#admin-username').val(),
    		phone : $('#admin-pn').val()
		}, function(data) {location.reload();});
	});
    $('#change-an').click(function(){
    	$.post("changeAdminName", {
    		uname : $('#admin-username').val(),
    		fname : $('#admin-fn').val(),
    		lname : $('#admin-ln').val()
		}, function(data) {location.reload();});
	});
    $('.tr-user-list-item').click(function(){
    	$('#tr-user-id').val($(this).attr("id"));
    	$('#borrowersModal').modal('hide');
	});
    $('#delete_item').click(function(){
    	$.post("deleteMeterial", {
    		id : $('#mid').val()
		}, function(data) {location.reload();});
	});
    $('#delete_user').click(function(){
    	$.post("deleteUser", {
    		id : $('#uid').val()
		}, function(data) {location.reload();});
	});
    

   
    
});


$(document).ready(function(){
    $("form.transaction").on('submit',function(){
    	console.log("ssss");
    	var x = $("form.transaction").serialize();
    	console.log(x);
    	
       $.ajax({
        	url:'saveTransaction',
        	type:'POST',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		//console.log(data); 
        		console.log(data.status);
        		if(data.status=="Sucessfully done")
        			{
        				$("#success").text(data.status);
        				$('.transaction')[0].reset();
        			}
        		else
        			$("#success").text(data.status);
        	}
        });
        return false;
    });
});

