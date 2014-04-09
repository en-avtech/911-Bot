import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class Places
{

  // URL prefix to places
  private static final String PLACES_REQUEST_PREFIX_FOR_XML = "http://maps.googleapis.com/maps/api/place/textsearch/xml";

  // query address
  static String search = "1600 Amphitheatre Parkway, Mountain View, CA";
  
  public void setSearch(String input_search)
  {
	  search = input_search;
  }
  
  public static final void main (String[] argv) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException 
  {

    // prepare a URL to the places API
    URL url = new URL(PLACES_REQUEST_PREFIX_FOR_XML + "?query=" + URLEncoder.encode(search, "UTF-8") + "&sensor=false");

    // prepare an HTTP connection to the places API
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    Document placesResultDocument = null;
    try 
    {
      // open the connection and get results as InputSource.
      conn.connect();
      InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());

      // read result and parse into XML Document
      placesResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
    } 
    finally 
    {
      conn.disconnect();
    }

    // prepare XPath
    XPath xpath = XPathFactory.newInstance().newXPath();

    // extract the result
    NodeList resultNodeList = null;

//    // b) extract the locality for the first result
//    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/address_component[type/text()='locality']/long_name", geocoderResultDocument, XPathConstants.NODESET);
//    for(int i=0; i<resultNodeList.getLength(); ++i) {
//      System.out.println(resultNodeList.item(i).getTextContent());
//    }

    // c) extract the coordinates of the first result
    resultNodeList = (NodeList) xpath.evaluate("/PlaceSearchResponse/result[1]/name/*", placesResultDocument, XPathConstants.NODESET);
    String name = "";
    for(int i=0; i<resultNodeList.getLength(); ++i) {
      Node node = resultNodeList.item(i);
      if(name.equals(node.getNodeName())) name = (node.getTextContent());
      //if("lng".equals(node.getNodeName())) lng = Float.parseFloat(node.getTextContent());
    }
    System.out.print("The nearest place is: " + name + " at ");
    
    
    // a) obtain the formatted_address field for every result
    resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result/formatted_address", placesResultDocument, XPathConstants.NODESET);
    for(int i=0; i<resultNodeList.getLength(); ++i) 
    {
      System.out.print(resultNodeList.item(i).getTextContent());
    }

  }

}