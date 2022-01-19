package gr.hua.dit.ds.DistributedSystems.Controllers;

import gr.hua.dit.ds.DistributedSystems.DAOs.ApplicationsDAO;
import gr.hua.dit.ds.DistributedSystems.DAOs.UsersDAO;
import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import gr.hua.dit.ds.DistributedSystems.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontendController implements ErrorController {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private ApplicationsDAO applicationsDAO;

    @GetMapping("/admin")
    public String adminView() {

        return "admin";
    }

    @GetMapping("/civilian")
    public String civilianView(Model model) throws Exception {

        // Get user's id
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user = usersDAO.getUserByUsername(auth.getName());
        int id = user.getId();

        // Check if user already applied
        Boolean hasApplied = applicationsDAO.applicationExists(id);
        model.addAttribute("id", id);
        model.addAttribute("hasApplied", hasApplied);

        // Track application status
        if(hasApplied) {
            Applications application = applicationsDAO.getApplicationById(id);
            String validated = application.getValidated();
            String confirmed = application.getConfirmed();
            model.addAttribute("validated", validated);
            model.addAttribute("confirmed", confirmed);

            // Add applications as array list for use in javascript
            List<Applications> app = new ArrayList<>();
            app.add(application);
            model.addAttribute("app", app);
        }

        return "civilian";
    }

    @GetMapping("/oaed")
    public String oaedView(Model model) {
        List<Applications> applications = applicationsDAO.getNewApplications();
        model.addAttribute("applications", applications);

        return "oaed";
    }

    @GetMapping("/oasa")
    public String oasaView(Model model) {
        List<Applications> applications = applicationsDAO.getValidatedApplications();
        System.out.println(applications);
        model.addAttribute("applications", applications);

        return "oasa";
    }

    @GetMapping("/")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role = authResult.getAuthorities().toString();

        if (role.contains("ROLE_ADMIN")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        } else if (role.contains("ROLE_CIVILIAN")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/civilian"));
        } else if (role.contains("ROLE_OAED")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/oaed"));
        } else if (role.contains("ROLE_OASA")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/oasa"));
        }

    }

    @GetMapping("/login")
    public String loginView() {

        return "login";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        model.addAttribute("exception", exception);

        return "error";
    }

}
