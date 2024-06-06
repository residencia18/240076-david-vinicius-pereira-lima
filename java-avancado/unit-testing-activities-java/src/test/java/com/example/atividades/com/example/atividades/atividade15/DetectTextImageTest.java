package com.example.atividades.atividade15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetectTextImageTest {
    @InjectMocks
    private DetectTextImage detectTextImage;

    @Mock
    private RekognitionClient rekognitionClient;

    @BeforeEach
    void setUp() {
        detectTextImage = new DetectTextImage("data/img-example-for-aws-task.jpg", rekognitionClient);
    }

    @Test
    void testDetectTextLabels() throws IOException {
        String resultFilePath = "data/detected_text_results_test.txt";
        TextDetection textDetection = TextDetection.builder()
                .detectedText("Sample Text")
                .confidence(99.0f)
                .id(1)
                .parentId(0)
                .type(TextTypes.LINE)
                .build();
        List<TextDetection> textDetections = Collections.singletonList(textDetection);
        DetectTextResponse detectTextResponse = DetectTextResponse.builder()
                .textDetections(textDetections)
                .build();

        when(rekognitionClient.detectText(any(DetectTextRequest.class))).thenReturn(detectTextResponse);

        detectTextImage.detectTextLabels(resultFilePath);

        verify(rekognitionClient, times(1)).detectText(any(DetectTextRequest.class));

        File resultFile = new File(resultFilePath);
        List<String> lines = Files.readAllLines(resultFile.toPath());
        assert lines.contains("Detected: Sample Text");
        assert lines.contains("Confidence: 99.0");
        assert lines.contains("Id: 1");
        assert lines.contains("Parent Id: 0");
        assert lines.contains("Type: LINE");
    }

    @Test
    void testDetectTextLabelsWithException() {
        String resultFilePath = "data/detected_text_results_test.txt";
        when(rekognitionClient.detectText(any(DetectTextRequest.class))).thenThrow(RekognitionException.builder().message("Test exception").build());

        try {
            detectTextImage.detectTextLabels(resultFilePath);
        } catch (RuntimeException e) {
            assert e.getMessage().contains("Test exception");
        }

        verify(rekognitionClient, times(1)).close();
    }
}
