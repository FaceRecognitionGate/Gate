package Package;
import java.io.IOException;
import java.io.PrintWriter;
import Package.RaspiStill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	
	RaspiStill camera = new RaspiStill();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	

        //content type must be set to text/event-stream
        //response.setContentType("text/event-stream"); 
        //cache must be set to no-cache
        //response.setHeader("Cache-Control", "no-cache");     
        //encoding is set to UTF-8
        //response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        for(int i=0; i<10; i++) {
            System.out.println(i);
            camera.TakePicture("image.jpg",800,600);
            writer.write("data: "+ camera +"\r\n");
            //writer.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writer.close(); 
    }
}