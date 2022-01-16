package gr.hua.dit.ds.DistributedSystems.Controllers;

import gr.hua.dit.ds.DistributedSystems.DAOs.*;
import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import gr.hua.dit.ds.DistributedSystems.Entities.Authorities;
import gr.hua.dit.ds.DistributedSystems.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@RestController
@Validated
public class UsersController {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private CiviliansDAO civiliansDAO;

    @Autowired
    private OaedDAO oaedDAO;

    @Autowired
    private OasaDAO oasaDAO;

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private ApplicationsDAO applicationsDAO;

    @Autowired
    private AuthoritiesDAO authoritiesDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/api/users/getAllUsers")
    public List<Users> getAllUsers() {
        List<Users> users = usersDAO.getAllUsers();
        return users;
    }

    @GetMapping("/api/users/getUserById")
    public Users getUserById(@RequestParam int id) {
        Users user = usersDAO.getUserById(id);

        return user;
    }

    @GetMapping("/api/users/getUserByUsername")
    public Users getUserById(@RequestParam String username) throws Exception {
        Users user = usersDAO.getUserByUsername(username);

        return user;
    }

    @GetMapping("/api/users/getUserByEmail")
    public Users getUserByEmail(@RequestParam String email) {
        Users user = usersDAO.getUserByEmail(email);

        return user;
    }

    @PostMapping("/api/signIn")
    public Users signIn(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) throws Exception {
        String auth;
        Boolean enabled = true;

        // Extract email domain
        int index1 = email.lastIndexOf("@");
        int index2 = email.lastIndexOf(".");
        String domain = email.substring(index1, index2);

        // Assign authority based on email domain
        switch (domain) {
            case "@oasa":
                auth = "ROLE_OASA";
                break;
            case "@oaed":
                auth = "ROLE_OAED";
                break;
            default:
                auth = "ROLE_CIVILIAN";
                break;
        }

        validateCredentials(username, email, auth);
        String passwordEncoded = passwordEncoder.encode(password);
        Users user = new Users(username, passwordEncoded, email, enabled);
        usersDAO.signIn(user);
        Authorities authority = new Authorities(username, auth);
        authoritiesDAO.addAuthority(authority);

        return user;
    }

    @PostMapping("/api/civilian/apply")
    public Applications apply(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                              @RequestParam String phoneNumber, @RequestParam String city, @RequestParam String address1, @RequestParam String address2,
                              @RequestParam String zipcode, @RequestParam String unemplDate, @RequestParam MultipartFile photo, String validated, String confirmed) throws Exception {
        byte[] image;

        try {
            image = compressImage(photo);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Empty value for 'Photo'.");
        }

        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new AddressException("Invalid email.");
        }

        Applications application = new Applications(id, firstName, lastName, email, phoneNumber, city, address1, address2, zipcode, unemplDate, image, validated, confirmed);
        civiliansDAO.apply(application);

        return application;
    }

    @PostMapping("/api/oaed/validateApplication")
    public Applications validateApplication(@RequestParam int id) {
        Applications application = applicationsDAO.getApplicationById(id);
        oaedDAO.validateApplication(application);

        return application;
    }

    @PostMapping("/api/oaed/rejectApplication")
    public Applications rejectApplication(@RequestParam int id) {
        Applications application = applicationsDAO.getApplicationById(id);
        oaedDAO.rejectApplication(application);

        return application;
    }

    @PostMapping("/api/oasa/confirmApplication")
    public Applications confirmApplication(@RequestParam int id) {
        Applications application = applicationsDAO.getApplicationById(id);
        oasaDAO.confirmApplication(application);

        return application;
    }

    @PostMapping("/api/oasa/rejectApplication")
    public Applications rejectValidation(@RequestParam int id) {
        Applications application = applicationsDAO.getApplicationById(id);
        oasaDAO.rejectApplication(application);

        return application;
    }

    @PostMapping("/api/admin/createUser")
    public Users createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String auth) throws Exception {
        validateCredentials(username, email, auth);
        Boolean enabled = true;
        String passwordEncoded = passwordEncoder.encode(password);
        Users user = new Users(username, passwordEncoded, email, enabled);
        adminDAO.createUser(user);
        Authorities authority = new Authorities(username, auth);
        authoritiesDAO.addAuthority(authority);

        return user;
    }

    @PostMapping("/api/admin/modifyUser")
    public Users modifyUser(@RequestParam String username, @RequestParam String newUsername, @RequestParam String newPassword,
                            @RequestParam String newEmail, @RequestParam String newAuth) throws Exception {

        if(!usersDAO.usernameExists(username))
            throw new Exception("Username doesn't exist.");

        validateCredentials(newUsername, newEmail, newAuth);
        String passwordEncoded = passwordEncoder.encode(newPassword);
        Users user = adminDAO.modifyUser(username, newUsername, passwordEncoded, newEmail, newAuth);
        authoritiesDAO.modifyAuthority(user.getId(), newUsername, newAuth);

        return user;
    }

    @PostMapping("/api/admin/deleteUser")
    public Users deleteUser(@RequestParam String username) throws Exception {
        Users user = usersDAO.getUserByUsername(username);
        adminDAO.deleteUser(user);
        Authorities authority = authoritiesDAO.getAuthorityByUsername(username);
        authoritiesDAO.deleteAuthority(authority);

        return user;
    }

    public void validateCredentials(String username, String email, String auth) throws Exception {

        if(username==null || email==null || auth==null)
            throw new Exception("Empty value.");

        if(usersDAO.usernameExists(username))
            throw new Exception("Username already in use.");


        if(usersDAO.emailExists(email))
            throw new Exception("Email already in use.");

        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new AddressException("Invalid email.");
        }

        switch (auth) {
            case "ROLE_OASA":
            case "ROLE_CIVILIAN":
            case "ROLE_OAED":
                break;
            default:
                throw new Exception("Invalid authority.");
        }

    }

    // Image compression βασισμένο εδώ: 'https://www.tutorialspoint.com/java_dip/image_compression_technique.htm'
    public byte[] compressImage(MultipartFile photo) throws IOException {

        byte[] array = photo.getBytes();
        ByteArrayOutputStream compressedImage = new ByteArrayOutputStream(array.length);
        InputStream is = new ByteArrayInputStream(array);
        BufferedImage image = ImageIO.read(is);

        Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(compressedImage);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.05f);
        writer.write(null, new IIOImage(image, null, null), param);
        System.out.println(compressedImage.size());

        ios.close();
        writer.dispose();

        return compressedImage.toByteArray();
    }
}