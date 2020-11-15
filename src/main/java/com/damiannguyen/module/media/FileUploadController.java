package com.damiannguyen.module.media;

import com.damiannguyen.module.media.storage.FileSystemStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    @Autowired
    FileSystemStorage storage;

    @GetMapping("/media")
    public String getMediaPage(Model model){
        model.addAttribute("files", storage.getList());
        return "media/index";
    }

    @PostMapping("/media")
    public String saveFile(@RequestParam("file")MultipartFile file, RedirectAttributes attributes){
        storage.store(file);

        attributes.addFlashAttribute(
            "message",
            "You successfully uploaded: " + file.getOriginalFilename()
        );

        return "redirect:/media";
    }

}
