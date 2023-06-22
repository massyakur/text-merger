package com.massyakur.textmerge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.List;

@Document(collection = "texts")
@Data // Add getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class CombinedTextRecord {

    @Id
    private ObjectId id;
    private List<String> inputTexts;
    private String combinedText;
    private Duration elapsedTime;

    public CombinedTextRecord(List<String> inputTexts, String combinedText, Duration elapsedTime) {
        this.inputTexts = inputTexts;
        this.combinedText = combinedText;
        this.elapsedTime = elapsedTime;
    }


}
