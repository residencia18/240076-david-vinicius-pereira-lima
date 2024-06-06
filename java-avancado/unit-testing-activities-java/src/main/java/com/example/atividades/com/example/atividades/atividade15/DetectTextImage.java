package com.example.atividades.atividade15;

/**
*  To run this code example, ensure that you perform the Prerequisites as stated in the Amazon Rekognition Guide:
*  https://docs.aws.amazon.com/rekognition/latest/dg/video-analyzing-with-sqs.html
*
* Also, ensure that set up your development environment, including your credentials.
*
* For information, see this documentation topic:
*
* https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
*/

/**
 * Link for the code example
 * https://docs.aws.amazon.com/rekognition/latest/dg/text-detecting-text-procedure.html
*/

//snippet-start:[rekognition.java2.detect_text.import]
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.TextDetection;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//snippet-end:[rekognition.java2.detect_text.import]

/**
* Before running this Java V2 code example, set up your development environment, including your credentials.
*
* For more information, see the following documentation topic:
*
* https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
*/
public class DetectTextImage {
	public String sourceImage;
	public Region region;
	public RekognitionClient rekClient;

	 public DetectTextImage() {
	     this.sourceImage = "data/img-example-for-aws-task.jpg";
	     this.region = Region.US_WEST_2;
	     this.rekClient = RekognitionClient.builder()
	         .region(region)
	         .credentialsProvider(ProfileCredentialsProvider.create("default"))
	         .build();	 
	 }
	 
	 // Construtor para injeção de dependências (útil para testes)
	 public DetectTextImage(String sourceImage, RekognitionClient rekClient) {
	     this.sourceImage = sourceImage;
	     this.rekClient = rekClient;
	 }
	
	 public void detectTextLabels(String resultFilePath) {
		 // String resultFilePath = = "data/detected_text_results.txt";
         List<TextDetection> textCollection = null;
	     try {
	         InputStream sourceStream = new FileInputStream(this.sourceImage);
	         SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);
	         Image souImage = Image.builder()
	             .bytes(sourceBytes)
	             .build();
	
	         DetectTextRequest textRequest = DetectTextRequest.builder()
	             .image(souImage)
	             .build();
	
	         DetectTextResponse textResponse = this.rekClient.detectText(textRequest);
	         textCollection = textResponse.textDetections();
	         
	 		// Salva o resultado em um arquivo TXT
	 		saveResultToTextFile(textCollection, resultFilePath);
	
	     } catch (RekognitionException | FileNotFoundException e) {
	         System.out.println(e.getMessage());
	         System.exit(1);
	     } finally {
	    	 rekClient.close();
		}
	 }
	 
	 private void saveResultToTextFile(List<TextDetection> textCollection, String fileName) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
				for (TextDetection text : textCollection) {
					writer.write("Detected: " + text.detectedText() + "\n");
					writer.write("Confidence: " + text.confidence().toString() + "\n");
	                writer.write("Id: " + text.id() + "\n");
	                writer.write("Parent Id: " + text.parentId() + "\n");
	                writer.write("Type: " + text.type() + "\n");
	            	writer.write("\n");
				}
				System.out.println("Results saved to " + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
