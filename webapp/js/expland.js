function cleanWhitespace(element) {   
 //����element���ӽ��   
 for (var i = 0; i < element.childNodes.length; i++) {   
  var node = element.childNodes[i];   
  //�ж��Ƿ��ǿհ��ı���㣬����ǣ���ɾ���ý��   
  if (node.nodeType == 3 && !/\S/.test(node.nodeValue))    
  node.parentNode.removeChild(node);   
 }   
}   
//��ñ�����   
var _table=document.getElementById("table1");   
//cleanWhitespace(_table);   
//ʹ��������ƣ����ղ���Ϊ���Ӷ���   
function moveUp(_a){   
 //ͨ�����Ӷ����ȡ����е�����   
 var _row=_a.parentNode.parentNode;   
 //������ǵ�һ�У�������һ�н���˳��   
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
//ʹ��������ƣ����ղ���Ϊ���Ӷ���   
function moveDown(_a){   
 //ͨ�����Ӷ����ȡ����е�����   
 var _row=_a.parentNode.parentNode;   
 //����������һ�У�������һ�н���˳��   
 if(_row.nextSibling)swapNode(_row,_row.nextSibling);   
}   
//����ͨ�õĺ���������������λ��   
function swapNode(node1,node2){   
 //��ȡ�����   
 var _parent=node1.parentNode;   
 //��ȡ�����������λ��   
 var _t1=node1.nextSibling;   
 var _t2=node2.nextSibling;   
 //��node2���뵽ԭ��node1��λ��   
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
 //��node1���뵽ԭ��node2��λ��   
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
