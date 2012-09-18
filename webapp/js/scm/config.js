
// this variable is used to set which window will be 
// used to hold jQuery data 
// this frame must exist all the time
var dataHolderWin;
if(top.frames['mainFrame'] && top.frames['mainFrame'].frames['srchCustAct_iframe'])
{
	dataHolderWin = top.frames['mainFrame'].frames['srchCustAct_iframe'];
}
else if(top.frames['mainFrame'])
{
	dataHolderWin = top.frames['mainFrame'];
}
else if(top){
	dataHolderWin = top.window;
}
else
{
	
}
