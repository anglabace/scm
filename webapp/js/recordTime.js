var countTime = 7200;
function recordTime () {
  countTime --;
  if (countTime == 120) {
	  alert("For the safety and protection your work session is about to be timed out if there is no additional activity.\n"
			  +"If you are still working in your session, please click OK to continue.\n"
	  );
  } else if  (countTime == 0) {
	  window.location.href = "logout.action";
	  return;
  }
  setTimeout( "recordTime()",1000); 
}