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
	public String program_name;
	@XmlElement
	public String ip;
	@XmlElement
	public int port;

	public MyProperties()
	{
		JAXBContext jaxbContext;
		try
		{
			jaxbContext = JAXBContext.newInstance(MyProperties.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			MyProperties p = (MyProperties) jaxbUnmarshaller
					.unmarshal(new File("properties.xml"));
			ip = p.ip;
			port = p.port;
			program_name = p.program_name;
		} catch (JAXBException e)
		{

		}
	}

}
