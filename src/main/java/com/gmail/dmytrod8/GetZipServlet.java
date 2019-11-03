package com.gmail.dmytrod8;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(
        name = "GetZipServlet",
        urlPatterns = {"/download"}
)
public class GetZipServlet extends HttpServlet {

    private ServerResponse srvResp = ServerResponse.getInstance();
    private final int ARBITARY_SIZE = 1048;
    static EntityManagerFactory emf;
    static EntityManager em;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String file = (String) req.getParameter("file");
        getFile(file, req, resp);

    }

    private void getFile(String name, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            emf = Persistence.createEntityManagerFactory("JPAArchive");
            em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                try {
                    Archive c = null;
                    try {
                        Query query = em.createQuery("SELECT c FROM Archive c WHERE c.name = :name", Archive.class);
                        query.setParameter("name", name);
                        c = (Archive) query.getSingleResult();
                        System.out.println(c);
                        sendFile(req, resp, c);
                    } catch (NoResultException ex) {
                        srvResp.sendResponse(resp, "response:2");
                        ex.printStackTrace();
                        //System.out.println("File not found!");
                        return;
                    } catch (NonUniqueResultException ex) {
                        srvResp.sendResponse(resp, "response:2");
                        ex.printStackTrace();
                        //System.out.println("Non unique result!");
                        return;
                    }
                } catch (Exception ex) {
                    srvResp.sendResponse(resp, "response:2");
                    ex.printStackTrace();
                    em.getTransaction().rollback();
                }
            } finally {
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            srvResp.sendResponse(resp, "response:2");
            ex.printStackTrace();
            return;
        }

    }

    private void sendFile(HttpServletRequest req, HttpServletResponse resp, Archive c) throws IOException {
        resp.setContentType("application/zip");
        resp.setHeader("Content-disposition", "attachment; filename=" + c.getName() + ".zip");

        File file = new File(c.getPath());

        try (InputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        } catch (IOException ex) {
            srvResp.sendResponse(resp, "response:2");
            ex.printStackTrace();
        }
    }
}
