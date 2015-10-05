package senser;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ADSBSentence implements ADSBSentenceInterface
{
	String timestamp;
	String dfca;
	String icao;
	String payload;
	String parity;

	public ADSBSentence(String timestamp, String dfca, String icao, String payload, String parity)
	{
		This.timestamp = timestamp;
		This.dfca = dfca;
		This.icao = icao;
		This.payload = payload;
		This.parity = parity;
	}

	@Override
	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public String getDfca() {
		return dfca;
	}

	@Override
	public String getIcao() {
		return icao;
	}

	@Override
	public String getPayload() {
		return payload;
	}

	@Override
	public String getParity() {
		return parity;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setDfca(String dfca) {
		this.dfca = dfca;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public void setParity(String parity) {
		this.parity = parity;
	}

	//TODO: Overwrite toString() method to print our relevant fields
	@Override
	public String toString()
	{
		//1379574427.9127481			
		String[] times = this.getTimestamp().split("\\."); 
		
		//TODO: Define date format
		SimpleDateFormat dateFormat =...;
		//TODO: Create a DAte object
		Date date = ...;
		//Create time string
		String time = dateFormat.format(date) + "." + times[1];
		
		return "Time:\t\t " + time + "\n" +
				"Dfca:\t\t " + this.getDfca() + "\n" +
				"Originator:\t " + this.getIcao() + "\n" +
				"Payload:\t " + this.getPayload() + "\n" +
				"Parity:\t\t " + this.getParity() + "\n";
	}
}