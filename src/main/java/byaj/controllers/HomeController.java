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

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by student on 6/20/17.
 */
@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    
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
        return "base";
    }

    @GetMapping("/resume")
    public String newResume(Model model, Principal principal){
        model.addAttribute("user", userRepository.findOneByUsername(principal.getName()));
        if(userRepository.findOneByUsername(principal.getName()).getUserResume() ==-1) {
            Resume resume = new Resume();
            model.addAttribute("resume", resume);
            userRepository.findOneByUsername(principal.getName()).setUserResume(resume.getResID());
            userRepository.save(userRepository.findOneByUsername(principal.getName()));
            
            resume.setResAcc(userRepository.findOneByUsername(principal.getName()).getID());
            resumeRepository.save(resume);
        }
        else{
            model.addAttribute("resume", resumeRepository.findOneByResAcc(userRepository.findOneByUsername(principal.getName()).getID()));
        }
        model.addAttribute("education", new Education());
        model.addAttribute("work", new Work());
        model.addAttribute("duty", new Duty());
        model.addAttribute("skill", new Skill());
        return "base";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    /*@GetMapping("/add")
    public String getRooms(Model model){
        model.addAttribute(new TransactionATM());
        model.addAttribute("items", transactionATMRepository.findAllByOrderByTranDateDesc());
        return "result";
    }

    @GetMapping("/user")
    public String getReddit(Model model, Principal principal){

        model.addAttribute("items", transactionATMRepository.findAllByTranAccOrderByTranDateDesc(Integer.parseInt(principal.getName())));
        return "byuser";
    }

    @PostMapping("/add")
    public String processTransaction(@Valid TransactionATM transactionATM, BindingResult bindingResult, Account user, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("two");
            return "redirect:/";
        }

        userRepository.save(user);
        transactionATMRepository.save(transactionATM);
        return "redirect:/user";

    }*/

    @PostMapping(path = "/add/Education")
    public String processEducation(@Valid Education education, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("two");
            return "redirect:/";
        }

        education.setEduRes(resumeRepository.findOneByResAcc(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        educationRepository.save(education);
        return "redirect:/user";

    }
    
    @PostMapping(path = "/add/Skill")
    public String processTransaction(@Valid Skill skill, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("two");
            return "redirect:/";
        }
        skill.setSkillRes(resumeRepository.findOneByResAcc(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        skillRepository.save(skill);
        return "redirect:/user";

    }


    @PostMapping(path = "/add/Work")
    public String processTransaction(@Valid Work work, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("two");
            return "redirect:/";
        }
        work.setWorkRes(resumeRepository.findOneByResAcc(userRepository.findOneByUsername(principal.getName()).getID()).getResID());
        workRepository.save(work);
        return "redirect:/user";

    }


}
