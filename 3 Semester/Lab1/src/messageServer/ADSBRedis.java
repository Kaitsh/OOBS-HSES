package messageServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import redis.clients.jedis.Jedis;


/**
 * Created by danie on 1/11/2016.
 */
public class ADSBRedis implements Observer
{
    Jedis jed;
    public ADSBRedis()
    {
        jed = new Jedis("localhost", 6379);
    }
    private class Triple{
        ADSBAirbornePositionMessage posMsg;
        ADSBAirborneVelocityMessage velMsg;
        ADSBAircraftIdentificationMessage idMsg;
    }

    private HashMap<String, Triple> map = new HashMap<>();

    private void AddMessage(ADSBMessage msg)
    {
        if(msg == null)
            return;

        if (!map.containsKey(msg.getIcao()))
        {
            map.put(msg.getIcao(), new Triple());
        }
        if(msg instanceof  ADSBAirbornePositionMessage)
        {
                map.get(msg.getIcao()).posMsg = ADSBAirbornePositionMessage.class.cast(msg);
        }
        if(msg instanceof  ADSBAirborneVelocityMessage)
        {
                map.get(msg.getIcao()).velMsg = ADSBAirborneVelocityMessage.class.cast(msg);
        }
        if(msg instanceof  ADSBAircraftIdentificationMessage)
        {
                map.get(msg.getIcao()).idMsg = ADSBAircraftIdentificationMessage.class.cast(msg);
        }
    }
    private boolean CheckMessage(String icao)
    {
        return map.get(icao).posMsg != null && map.get(icao).idMsg != null && map.get(icao).velMsg != null;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        ADSBMessage message = ADSBMessage.class.cast(arg);
        if(message == null)
            return;
        AddMessage(message);

        if(!CheckMessage(message.getIcao()))
            return;

        ADSBAirbornePositionMessage posMsg = map.get(message.getIcao()).posMsg;
        ADSBAirborneVelocityMessage velMsg = map.get(message.getIcao()).velMsg;
        ADSBAircraftIdentificationMessage idMsg = map.get(message.getIcao()).idMsg;

        StringBuilder kmlString = new StringBuilder();

        kmlString.append("<Document>\r\n");
        kmlString.append("<Style id=\""); kmlString.append(message.getIcao()); kmlString.append("\">\r\n");
        kmlString.append("<IconStyle>\r\n");
        kmlString.append("<scale>0.7</scale>\r\n");
        kmlString.append("<heading>137</heading>\r\n");
        kmlString.append("<Icon>");
        kmlString.append("<href>http://localhost:3333/icon.png</href>\r\n");
        kmlString.append("</Icon>\r\n");
        kmlString.append("</IconStyle>\r\n");
        kmlString.append("</Style>\r\n");
        kmlString.append("<Placemark>\r\n");
        kmlString.append("<name>"); kmlString.append(message.getIcao()); kmlString.append("</name>\r\n");
        kmlString.append("<description>\r\n");
        kmlString.append(idMsg.getAircraftId());
            kmlString.append(" Lon: "); kmlString.append(posMsg.getCprLongitude());
            kmlString.append(" Lat: "); kmlString.append(posMsg.getCprLatitude());
            kmlString.append(" Alt: "); kmlString.append(posMsg.getAltitude());
            kmlString.append(" Dir: "); kmlString.append("137deg"); //TODO
            kmlString.append(" Vel: "); kmlString.append(velMsg.getSpeed());
            kmlString.append(" Clm: "); kmlString.append("0ft/min\r\n"); //TODO;
        kmlString.append("</description>\r\n");
        kmlString.append("<styleUrl>#"); kmlString.append(message.getIcao()); kmlString.append("</styleUrl>\r\n");
        kmlString.append("<Point>\r\n");
        kmlString.append("<coordinates>");
            kmlString.append(posMsg.getCprLongitude() + ", ");
            kmlString.append(posMsg.getCprLatitude() + ", ");
            kmlString.append(posMsg.getAltitude() + ", ");
            /*kmlString.append(0 + ", ");
            kmlString.append(0 + ", ");
            kmlString.append(0);*/
        kmlString.append("</coordinates>\r\n");
        kmlString.append("<altitudeMode>relativeToGround</altitudeMode>\r\n");
        kmlString.append("<extrude>1</extrude>\r\n");
        kmlString.append("</Point>\r\n");
        kmlString.append("</Placemark>\r\n");
        kmlString.append("</Document>\r\n");

        jed.setex(message.getIcao(),240,kmlString.toString());

        //System.out.print(jed.get(message.getIcao()));

        //in Textfile
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\Daniel\\Desktop\\TestfileKML.kml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            writer.write (kmlString.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Close writer
        try
        {
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}