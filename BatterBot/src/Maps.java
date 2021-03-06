import java.net.URL;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class Maps
{

  // URL prefix for the map
  private static final String MAP_REQUEST_PREFIX_FOR_XML = "https://maps.googleapis.com/maps/api/staticmap";

  // query address
  static String address = "3333 University Way, Kelowna, BC";
  
  public void setAddress(String input_address)
  {
	  address = input_address;
  }
  
  public static final void main (String[] argv) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException 
  {

    // prepare a URL for the map
    URL url = new URL(MAP_REQUEST_PREFIX_FOR_XML + "?center=" + URLEncoder.encode(address, "UTF-8") + "&size=600x300&markers=color:blue%7C" + URLEncoder.encode(address, "UTF-8") + "&sensor=false&key=AIzaSyDJ8jbyWJzJQrrYGVN807xxnkfy7PlHEdk");

  	//System.out.println(url);
  	

            try 
            {
                System.out.println("<Dispatcher> Getting Map from " + url);
                BufferedImage image = ImageIO.read(url);
                JLabel label = new JLabel(new ImageIcon(image));
                JFrame f = new JFrame();
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(label);
                f.pack();
                f.setLocation(200, 200);
                f.setVisible(true);
            } 
            catch (Exception exp) 
            {
                exp.printStackTrace();
            }
        }
  }