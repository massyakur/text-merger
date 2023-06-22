package com.massyakur.textmerge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TextCombinationService {

    @Autowired
    private CombinedTextRecordRepository combinedTextRecordRepository;

    private static final int MIN_OVERLAP_LENGTH = 3;

    public CombineResult combineTexts(List<String> texts) {
        long startTime = System.nanoTime();
        StringBuilder combinedText = new StringBuilder(texts.get(0));
        boolean hasCombinedText = false;

        for (String text : texts.subList(1, texts.size())) {
            int overlapIndex = -1;

            for (int i = text.length(); i >= MIN_OVERLAP_LENGTH; i--) {
                String suffix = text.substring(0, i);
                if (combinedText.toString().contains(suffix)) {
                    overlapIndex = i;
                    break;
                }
            }

            if (overlapIndex != -1) {
                int startOverlapInCombinedText = combinedText.lastIndexOf(text.substring(0, overlapIndex));
                combinedText.replace(startOverlapInCombinedText, combinedText.length(), text);
                hasCombinedText = true;
            } else {
                // Continue with the next text and set hasUncombinedText to true
                continue;
            }
        }

        long endTime = System.nanoTime();
        Duration elapsedTime = Duration.ofNanos(endTime - startTime);

        if (!hasCombinedText) {
            return new CombineResult("Some texts couldn't be combined.", elapsedTime);
        } else {
            return new CombineResult(combinedText.toString(), elapsedTime);
        }
    }

//    public CombineResult combineTexts(List<String> texts) {
//        long startTime = System.nanoTime();
//        StringBuilder combinedText = new StringBuilder(texts.get(0));

//        for (String text : texts.subList(1, texts.size())) {
//            int overlapIndex = -1;
//
//            for (int i = text.length(); i >= MIN_OVERLAP_LENGTH; i--) {
//                String suffix = text.substring(0, i);
//                if (combinedText.toString().contains(suffix)) {
//                    overlapIndex = i;
//                    break;
//                }
//            }
//
//            if (overlapIndex != -1) {
//                int startOverlapInCombinedText = combinedText.lastIndexOf(text.substring(0, overlapIndex)); // new keeps the 2nd
//                combinedText.replace(startOverlapInCombinedText, combinedText.length(), text); // new
////                combinedText.append(text.substring(overlapIndex)); // old one, keep the 1st
//            } else {
//                // HERE
//                return new CombineResult("Can't combine the given texts", Duration.ZERO);
//            }
//        }

//        long endTime = System.nanoTime();
//        Duration elapsedTime = Duration.ofNanos(endTime - startTime);
//        return new CombineResult(combinedText.toString(), elapsedTime);
//    }


    public CombinedTextRecord saveTexts(List<String> inputTexts, String combinedText, Duration elapsedTime) {
        CombinedTextRecord record = new CombinedTextRecord(inputTexts, combinedText, elapsedTime);
        return combinedTextRecordRepository.save(record);
    }

    public List<CombinedTextRecord> getHistory() {
        return combinedTextRecordRepository.findAll();
    }

    public static class CombineResult {
        private final String combinedText;
        private final Duration elapsedTime;

        public CombineResult(String combinedText, Duration elapsedTime) {
            this.combinedText = combinedText;
            this.elapsedTime = elapsedTime;
        }

        public String getCombinedText() {
            return combinedText;
        }

        public Duration getElapsedTime() {
            return elapsedTime;
        }
    }
}

