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
import java.util.Arrays;
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

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private ResumeBuilderRepository rbRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("search", new Search());
        return "base2";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("search", new Search());
        model.addAttribute("user", new User());
        return "register2";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("search", new Search());

        model.addAttribute("user", user);
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "register2";
        } else {
            if (user.getRoleSettings().toUpperCase().equals("ADMIN")) {
                //user.setRoles(Arrays.asList(adminRole));
                //userRepository.save(user);
                userService.saveAdmin(user);
            }
            if (user.getRoleSettings().toUpperCase().equals("EMPLOYER")) {
                //user.setRoles(Arrays.asList(employerRole));
                //userRepository.save(user);
                userService.saveEmployer(user);
            }
            if (user.getRoleSettings().toUpperCase().equals("USER")) {
                //user.setRoles(Arrays.asList(userRole));
                //userRepository.save(user);
                userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }

        }
        return "redirect:/";
    }
    @GetMapping("/resume")
    public String newResume(Model model, Principal principal){
        model.addAttribute("search", new Search());

        model.addAttribute("education", new Education());
        model.addAttribute("work", new Work());
        // model.addAttribute("duty", new Duty());
        model.addAttribute("skill", new Skill());
        model.addAttribute("resume", userRepository.findOneByUsername(principal.getName()));
        model.addAttribute("edus", educationRepository.findAllByEduResOrderByEduGradYearDesc(userRepository.findByUsername(principal.getName()).getId()));
        model.addAttribute("works", workRepository.findAllByWorkResOrderByWorkEndYearDescWorkEndMonthDesc(userRepository.findByUsername(principal.getName()).getId()));
        model.addAttribute("skills", skillRepository.findAllBySkillResOrderBySkillNameAsc(userRepository.findByUsername(principal.getName()).getId()));
        model.addAttribute("principal", principal);

        return "form2";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("search", new Search());
        return "login2";
    }

    @PostMapping(path = "/add/education")
    public String processEducation(@Valid Education education, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("education");
            return "redirect:/resume";
        }
        education.setEduRes(userRepository.findByUsername(principal.getName()).getId());
        educationRepository.save(education);
        return "redirect:/resume";

    }
    
    @PostMapping(path = "/add/skill")
    public String processSkill(@Valid Skill skill, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("skill");
            return "redirect:/resume";
        }
        skill.setSkillRes(userRepository.findByUsername(principal.getName()).getId());
        skillRepository.save(skill);
        return "redirect:/resume";

    }


    @PostMapping(path = "/add/work")
    public String processWork(@Valid Work work, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("work");
            return "redirect:/resume";
        }
        work.setWorkRes(userRepository.findByUsername(principal.getName()).getId());
        workRepository.save(work);
        return "redirect:/resume";

    }
   /* @PostMapping(path = "/add/duty")
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
        model.addAttribute("search", new Search());
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

    @PostMapping("/search")
    public String searchForResumes(Search search, BindingResult bindingResult, Principal principal, Model model){
        if (bindingResult.hasErrors()) {
            System.out.println("search");
            return "redirect:/";
        }
        model.addAttribute("search", new Search());
        model.addAttribute("rb", new ResumeBuilder());
        search.setSearchRes(userRepository.findByUsername(principal.getName()).getId());
        searchRepository.save(search);
        if(search.getSearchType().toLowerCase().equals("person")){
            model.addAttribute("results", userRepository.findAllByFullNameOrderByIdAsc(search.getSearchValue()));
            return "searchResults2";
        }
        if(search.getSearchType().toLowerCase().equals("company")){
                ArrayList<User> result = new ArrayList();
                List<Work> comp = workRepository.findAllByWorkEmployerOrderByWorkResAsc(search.getSearchValue());
                for (int count = 0; count< comp.size(); count++){
                result.add(userRepository.findById(comp.get(count).getWorkRes()));
                }
            model.addAttribute("results", result);
            return "searchResults2";
        }
        if(search.getSearchType().toLowerCase().equals("school")){
            ArrayList<User> result = new ArrayList();
            List<Education> comp = educationRepository.findAllByEduSchoolOrderByEduResAsc(search.getSearchValue());
            for (int count = 0; count< comp.size(); count++){
                result.add(userRepository.findById(comp.get(count).getEduRes()));
            }
            model.addAttribute("results", result);
            return "searchResults2";
        }
        if(search.getSearchType().toLowerCase().equals("jobtitle")){
            ArrayList<User> job = new ArrayList();
            List<Job> comp = jobRepository.findAllByJobTitleOrderByJobStartYearDesc(search.getSearchValue());
            model.addAttribute("jobs", job);
            return "jobResults2";
        }
        return "redirect:/";
    }

    @GetMapping("/all/resumes")
    public String allResumes( Principal principal, Model model){

        model.addAttribute("search", new Search());
        model.addAttribute("rb", new ResumeBuilder());

            model.addAttribute("results", userRepository.findAllByOrderByLastNameAscFirstNameAsc());
            return "searchResults2";

    }

    @PostMapping("/generate/resume")
    public String displaySearchedResume(ResumeBuilder rb, BindingResult bindingResult, Model model, Principal principal){
        model.addAttribute("search", new Search());
        model.addAttribute("resume", userRepository.findById(Integer.parseInt(rb.getRbValue())));
        model.addAttribute("edus", educationRepository.findAllByEduResOrderByEduGradYearDesc(userRepository.findById(Integer.parseInt(rb.getRbValue())).getId()));
        model.addAttribute("works", workRepository.findAllByWorkResOrderByWorkEndYearDescWorkEndMonthDesc(userRepository.findById(Integer.parseInt(rb.getRbValue())).getId()));
        model.addAttribute("skills", skillRepository.findAllBySkillResOrderBySkillNameAsc(userRepository.findById(Integer.parseInt(rb.getRbValue())).getId()));
        model.addAttribute("principal", principal);

        return "resume2";
    }

    @GetMapping("/job")
    public String newJob(Model model, Principal principal) {
        model.addAttribute("search", new Search());
        model.addAttribute("job", new Job());
        model.addAttribute("jobs", jobRepository.findAllByJobResOrderByJobStartYearDescJobStartMonthDesc(userRepository.findByUsername(principal.getName()).getId()));
        return "job2";
    }

    @PostMapping(path = "/job")
    public String processJob(@Valid Job job, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            System.out.println("job");
            return "redirect:/job";
        }
        job.setJobRes(userRepository.findByUsername(principal.getName()).getId());
        jobRepository.save(job);
        return "redirect:/job";

    }

    @GetMapping("/all/jobs")
    public String allJobs( Principal principal, Model model){

        model.addAttribute("search", new Search());
        model.addAttribute("jb", new JobBuilder());

        model.addAttribute("jobs", jobRepository. findAllByOrderByJobTitleAscJobEmployerAsc());
        return "jobResults2";

    }

    @PostMapping("/generate/job")
    public String displaySearchedRJob(JobBuilder jb, BindingResult bindingResult, Model model, Principal principal){
        model.addAttribute("search", new Search());
        model.addAttribute("jobs", jobRepository.findAllByJobResOrderByJobStartYearDescJobStartMonthDesc(Integer.parseInt(jb.getJbValue())));
        model.addAttribute("principal", principal);

        return "job2";
    }

    @GetMapping("/add")
    public String redirectAdd(){
        return "redirect:/";
    }

    @GetMapping("/all")
    public String redirectAll(){
        return "redirect:/";
    }
}
