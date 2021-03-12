import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetLab_form
 */
@WebServlet("/doCheckID")
public class Check_ID extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//建議加上這行轉utf-8
		String Id=request.getParameter("id");
		Id = Id.toUpperCase();
		int total=0,n1=0;
       	String idchk = "0123456789ABCDEFGHJKLMNPQRSTUVXYWZIO"; 
        //首字字元的加權值
        n1=idchk.indexOf(Id.charAt(0));
        total=n1/10+(n1%10)*9;
        //total=total+其他字元的加權值
        for(int i=1;i<9;i++) {
        	total+=idchk.indexOf(Id.charAt(i))*(9-i);
        }
       total+=idchk.indexOf(Id.charAt(9));
        if(total % 10 != 0){
        	RequestDispatcher dispatcher=request.getRequestDispatcher("errorPage.html");
		    dispatcher.forward(request, response);
        }
		response.setContentType("text/html;charset=utf-8");//建議加上這行轉utf-8
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>個人資料</title></head>");
		out.println("<body>");
		out.print("<h2>身分證字號格式正確</h2>");
		out.print("<h1>"+Id+"</h1>");
		out.print("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}