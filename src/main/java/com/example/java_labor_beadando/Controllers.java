package com.example.java_labor_beadando;

import com.example.java_labor_beadando.kapcsolat.Message;
import com.example.java_labor_beadando.kapcsolat.MessageRepository;
import com.example.java_labor_beadando.modelclasses.Belepesek;
import com.example.java_labor_beadando.modelclasses.Meccsek;
import com.example.java_labor_beadando.securityrole.Role;
import com.example.java_labor_beadando.securityrole.User;
import com.example.java_labor_beadando.securityrole.UserRepository;
import jdk.dynalink.linker.MethodTypeConversionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class Controllers  {
    @Autowired
    private dataRepository dataRepository;

    @Autowired
    private dataIIRepository dataIIRepository;
    @Autowired
    private dataIIIRepository dataIIIRepository;



    @GetMapping("/meccseink")
    public String Kezdooldal(Model model, String uzenet) {
        model.addAttribute("meccsek", dataRepository.findAll());
        model.addAttribute("nezok", dataIIRepository.findAll());
        model.addAttribute("belepesek", dataIIIRepository.findAll());

        model.addAttribute("uzenet",model.addAttribute("uzenet"));
        return "Meccseink";
    }




    @GetMapping("/")
    public String index(){
        return ("Home");
    }


    @GetMapping("/Regisztracio")
    public String register(Model model) {
        model.addAttribute("reg", new User());
        return "Regisztracio";
    }



    @Autowired
    private UserRepository userRepo;
    @PostMapping("/regisztral_feldolgoz")
    public String Regisztráció(@ModelAttribute User user, Model model) {
        for(User felhasznalo2: userRepo.findAll())
            if(felhasznalo2.getEmail().equals(user.getEmail())){
                model.addAttribute("uzenet", "A regisztrációs email már foglalt!");
                return "reghiba";
            }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
// Minden regisztrációkor USER szerepet adunk a felhasználónak:
        role.setId(2); role.setName("ROLE_USER");
        List<Role> rolelist = new ArrayList<Role>();
        rolelist.add(role);
        user.setRoles(rolelist);
        userRepo.save(user);
        model.addAttribute("id", user.getId());
        return "/Home";
    }

    @Autowired
    private MessageRepository messageRepo;
    //
    @GetMapping("/Kapcsolatok")
    public String ujUzenet(Model model){
        model.addAttribute("message","");
        return "Kapcsolatok";
    }

    @PostMapping(value = "/ment")
    public String uzenetMentese(@Validated @RequestParam String message, HttpServletRequest request, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "Kapcsolatok";
        Principal userPrincipal= request.getUserPrincipal();
        Message msg= new Message();
        msg.setMessage(message);
        if(userPrincipal!=null){msg.setName(userPrincipal.getName());}
        else{msg.setName("Vendég");}
        msg.setDate(new Date());
        messageRepo.save(msg);
        return "Home";
    }

    @GetMapping("/uzenetek")
    public String uzenetek(Model model){
        model.addAttribute("messages",messageRepo.findByOrderByDateDesc());
        return "uzenetek";
    }

    //Restful API

    //Add
    @GetMapping("/add")
    public String getForm(Model model)
    {
        Meccsek m = new Meccsek();
        model.addAttribute("meccsek", m);
        return "hozzaadas";
    }

    //Post - adatfogadás
    @PostMapping("/add")
    public String addMeccsek(@ModelAttribute Meccsek m, Model model)
    {
        dataRepository.save(m);
        model.addAttribute("meccsek", new Meccsek());

        return "/Hozzaadas";
    }

    @GetMapping("/edit/{id}")
    public String GoEdit(@PathVariable(name = "id") int id, Model model)
    {
        Optional<Meccsek> m = dataRepository.findById(id);
        model.addAttribute("meccsek", m);

        return "Valtoztatas";
    }

    @PostMapping("/edit")
    public String Edited(@ModelAttribute Meccsek meccsek, Model model)
    {
        dataRepository.save(meccsek);
        model.addAttribute("meccsek", new Meccsek());

        return "/Valtoztatas";
    }

    @GetMapping("/delete/{id}")
    public String deleteData(@PathVariable(name = "id") int id)
    {
        dataRepository.deleteById(id);

        return "Torles";
    }



}
