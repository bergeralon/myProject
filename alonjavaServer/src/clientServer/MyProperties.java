package clientServer;

import java.io.File;
import java.io.Serializable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyProperties implements Serializable
{
	@XmlElement
	public int port;
	@XmlElement
	public int numOfClients;

	public MyProperties()
	{
		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(MyProperties.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MyProperties p = (MyProperties) jaxbUnmarshaller
					.unmarshal(new File("properties.xml"));
			port = p.port;
			numOfClients = p.numOfClients;
		} catch (JAXBException e)
		{

		}
	}

}
