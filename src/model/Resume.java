package model;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yc on 2016/4/10 for students_system.
 */
public class Resume {
    private InputStream resume;
    private String resume_type;

    public Resume(InputStream resume, String resume_type) {
        this.resume = resume;
        this.resume_type = resume_type;
    }

    public String getText() throws IOException {

        try {
            HWPFDocument doc = new HWPFDocument(resume);
            return doc.getText().toString();
        } catch (IOException e) {
            e.printStackTrace();
            XWPFDocument doc = new XWPFDocument(resume);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            return extractor.getText();
        }
    }
    public void printDocx() throws IOException {
        XWPFDocument doc = new XWPFDocument(resume);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        System.out.println(extractor.getText());
    }

    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.setUsername("yu");
        info.selectResume();
    }
}
