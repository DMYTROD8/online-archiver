package com.gmail.dmytrod8;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@WebServlet(
        name = "FilesToZipServlet",
        urlPatterns = {"/upload"}
)
public class FilesToZipServlet extends HttpServlet {

    static EntityManagerFactory emf;
    static EntityManager em;
    private ServerResponse srvResp = ServerResponse.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            srvResp.sendResponse(resp, "response:2");
            return;
        }

        parseRequest(req, resp);
    }


    private void parseRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<FileItem> uploadedFiles = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            uploadedFiles = upload.parseRequest(req);
            parseFiles(req, resp, repository, uploadedFiles);
        } catch (FileUploadException e) {
            srvResp.sendResponse(resp, "response:2");
            e.printStackTrace();
        }

    }

    private void parseFiles(HttpServletRequest req, HttpServletResponse resp, File repository,
                            List<FileItem> uploadedFiles) throws IOException {
        List<String> fileNames = new ArrayList<>();

        String millis = String.valueOf(System.currentTimeMillis());
        String zipName = repository + "\\" + millis + ".zip";
        FileOutputStream fos = new FileOutputStream(zipName);

        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (FileItem file : uploadedFiles) {
            String fileName = file.getName();
            InputStream input = file.getInputStream();
            byte[] strToBytes = IOUtils.toByteArray(input);
            File fileToZip = new File(fileName);
            fileNames.add(fileName);

            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            zipOut.write(strToBytes, 0, strToBytes.length);
            zipOut.closeEntry();
        }

        zipOut.close();

        String fileNamesToString = String.join(",", fileNames);
        addArchive(millis, fileNamesToString, zipName, resp);

    }


    private void addArchive(String zipName, String filesInside, String zipPath, HttpServletResponse resp) throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("JPAArchive");
            em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                try {
                    Archive c = new Archive(zipName, filesInside, zipPath);
                    int id = c.getId();
                    em.persist(c);
                    em.getTransaction().commit();
                    srvResp.sendResponse(resp, "response:0", "link:" + zipName);
                    //viewClients();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    em.getTransaction().rollback();
                    srvResp.sendResponse(resp, "response:2");
                }
            } finally {
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void viewClients() {
        Query query = em.createQuery("SELECT c FROM Archive c", Archive.class);
        List<Archive> list = (List<Archive>) query.getResultList();

        for (Archive c : list)
            System.out.println(c);
    }


    private void getAllArchives(HttpServletResponse resp) throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("JPAArchive");
            em = emf.createEntityManager();

            try {
                Query query = em.createQuery("SELECT c FROM Archive c", Archive.class);
                List<Archive> archivesList = (List<Archive>) query.getResultList();
                if (archivesList.size() > 0) {
                    final JsonElement jelement = toJSON((ArrayList<Archive>) archivesList);
                    srvResp.sendResponse(resp, 0, jelement);
                } else {
                    srvResp.sendResponse(resp, "response:2");
                }
            } finally {
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private JsonElement toJSON(ArrayList<Archive> archivesList) {
        return (JsonElement) new Gson().toJsonTree(new JsonArchives(archivesList));

    }
}