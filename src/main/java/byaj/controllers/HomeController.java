package byaj.controllers;

import byaj.models.*;
import byaj.repositories.*;
import byaj.services.UserService;
import byaj.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by student on 6/20/17.
 */
@Controller
public class HomeController {
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private WorkRepository workRepository;

  //  @Autowired
  //  private DutyRepository dutyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String home(){
        return "base2";
    }
/*    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("resFirstName", new String());
        model.addAttribute("resLastName", new String());
        model.addAttribute("resEmail", new String());
        model.addAttribute("username", new String());
        model.addAttribute("password", new String());
        model.addAttribute("role", new String());

        return "register2";
    }
    @PostMapping("/register")
    public String registering(@RequestParam("resFirstName") String resFirstName,
                           @RequestParam("resLastName") String resLastName,
                           @RequestParam("resEmail") String resEmail,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String theRole,
                           Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Role role = new Role();
        role.setRole(theRole);
        Resume resume = new Resume();
        resume.setResFirstName(resFirstName);
        resume.setResLastName(resLastName);
        resume.setResEmail(resEmail);

        userRepository.save(user);
        resumeRepository.save(resume);
        roleRepository.save(role);

        userRepository.findOneByUsername(user.getUsername()).setUserResume(resume.getResID());
        userRepository.save(userRepository.findOneByUsername(user.getUsername()));

        resume.setResUser(userRepository.findOneByUsername(user.getUsername()).getId());
        resumeRepository.save(resume);
        return "login2";
    }*/

    /*@PostMapping("/register")
    public String newUser(){
        if (userBindingResult.hasErrors()) {
            System.out.println("user");
            return "register";
        }
        if (resBindingResult.hasErrors()) {
            System.out.println("resume");
            return "register";
        }
        if (roleBindingResult.hasErrors()) {
            System.out.println("role");
            return "register";
        }
        userRepository.save(user);
        resumeRepository.save(resume);
        roleRepository.save(role);

        userRepository.findOneByUsername(user.getUsername()).setUserResume(resume.getResID());
        userRepository.save(userRepository.findOneByUsername(user.getUsername()));

        resume.setResUser(userRepository.findOneByUsername(user.getUsername()).getID());
        resumeRepository.save(resume);
        return "login";
    }*/
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register2";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

        model.addAttribute("user", user);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "register2";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }

        return "base2";
    }
    @GetMapping("/resume")
    public String newResume(Model model, Principal principal){
        model.addAttribute("resume", userRepository.findOneByUsername(principal.getName()));
        /*if(userRepository.findOneByUsername(principal.getName()).getUserResume() ==-1) {
            Resume resume = new Resume();
            model.addAttribute("resume", resume);
            userRepository.findOneByUsername(principal.getName()).setUserResume(resume.getResID());
            userRepository.save(userRepository.findOneByUsername(principal.getName()));
            
            resume.setResUser(userRepository.findOneByUsername(principal.getName()).getId());
            resumeRepository.save(resume);
        }
        else{
            model.addAttribute("resume", resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getId()));
        }*/
        model.addAttribute("education", new Education());
        model.addAttribute("work", new Work());
        model.addAttribute("duty", new Duty());
        model.addAttribute("skill", new Skill());
        return "form2";
    }

    @RequestMapping("/login")
    public String login() {
        return "login2";
    }


    /*@GetMapping("/add")
    public String getRooms(Model model){
        model.addAttribute(new TransactionATM());
        model.addAttribute("items", transactionATMRepository.findAllByOrderByTranDateDesc());
        return "result";
    }

    @GetMapping("/user")
    public String getReddit(Model model, Principal principal){

        model.addAttribute("items", transactionATMRepository.findAllByTranUserOrderByTranDateDesc(Integer.parseInt(principal.getName())));
        return "byuser";
    }

    @PostMapping("/add")
    public String processTransaction(@Valid TransactionATM transactionATM, BindingResult bindingResult, Userount user, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("two");
            return "redirect:/";
        }

        userRepository.save(user);
        transactionATMRepository.save(transactionATM);
        return "redirect:/user";

    }*/

    /*@PostMapping(path = "/add/Resume")
    public String processResume(@Valid Resume resume, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("resume");
            return "redirect:/resume";
        }


        resumeRepository.save(resume);
        return "redirect:/resume";

    }*/

    @PostMapping(path = "/add/Education")
    public String processEducation(@Valid Education education, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("education");
            return "redirect:/resume";
        }

        education.setEduRes(userRepository.findByUsername(principal.getName()).getId());
        educationRepository.save(education);
        return "redirect:/resume";

    }
    
    @PostMapping(path = "/add/Skill")
    public String processSkill(@Valid Skill skill, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("skill");
            return "redirect:/resume";
        }
        skill.setSkillRes(userRepository.findByUsername(principal.getName()).getId());
        skillRepository.save(skill);
        return "redirect:/resume";

    }


    @PostMapping(path = "/add/Work")
    public String processWork(@Valid Work work, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("work");
            return "redirect:/resume";
        }
        work.setWorkRes(userRepository.findByUsername(principal.getName()).getId());
        workRepository.save(work);
        return "redirect:/resume";

    }
   /* @PostMapping(path = "/add/Duty")
    public String processDuty(@Valid Duty duty, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("duty");
            return "redirect:/resume";
        }
        duty.setDutyWork(workRepository.findTopByWorkRes(userRepository.findByUsername(principal.getName()).getId()).getWorkID());
        duty.setDutyWorkTitle(workRepository.findTopByWorkRes(userRepository.findByUsername(principal.getName()).getId()).getWorkTitle());
        duty.setDutyRes(userRepository.findByUsername(principal.getName()).getId());
        dutyRepository.save(duty);
        return "redirect:/resume";

    }*/

    @PostMapping("/resume")
    public String finalizeResume(){
        return "redirect:/myresume";
    }

    @GetMapping("/myresume")
    public String displayResume(Model model, Principal principal){
        model.addAttribute("resume", userRepository.findOneByUsername(principal.getName()));
        model.addAttribute("edus", educationRepository.findAllByEduResOrderByEduGradYearDesc(userRepository.findByUsername(principal.getName()).getId()));
        model.addAttribute("works", workRepository.findAllByWorkResOrderByWorkEndYearDescWorkEndMonthDesc(userRepository.findByUsername(principal.getName()).getId()));
        //model.addAttribute("duties", dutyRepository.findAllByDutyWorkOrderByDutyTitleAsc(workRepository.findOneByWorkRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()).getWorkID()));
        //model.addAttribute("duties", dutyRepository.findAllByDutyResOrderByDutyWorkAscDutyTitleAsc(userRepository.findByUsername(principal.getName()).getId()));
       // for(int count = 0; count<dutyRepository.findAllByDutyResOrderByDutyWorkAscDutyTitleAsc(userRepository.findByUsername(principal.getName()).getId()).size();count++) {
      //      System.out.println(dutyRepository.findAllByDutyResOrderByDutyWorkAscDutyTitleAsc(userRepository.findByUsername(principal.getName()).getId()).get(count));
      //  }
        model.addAttribute("skills", skillRepository.findAllBySkillResOrderBySkillNameAsc(userRepository.findByUsername(principal.getName()).getId()));
        model.addAttribute("principal", principal);

        return "resume2";
    }

}
