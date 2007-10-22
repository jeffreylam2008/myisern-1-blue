package edu.hawaii.myisern.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.researchers.jaxb.Researchers;

/**
 * Provides code for marshalling JAXB related classes into XML files.
 * 
 * @author Philip Johnson
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
 */
public class MyIsernXmlSaver {

  // Code inspired by MyIsern-1-Green. Thanks guys.
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext collaborationsJaxbContext;
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext organizationsJaxbContext;
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext researchersJaxbContext;

  /**
   * Initializes this instance.
   * 
   * @throws JAXBException if problems exists.
   */
  public MyIsernXmlSaver() throws JAXBException {
    String jaxbContentString = "edu.hawaii.myisern.collaborations.jaxb";
    this.collaborationsJaxbContext = JAXBContext.newInstance(jaxbContentString);
    jaxbContentString = "edu.hawaii.myisern.organizations.jaxb";
    this.organizationsJaxbContext = JAXBContext.newInstance(jaxbContentString);
    jaxbContentString = "edu.hawaii.myisern.researchers.jaxb";
    this.researchersJaxbContext = JAXBContext.newInstance(jaxbContentString);
  }

  /**
   * Saves researchers to an xml file.
   * 
   * @param researchers List of researchers to save.
   * @throws JAXBException if problems exists.
   * @throws FileNotFoundException if problems exists.
   */
  public void saveResearchersXml(Researchers researchers) throws JAXBException,
      FileNotFoundException {
    String currentWorkingDirectory = System.getProperty("user.dir");
    String researcherFilePath = currentWorkingDirectory + "/xml/examples/researchers.save.xml";
    Marshaller marshaller = this.researchersJaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(researchers, new FileOutputStream(researcherFilePath));
  }

  /**
   * Saves collaborations to an xml file.
   * 
   * @param collaboraions List of collaborations to save.
   * @throws JAXBException if problems exists.
   * @throws IOException if problems exists.
   */
  public void saveCollaboratotionsXml(Collaborations collaboraions) throws JAXBException,
      IOException {
    String currentWorkingDirectory = System.getProperty("user.dir");
    String collabFilePath = currentWorkingDirectory + "/xml/examples/collaborations.save.xml";
    Marshaller marshaller = this.collaborationsJaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(collaboraions, new FileOutputStream(collabFilePath));
  }

  /**
   * Saves organizations to an xml file.
   * 
   * @param organizations List of organizations to save.
   * @throws JAXBException if problems exists.
   * @throws IOException if problems exists.
   */
  public void saveOrganizationsXml(Organizations organizations) throws JAXBException, IOException {
    String currentWorkingDirectory = System.getProperty("user.dir");
    String orgFilePath = currentWorkingDirectory + "/xml/examples/organizations.save.xml";
    Marshaller marshaller = this.organizationsJaxbContext.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(organizations, new FileOutputStream(orgFilePath));
  }
}
