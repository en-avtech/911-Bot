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

public class Image
{

  // URL prefix to the geocoder
  private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "https://maps.googleapis.com/maps/api/streetview";

  // query address
  static String address = "3333 University Way, Kelowna, BC";
  
  public void setAddress(String input_address)
  {
	  address = input_address;
  }
  
  public static final void main (String[] argv) throws IOException, XPathExpressionException, ParserConfigurationException, SAXException 
  {

    // prepare a URL to the geocoder
    URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?size=600x300" + "&location=" + URLEncoder.encode(address, "UTF-8") + "&sensor=false&key=AIzaSyDJ8jbyWJzJQrrYGVN807xxnkfy7PlHEdk");

  	//System.out.println(url);
  	

            try 
            {
                System.out.println("<Dispatcher> Getting Image from " + url);
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
