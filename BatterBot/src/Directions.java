import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class Directions
{

  // URL prefix to directions
  private static final String DIRECTIONS_REQUEST_PREFIX_FOR_XML = "https://maps.googleapis.com/maps/api/directions/xml?origin=";

  // query locations
  static String origin = "3333 University Way, Kelowna, BC";
  static String destination = "Old Vernon Road, Kelowna, BC";
  
  public void setOrigin(String input_origin)
  {
	  origin = input_origin;
  }
  
  public void setDestination(String input_destination)
  {
	  destination = input_destination;
  }
  
  public static final void main (String[] argv) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException 
  {

    // prepare a URL to the directions API
    URL url = new URL(DIRECTIONS_REQUEST_PREFIX_FOR_XML + "?origin=" + URLEncoder.encode(origin, "UTF-8") + "&destination=" + URLEncoder.encode(destination, "UTF-8") + "&sensor=false&key=AIzaSyDJ8jbyWJzJQrrYGVN807xxnkfy7PlHEdk");

  	//System.out.println(url);
    
    // prepare an HTTP connection to the directions API
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    Document directionsResultDocument = null;
    try 
    {
      // open the connection and get results as InputSource.
      conn.connect();
      InputSource directionsResultInputSource = new InputSource(conn.getInputStream());

      // read result and parse into XML Document
      directionsResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(directionsResultInputSource);
    } 
    finally 
    {
      conn.disconnect();
    }

    // prepare XPath
    XPath xpath = XPathFactory.newInstance().newXPath();

    // extract the result
    NodeList resultNodeList = null;
   
    String directions = "";
    for(int j=1;j<=15;j++)
    {
    	// obtain the html_instructions field for every result
    	resultNodeList = (NodeList) xpath.evaluate("/DirectionsResponse/route/leg/step[" + j + "]/html_instructions", directionsResultDocument, XPathConstants.NODESET);
   		for(int i=0; i<resultNodeList.getLength(); ++i) 
   		{
   			directions += resultNodeList.item(i).getTextContent();
   		}
    } 
    
    directions = directions.replace("<b>", " ");
    directions = directions.replace("</b>", " ");
  
    System.out.println("<Dispatcher> " + directions);

  }
}

