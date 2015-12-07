package messageServer;

import messageServer.Interfaces.ADSBMessageServerObserverInterface;

import java.util.*;

/**
 * Created by danie on 11/17/2015.
 */
public class ADSBMessageMap extends ADSBMessageServerObserverInterface
{
    public enum MsgType {positionMessage, identificationMessage, velocityMessage};

    private HashMap <String, List<ADSBMessage>> icaoMap;
    private static final ADSBMessageMap myMap = new ADSBMessageMap();


    private ADSBMessageMap()
    {
        icaoMap = new HashMap <String, List<ADSBMessage>>();
    }
    public static ADSBMessageMap getInstance () {return myMap;}

    @Override
    public void update(Observable o, Object arg)
    {
        ADSBMessage message = ADSBMessage.class.cast(arg);
        if(message == null)
            return;

        List <ADSBMessage> myList;

        if (icaoMap.containsKey(message.getIcao()))
        {
            myList = icaoMap.get(message.getIcao());
            if (myList.size()==11)
            {
                ListIterator myiterator = myList.listIterator();
                //Iterator auf erstes Element bewegen
                myiterator.next();
                myiterator.remove();
            }

        }
        else
        {
            myList = new ArrayList<ADSBMessage>();
        }

        myList.add(message);
        icaoMap.put(message.getIcao(),myList);
    }

    public List<ADSBMessage> getMessagetype(String icao, MsgType type)
    {
        List<ADSBMessage> tmpList = icaoMap.get(icao);
        List<ADSBMessage> filteredList = new ArrayList<ADSBMessage>();

        for(ADSBMessage element : tmpList)
        {
            if (MsgType.class.cast(element.getMsgType()) == type);
                filteredList.add(element);
        }

        return filteredList;
    }

    public ADSBMessage getLastMessageOfType(String icao, MsgType type)
    {
        ADSBMessage message = null;
        for(ADSBMessage element : tmpList)
        {
            if (MsgType.class.cast(element.getMsgType()) == type);
             message = element;
        }

        return message;
    }

    public List<String> getFlights()
    {
        List<String> keys = new ArrayList<String>();
        for(String key : icaoMap.keySet())
        {
            keys.add(key);
        }
        return keys;
    }
}
