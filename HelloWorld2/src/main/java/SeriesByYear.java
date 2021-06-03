

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class SeriesbyYear
 */
@WebServlet("/SeriesByYear")
public class SeriesByYear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeriesByYear() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	 	response.setContentType("application/json");
	 	response.setCharacterEncoding("UTF-8");
	 	JSONObject myResponse = new JSONObject();
	 	
	 	JSONArray PeliculasActor = new JSONArray();
	 	
	 	String myActor = request.getParameter("year_1");
	 	String myActor2 = request.getParameter("year_2");
	 	 try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "Proyecto", "Prueba1234" ) )
	        {
			 	LinkedList<String> myactors = greeter.getSeriesByYear(myActor, myActor2);
			 	
			 	for (int i = 0; i < myactors.size(); i++) {
			 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
			 		PeliculasActor.add(myactors.get(i));
			 	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 	myResponse.put("conteo", PeliculasActor.size()); //Guardo la cantidad de actores
	 	myResponse.put("series", PeliculasActor);
	 	out.println(myResponse);
	 	out.flush();  
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
