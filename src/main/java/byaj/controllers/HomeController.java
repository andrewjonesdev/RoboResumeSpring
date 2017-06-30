package byaj.controllers;

import byaj.models.*;
import byaj.repositories.*;
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
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String home(){
        return "base2";
    }
    @GetMapping("/register")
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

        resume.setResUser(userRepository.findOneByUsername(user.getUsername()).getID());
        resumeRepository.save(resume);
        return "login2";
    }

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
    
    @GetMapping("/resume")
    public String newResume(Model model, Principal principal){
        model.addAttribute("user", userRepository.findOneByUsername(principal.getName()));
        if(userRepository.findOneByUsername(principal.getName()).getUserResume() ==-1) {
            Resume resume = new Resume();
            model.addAttribute("resume", resume);
            userRepository.findOneByUsername(principal.getName()).setUserResume(resume.getResID());
            userRepository.save(userRepository.findOneByUsername(principal.getName()));
            
            resume.setResUser(userRepository.findOneByUsername(principal.getName()).getID());
            resumeRepository.save(resume);
        }
        else{
            model.addAttribute("resume", resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()));
        }
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

    @PostMapping(path = "/add/Resume")
    public String processResume(@Valid Resume resume, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("resume");
            return "form2";
        }

        
        resumeRepository.save(resume);
        return "form2";

    }

    @PostMapping(path = "/add/Education")
    public String processEducation(@Valid Education education, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("education");
            return "form2";
        }

        education.setEduRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        educationRepository.save(education);
        return "form2";

    }
    
    @PostMapping(path = "/add/Skill")
    public String processSkill(@Valid Skill skill, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("skill");
            return "form2";
        }
        skill.setSkillRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        skillRepository.save(skill);
        return "form2";

    }


    @PostMapping(path = "/add/Work")
    public String processWork(@Valid Work work, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("work");
            return "form2";
        }
        work.setWorkRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        workRepository.save(work);
        return "form2";

    }
    @PostMapping(path = "/add/Duty")
    public String processDuty(@Valid Duty duty, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("duty");
            return "form2";
        }
        duty.setDutyWork(workRepository.findOneByWorkRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()).getWorkID());
        duty.setDutyWorkTitle(workRepository.findOneByWorkRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()).getWorkTitle());
        duty.setDutyRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        dutyRepository.save(duty);
        return "form2";

    }

    @PostMapping("/resume")
    public String finalizeResume(){
        return "redirect:/myresume";
    }

    @GetMapping("/myresume")
    public String displayResume(Model model, Principal principal){
        model.addAttribute("resume", resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()));
        model.addAttribute("edus", educationRepository.findAllByEduResOrderByEduGradYearDesc(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()));
        model.addAttribute("works", workRepository.findAllByWorkResOrderByWorkEndYearDescWorkEndMonthDesc(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()));
        //model.addAttribute("duties", dutyRepository.findAllByDutyWorkOrderByDutyTitleAsc(workRepository.findOneByWorkRes(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()).getWorkID()));
        model.addAttribute("duties", dutyRepository.findAllByDutyResOrderByDutyWorkAscDutyTitleAsc(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()));
        model.addAttribute("skills", skillRepository.findAllBySkillResOrderBySkillNameAsc(resumeRepository.findOneByResUser(userRepository.findOneByUsername(principal.getName()).getID()).getResID()));
        model.addAttribute("principal", principal);

        return "resume2";
    }

}
