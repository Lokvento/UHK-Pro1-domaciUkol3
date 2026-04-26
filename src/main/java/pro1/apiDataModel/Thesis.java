package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {
    @SerializedName("datumZadani")
    public ApiDate assigmentDate;
    @SerializedName("datumOdevzdani")
    public ApiDate submissionDate;
}
