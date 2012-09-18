var tooltip=function(){
	var id = 'tt';
	var top = -84;
	var left = 3;
	var maxw = 300;
	var speed = 10;
	var timer = 20;
	var endalpha = 95;
	var alpha = 0;
	var tt,t,c,b,h;
	var ie = document.all ? true : false;
	return{
		show:function(v,w){
			if(tt == null){
				tt = document.createElement('div');
				tt.setAttribute('id',id);
				t = document.createElement('div');
				t.setAttribute('id',id + 'top');
				c = document.createElement('div');
				c.setAttribute('id',id + 'cont');
				b = document.createElement('div');
				b.setAttribute('id',id + 'bot');
				tt.appendChild(t);
				tt.appendChild(c);
				tt.appendChild(b);
				document.body.appendChild(tt);
				tt.style.opacity = 0;
				tt.style.filter = 'alpha(opacity=0)';
				document.onmousemove = this.pos;
			}
			tt.style.display = 'block';
			tt.style.position = 'absolute';
			c.innerHTML = v;
			tt.style.fontSize = '11px';
			//tt.style.color = '#12097F';
			tt.style.fontWeight = 'bold';
			tt.style.backgroundColor = '#fff';
			tt.style.width = w ? w + 'px' : '200px';
			tt.style.border='1px solid #93969B';
			tt.style.padding='5px';
			if(!w && ie){
				t.style.display = 'none';
				b.style.display = 'none';
				tt.style.width = tt.offsetWidth;
				t.style.display = 'block';
				b.style.display = 'block';
			}
			if(tt.offsetWidth > maxw){tt.style.width = maxw + 'px'}
			h = parseInt(tt.offsetHeight) + top;
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(1)},timer);
		},
		pos:function(e){
			var u = ie ? event.clientY + document.documentElement.scrollTop : e.pageY;
			var l = ie ? event.clientX + document.documentElement.scrollLeft : e.pageX;					
			tt.style.top = (u - h ) + 'px';
			tt.style.left = (l + left - 220) + 'px';			
		},		
		fade:function(d){
			var a = alpha;
			if((a != endalpha && d == 1) || (a != 0 && d == -1)){
				var i = speed;
				if(endalpha - a < speed && d == 1){
					i = endalpha - a;
				}else if(alpha < speed && d == -1){
					i = a;
				}
				alpha = a + (i * d);
				tt.style.opacity = alpha * .01;
				tt.style.filter = 'alpha(opacity=' + alpha + ')';
			}else{
				clearInterval(tt.timer);
				if(d == -1){tt.style.display = 'none'}
			}
		},
		hide:function(){
			clearInterval(tt.timer);
			tt.timer = setInterval(function(){tooltip.fade(-1)},timer);
		}
	};
}();

function checkAll(obj, name){
	if(obj.checked){
		$("[name='"+name+"']").attr("checked", true);
	}else{
		$("[name='"+name+"']").attr("checked", false);
	}
}

$(document).ready(function(){
	function initSearchForm(){
		var initVal = $("#approveType").attr("initVal");
		if(initVal){
			$("#approveType option[value='"+initVal+"']").attr("selected", true);
		}
		return;
	}
	function getSelectedIds(){
		var tmpArr = [];
		var requestIdApproved=$("#requestIdApproved").val();
		if(requestIdApproved==null||requestIdApproved==""||requestIdApproved=="null"){
			$("[name='requestId']").each(function(i, n){
				if($(n).attr("checked")){
					tmpArr.push($(n).val());
				}
			});
		}else{
			tmpArr = requestIdApproved;
		}
		return tmpArr;
	}
	
	initSearchForm();
	$("#approveType").change(function(){
		$("#searchForm").trigger("submit");
	});
	
	$("#approveBtn").click(function(){
		var selectedIds = getSelectedIds();
		selectedIds = selectedIds.toString();
		if(!selectedIds){
			alert("In manager's task list ,approve without selecting any one record!");
			return;
		}
		$.ajax({
			url:"product/product!approveManageTask.action",
			type:"get",
			data:"selectIds="+selectedIds,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#approveBtn').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("Approve successfully");
					}else{
						if(data){
							alert(data.message);
						}else{
							alert("System error! Please contact system administrator for help.");
						}
					}
				}
				var requestIdApproved=$("#requestIdApproved").val();
				if(requestIdApproved==null||requestIdApproved==""||requestIdApproved=="null"){
					window.location.reload();
				}else{
					window.location.href="product/product!showManagerTaskList.action";
				}
			},
			error:function(data){
				if(data)
					alert(data.message);
				else
					alert("System error! Please contact system administrator for help.");
				window.location.reload();
			},
			async:false
		});
	});
	
	$("#rejectDialog").dialog({
		autoOpen: false,
		height: 200,
		width: 600,
		modal: true,
		bgiframe: true,
		buttons: {
		},
		open: function(){
		}
	});
	
	$("#rejectBtn").click(function(){
		var selectedIds = getSelectedIds();
		selectedIds = selectedIds.toString();
		if(!selectedIds){
			alert("Please select one item to continue your operation.");
			$("#rejectDialog").dialog("close");
			return;
		}
		var tmpVal = $("#approveType").val();
		var tmpTitle = tmpVal+" Change Request";
		$("#rejectDialog").dialog("option", "title", tmpTitle);
		$("#rejectDialog").dialog("open");
	});
	
	$("#rejectDialogConfirm").click(function(){
		var selectedIds = getSelectedIds();
		selectedIds = selectedIds.toString();
		if(!selectedIds){
			alert("Please select one item to continue your operation.");
			$("#rejectDialog").dialog("close");
			return;
		}
		var rejectReason = $("#rejectReason").val();
		rejectReason = $.trim(rejectReason);
		if(rejectReason==''||rejectReason==null){
			alert("Please enter the return.");return;
		}
		$.ajax({
			url:"product/product!rejectManageTask.action",
			type:"get",
			data:"selectIds="+selectedIds+"&rejectReason="+rejectReason,
			dataType:"json",
			success:function(data){
				if(hasException(data)){
					$('#approveBtn').attr("disabled", false);	
				}else{
					if(data.message == "success"){
						alert("Reject successfully");
					}else{
						if(data){
							alert(data.message);
						}else{
							alert("System error! Please contact system administrator for help.");
						}
					}
				}
			},
			error:function(data){
				if(data)
					alert(data.message);
				else
					alert("System error! Please contact system administrator for help.");
			},
			async:false
		});
		$("#rejectDialog").dialog("close");
		window.location.reload();
	});
	
	$("#rejectDialogCancel").click(function(){
		$("#rejectDialog").dialog("close");
	});
});