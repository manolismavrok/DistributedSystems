package gr.hua.dit.ds.DistributedSystems.Controllers;

import gr.hua.dit.ds.DistributedSystems.DAOs.*;
import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import gr.hua.dit.ds.DistributedSystems.Entities.Authorities;
import gr.hua.dit.ds.DistributedSystems.Entities.Users;
import gr.hua.dit.ds.DistributedSystems.Entities.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    private ValidationsDAO validationsDAO;

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
    public Users getUserById(@RequestParam String username) {
        Users user = usersDAO.getUserByUsername(username);

        return user;
    }

    @GetMapping("/api/users/getUserByEmail")
    public Users getUserByEmail(@RequestParam String email) {
        Users user = usersDAO.getUserByEmail(email);

        return user;
    }

    @PostMapping("/api/signIn")
    public Users signIn(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
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
                              @RequestParam String zipcode, @RequestParam String unemplDate, @RequestParam String photo, String validated, String confirmed) {

        Applications application = new Applications(id, firstName, lastName, email, phoneNumber, city, address1, address2, zipcode, unemplDate, photo, validated, confirmed);
        civiliansDAO.apply(application);

        return application;
    }

    @PostMapping("/api/oaed/validateApplication")
    public Validations validateApplication(@RequestParam int id) {
        Applications app = applicationsDAO.getApplicationById(id);
        app.setValidated("true");
        Validations validation = new Validations(id, app.getFirstName(), app.getLastName(), app.getEmail(), app.getPhoneNumber(), app.getCity(),
                app.getAddress1(), app.getAddress2(), app.getZip(), app.getUnemplDate(), app.getPhoto(), app.getValidated(), app.getConfirmed());
        oaedDAO.validate(validation);

        return validation;
    }

    @PostMapping("/api/oaed/rejectApplication")
    public Applications rejectApplication(@RequestParam int id) {
        Applications application = applicationsDAO.getApplicationById(id);
        oaedDAO.reject(application);

        return application;
    }

    @PostMapping("/api/oasa/confirmValidation")
    public Validations confirm(@RequestParam int id) {
        Validations validation = validationsDAO.getValidationsById(id);
        validation.setConfirmed("true");

        return validation;
    }

    @PostMapping("/api/oasa/rejectValidation")
    public Validations rejectValidation(@RequestParam int id) {
        Validations validation = validationsDAO.getValidationsById(id);
        oasaDAO.reject(validation);

        return validation;
    }

    @PostMapping("/api/admin/createUser")
    public Users createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String auth) {
        Boolean enabled = true;
        Users user = new Users(username, password, email, enabled);
        adminDAO.createUser(user);
        Authorities authority = new Authorities(username, auth);
        authoritiesDAO.addAuthority(authority);

        return user;
    }

    @PostMapping("/api/admin/modifyUser")
    public Users modifyUser(@RequestParam int id, @RequestParam String newUsername, @RequestParam String newPassword,
                            @RequestParam String newEmail, @RequestParam String newAuth) {
        Users user = adminDAO.modifyUser(id, newUsername, newPassword, newEmail, newAuth);
        authoritiesDAO.modifyAuthority(newUsername, newAuth);

        return user;
    }

    @PostMapping("/api/admin/deleteUser")
    public Users deleteUser(@RequestParam int id) {
        Users user = usersDAO.getUserById(id);
        adminDAO.deleteUser(user);

        return user;
    }

    /*
    @GetMapping("/")
    public String login() {

        return "login";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "sign-in";
    }

    @GetMapping("/civilian")
    public String civilianUI(@RequestParam String id, Model model) {
        Users user = usersDAO.getUserById(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "civilian";
    }

    @GetMapping("/oasa")
    public String oasaUI() {
        return "oasa";
    }

    @GetMapping("/oaed")
    public String oaedUI() {
        return "oaed";
    }

    @GetMapping("/admin")
    public String adminUI() {
        return "admin";
    }


    @PostMapping("/api/confirmUser")
    public String confirmUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        int id = usersDAO.confirmUser(username, password);
        if (id == 0) {
            return "redirect:/";
        }
        Users user = usersDAO.getUserById(id);
        String title = user.getTitle();
        model.addAttribute("user", user);

        switch (title) {
            case "ROLE_OASA":
                return "redirect:/oasa";
            case "ROLE_OAED":
                return "redirect:/oaed";
            case "ROLE_ADMIN":
                return "redirect:/admin";
            default:
                return "redirect:/civilian?id="+id;
        }
    }

    @PostMapping("/api/signIn")
    public String addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
        int index1 = email.lastIndexOf("@");
        int index2 = email.lastIndexOf(".");
        String domain = email.substring(index1, index2);
        String title;
        switch (domain) {
            case "@oasa":
                title = "ROLE_OASA";
                break;
            case "@oaed":
                title = "ROLE_OAED";
                break;
            default:
                title = "ROLE_CIVILIAN";
                break;
        }
        Users user = new Users(username, password, email, title);
        usersDAO.addUser(user);

        return "redirect:/";
    }*/

}