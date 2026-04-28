package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {
    @SerializedName("datumZadani")
    public ApiDate assignmentDate;
    @SerializedName("datumOdevzdani")
    public ApiDate submissionDate;
}
