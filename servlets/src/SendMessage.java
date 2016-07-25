

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String phone = "+" + request.getParameter("phone");
		String otp = request.getParameter("otp");
		String authToken = request.getParameter("auth");
		String from=request.getParameter("from");
		
		String otpMessage = "Hi. Your OTP is : " + otp;
		
		OkHttpClient client = new OkHttpClient();
		
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "To=" + phone + "&From=" + from + "&Body=" + otpMessage);
		Request rq = new Request.Builder()
		  .url("https://api.twilio.com/2010-04-01/Accounts/AC1b256ee94ac78c1065f433df2c9ff308/SMS/Messages")
		  .post(body)
		  .addHeader("authorization", "Basic " + authToken)
		  .addHeader("cache-control", "no-cache")
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .build();
		
		
		Response rp = client.newCall(rq).execute();
		PrintWriter pw = response.getWriter(); 
        pw.print(rp.isSuccessful());
        pw.close();
        
	}

}
