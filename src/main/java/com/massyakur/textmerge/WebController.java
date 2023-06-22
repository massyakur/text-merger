package com.massyakur.textmerge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private TextCombinationService textCombinationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/combine")
    public String combine(@RequestParam("text") List<String> texts, Model model) {
        TextCombinationService.CombineResult result = textCombinationService.combineTexts(texts);
        model.addAttribute("inputTexts", texts);
        model.addAttribute("combinedText", result.getCombinedText());
        model.addAttribute("elapsedTime", result.getElapsedTime().toString());
        return "index";
    }

    @PostMapping("/save")
    public String save(@RequestParam("inputTexts") List<String> inputTexts,
                       @RequestParam("combinedText") String combinedText,
                       @RequestParam("elapsedTime") String elapsedTimeStr,
                       Model model) {
        Duration elapsedTime = Duration.parse(elapsedTimeStr);
        CombinedTextRecord record = textCombinationService.saveTexts(inputTexts, combinedText, elapsedTime);
        model.addAttribute("message", "Texts saved with ID: " + record.getId());
        return "index";
    }

    @GetMapping("/history")
    public String history(Model model) {
        List<CombinedTextRecord> records = textCombinationService.getHistory();
        Collections.reverse(records);  // Reverse the order of the records
        model.addAttribute("records", records);
        return "history";
    }
}

