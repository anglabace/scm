/*
<script type="text/javascript" src="move_li.js"></script>
使用说明：
1：四个参数需要在JavaScript里面配置
var left_div_ID='div_move_left';  左边div id
var right_div_ID='div_move_right'; 右边div id
var li_wait_move_class_name ='wait_move'; 选中状态的li的className
var li_normal_class_name ='normal_li';没有选中状态的li的className
2：注意严格按照如下的格式
<div> (需要写ID eg:div_move_left)
	<ul>
		<li>test_001
			<ul>
				<li>move test 001<li>
				<li>move test 002<li>
				<li>move test 003<li>
			</ul>
		</li>
		<li>test_002
			<ul>
				<li>move test 004<li>
				<li>move test 005<li>
				<li>move test 006<li>
			</ul>
		</li>
	<ul>
</div> 
4: 四个方法：
1) onclick="moveall()"  -----> 全体从左边到右边
2) onclick="moveall('rtol')"  -----> 全体从右边到左边
3) onclick="move()"  -----> 被选中的右移 (可以不止一个的移动)
4) onclick="move('rtol')"  -----> 被选中的左移 (可以不止一个的移动) 
5：缺点:
1)li 文字不能重复, 
2)同级别li之间没有先后顺序关系
*/
/*------------config------------ */
var left_div_ID='div_move_left';
var right_div_ID='div_move_right';
var li_wait_move_class_name ='wait_move';
var li_normal_class_name ='normal_li';
/*------------config End------- */
function move(direction)
{ 
	if(direction == 'rtol')
	{
		var tmp=left_div_ID;
		left_div_ID = right_div_ID;
		right_div_ID=tmp;
	}
	var ele_left = document.getElementById(left_div_ID);
	var ele_right = document.getElementById(right_div_ID); 
	//var LeftAllMoveLI = ele_left.getElementsByClassName(li_wait_move_class_name);
	var LeftAllMoveLI = ele_left.getElementsByTagName('li'); 
	var RightAllMoveLI = ele_right.getElementsByTagName('li'); 
	var move_li_arr = new Array();
	var move_li_father_arr = new Array();
	for (var i=0;i < LeftAllMoveLI.length;i++)
	{ 
		if(LeftAllMoveLI[i].className !=li_wait_move_class_name)continue;
		var li_text = LeftAllMoveLI[i].firstChild.nodeValue;
		var father_li_text = LeftAllMoveLI[i].parentNode.parentNode.firstChild.nodeValue;
		//alert(li_text);
		move_li_arr.push(li_text);
		//alert('father: '+father_li_text);
		move_li_father_arr.push(father_li_text);
		var select_li = LeftAllMoveLI[i];
		var father_li = LeftAllMoveLI[i].parentNode; 
		LeftAllMoveLI[i].parentNode.removeChild(select_li);//remove 
		if(father_li.getElementsByTagName('li').length==0){  
			var big_li = father_li.parentNode; 
			big_li.removeChild(father_li); 
			big_li.parentNode.removeChild(big_li);   
		}
		i--;
	}
	var len = move_li_arr.length;
	for (var j=0;j<len;j++)
	{
		var big_li_text = move_li_father_arr[j];
		var li_text = move_li_arr[j];
		//alert(big_li_text +' '+li_text);
		var needbreak = 0;
		for (var c=0;c<RightAllMoveLI.length;c++)
		{
			if(RightAllMoveLI[c].firstChild.nodeValue == big_li_text)
			{
				var uls = RightAllMoveLI[c].getElementsByTagName('ul'); 
				ul = uls[0];
				var new_li = document.createElement('li');
				new_li.innerHTML=li_text;
				new_li.calssName=li_normal_class_name;
				new_li.onclick=change_class_name;
				new_li.onmouseover=mouseover;
				new_li.onmouseout=mouseout;
				ul.appendChild(new_li);
				needbreak = 1;
				break;
			}
		}
		if(needbreak) continue;
		var new_big_li = document.createElement('li');
		new_big_li.innerHTML=big_li_text+'<ul><li class="'+li_normal_class_name+'" onclick="change_class_name(event)" onmouseover="mouseover(event)" onmouseout="mouseout(event)">'+li_text+'</li></ul>';
		var RightAllUL = ele_right.getElementsByTagName('ul'); 
		if(RightAllUL.length==0)
		{
			bigestUL = document.createElement('ul'); 
			bigestUL.appendChild(new_big_li);
			ele_right.appendChild(bigestUL);
		}else
		{
			RightAllUL[0].appendChild(new_big_li);
		}  
	}
	move_li_father_arr=new Array();
	move_li_arr=new Array();
	if(direction == 'rtol')
	{
		var tmp=left_div_ID;
		left_div_ID = right_div_ID;
		right_div_ID=tmp;
	}
}
if (document.all){ 
 window.attachEvent("onload",addListen);
} 
else{ 
 window.addEventListener('load',addListen,false); 
}
function addListen()
{
	var ele_left = document.getElementById(left_div_ID);
	var ele_right = document.getElementById(right_div_ID);
	var all_left_li = ele_left.getElementsByTagName('li');
	var all_right_li = ele_right.getElementsByTagName('li');
	for (var i=0;i<all_left_li.length;i++)
	{ 
		var child = all_left_li[i].getElementsByTagName('ul'); 
		if(child.length) continue;
		all_left_li[i].onclick=change_class_name;
		all_left_li[i].onmouseover=mouseover;
		all_left_li[i].onmouseout=mouseout;
	}
	for (var i=0;i<all_right_li.length;i++)
	{ 
		var child = all_right_li[i].getElementsByTagName('ul'); 
		if(child.length) continue;
		all_right_li[i].onclick=change_class_name;
		all_right_li[i].onmouseover=mouseover;
		all_right_li[i].onmouseout=mouseout;
	}
}
function change_class_name(e)
{
	var e=e?e:window.event;
	e = e.srcElement || e.target; 
	if(e.className == li_wait_move_class_name)
	{
		e.className=li_normal_class_name;
	}else
	{
		e.className=li_wait_move_class_name;
	} 
	//cancelbubble=true;
}
function moveall(direction)
{
	if(direction == 'rtol')
	{
		var tmp=left_div_ID;
		left_div_ID = right_div_ID;
		right_div_ID=tmp;
	}
	var ele_left = document.getElementById(left_div_ID); 
	var all_left_li = ele_left.getElementsByTagName('li'); 
	for (var i=0;i<all_left_li.length;i++)
	{ 
		var child = all_left_li[i].getElementsByTagName('ul'); 
		if(child.length) continue;
		all_left_li[i].className=li_wait_move_class_name;
	}
	if(direction == 'rtol')
	{
		var tmp=left_div_ID;
		left_div_ID = right_div_ID;
		right_div_ID=tmp;
	}
	move(direction);
} 
function mouseover(e)
{
	var e=e?e:window.event;
	e = e.srcElement || e.target; 
	e.style.fontWeight="bold";
}
function mouseout(e)
{
	var e=e?e:window.event;
	e = e.srcElement || e.target; 
	e.style.fontWeight="normal";
}
