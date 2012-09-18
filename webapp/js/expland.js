function cleanWhitespace(element) {   
 //遍历element的子结点   
 for (var i = 0; i < element.childNodes.length; i++) {   
  var node = element.childNodes[i];   
  //判断是否是空白文本结点，如果是，则删除该结点   
  if (node.nodeType == 3 && !/\S/.test(node.nodeValue))    
  node.parentNode.removeChild(node);   
 }   
}   
//获得表格对象   
var _table=document.getElementById("table1");   
//cleanWhitespace(_table);   
//使表格行上移，接收参数为链接对象   
function moveUp(_a){   
 //通过链接对象获取表格行的引用   
 var _row=_a.parentNode.parentNode;   
 //如果不是第一行，则与上一行交换顺序   
 if(_row.previousSibling.previousSibling)swapNode(_row,_row.previousSibling);   
}   
function moveUp1(){   
	var   a   =   document.getElementsByName("mm");  
    for   (var   i=0;   i<a.length;   i++) 
	{	
		if(a[i].checked)
		{
			if(a[i].parentNode.parentNode.previousSibling.previousSibling) {swapNode(a[i].parentNode.parentNode,a[i].parentNode.parentNode.previousSibling); 
			}
			else{
			break;
			}
		}  
	}
}
function moveDown1(){   
	var   a   =   document.getElementsByName("mm");  
    for   (var i=a.length-1;   i>=0; i--) 
	{	
		if( a[i].checked)
		{
			if(a[i].parentNode.parentNode.nextSibling)
			{		
				
				swapNode(a[i].parentNode.parentNode,a[i].parentNode.parentNode.nextSibling); 
			}else{break;}
		}  
	}
}
//使表格行下移，接收参数为链接对象   
function moveDown(_a){   
 //通过链接对象获取表格行的引用   
 var _row=_a.parentNode.parentNode;   
 //如果不是最后一行，则与下一行交换顺序   
 if(_row.nextSibling)swapNode(_row,_row.nextSibling);   
}   
//定义通用的函数交换两个结点的位置   
function swapNode(node1,node2){   
 //获取父结点   
 var _parent=node1.parentNode;   
 //获取两个结点的相对位置   
 var _t1=node1.nextSibling;   
 var _t2=node2.nextSibling;   
 //将node2插入到原来node1的位置   
 if(_t1 ){
 //alert(_t1.childNodes[0].childNodes[0].checked)
	//if(!_t1.childNodes[0].childNodes[0].checked)
	_parent.insertBefore(node2,_t1);  
	
//node2.childNodes[0].childNodes[3].checked='true';	
 }
 else 
 {_parent.appendChild(node2);   
 
// node2.childNodes[0].childNodes[3].checked='true';	
 }
 //将node1插入到原来node2的位置   
 if(_t2){
 //alert(_t1.childNodes[0].childNodes[0].checked)
	// if(!_t2.childNodes[0].childNodes[0].checked)
		_parent.insertBefore(node1,_t2);  
 
	node1.childNodes[0].childNodes[0].checked='true';	
 }
 else{ _parent.appendChild(node1);  
 
node1.childNodes[0].childNodes[0].checked='true';	 
 }
}   
