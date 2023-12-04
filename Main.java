package main;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Main {
    public static void main(String[] args) {
        String xmlFilePath = "mascotas.xml";
        List<Mascota> mascotas = leerXML(xmlFilePath);

        // Imprimir la información de las mascotas
        for (Mascota mascota : mascotas) {
            System.out.println("ID: " + mascota.getId());
            System.out.println("Nombre: " + mascota.getNombre());
            System.out.println("Especie: " + mascota.getEspecie());
            System.out.println("Sexo: " + mascota.getSexo());
            System.out.println("Edad: " + mascota.getEdad());
            System.out.println("Citas:");
            for (Cita cita : mascota.getCitas()) {
                System.out.println("\tFecha: " + cita.getFecha() + ", Motivo: " + cita.getMotivo());
            }
            System.out.println("-----------------------");
        }
    }

    private static List<Mascota> leerXML(String xmlFilePath) {
        List<Mascota> mascotas = new ArrayList<>();

        try {
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList mascotaList = doc.getElementsByTagName("mascota");

            for (int i = 0; i < mascotaList.getLength(); i++) {
                Node mascotaNode = mascotaList.item(i);

                if (mascotaNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element mascotaElement = (Element) mascotaNode;

                    int id = Integer.parseInt(mascotaElement.getElementsByTagName("id").item(0).getTextContent());
                    String nombre = mascotaElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String especie = mascotaElement.getElementsByTagName("especie").item(0).getTextContent();
                    String sexo = mascotaElement.getElementsByTagName("sexo").item(0).getTextContent();
                    int edad = Integer.parseInt(mascotaElement.getElementsByTagName("edad").item(0).getTextContent());

                    List<Cita> citas = new ArrayList<>();
                    NodeList citaList = mascotaElement.getElementsByTagName("cita");
                    for (int j = 0; j < citaList.getLength(); j++) {
                        Node citaNode = citaList.item(j);
                        if (citaNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element citaElement = (Element) citaNode;
                            String fechaStr = citaElement.getElementsByTagName("fecha").item(0).getTextContent();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date fecha = sdf.parse(fechaStr);
                            String motivo = citaElement.getElementsByTagName("motivo").item(0).getTextContent();
                            citas.add(new Cita(fecha, motivo));
                        }
                    }

                    mascotas.add(new Mascota(id, nombre, especie, sexo, edad, citas));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mascotas;
    }
}