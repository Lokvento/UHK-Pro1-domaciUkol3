package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("obsazeni")
    public String registeredStudents;
    @SerializedName("ucitIdno")
    public Long teacherId;
}
