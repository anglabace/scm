package com.genscript.gsscm.common;

import java.io.IOException;
import java.io.PrintWriter;
import com.genscript.gsscm.common.util.Struts2Util;
import com.genscript.gsscm.ws.WSException;

public class ExceptionOut {
		/**
		 * 打印错误信息的方法
		 * @param messageDetail
		 * @throws IOException
		 */
        public static void printException(WSException exception){   
			PrintWriter out = null;
			try {
				out = Struts2Util.getResponse().getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String ret = "<script>alert('"+exception.getMessageDetail()+ ", " + exception.getAction()+"');</script>";
			out.print(ret);
        }   
}
