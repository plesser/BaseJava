package experience;

import model.Resume;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch2 {
    public static void main(String[] args) {
        Resume[] resumes = new Resume[5];
        Random random = new Random();
        for (int i=0; i < 5; i++){
            Resume resume = new Resume();
            resume.setUuid("uuid"+random.nextInt(12));
            resumes[i] = resume;
            System.out.println(resume);
        }

        Resume resume = new Resume();
        resume.setUuid("uuid5");

        int index = Arrays.binarySearch(resumes, resume);
        System.out.println(index);

    }
}
