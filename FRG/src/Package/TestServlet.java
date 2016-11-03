package Package;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import Package.RaspiStill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/photo")
public class TestServlet extends HttpServlet {
	
	RaspiStill camera = new RaspiStill();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter writer = response.getWriter();

        camera.TakePicture("image.png",800,600);
        writer.write("data: "+ camera +"\r\n");
        writer.flush();
        writer.close();

		String FileName = "image.png";
		String contextPath = getServletContext().getRealPath(File.separator);
		File pngFile = new File("/opt/apache-tomcat-9.0.0.M11/bin/" + FileName);

		response.setContentType("image/png");
		response.addHeader("Content-Disposition", "attachment; filename=" + FileName);
		response.setContentLength((int) pngFile.length());
		
		FileInputStream fileInputStream = new FileInputStream(pngFile);
		OutputStream responseOutputStream = response.getOutputStream();
		int bytes;
		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
		}
		responseOutputStream.close();
    }
}