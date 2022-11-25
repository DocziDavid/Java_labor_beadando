package com.example.java_labor_beadando;

import com.example.java_labor_beadando.kapcsolat.Message;
import com.example.java_labor_beadando.kapcsolat.MessageRepository;
import com.example.java_labor_beadando.securityrole.Role;
import com.example.java_labor_beadando.securityrole.User;
import com.example.java_labor_beadando.securityrole.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class Controllers  {
    @Autowired
    private dataRepository dataRepository;

    @GetMapping("/teszt")
    public String Kezdooldal(Model model, String uzenet) {
        model.addAttribute("meccsek", dataRepository.findAll());
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
    public String uzenetMentese(@ModelAttribute String message, HttpServletRequest request, RedirectAttributes redirAttr){
        Principal userPrincipal= request.getUserPrincipal();
        Message msg= new Message();
        msg.setMessage(message);
        if(userPrincipal!=null){msg.setName(userPrincipal.getName());}
        else{msg.setName("Látogató");}
        messageRepo.save(msg);
        redirAttr.addFlashAttribute("uzenet","Köszönjük az üzenetet!");
        return "/Home";
    }





}
